package test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)// 標註會留在.class，Runtime期可以使用
@Target(ElementType.METHOD)// 限制只能用在方法
public @interface ABC {
    String value();
}
