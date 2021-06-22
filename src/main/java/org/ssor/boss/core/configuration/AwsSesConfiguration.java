package org.ssor.boss.core.configuration;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfiguration {
    /*
    *   Configures AWS SES bean.
    *   Must have AWS CLI configured for this method to work
    */
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    }
}
