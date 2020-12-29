package idv.lingerkptor.Regex;

public class Main {
    public static void main(String... args) {

        // issue #8 start
        /**
         * \\d =>數字 <br/>
         * \\D =>非數字 <br/>
         * {n} =>次數<br/>
         */
        String aaa = "123456aaa123456";
        System.out.println(aaa + " 符合 \\d*a{3}\\d* 規則 " + aaa.matches("\\d*a{3}\\d*"));
        //match 3number + 4char +5number pattern
        String str = "333dgwe92153";
        System.out.println("333dgwe92153 match \\d{3}\\D{4}\\d{5} " + str.matches("\\d{3}\\D{4}\\d{5}"));
        // issue #8 end
        System.out.println("A 八進位的ASCII為\\x41" + "A".matches("\\x41"));


    }
}
