package com.onlinelearning.Services.Impl;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.onlinelearning.Models.Email;
import com.onlinelearning.Services.EmailService;
import com.onlinelearning.Utils.DotEnv;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailGunEmailServiceImpl implements EmailService {

    private static MailGunEmailServiceImpl mailGunEmailServiceInstance;

    private static MailgunMessagesApi mailgunMessagesApi;

    private static String DOMAIN;

    private MailGunEmailServiceImpl() {
        mailgunMessagesApi = MailgunClient.config(DotEnv.get("MAILGUN_BASE_URL"), DotEnv.get("MAILGUN_API_KEY"))
                .createApi(MailgunMessagesApi.class);
        DOMAIN = DotEnv.get("MAILGUN_DOMAIN");
    }

    public MailGunEmailServiceImpl load() {
        if (mailGunEmailServiceInstance == null) {
            mailGunEmailServiceInstance = new MailGunEmailServiceImpl();
        }
        return mailGunEmailServiceInstance;
    }

    @Override
    public Email sendEmail(Email email) {
        Message message = Message.builder()
                .from(email.getFrom())
                .to(email.getTo())
                .subject(email.getSubject())
                .html(email.getHtmlContent())
                .build();
        try {
            MessageResponse response = mailgunMessagesApi.sendMessage(DOMAIN, message);
            return email;
        } catch (Exception e) {
            Logger.getLogger(MailGunEmailServiceImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

}
