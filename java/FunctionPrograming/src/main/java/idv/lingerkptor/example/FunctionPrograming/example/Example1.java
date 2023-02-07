package idv.lingerkptor.example.FunctionPrograming.example;

import idv.lingerkptor.example.FunctionPrograming.bean.mutable.Rectangle;

import java.util.HashSet;
import java.util.Set;


public class Example1 {
    public void identifyCrisis(){
        Set<Rectangle> rectangleSet = new HashSet<>();
        Rectangle rectangle1 = new Rectangle(5,6);
        rectangleSet.add(rectangle1);

        rectangle1.setWidth(2);
        System.out.println("Set containObject? "+ rectangleSet.contains(rectangle1));

        rectangleSet.add(rectangle1);
        System.out.println("size: "+ rectangleSet.size());
        rectangleSet.remove(rectangle1);
        System.out.println("size: "+ rectangleSet.size());
    }
}
