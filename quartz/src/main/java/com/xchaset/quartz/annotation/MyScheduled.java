package com.xchaset.quartz.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface MyScheduled {

    String name() default "";

    String description() default "";

    String cronExpression() default "";

    boolean enable() default true;

}
