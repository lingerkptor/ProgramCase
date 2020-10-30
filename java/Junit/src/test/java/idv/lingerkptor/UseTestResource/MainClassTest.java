package idv.lingerkptor.UseTestResource;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class MainClassTest {
    MainClass obj = new MainClass();
    Properties props = new Properties();


    @Test
    public void testmod10() throws URISyntaxException {
        URL testResourceFile = getClass().getClassLoader().getResource("test.properties");
        URL dir = getClass().getResource(".");//如果只用這個定義下會在這個類別的package下尋找
        System.out.println(dir.toString());
        System.out.println(testResourceFile.toString());
        File testfile = new File(testResourceFile.toURI());

        try {
            props.load(new FileReader(testfile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("15mod10", 5, obj.mod10(15));
        Assert.assertEquals("5mod10", 5, obj.mod10(Integer.parseInt((String) props.get("num1"))));
        Assert.assertEquals("10mod10", 0, obj.mod10(Integer.parseInt((String) props.get("num2"))));
        Assert.assertEquals("15mod10", 5, obj.mod10(Integer.parseInt((String) props.get("num3"))));
        Assert.assertEquals("16mod10", 6, obj.mod10(Integer.parseInt((String) props.get("num4"))));


    }

    @Test
    public void testsplitdot() throws URISyntaxException {
        URL testResourceFile = getClass().getClassLoader().getResource("test.properties");
        File testfile = new File(testResourceFile.toURI());
        try {
            props.load(new FileReader(testfile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals("splitdot", 3, obj.splitdot((String) props.get("str1")).length);

        Assert.assertEquals("splitdot", 5, obj.splitdot((String) props.get("str2")).length);

    }

}
