package org.example.quartz.service;

import org.springframework.stereotype.Service;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: SampleJobService
 * Author:   lingerkptor
 * Date:     2021/10/31 下午 03:22
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/10/31 下午 03:22</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Service
public class SampleJobService {
    public void executeSampleJob(String user) {
        System.out.println(user + "工作");
    }
}
