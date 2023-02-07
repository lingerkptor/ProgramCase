package idv.lingerkptor.example.FunctionPrograming.bean;


import idv.lingerkptor.example.FunctionPrograming.bean.mutable.Rectangle;
import idv.lingerkptor.example.FunctionPrograming.bean.mutable.Square;

import java.util.ArrayList;
import java.util.List;

public class BeanCollection {
    public static List<Rectangle> getRectangles() {
        return new ArrayList<>(List.of(
                new Rectangle(4, 34),
                new Rectangle(4, 35),
                new Rectangle(4, 33),
                new Rectangle(1, 34),
                new Rectangle(2, 32),
                new Square(1),
                new Rectangle(4, 34),
                new Square(2),
                new Rectangle(4, 343),
                new Square(4),
                new Rectangle(4, 34),
                new Square(5)));
    }
}
