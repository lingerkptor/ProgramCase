package idv.lingerkptor.example.FunctionPrograming.example;

import idv.lingerkptor.example.FunctionPrograming.Collections.*;
import idv.lingerkptor.example.FunctionPrograming.bean.Immutable.Rectangle;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.LongStream;


@Slf4j
public class PerformanceExample {
    private final int OPERATE_COUNT = 10000000;
    private final int THREAD_COUNT = 16;

    private Random random = new Random();

    public void casQueueBenchmark() {
        CasQueue<Rectangle> casQueue = new CasQueue<>(THREAD_COUNT);
        averageWorkerTime("CasQueue", this::pureQueueTestPerformanceTest, casQueue);
    }
    public void lockQueueBenchmark(){
                LockQueue<Rectangle> lockQueue = new LockQueue<>();
        averageWorkerTime("LockQueue", this::pureQueueTestPerformanceTest, lockQueue);
    }
    public void linkedBlockingQueueBenchmark(){
        LinkedBlockingQueue<Rectangle> linkedBlockingQueue = new LinkedBlockingQueue<>();
        averageWorkerTime("BlockingQueue", this::blockingQueueTestPerformanceTest, linkedBlockingQueue);
    }
    public void betterCasQueueBenchmark(){
        BetterCasQueue<Rectangle> betterCasQueue = new BetterCasQueue<>(THREAD_COUNT);
        averageWorkerTime("BetterCasQueue", this::pureQueueTestPerformanceTest, betterCasQueue);
    }
    public void betterLockQueueBenchmark(){
        BetterLockQueue<Rectangle> betterLockQueue = new BetterLockQueue<>();
        averageWorkerTime("BetterLockQueue", this::pureQueueTestPerformanceTest, betterLockQueue);
    }

    private Long pureQueueTestPerformanceTest(IQueue<Rectangle> queue) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future> futureList = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            futureList.add(executorService.submit(generateOperateQueueWorker(queue)));
        }

        waitDone(futureList);
        long workerTime = System.currentTimeMillis() - startTime;
//        log.info(queueName + " performance test cost ms:{} thread:{},per thread operate Count:{}",
//                workerTime,
//                THREAD_COUNT, OPERATE_COUNT / THREAD_COUNT);
        executorService.shutdownNow();
        return workerTime;
    }

    private Long blockingQueueTestPerformanceTest(BlockingQueue<Rectangle> blockingQueue) {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future> futureList = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            futureList.add(executorService.submit(generateOperateLinkedBlockingQueueWorker(blockingQueue)));
        }

        waitDone(futureList);
        long workerTime = System.currentTimeMillis() - startTime;
//        log.info("BlockingQueue performance test cost ms:{} thread:{},per thread operate Count:{}",
//                workerTime, THREAD_COUNT, OPERATE_COUNT / THREAD_COUNT);
        executorService.shutdownNow();

        return workerTime;
    }

    private Runnable generateOperateLinkedBlockingQueueWorker(BlockingQueue<Rectangle> blockingQueue) {
        return () -> {
            for (int i = 0; i < OPERATE_COUNT / THREAD_COUNT; i++) {
                try {
                    if (i < OPERATE_COUNT / THREAD_COUNT / 2) {
                        Rectangle rectangle = generateRandomRectangle();
                        blockingQueue.add(rectangle);
//                log.info("enqueue push {}", rectangle);
                    } else {
                        Rectangle element = blockingQueue.take();
//                log.info("dequeue element:{}", element);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public void waitDone(List<Future> futureList) {
        futureList.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Runnable generateOperateQueueWorker(IQueue<Rectangle> queue) {
        return () -> {
            for (int i = 0; i < OPERATE_COUNT / THREAD_COUNT; i++) {
                if (i < OPERATE_COUNT / THREAD_COUNT / 2) {
                    Rectangle rectangle = generateRandomRectangle();
                    queue.offer(rectangle);
                } else {
                    Rectangle rectangle = queue.take();
                }
            }
        };
    }

    private Rectangle generateRandomRectangle() {
        return new Rectangle(random.nextInt(100), random.nextInt(100));
    }

    private void randomOperate(IQueue<Rectangle> queue) {
        int opInt = new Random().nextInt(0, 2) % 2;

        switch (opInt) {
            case 0:
                Rectangle rectangle = generateRandomRectangle();
//                log.info("enqueue push {}", rectangle);

                queue.offer(rectangle);
            default:
                rectangle = queue.take();
//                log.info("dequeue element:{}", rectangle);
        }
    }

    private <InputType> void averageWorkerTime(String worker, Function<InputType, Long> testMethod, InputType input) {
        LongStream.Builder workerTimeStreamBuilder = LongStream.builder();
        for (int i = 0; i < 10; i++) {
            Long executeTime = testMethod.apply(input);
//            log.info("{} execute time:{}", worker, executeTime);
            workerTimeStreamBuilder.add(executeTime);
        }

        log.info("{} avg execute time: {}", worker, workerTimeStreamBuilder.build().average().getAsDouble());
    }
}
