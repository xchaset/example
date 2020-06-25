package com.xchaset.rocketmq;

import com.xchaset.rocketmq.config.RocketmqProperties;
import com.xchaset.rocketmq.impl.HelloServiceImpl;
import com.xchaset.rocketmq.processor.MQConsumeMsgListenerProcessor;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties({MqProperties.class,RocketmqProperties.class})
public class RocketMqAutoConfiguration {


    @Autowired
    private MqProperties mqProperties;

    @Autowired
    private RocketmqProperties rocketmqProperties;

    @ConditionalOnMissingBean
    @Bean
    public HelloService helloService(){
        return new HelloServiceImpl(mqProperties.getNameServer(),mqProperties.getHost());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rocketmq.producer",value = "default",havingValue = "true")
    public DefaultMQProducer defaultMQProducer() throws MQClientException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(rocketmqProperties.getProducer().getGroupName());
        defaultMQProducer.setNamesrvAddr(rocketmqProperties.getProducer().getNamesrvAddr());
        defaultMQProducer.setVipChannelEnabled(true);
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(10);
        defaultMQProducer.start();
        return defaultMQProducer;
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageListenerConcurrently messageListenerConcurrently(){
        return new MQConsumeMsgListenerProcessor();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "rocketmq.consumer",value = {"namesrvAddr","topics","groupName"})
    public DefaultMQPushConsumer defaultMQPushConsumer(MessageListenerConcurrently messageListenerConcurrently) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(rocketmqProperties.getConsumer().getGroupName());
        consumer.setNamesrvAddr(rocketmqProperties.getConsumer().getNamesrvAddr());
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(messageListenerConcurrently);
        String topics = rocketmqProperties.getConsumer().getTopics();
        String[] topicTagsArr = topics.split(";");
        for (String topicTags : topicTagsArr) {
            String[] topicTag = topicTags.split("~");
            consumer.subscribe(topicTag[0],topicTag[1]);
        }
        consumer.start();
        return consumer;

    }
}
