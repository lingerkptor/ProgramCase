package idv.lingerkptor.example.FunctionPrograming.Collections;


public interface FunctionStack<Element> {
    public FunctionStack<Element> pop();

    public FunctionStack<Element> push(Element element);

    public Element top();

    public boolean isEmpty();
    public int size();
}
