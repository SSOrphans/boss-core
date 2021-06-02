package org.ssor.boss.core.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AwsSesService {

    private static final String CHAR_SET = "UTF-8";

    @Autowired
    private AmazonSimpleEmailService emailService;

    public SendEmailResult sendEmail(String senderEmail,String recieverEmail, String subject, String body) {
        try {
            // The time for request/response round trip to aws in milliseconds
            int requestTimeout = 3000;
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(recieverEmail))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset(CHAR_SET).withData(body)))
                            .withSubject(new Content()
                                    .withCharset(CHAR_SET).withData(subject)))
                    .withSource(senderEmail).withSdkRequestTimeout(requestTimeout);
            return emailService.sendEmail(request);
        } catch (RuntimeException e) {
            log.error("Error occurred sending email to {} ", recieverEmail, e);
            return null;
        }
    }
}
