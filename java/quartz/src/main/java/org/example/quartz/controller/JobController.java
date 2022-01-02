package org.example.quartz.controller;

import org.example.quartz.JobDto;
import org.example.quartz.job.DemoJob1;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: JobController
 * Author:   lingerkptor
 * Date:     2021/10/31 下午 02:48
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/10/31 下午 02:48</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@RestController
public class JobController {
    private final Scheduler scheduler;

    @Autowired
    public JobController(SchedulerFactoryBean schedulerFactoryBean) {
        this.scheduler = schedulerFactoryBean.getScheduler();
    }

    @GetMapping("/job")
    public List<JobDto> getJobList() {
        try {
            if (scheduler.getJobGroupNames().size() > 0) {
                List<JobDto> jobList = new LinkedList<>();
                scheduler.getJobKeys(GroupMatcher.anyGroup()).forEach(jobKey -> {
                    jobList.add(new JobDto(jobKey.getGroup(), jobKey.getName()));
                });
//                取得正在執行的工作(註:排程中還沒執行不會列在裡面)
//                List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
//                System.out.println("execute job size:" + executingJobs.size());
                return jobList;
            }
        } catch (SchedulerException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }


    @PutMapping("/job/addJob/{user}")
    public JobDto addJob(@PathVariable String user) throws SchedulerException {
        // 建立工作細節
        JobDetail jobDetail = JobBuilder.newJob(DemoJob1.class)
                .withIdentity(user)                 // 底層是使用HashMap<String,Object>來存
                .usingJobData("user", user) // JobDataMap jobDataMap = new JobDataMap();
                .withIdentity(user).build();
        // 行程 功能同於 Spring boot @Schedule
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever(); // 每五秒執行一次
        // 觸發器
        Trigger trigger = scheduleBuilder.build().getTriggerBuilder()
                .forJob(jobDetail)
                .startNow() //.startAt(Date startTriggerDate)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ObjectAlreadyExistsException exception) {
            System.out.println(user + "已經正在工作");
            JobKey key = this.findJobKey(user).get();
            return new JobDto(key.getGroup(), key.getName());
        }
        JobKey key = jobDetail.getKey();
        return new JobDto(key.getGroup(), key.getName());
    }

    @GetMapping("/job/{group}")
    public List<String> getAllJobByGroup(@PathVariable String group) {
        try {
            if (scheduler.getJobGroupNames().size() > 0) {
                List<String> jobList = new LinkedList<>();
                scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group))
                        .forEach(jobKey -> {
                            System.out.println("group: " + jobKey.getGroup() + "name: " + jobKey.getName());
                            jobList.add(jobKey.getName());
                        });

//                List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
//                System.out.println(executingJobs.stream()
//                        .map(job -> job.getTrigger().getJobKey().getName())
//                        .collect(Collectors.toList()));
                return jobList;
            }

        } catch (SchedulerException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * 找尋 JobKey 借由 name
     *
     * @param name
     * @return
     * @throws SchedulerException
     */
    private Optional<JobKey> findJobKey(String name) throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.anyGroup()).stream()
                .filter(key -> key.getName().equals(name))
                .findFirst();
    }

    @PatchMapping("/job/pause/{name}")
    public void pauseJob(@PathVariable String name) throws SchedulerException {
        JobKey jobKey = this.findJobKey(name).orElseThrow();
        scheduler.pauseJob(jobKey);
    }

    @PatchMapping("/job/resume/{name}")
    public void resumeJob(@PathVariable String name) throws SchedulerException {
        JobKey jobKey = this.findJobKey(name).orElseThrow();
        scheduler.resumeJob(jobKey);
    }

    @DeleteMapping("/job/delete/{name}")
    public Boolean delete(@PathVariable String name) throws SchedulerException {
        JobKey jobKey = this.findJobKey(name).orElseThrow();
        return scheduler.deleteJob(jobKey);
    }


    @PutMapping("/job/addJob/{group}/{user}")
    public JobDto addJob(@PathVariable String group, @PathVariable String user) {
        // 類似於 Spring boot 內的 Trigger
        // 建立工作細節
        JobDetail jobDetail = JobBuilder.newJob(DemoJob1.class)
                .withIdentity(user, group)                 // 底層是使用HashMap<String,Object>來存
                .usingJobData("user", user) // JobDataMap jobDataMap = new JobDataMap();
                .withIdentity(user).build();
        // 行程 功能同於 Spring boot @Schedule
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5).repeatForever(); // 每五秒執行一次
        // 觸發器
        Trigger trigger = scheduleBuilder.build().getTriggerBuilder()
                .forJob(jobDetail)
                .startNow() //.startAt(Date startTriggerDate)
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ObjectAlreadyExistsException exception) {
            System.out.println(user + "已經正在工作");
            return null;
        } catch (SchedulerException exception) {
            exception.printStackTrace();
        }
        JobKey key = jobDetail.getKey();
        return new JobDto(key.getGroup(), key.getName());
    }
}
