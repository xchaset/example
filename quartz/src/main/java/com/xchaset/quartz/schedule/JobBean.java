package com.xchaset.quartz.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.JobDataMap;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobBean {

    private Class<? extends QuartzJobBean> quartzJobBean;

    private String jobName;

    private String groupName;

    private String cronExpression;

    private String description;

}
