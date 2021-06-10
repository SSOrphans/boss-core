package org.ssor.boss.core.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfiguration {

    private final String REGION="us-east-2";

    /**
     * Build the AWS ses client
     *
     * @return AmazonSimpleEmailServiceClientBuilder
     */
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        //FIXME: Find a better way to get aws credentials
        AWSCredentialsProvider credentialsProvider = new AWSCredentialsProvider() {
            @Override
            public AWSCredentials getCredentials() {
                return new AWSCredentials() {
                    @Override
                    public String getAWSSecretKey() {
                        return "x1x57lalNj4jtp2XmlU3nK1cftq+4DPXGX446JpO";
                    }
                    @Override
                    public String getAWSAccessKeyId() {
                        return "AKIA2DCR3XYBACCVPG2L";
                    }
                };
            }

            @Override
            public void refresh() {
            }
        };
        return AmazonSimpleEmailServiceClientBuilder.standard().withCredentials(credentialsProvider)
                .withRegion(REGION).build();
    }
}
