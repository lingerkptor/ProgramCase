package org.example.springboot.validator;

import javax.validation.Payload;
import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: PasswordConstraint
 * Author:   lingerkptor
 * Date:     2021/6/5 下午 02:43
 * Description:
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/5 下午 02:43</td><td>1</td><td>file created</td></tr>
 * </table>
 */

/**
 * @Target 標註使用，用來可以標註的位址
 * @Retention 標註使用，用來說明標註的生命週期．
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
/** 指定標註使用的驗證器
 *
 */
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface PasswordConstraint {
    String message() default "密碼規則必須包含大小寫及數字,最少8~20字元";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
