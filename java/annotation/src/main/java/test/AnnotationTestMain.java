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
