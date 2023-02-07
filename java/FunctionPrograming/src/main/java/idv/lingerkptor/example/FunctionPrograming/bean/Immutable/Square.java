package idv.lingerkptor.example.FunctionPrograming.bean.Immutable;


public class Square extends Rectangle {

    public Square(int length) {
        super(length, length);
    }

    public Square setLength(int length) {
        return new Square(length);
    }

    @Override
    public Rectangle setWidth(int width) {
        return this.setLength(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        return this.setLength(height);
    }
}
