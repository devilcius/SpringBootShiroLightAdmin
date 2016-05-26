package com.predatum.sbsla.controller;

import com.predatum.sbsla.entity.EmailMessage;
import com.predatum.sbsla.entity.User;
import com.predatum.sbsla.entity.UserRole;
import com.predatum.sbsla.repository.UserRepository;
import com.predatum.sbsla.repository.UserRoleRepository;
import com.predatum.sbsla.util.email.EmailHtmlSender;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.context.Context;

/**
 *
 * @author Marcos Peña
 */
@Controller
public class UserController
{

    private static final Logger LOGGER = LoggerFactory
            .getLogger(UserController.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private EmailHtmlSender emailHtmlSender;

    @Value("${application.email.defaultsender}")
    private String emailSenderAddress;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin(@RequestParam(value = "logout", required = false) String logout)
    {
        // get the currently executing user:
        Subject currentUser = SecurityUtils
                .getSubject();
        if (currentUser != null && logout != null) {
            currentUser.logout();
        }
        if (currentUser != null && (currentUser.isAuthenticated() || currentUser.isRemembered())) {

            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView postLogin(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "rememberme", required = false) boolean rememberMe,
            RedirectAttributes redirectAttributes)
    {
        if (tryLogin(email, password, rememberMe)) {
            redirectAttributes.addFlashAttribute("alert", "info");
            redirectAttributes.addFlashAttribute("message", "Login succeed. Welcome!");

            return new ModelAndView("redirect:/");
        } else {
            redirectAttributes.addFlashAttribute("alert", "danger");
            redirectAttributes.addFlashAttribute("message", "Wrong email/password...");

            return new ModelAndView("redirect:/login");
        }

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView postRegister(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "role-admin", required = false) String admin,
            RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("alert", "info");

        try {
            registrate(email, password, admin != null);
            redirectAttributes.addFlashAttribute("message", "User created. Try login now");
        } catch (AccountException exception) {
            redirectAttributes.addFlashAttribute("alert", "danger");
            redirectAttributes.addFlashAttribute("message", exception.getMessage());

            return new ModelAndView("redirect:/register");

        }

        return new ModelAndView("redirect:/login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister()
    {

        return "register";
    }

    @RequestMapping(value = "/recover", method = RequestMethod.GET)
    public String getRecover(@RequestParam(value = "token", required = false) String token, Model model)
    {
        if (token != null) {
            User user = userRepository.findOneByConfirmationToken(token);
            if (user == null) {
                model.addAttribute("alert", "danger");
                model.addAttribute("message", "Invalid token");

                return "recover";
            }
            model.addAttribute("token", token);

            return "reset";
        }

        return "recover";
    }

    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    public String sendRecoverToken(
            HttpServletRequest request,
            @RequestParam(value = "email", required = true) String email, Model model)
    {
        User user = userRepository.findOneByEmail(email);
        if (user == null) {
            model.addAttribute("alert", "danger");
            model.addAttribute("message", "User not found");

            return "recover";
        }
        if (user.getPasswordRequestedAt() != null && !this.tokenRequestIntervalRespected(user.getPasswordRequestedAt())) {
            model.addAttribute("alert", "danger");
            model.addAttribute("message", "You must wait at least one hour to request again a password recovery.");

            return "recover";
        }
        user.setConfirmationToken(this.generateConfirmationToken(email));
        user.setPasswordRequestedAt(new Date());
        userRepository.save(user);
        String baseUrl = String.format("%s://%s:%d/sbsla/recover", request.getScheme(), request.getServerName(), request.getServerPort());
        String resetUrl = baseUrl + "?token=" + user.getConfirmationToken();
        this.sendPasswordResetEmail(resetUrl, user.getEmail());
        model.addAttribute("alert", "info");
        model.addAttribute("message", String.format("We've send an email to %s with instructions to recover the password", email));

        return "recover";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public String getReset(@RequestParam(value = "token", required = true) String token)
    {

        return "reset";
    }

    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public ModelAndView doReset(
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "token", required = true) String token,
            RedirectAttributes redirectAttributes)
    {
        User user = userRepository.findOneByConfirmationToken(token);
        if (user == null) {
            redirectAttributes.addFlashAttribute("alert", "danger");
            redirectAttributes.addFlashAttribute("message", "Invalid token");

            return new ModelAndView("redirect:/recover");
        }
        if (user.getPasswordRequestedAt() != null && this.tokenHasExpired(user.getPasswordRequestedAt())) {
            redirectAttributes.addFlashAttribute("alert", "danger");
            redirectAttributes.addFlashAttribute("message", "Token has expired");

            return new ModelAndView("redirect:/recover");
        }
        this.generatePassword(user, password);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("alert", "success");
        redirectAttributes.addFlashAttribute("message", "Password reset successfully");

        return new ModelAndView("redirect:/login");
    }

    private boolean tryLogin(String username, String password, Boolean rememberMe)
    {
        // get the currently executing user:
        Subject currentUser = SecurityUtils
                .getSubject();

        if (!currentUser.isAuthenticated()) {
            // collect user principals and credentials in a gui specific manner
            // such as username/password html form, X509 certificate, OpenID,
            // etc.
            // We'll use the username/password example here since it is the most
            // common.
            UsernamePasswordToken token = new UsernamePasswordToken(username,
                    password);
            // this is all you have to do to support 'remember me' (no config -
            // built in!):
            token.setRememberMe(rememberMe);

            try {
                currentUser.login(token);
                System.out.println("User ["
                        + currentUser.getPrincipal().toString()
                        + "] authenticated.");

                // save current username in the session, so we have access to
                // our User model
                currentUser.getSession().setAttribute("username", username);
                return true;
            } catch (UnknownAccountException uae) {
                System.out.println("No user found with such username "
                        + token.getPrincipal());
                LOGGER.error(uae.getMessage());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password for acount "
                        + token.getPrincipal()
                        + " is not valid!");
                LOGGER.error(ice.getMessage());
            } catch (LockedAccountException lae) {
                System.out.println("The account for user "
                        + token.getPrincipal()
                        + " is blocked.  "
                        + "Please, contact the administrator to unblock it.");
                LOGGER.error(lae.getMessage());
            } catch (AuthenticationException ae) {
                System.out.println("User cannot be authenticated. "
                        + token.getPrincipal());
                LOGGER.error(ae.getMessage());
            }
        } else {
            return true; // already logged in
        }

        return false;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model)
    {
        Subject subject = SecurityUtils.getSubject();

        User user = userRepository.findOneByEmail(subject.getPrincipal().toString());
        if (user == null) {

            throw new ResourceNotFoundException();
        }
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", subject.hasRole("admin"));

        return "profile";
    }

    private void registrate(String email, String plainTextPassword, boolean isAdmin) throws AccountException
    {
        User user = userRepository.findOneByEmail(email);

        if (user == null) {
            user = new User();
            user.setUsername(email);
            user.setEmail(email);
        } else {
            throw new AccountException("User with email " + email + " already registered");
        }

        this.generatePassword(user, plainTextPassword);
        userRepository.save(user);

        System.err.println("User with email: " + user.getEmail()
                + " password hash:" + user.getPassword() + " salt:"
                + user.getSalt());

        // create role
        if (isAdmin) {
            UserRole role = new UserRole();
            role.setEmail(email);
            role.setRoleName("admin");
            userRoleRepository.save(role);
        }
    }

    private void generatePassword(User user, String plainTextPassword)
    {
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        Object salt = randomNumberGenerator.nextBytes();
        String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt,
                1024).toBase64();
        user.setPassword(hashedPasswordBase64);
        user.setSalt(salt.toString());
    }

    private String generateConfirmationToken(String email)
    {
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        Object randomNumber = rng.nextBytes();

        return new Sha256Hash(email, randomNumber, 1024).toHex();
    }

    private void sendPasswordResetEmail(String url, String recipientAddress)
    {
        EmailMessage message = new EmailMessage();
        message.setSubject("SBLSA recover password");
        message.setSender(emailSenderAddress);
        message.setRecipient(recipientAddress);

        Context context = new Context();
        context.setVariable("recoverUrl", url);
        emailHtmlSender.send(message, "email/reset-password", context);

    }

    private boolean tokenHasExpired(Date tokenDate)
    {
        DateTimeZone timeZone = DateTimeZone.forID("Europe/Madrid");
        DateTime now = new DateTime(timeZone);
        DateTime twentyFourHoursAgo = now.minusHours(24);
        DateTime tokenDateTime = new DateTime(tokenDate);

        return tokenDateTime.isBefore(twentyFourHoursAgo);
    }

    private boolean tokenRequestIntervalRespected(Date tokenDate)
    {
        DateTimeZone timeZone = DateTimeZone.forID("Europe/Madrid");
        DateTime now = new DateTime(timeZone);
        DateTime oneHourAgo = now.minusHours(1);
        DateTime tokenDateTime = new DateTime(tokenDate);

        return tokenDateTime.isBefore(oneHourAgo);
    }
}
