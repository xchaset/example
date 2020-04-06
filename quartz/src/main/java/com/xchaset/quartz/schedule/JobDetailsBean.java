package com.xchaset.quartz.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.JobDataMap;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDetailsBean {

    private String className;

    private String jobName;

    private String groupName;

    private String cronExpression;

    private String description;

    private String triggerState;

    private Date nextFireTime;

    private Date previousFireTime;

    private JobDataMap jobDataMap;
}
