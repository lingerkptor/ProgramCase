package idv.lingerkptor.example.FunctionPrograming.example;

import idv.lingerkptor.example.FunctionPrograming.Collections.FunctionStack;
import idv.lingerkptor.example.FunctionPrograming.Collections.Stack;
import idv.lingerkptor.example.FunctionPrograming.bean.Immutable.Rectangle;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Example4 {
    private Random random = new Random();

    public void doSomething() {
        FunctionStack<Rectangle> stack = Stack.empty();
        for (int i = 0; i < 100; i++) {
            stack = randomOperate(stack);
        }
    }

    private Rectangle generateRandomRectangle() {
        return new Rectangle(random.nextInt(100), random.nextInt(100));
    }

    private FunctionStack<Rectangle> randomOperate(FunctionStack<Rectangle> stack) {
        int opInt = new Random().nextInt(0,2) % 2;

        switch (opInt) {
            case 0:
                Rectangle rectangle = generateRandomRectangle();
                log.info("stack push {}", rectangle);

                return stack.push(rectangle);
            default:
                log.info("pop element:{}", stack.top());

                return stack.pop();
        }
    }
}
