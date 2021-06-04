import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ScheduledThreadPoolExample
 * Author:   lingerkptor
 * Date:     2021/4/10 下午 02:17
 * Description: ScheduledThreadPool Example
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/10 下午 02:17 0 create
 */
public class ScheduledThreadPoolExample {
    public static void example() {
        int initDelay = 0;
        int period = 5;
        int delay = 5;

        ScheduledExecutorService service = Executors.newScheduledThreadPool(20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        // 每隔一段(period)  時間在執行
        service.scheduleAtFixedRate(() -> {
            System.out.println("scheduleAtFixedRate " + dateFormat.format(new Date()).toString());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, initDelay, period, TimeUnit.SECONDS);
        // 每次執行後   經過一段時間(delay)  再執行
        service.scheduleWithFixedDelay(() -> {
            System.out.println("scheduleWithFixedDelay " + dateFormat.format(new Date()).toString());
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, initDelay, delay, TimeUnit.SECONDS);

        Scanner scanner = new Scanner(System.in);
        System.out.println("continue?y/n");
        if ("y".equals(scanner.next())) {
            service.shutdown();
        }
        try {
            while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                System.out.println("thread pool closing.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread pool is closing.");
    }
}
