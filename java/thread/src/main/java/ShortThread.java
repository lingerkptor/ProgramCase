/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ShortThreqad
 * Author:   lingerkptor
 * Date:     2021/4/10 下午 01:33
 * Description: implement Runnable interface of Class
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/10 下午 01:33 0 create
 */
public class ShortThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Running short time Job.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" short time Job is closed.");
    }
}
