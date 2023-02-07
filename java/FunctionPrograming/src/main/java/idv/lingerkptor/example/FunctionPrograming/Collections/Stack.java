package idv.lingerkptor.example.FunctionPrograming.Collections;

import java.util.Objects;

public class Stack<Element> implements FunctionStack<Element> {
    private static final Stack<Object> EMPTY = new Stack<>();
    private final Element element;
    private final Stack<Element> tail;

    private Stack() {
        this(null, null);
    }

    private Stack(Element element, Stack<Element> stack) {
        this.element = element;
        this.tail = stack;
    }

    public FunctionStack<Element> pop() {
        return Objects.nonNull(tail) ? tail : (Stack<Element>) EMPTY;
    }

    public FunctionStack<Element> push(Element element) {
        return new Stack<>(element, this);
    }

    public Element top() {
        return element;
    }

    public boolean isEmpty() {
        return this.element == null;
    }

    public static <Element> Stack<Element> empty() {
        return (Stack<Element>) EMPTY;
    }

    public int size() {
        return isEmpty() ? 0 : tail.size() + 1;
    }

    public static <Element> FunctionStack<Element> reverseStack(FunctionStack<Element> stack) {
        FunctionStack<Element> reverseStack = Stack.empty();

        while (!stack.isEmpty()) {
            Element element = stack.top();
            reverseStack = reverseStack.push(element);
            stack = stack.pop();
        }

        return reverseStack;
    }
}
