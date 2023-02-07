package idv.lingerkptor.example.FunctionPrograming.Collections;

public interface FunctionQueue<Element> {
    public FunctionQueue<Element> offer(Element element);

    public FunctionQueue<Element> take();

    public Element top();

    public boolean isEmpty();
}
