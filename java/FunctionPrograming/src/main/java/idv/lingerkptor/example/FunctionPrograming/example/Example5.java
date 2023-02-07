package idv.lingerkptor.example.FunctionPrograming.example;

import idv.lingerkptor.example.FunctionPrograming.Collections.FunctionQueue;
import idv.lingerkptor.example.FunctionPrograming.Collections.FunctionStack;
import idv.lingerkptor.example.FunctionPrograming.Collections.Queue;
import idv.lingerkptor.example.FunctionPrograming.bean.Immutable.Rectangle;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;


@Slf4j
public class Example5 {
    private Random random = new Random();
    public void doSomething() {
        FunctionQueue<Rectangle> queue = Queue.empty();

        for (int i = 0; i < 100; i++) {
            queue = randomOperate(queue);
        }
    }

    private Rectangle generateRandomRectangle() {
        return new Rectangle(random.nextInt(100), random.nextInt(100));
    }

    private FunctionQueue<Rectangle> randomOperate(FunctionQueue<Rectangle> stack) {
        int opInt = new Random().nextInt(0,2) % 2;

        switch (opInt) {
            case 0:
                Rectangle rectangle = generateRandomRectangle();
                log.info("queue push {}", rectangle);

                return stack.offer(rectangle);
            default:
                log.info("pop element:{}", stack.top());

                return stack.take();
        }
    }
}
