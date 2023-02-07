package idv.lingerkptor.example.FunctionPrograming.Collections;

public class Queue<Element> implements FunctionQueue<Element> {
    private static final Queue<Object> EMPTY = new Queue<>();
    private final FunctionStack<Element> head, tail;

    private Queue() {
        this(Stack.empty(), Stack.empty());
    }

    private Queue(FunctionStack<Element> head, FunctionStack<Element> tail) {
        if (tail.isEmpty()) {
            this.head = Stack.empty();
            this.tail = Stack.reverseStack(head);
        } else {
            this.head = head;
            this.tail = tail;
        }
    }

    public FunctionQueue<Element> offer(Element element) {
        return new Queue<>(head.push(element), tail);
    }

    public FunctionQueue<Element> take() {
        return new Queue<>(head, tail.pop());
    }

    public Element top() {
        return tail.top();
    }

    public boolean isEmpty() {
        return tail.isEmpty();
    }

    public static <Element> Queue<Element> empty() {
        return (Queue<Element>) EMPTY;
    }
}
