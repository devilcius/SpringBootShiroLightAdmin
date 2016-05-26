package com.predatum.sbsla.util.email;

import com.predatum.sbsla.entity.EmailMessage;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Marcos Peña
 */
@Component
public class EmailSender
{

    private static final Logger LOGGER = LoggerFactory
            .getLogger(EmailSender.class);

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailStatus sendPlainText(EmailMessage emailMessage)
    {

        return sendEmail(emailMessage, false);
    }

    public EmailStatus sendHtml(EmailMessage emailMessage)
    {

        return sendEmail(emailMessage, true);
    }

    private EmailStatus sendEmail(EmailMessage emailMessage, boolean isHtml)
    {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(emailMessage.getRecipient());
            helper.setFrom(emailMessage.getSender());
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getBody(), isHtml);
            javaMailSender.send(mail);
            LOGGER.info(String.format("Send email %s to: %s", emailMessage.getSubject(), emailMessage.getRecipient()));

            return new EmailStatus(emailMessage).success();
        } catch (Exception e) {
            LOGGER.error(String.format("Problem with sending email to: %s, error message: %s", emailMessage.getRecipient(), e.getMessage()));

            return new EmailStatus(emailMessage).error(e.getMessage());
        }
    }

}
