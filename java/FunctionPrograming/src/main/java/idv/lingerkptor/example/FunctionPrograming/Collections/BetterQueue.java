package idv.lingerkptor.example.FunctionPrograming.Collections;

public class BetterQueue<Element> {
    private static final BetterQueue<Object> EMPTY = new BetterQueue<>();
    private final FunctionStack<Element> head, tail;
    private final BetterQueue<FunctionStack<Element>> tempQueue;

    private BetterQueue() {
        this.head = Stack.empty();
        this.tail = Stack.empty();
        this.tempQueue = (BetterQueue<FunctionStack<Element>>) this;
    }

    private BetterQueue(FunctionStack<Element> head, BetterQueue<FunctionStack<Element>> tempQueue, FunctionStack<Element> tail) {
        if (tail.isEmpty()) {
            if (tempQueue.isEmpty()) {
                this.head = Stack.empty();
                this.tempQueue = BetterQueue.empty();
                this.tail = Stack.reverseStack(head);
            } else {
                this.head = head;
                this.tempQueue = tempQueue.take();
                this.tail = tempQueue.front();
            }
        } else {
            if (head.size() == 3) {
                this.head = Stack.empty();
                this.tempQueue = tempQueue.offer(Stack.reverseStack(head));
                this.tail = tail;
            } else {
                this.head = head;
                this.tempQueue = tempQueue;
                this.tail = tail;
            }
        }
    }

    public BetterQueue<Element> offer(Element element) {
        return new BetterQueue<>(head.push(element), this.tempQueue, tail);
    }

    public BetterQueue<Element> take() {
        return new BetterQueue<>(head, this.tempQueue, tail.pop());
    }

    public Element front() {
        return tail.top();
    }

    public boolean isEmpty() {
        return tail.isEmpty();
    }

    public static <Element> BetterQueue<Element> empty() {
        return (BetterQueue<Element>) EMPTY;
    }
}
