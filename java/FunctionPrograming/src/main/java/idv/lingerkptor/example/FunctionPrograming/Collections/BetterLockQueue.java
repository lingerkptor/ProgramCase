package idv.lingerkptor.example.FunctionPrograming.Collections;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BetterLockQueue<Element> implements IQueue<Element> {
    private BetterQueue<Element> queue;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public BetterLockQueue() {
        this.queue = BetterQueue.empty();
    }

    @Override
    public void offer(Element element) {
        lock.lock();
        try {
            this.queue = queue.offer(element);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Element take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                condition.await();
            }
            Element element = queue.front();
            queue = queue.offer(element);

            return element;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
