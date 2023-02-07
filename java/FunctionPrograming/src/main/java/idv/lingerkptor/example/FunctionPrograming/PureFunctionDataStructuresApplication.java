package idv.lingerkptor.example.FunctionPrograming;

import idv.lingerkptor.example.FunctionPrograming.example.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PureFunctionDataStructuresApplication {

    public static void main(String[] args) throws CloneNotSupportedException {

//        new Example1().doSomeThing();
//		new Example2().doSomething();
//        new Example3().doSomething();
//        new Example4().doSomething();
//        new Example5().doSomething();
        new PerformanceExample().doSomething();

//        Rectangle rectangle = new Rectangle(1, 2);
//        log.info("Rectangle width: {}, height: {}", rectangle.getWidth(), rectangle.getHeight());
//        Rectangle clone = rectangle.clone();
//        log.info("same obj ? {}",rectangle == clone);
//        log.info("Rectangle width: {}, height: {}", clone.getWidth(), clone.getHeight());
//		SpringApplication.run(PureFunctionDataStructuresApplication.class, args);
    }

}
