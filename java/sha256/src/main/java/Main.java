import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            File test = new File("./1/test");
            FileInputStream input = new FileInputStream(test);
            byte[] buff = new byte[128];
            for (int content = 0; (content = input.read(buff)) > -1; ) {
                md.update(buff, 0, content);
            }
            input.close();
            StringBuilder sb = new StringBuilder();
            for (byte code : md.digest()) {
                sb.append(code);
            }

            MessageDigest md2 = MessageDigest.getInstance("SHA-256");
            File test2 = new File("./2/test2");
            FileInputStream input2 = new FileInputStream(test2);
            byte[] buff2 = new byte[128];
            for (int content = 0; (content = input2.read(buff2)) > -1; ) {
                md.update(buff2, 0, content);
            }
            input2.close();
            StringBuilder sb2 = new StringBuilder();
            for (byte code : md2.digest()) {
                sb2.append(code);
            }

            MessageDigest md3 = MessageDigest.getInstance("SHA-256");
            File test3 = new File("./2/test");
            FileInputStream input3 = new FileInputStream(test3);
            byte[] buff3 = new byte[128];
            for (int content = 0; (content = input3.read(buff3)) > -1; ) {
                md3.update(buff3, 0, content);
            }
            input3.close();
            StringBuilder sb3 = new StringBuilder();
            for (byte code : md3.digest()) {
                sb3.append(code);
            }
            //原始檔案
            System.out.println("1/test sha-256=>" + sb.toString());
            //檔名一改sha-256 hashcode就會變
            System.out.println("2/test2 sha-256=>" + sb2.toString());
            //原始檔案複製黨
            System.out.println("2/test sha-256=>" + sb3.toString());
        } catch (NoSuchAlgorithmException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
