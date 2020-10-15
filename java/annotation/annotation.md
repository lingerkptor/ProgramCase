### 標註使用

### 標註類別
```java
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
```

### 父類別如果要用子類別的標註
```java
package test;

public abstract class ProcessStep {
    public void step1() {
        String value=null;
        try {
            value = this.getClass().getMethod("step2").getAnnotation(ABC.class).value();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("Step1");
        System.out.println("Step2 annotation value is " + value);
    }
    protected abstract void step2();
}

```
### 子類別標註的地方，必須使用public，不然讀不到．
```java
package test;
import test.ProcessStep;

public class AnnotationTestMain {

    public static void main(String[] args){
        ProcessStep process1 = new ProcessStep() {
            @Override
            @ABC(value="annotation1")
            protected void step2() { // 這裡必須用public 不然會找不到
                System.out.println("Step2");
            }

        };
      process1.step1();
        ProcessStep process2 = new ProcessStep() {
            @Override
            @ABC(value="annotation2")
            public void step2() { // 這裡必須用public 不然會找不到
                System.out.println("Step2");
            }

        };
        process2.step1();
    }
}

```


 