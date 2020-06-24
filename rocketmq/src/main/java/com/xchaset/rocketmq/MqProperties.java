package com.xchaset.rocketmq;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xchaset.mq")
@Data
public class MqProperties {

    private String nameServer;

    private String host;
}
