package com.maliware.let.srecruit.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;


@Configuration
public class AMQOutboundConfiguration {
    @Autowired
    CustomAMQPProperties customAMQPProperties;

//    @Bean
//    public MessageChannel input(){
//        return new DirectChannel();
//    }
//    @Bean
//    @Transformer(inputChannel = "outboundChannel",
//            outputChannel = "outboundChannel")
//    public ObjectToJsonTransformer transformObjToJason(){
//        return new ObjectToJsonTransformer();
//    }

    @Bean
    public MessageChannel outboundChannel(){
        return new DirectChannel();
    }

    @Bean
    Queue queue(){
        return new Queue(customAMQPProperties.getQueueName(), false);
    }

    @Bean
    public MessageConverter transformObjToJason(){
            return  new Jackson2JsonMessageConverter();
    }

}
