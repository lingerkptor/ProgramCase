package org.example.springboot.bean;

import lombok.Data;
import org.example.springboot.validator.PasswordConstraint;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: User
 * Author:   lingerkptor
 * Date:     2021/6/5 下午 02:31
 * Description: 使用者資料
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/5 下午 02:31</td><td>1</td><td>file created</td></tr>
 * </table>
 */
@Data
public class UserForm implements Serializable {
    /**
     *
     */
    private Long id;
    /**
     * 使用者名稱
     */
    @NotBlank(message = "使用者不得為空")
    @Length(min = 5, max = 20, message = "使用者名稱長度為5-20個字元")
    private String name;


    @NotNull(message = "年齡不得為空")
//    @Size(min = 18, max = 60, message = "年齡介於18~60歲")
    @Min(value = 18 ,message = "年齡最少18歲")
    @Max(value = 60,message = "年齡最多60歲")
    private Integer age;

    @Email(message = "錯誤的信箱")
    @NotBlank(message = "電子信箱不得為空")
    private String email;

    @NotBlank(message = "請輸入密碼")
    @PasswordConstraint // 自訂驗證限制
    private String password;
}
