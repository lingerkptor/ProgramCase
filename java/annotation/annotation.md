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
            Class subClass = this.getClass();
            Method step2Method = subClass.getDeclaredMethod("step2");// 取得方法(被封裝的也可以找的到)
            step2Method.setAccessible(false); // 移除存取權限的保護機制
            value = step2Method.getAnnotation(ABC.class).value();
            step2Method.setAccessible(true); // 回復存取權限的保護機制
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("Step1");
        System.out.println("Step2 annotation value is " + value);
    }
    protected abstract void step2();
}

```
### 子類別標註的地方
```java
package test;
import test.ProcessStep;

public class AnnotationTestMain {

    public static void main(String[] args){
        ProcessStep process1 = new ProcessStep() {
            @Override
            @ABC(value="annotation1")
            protected void step2() { 
                System.out.println("Step2");
            }
        };
      // 要存取其他權限的標註，要使用reflection，並setAccessible(false)
      process1.step1();
        ProcessStep process2 = new ProcessStep() {
            @Override
            @ABC(value="annotation2")
            public void step2() { 
                System.out.println("Step2");
            }
        };
        process2.step1();
    }
}

```


 