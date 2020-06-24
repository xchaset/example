package com.xchaset.rocketmq;

import com.xchaset.rocketmq.impl.HelloServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(MqProperties.class)
public class RocketMqAutoConfiguration {


    private MqProperties mqProperties;

    public RocketMqAutoConfiguration(MqProperties mqProperties) {
        this.mqProperties = mqProperties;
    }

    @ConditionalOnMissingBean
    @Bean
    public HelloService helloService(){
        return new HelloServiceImpl(mqProperties.getNameServer(),mqProperties.getHost());
    }
}
