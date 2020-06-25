package com.xchaset.rocketmq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "rocketmq")
public class RocketmqProperties {


    private Consumer consumer = new Consumer();

    private Producer producer = new Producer();

    @Data
    public static class Consumer{
        private String groupName;

        private String namesrvAddr;

        private String topics;
    }

    @Data
    public static class Producer{
        private String groupName;

        private String namesrvAddr;

    }
}
