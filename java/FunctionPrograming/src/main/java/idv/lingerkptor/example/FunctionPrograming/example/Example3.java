package idv.lingerkptor.example.FunctionPrograming.example;

import idv.lingerkptor.example.FunctionPrograming.bean.BeanCollection;
import idv.lingerkptor.example.FunctionPrograming.bean.mutable.Rectangle;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class Example3 {

    public void doSomething() {
        List<Rectangle> originList = BeanCollection.getRectangles();
        for (Rectangle rectangle : originList) {
            log.info("Rectangle {width: {}, height: {}} class: {}",
                    rectangle.getWidth(), rectangle.getHeight(),
                    rectangle.getClass().getSimpleName());
        }

        for (Rectangle rectangle : copyRectangleList(originList)) {
            log.info("Rectangle {width: {}, height: {}} class: {}",
                    rectangle.getWidth(), rectangle.getHeight(),
                    rectangle.getClass().getSimpleName());
        }
    }

    public synchronized List<Rectangle> copyRectangleList(List<Rectangle> originList) {
        return originList
                .stream()
                .map(Rectangle::new)
                .toList();
    }
}
