package com.maliware.let.srecruit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "my.app")
public class CustomAMQPProperties {
    private String queueName;
    private String replyQueue;
    private String exchange;
    private String replyExchangeQueue;

}
