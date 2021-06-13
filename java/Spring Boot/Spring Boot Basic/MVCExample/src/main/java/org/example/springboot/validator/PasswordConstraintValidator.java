package org.example.springboot.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: PasswordConstraintValidator
 * Author:   lingerkptor
 * Date:     2021/6/5 下午 02:40
 * Description: 驗證密碼範圍為a-z,A-Z,0-9,且最少要有大小字母及數字各一個,且密碼長度為8~20字元.
 * History:
 * <table>
 * <tr><td><author></td><td><time></td><td><version></td><td><desc></td></tr>
 * <tr><td>lingerkptor</td><td>2021/6/5 下午 02:40</td><td>1</td><td>file created</td></tr>
 * </table>
 */
public class PasswordConstraintValidator implements ConstraintValidator<PasswordConstraint,String> {
    @Override
    public void initialize(PasswordConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{8,20}$",value);
//        return false;
    }
}
