package com.xchaset.quartz.schedule;

import com.xchaset.quartz.utils.MyAnnotationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleCommandLine implements CommandLineRunner {

    @Autowired
    private QuartzService quartzService;

    @Override
    public void run(String... args) throws Exception {
        List<JobBean> allMyScheduledTask = MyAnnotationUtil.getAllMyScheduledTask();
        for (JobBean jobBean : allMyScheduledTask) {
            quartzService.deleteJob(jobBean.getGroupName(),jobBean.getJobName());
            quartzService.addJob(jobBean.getQuartzJobBean(),jobBean.getGroupName(),jobBean.getJobName(),jobBean.getCronExpression(),null);
        }
    }
}
