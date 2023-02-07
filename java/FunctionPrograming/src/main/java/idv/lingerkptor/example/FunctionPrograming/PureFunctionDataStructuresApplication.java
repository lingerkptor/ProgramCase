package idv.lingerkptor.example.FunctionPrograming;

import idv.lingerkptor.example.FunctionPrograming.example.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PureFunctionDataStructuresApplication {

    public static void main(String[] args) throws CloneNotSupportedException {

//        new Example1().identifyCrisis();
//		new Example2().setWidthMustNotSetHeight();
//        new Example3().defensiveCopying();
//        new Example4().doSomething();
//        new Example5().doSomething();
        PerformanceExample example = new PerformanceExample();
        example.casQueueBenchmark();
//        example.lockQueueBenchmark();
//        example.linkedBlockingQueueBenchmark();
//        example.betterCasQueueBenchmark();
//        example.betterLockQueueBenchmark();

    }

}
