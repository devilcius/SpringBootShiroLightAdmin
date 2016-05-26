package com.predatum.sbsla.util.email;

import com.predatum.sbsla.entity.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
/**
 *
 * @author Marcos Peña
 */
@Component
public class EmailHtmlSender
{

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public EmailStatus send(EmailMessage emailMessage, String templateName, Context context)
    {
        String body = templateEngine.process(templateName, context);
        emailMessage.setBody(body);

        return emailSender.sendHtml(emailMessage);
    }
}
