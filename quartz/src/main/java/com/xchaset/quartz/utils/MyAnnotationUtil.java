package com.xchaset.quartz.utils;

import com.xchaset.quartz.annotation.MyScheduled;
import com.xchaset.quartz.schedule.JobBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MyAnnotationUtil {

    public static List<JobBean> getAllMyScheduledTask(){
        List<JobBean> jobBeans = new ArrayList<>();
        Map<String, QuartzJobBean> beansOfType = SpringContextHolder.getBeansOfType(QuartzJobBean.class);
        for (Map.Entry<String, QuartzJobBean> beanEntry : beansOfType.entrySet()) {
            MyScheduled annotation = AnnotationUtils.findAnnotation(beanEntry.getValue().getClass(), MyScheduled.class);
            QuartzJobBean value = beanEntry.getValue();
            if (annotation == null) {
                continue;
            }
            if (annotation.enable()) {
                String name = annotation.name();
                String description = annotation.description();
                String cronExpression = annotation.cronExpression();
                JobBean build = JobBean.builder().jobName(name)
                        .groupName("myGroup")
                        .cronExpression(cronExpression)
                        .description(description)
                        .quartzJobBean(value.getClass())
                        .build();
                jobBeans.add(build);
            }
        }
        return jobBeans;
    }
}
