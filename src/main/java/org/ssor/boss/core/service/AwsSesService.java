package org.ssor.boss.core.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssor.boss.core.transfer.Email;


@Slf4j
@Service
public class AwsSesService {

    private static final String CHAR_SET = "UTF-8";

    @Autowired
    private AmazonSimpleEmailService emailService;

    public SendEmailResult sendEmail(Email mail) {
        try {
            // The time for request/response round trip to aws in milliseconds
            int requestTimeout = 3000;
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(mail.getRecipient()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset(CHAR_SET).withData(mail.getBody())))
                            .withSubject(new Content()
                                    .withCharset(CHAR_SET).withData(mail.getSubject())))
                    .withSource(mail.getSender()).withSdkRequestTimeout(requestTimeout);
            return emailService.sendEmail(request);
        } catch (RuntimeException e) {
            log.error("Error occurred sending email to {} ", mail.getRecipient(), e);
            return null;
        }
    }
}
