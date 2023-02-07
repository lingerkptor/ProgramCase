package idv.lingerkptor.example.FunctionPrograming.example;

import idv.lingerkptor.example.FunctionPrograming.bean.BeanCollection;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;


@Slf4j
public class Example2 {

    public void setWidthMustNotSetHeight() {
        BeanCollection.getRectangles().forEach(rectangle -> {
            int originalHeight = rectangle.getHeight();
            int changWidth = new Random().nextInt(100);
            rectangle.setWidth(changWidth);

            log.info("Rectangle height: {}, origin height: {}", rectangle.getHeight(), originalHeight);
        });
    }

}
