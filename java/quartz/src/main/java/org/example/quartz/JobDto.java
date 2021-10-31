package org.example.quartz;

import lombok.Data;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: JobDto
 * Author:   lingerkptor
 * Date:     2021/10/31 下午 11:27
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/10/31 下午 11:27</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Data
public class JobDto {
    private String group;
    private String name;

    public JobDto(String group, String name) {
        this.group = group;
        this.name = name;
    }
}
