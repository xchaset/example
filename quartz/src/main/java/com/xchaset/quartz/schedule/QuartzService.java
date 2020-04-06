package com.xchaset.quartz.schedule;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class QuartzService {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void startScheduler() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个任务
     * @param jobClass
     * @param jobGroup
     * @param jobName
     * @param jobTime
     * @param jobMap
     * @throws SchedulerException
     */
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobGroup, String jobName, String jobTime, Map jobMap) throws SchedulerException {
        JobDetail build = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).build();
        if (jobMap != null && jobMap.size() > 0) {
            build.getJobDataMap().putAll(jobMap);
        }
        Trigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).startNow().build();

        scheduler.scheduleJob(build, cronTrigger);

    }

    /**
     * 更新任务时间表达式
     * @param jobGroup
     * @param jobName
     * @param jobTime
     * @throws SchedulerException
     */
    public void updateJob(String jobGroup, String jobName, String jobTime) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
        scheduler.rescheduleJob(triggerKey,trigger);
    }

    /**
     * 删除任务
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     */
    public void deleteJob(String jobGroup,String jobName) throws SchedulerException {
        scheduler.deleteJob(new JobKey(jobName,jobGroup));
    }

    public void pauseJob(String jobGroup,String jobName) throws SchedulerException {
        scheduler.pauseJob(new JobKey(jobName,jobGroup));
    }

    /**
     * 恢复任务
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     */
    public void resumeJob(String jobGroup,String jobName) throws SchedulerException {
        scheduler.resumeJob(new JobKey(jobName,jobGroup));
    }

    /**
     * 立刻执行一个任务
     * @param jobGroup
     * @param jobName
     * @throws SchedulerException
     */
    public void runAJobNow(String jobGroup,String jobName) throws SchedulerException {
        scheduler.triggerJob(new JobKey(jobName,jobGroup));
    }

    /**
     * 查询所有的任务
     * @return
     * @throws SchedulerException
     */
    public List<JobDetailsBean> queryAllJob() throws SchedulerException {
        GroupMatcher<JobKey> anyJobGroup = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(anyJobGroup);
        List<JobDetailsBean> result = getJobDetailsBeans(jobKeys);
        return result;
    }

    private List<JobDetailsBean> getJobDetailsBeans(Collection<JobKey> jobKeys) throws SchedulerException {
        List<JobDetailsBean> result = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggersOfJob = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggersOfJob) {
                String group = jobKey.getGroup();
                String name = jobKey.getName();
                String className = jobKey.getClass().getName();
                String description = trigger.getDescription();
                JobDataMap jobDataMap = trigger.getJobDataMap();
                Date nextFireTime = trigger.getNextFireTime();
                Date previousFireTime = trigger.getPreviousFireTime();
                Trigger.TriggerState triggerState = scheduler.getTriggerState(TriggerKey.triggerKey(name, group));
                String cronExpression = "";
                if (trigger instanceof CronTrigger) {
                    cronExpression = ((CronTrigger) trigger).getCronExpression();
                }

                JobDetailsBean build = JobDetailsBean.builder().className(className).groupName(group).jobName(name).description(description)
                        .cronExpression(cronExpression).jobDataMap(jobDataMap).nextFireTime(nextFireTime).previousFireTime(previousFireTime)
                        .triggerState(triggerState.name()).build();
                result.add(build);
            }
        }
        return result;
    }

    /**
     * 查询正在执行的任务
     * @return
     * @throws SchedulerException
     */
    public List<JobDetailsBean> queryCurrentExecutingJob() throws SchedulerException {
        List<JobDetailsBean> result = new ArrayList<>();
        List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext currentlyExecutingJob : currentlyExecutingJobs) {
            Trigger trigger = currentlyExecutingJob.getTrigger();
            JobDataMap jobDataMap = trigger.getJobDataMap();
            JobKey jobKey = trigger.getJobKey();
            String group = jobKey.getGroup();
            String name = jobKey.getName();
            String className = jobKey.getClass().getName();
            Date nextFireTime = trigger.getNextFireTime();
            String description = trigger.getDescription();
            Date previousFireTime = trigger.getPreviousFireTime();
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            String cronExpression = "";
            if (trigger instanceof CronTrigger) {
                cronExpression = ((CronTrigger) trigger).getCronExpression();
            }
            JobDetailsBean build = JobDetailsBean.builder().className(className).groupName(group).jobName(name).description(description)
                    .cronExpression(cronExpression).jobDataMap(jobDataMap).nextFireTime(nextFireTime).previousFireTime(previousFireTime)
                    .triggerState(triggerState.name()).build();
            result.add(build);
        }
        return result;
    }
}
