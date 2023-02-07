package idv.lingerkptor.example.FunctionPrograming.Collections;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class CasQueue<Element> implements IQueue<Element> {
    private final AtomicReference<FunctionQueue<Element>> queue;
    private final Semaphore semaphore;

    private CasQueue() {
        this.queue = new AtomicReference<>(Queue.empty());
        this.semaphore = new Semaphore(1);
    }

    public CasQueue(int threadCount) {
        this.queue = new AtomicReference<>(Queue.empty());
        this.semaphore = new Semaphore(threadCount);
    }

    @Override
    public void offer(Element element) {
        semaphore.acquireUninterruptibly();

        FunctionQueue<Element> originQueue = queue.get();
//        while (!queue.compareAndSet(originQueue, originQueue.offer(element))) {
//            originQueue = queue.get();
//        }

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
        FunctionQueue<Element> originQueue = queue.get();
        Element element = originQueue.top();

//        while (!queue.compareAndSet(originQueue, originQueue.offer(element))) {
//            originQueue = queue.get();
//        }
//
//        semaphore.release();
//
//        return element;

        if (queue.compareAndSet(originQueue, originQueue.take())) {
            semaphore.release();

            return element;
        }
        semaphore.release();

        return this.take();
    }
}
