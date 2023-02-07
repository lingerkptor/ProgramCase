package idv.lingerkptor.example.FunctionPrograming.bean.mutable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Rectangle {
    private int width, height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(Rectangle rectangle) {
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
    }
}
