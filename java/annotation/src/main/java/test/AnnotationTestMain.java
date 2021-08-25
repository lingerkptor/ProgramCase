package test;

import test.ProcessStep;

public class AnnotationTestMain {

    public static void main(String[] args) {
        ProcessStep process1 = new ProcessStep() {
            @Override
            @ABC(value = "annotation1")
            protected void step2() {
                System.out.println("Step2 protected");
            }

        };
        // 要存取其他權限的標註，要使用reflection，並setAccessible(false)
        process1.step1();
        ProcessStep process2 = new ProcessStep() {
            @Override
            @ABC(value = "annotation2")
            public void step2() {
                System.out.println("Step2 public");
            }
        };
        process2.step1();
    }
}
