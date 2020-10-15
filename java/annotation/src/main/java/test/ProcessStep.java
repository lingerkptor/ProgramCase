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
