package idv.lingerkptor.example.FunctionPrograming.bean.mutable;

public class Square extends Rectangle{
    public Square(int length) {
        super(length, length);
    }

    public void setLength(int length){
        super.setHeight(length);
        super.setWidth(length);
    }

    @Override
    public void setWidth(int width) {
        this.setLength(width);
    }

    @Override
    public void setHeight(int height) {
        this.setLength(height);
    }
}
