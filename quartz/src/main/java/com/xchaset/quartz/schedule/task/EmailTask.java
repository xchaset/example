package com.xchaset.quartz.schedule.task;

import com.xchaset.quartz.annotation.MyScheduled;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@DisallowConcurrentExecution
@MyScheduled(name = "EmailTask",description = "email schedule task",cronExpression = "0 0/2 * * * ?")
public class EmailTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("{} is execute!",this.getClass().getSimpleName());
    }
}
