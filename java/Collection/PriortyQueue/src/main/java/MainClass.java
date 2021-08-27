import java.util.PriorityQueue;

/**
 * Copyright ©, 2020 - 2021, lingerkptor
 * FileName: MainClass
 * Author:   lingerkptor
 * Date:     2021/4/4 下午 08:10
 * Description: PriorityQueue Example
 * History:
 * <author>    <time>  <version> <desc>
 * lingerkptor  2021/4/4 下午 08:10 0 create
 */
public class MainClass {
    /**
     * main method
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = "a", b = "b", c = "c";
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.offer(c);
        queue.offer(a);
        queue.offer(b);
        queue.offer(a);
        for (int i = 0; i < queue.size(); ) {
            System.out.println(queue.poll());
        }

        PriorityQueue<ComparableElement> comparableElements = new PriorityQueue<>();
        comparableElements.offer(new ComparableElement("element1",ComparableElement.Priority.First));
        comparableElements.offer(new ComparableElement("element2",ComparableElement.Priority.Second));
        comparableElements.offer(new ComparableElement("element3",ComparableElement.Priority.First));
        comparableElements.offer(new ComparableElement("element4",ComparableElement.Priority.Tried));
        comparableElements.offer(new ComparableElement("element5",ComparableElement.Priority.Second));
        for (int i = 0; i < comparableElements.size(); ) {
            System.out.println(comparableElements.poll().getName());
        }
    }
}
