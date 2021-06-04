import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ThreadPoolClose
 * Author:   lingerkptor
 * Date:     2021/4/10 下午 02:10
 * Description: Thread Pool close example.
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/10 下午 02:10 0 create
 */
public class ThreadPoolClose {
    public static void example(){
        // 建立固定式執行續池
        ExecutorService service = Executors.newFixedThreadPool(10);
        service.submit(new ShortThread());//短時間Thread
        service.submit(new ShortThread());//短時間Thread
        service.submit(new LongThread());// 長時間Thread
        service.submit(new ShortThread());//短時間Thread
        service.submit(new LongThread());// 長時間Thread
        service.submit(new ShortThread());//短時間Thread
        service.submit(new LongThread());// 長時間Thread
        service.submit(new LongThread());// 長時間Thread
        service.submit(new ShortThread());//短時間Thread
        service.submit(new LongThread());// 長時間Thread
        // 關閉ThreadPool
        service.shutdown();
        try {
            // 等待是否終止
            while (!service.awaitTermination(1, TimeUnit.SECONDS)) {
                //還沒終止
                System.out.println("service is closing.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //已終止(關閉)
        System.out.println("service is closed.");
    }
}
