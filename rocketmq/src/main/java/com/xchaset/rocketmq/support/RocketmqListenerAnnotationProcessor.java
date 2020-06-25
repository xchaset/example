package com.xchaset.rocketmq.support;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.*;
@Component
public class RocketmqListenerAnnotationProcessor implements BeanPostProcessor {

    private List<RocketmqListener> collection = new ArrayList<>();
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        RocketmqListener rocketmqListener = AnnotationUtils.findAnnotation(targetClass, RocketmqListener.class);
        ReflectionUtils.doWithMethods(targetClass, (method) -> {
            Collection<RocketmqListener> listenerAnnotations = this.findListenerAnnotations(method);
            collection.addAll(listenerAnnotations);
        }, ReflectionUtils.USER_DECLARED_METHODS);
        return bean;
    }

    private Collection<RocketmqListener> findListenerAnnotations(Method method) {
        Set<RocketmqListener> listeners = new HashSet();
        RocketmqListener ann = AnnotationUtils.findAnnotation(method, RocketmqListener.class);
        if (ann != null) {
            listeners.add(ann);
        }


        return listeners;
    }
}
