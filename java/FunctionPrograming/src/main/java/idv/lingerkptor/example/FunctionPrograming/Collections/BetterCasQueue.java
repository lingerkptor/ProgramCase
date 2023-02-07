package idv.lingerkptor.example.FunctionPrograming.Collections;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

public class BetterCasQueue<Element> implements IQueue<Element> {
    private final AtomicReference<BetterQueue<Element>> queue;
    private final Semaphore semaphore;

    private BetterCasQueue() {
        this.queue = new AtomicReference<>(BetterQueue.empty());
        this.semaphore = new Semaphore(1);
    }

    public BetterCasQueue(int threadCount) {
        this.queue = new AtomicReference<>(BetterQueue.empty());
        this.semaphore = new Semaphore(threadCount);
    }

    @Override
    public void offer(Element element) {
        semaphore.acquireUninterruptibly();
        BetterQueue<Element> originQueue = queue.get();
//        while (!queue.compareAndSet(originQueue, originQueue.offer(element))) {
//            originQueue = queue.get();
//        }
//        semaphore.release();

        if (queue.compareAndSet(originQueue, originQueue.offer(element))) {
            semaphore.release();

            return;
        }
        semaphore.release();

        this.offer(element);
    }

    @Override
    public Element take() {
        semaphore.acquireUninterruptibly();
        BetterQueue<Element> originQueue = queue.get();
        Element element = originQueue.front();
//        while (!queue.compareAndSet(originQueue, originQueue.offer(element))) {
//            originQueue = queue.get();
//        }

        if (queue.compareAndSet(originQueue, originQueue.take())) {
            semaphore.release();

            return element;
        }
        semaphore.release();

        return this.take();
    }
}
