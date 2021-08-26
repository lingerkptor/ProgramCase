package idv.lingerkptor.ConcurrentModificationException_Case;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;

/**
 * List.subList(fromIndex,toIndex);這個方法是這個sourceList的鏡像，如果移除其中的元素，就會影響原來的sourceList；
 * 同理，如果sourceList被修改也會影響subList．（多執行敘時要特別注意）
 * ConcurrentModificationException會發生在迴圈、地迴、多執行緒環境下修改集合．
 */
public  class Main {

    public static void main(String... args){
        List<String> stringList = new LinkedList<String>();
        stringList.add("testString1");
        stringList.add("testString2");
        stringList.add("testString3");
        stringList.add("testString4");
        stringList.add("testString5");
        stringList.add("testString6");
        stringList.add("testString7");
        Main main = new Main();
        main.testcase1(stringList);
        System.out.println();
        main.testcase2(stringList);
        System.out.println();
        main.testcase3(stringList);

    }

    /**
     * 修改來源的List後
     * subList發生的ConcurrentModificationException
     * @param origin 原始List
     */
    private void testcase1(List<String> origin) {
        System.out.println("testcase1 start");
        List<String> subList = origin.subList(2,5);
        origin.add("testString add String");//修改原List物件
        System.out.println("stringList size()=>"+origin.size());
//        System.out.flush();
        try{
            System.out.println("subList size()=>"+subList.size());
        }catch(ConcurrentModificationException e){
            System.err.println("testcase1 Exception: start");
            e.printStackTrace();
            System.err.println("呼叫subList.size()時發生的錯誤．");
            System.err.println("testcase1 Exception: end");
            System.err.println();
//            System.err.flush();
        }
        System.out.println("testcase1 end");
    }
    /**
     * 修改subList內的元素後
     * 迴圈發生的ConcurrentModificationException
     * @param origin 原始List
     */
    private void testcase2(List<String> origin) {
        System.out.println("testcase2 start");
        List<String> subList = origin.subList(2,5);
        System.out.println("stringList size()=>"+origin.size());
        System.out.println("subList size()=>"+subList.size());
        System.out.println();
        try{
            for(String str:origin){
                System.out.print(" "+str+" ");
                subList.remove(2);
            }
        }catch(ConcurrentModificationException e){
            System.err.println("testcase2 Exception: start");
            e.printStackTrace();
            System.err.println("呼叫subList.remove(2);後，迴圈發生的錯誤，");
            System.err.println("testcase2 Exception: end");
            System.err.println();
        }finally {
            System.out.println("\ntestcase2 end");
        }

    }

    /**
     * 迴圈內修改list導致發生的ConcurrentModificationException
     * @param origin 來源List
     */
    private void testcase3(List<String> origin){
        System.out.println("testcase3 start");
        System.out.println("stringList size()=>"+origin.size());
        System.out.println();
        try{
            for(String str:origin){
                System.out.print(" "+str+" ");
                origin.remove(2);
            }
        }catch(ConcurrentModificationException e){
            System.err.println("testcase3 Exception: start");
            e.printStackTrace();
            System.err.println("呼叫origin.remove(2);後，迴圈內發生的錯誤，");
            System.err.println("testcase3 Exception: end");
            System.err.println();
        }
        System.out.println("\ntestcase3 end");
    }


}
