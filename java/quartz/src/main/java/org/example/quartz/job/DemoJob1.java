package org.example.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.quartz.service.SampleJobService;


/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: DemoJob
 * Author:   lingerkptor
 * Date:     2021/10/31 下午 02:35
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/10/31 下午 02:35</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class DemoJob1 implements Job {

    @Autowired
    private SampleJobService jobService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        jobService.executeSampleJob(dataMap.getString("user"));
    }
}
