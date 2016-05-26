package com.predatum.sbsla.auth;

import com.predatum.sbsla.entity.User;
import com.predatum.sbsla.repository.UserRepository;
import com.predatum.sbsla.repository.UserRoleRepository;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcos Peña
 */
@Component
public class ShiroRealm extends JdbcRealm
{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        // identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        final String username = userPassToken.getUsername();

        if (username == null) {
            System.out.println("Username is null.");
            return null;
        }

        // read password hash and salt from db
        final User user = userRepository.findOneByEmail(username);

        if (user == null) {
            System.out.println("No account found for user [" + username + "]");
            return null;
        }
        // return salted credentials
        SaltedAuthenticationInfo info = new SaltedAuthentificationInfo(username, user.getPassword(), user.getSalt());

        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        String username = (String) getAvailablePrincipal(principalCollection);

        Set<String> roleNames = new LinkedHashSet<>();
        User user = userRepository.findOneByEmail(username);

        if (user == null) {
            SecurityUtils.getSubject().logout();

            throw new AuthorizationException("Unknown Account!");
        }

        userRoleRepository.findByEmail(user.getEmail()).stream().forEach((role) -> {
            roleNames.add(role.getRoleName());
        });

        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo(roleNames);
        if (getPermissionResolver() == null) {
            setPermissionResolver(new WildcardPermissionResolver());
        }

        return simpleAuthorInfo;
    }

}
