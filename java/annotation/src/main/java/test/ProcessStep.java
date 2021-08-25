package test;

import java.lang.reflect.Method;

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



    abstract void step2();


}
