/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: LongThread
 * Author:   lingerkptor
 * Date:     2021/4/10 下午 01:37
 * Description: Long time Thread implement Runnable
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/10 下午 01:37 0 create
 */
public class LongThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Long time Job is running");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Long time Job is closed.");
    }
}
