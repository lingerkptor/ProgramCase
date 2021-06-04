/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: ComparableElement
 * Author:   lingerkptor
 * Date:     2021/4/4 下午 08:04
 * Description: 實作Comparable的元素類別
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/4 下午 08:04 0 create
 */
public class ComparableElement implements Comparable<ComparableElement> {


    public enum Priority {
        First(0), Second(1), Tried(3);

        private int value;

        Priority(int i) {
            this.value = i;
        }

    }

    private Priority priority;
    private String name;

    public ComparableElement(String elementName, Priority priority) {
        this.name = elementName;
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(ComparableElement o) {
//        System.out.println(this.priority + " - " + o.priority + " = " + (this.getPriority().getValue() - o.priority.getValue()));
        return this.getPriority().value - o.priority.value;
    }

}
