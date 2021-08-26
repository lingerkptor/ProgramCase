```java
package idv.lingerkptor.GsonExample.bean;

import java.util.List;

/**
 * 用來作為範例的物件
 */
public class BeanOne {

	private String stringOne = null, stringTwo = null;
	private int numberInt = 0;;
	private Integer numberInteger = null;
	private boolean booleanValue = false;
	private Boolean booleanValueTwo = null;
	private List<String> stringList = null;

	public BeanOne(String one, String two, int num1, Integer num2, boolean boolOne, Boolean boolTwo,
			List<String> list) {
		this.stringOne = one;
		this.stringTwo = two;
		this.numberInt = num1;
		this.numberInteger = num2;
		this.booleanValue = boolOne;
		this.booleanValueTwo = boolTwo;
		this.stringList = list;
	}

	/**
	 * @return the stringOne
	 */
	public String getStringOne() {
		return stringOne;
	}

	/**
	 * @return the stringTwo
	 */
	public String getStringTwo() {
		return stringTwo;
	}

	/**
	 * @return the numberInt
	 */
	public int getNumberInt() {
		return numberInt;
	}

	/**
	 * @return the numberInteger
	 */
	public Integer getNumberInteger() {
		return numberInteger;
	}

	/**
	 * @return the booleanValue
	 */
	public boolean isBooleanValue() {
		return booleanValue;
	}

	/**
	 * @return the booleanValueTwo
	 */
	public Boolean getBooleanValueTwo() {
		return booleanValueTwo;
	}

	/**
	 * @return the stringList
	 */
	public List<String> getStringList() {
		return stringList;
	}
}
```

1. case 1 <br/><t>
將List寫入Json
```java
    List<String> stringList1 = new LinkedList<String>();
    stringList1.add("listElement1");
    stringList1.add("listElement2");
    stringList1.add("listElement3");
    stringList1.add("listElement4");
    File file = new File("./test/List.json");
    try {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        gson.toJson(stringList1, writer);
        writer.flush();
        writer.close();
    } catch (JsonIOException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
```
2. case 2<br/><t>
讀取json 轉成 List物件
```java
    File file = new File(getClass().getClassLoader().getResource("test/List.json").toURI());
    FileReader reader;
    try {
        reader = new FileReader(file);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return;
    }
    Type type = new TypeToken<List<String>>() {
    }.getType();
    List<String> strList = gson.fromJson(reader, type);
```

4. case 3<br/><t>
讀取json 轉成 物件
```java
    File file = new File(getClass().getClassLoader().getResource("test/BeanOne.json").toURI());
    FileReader reader;
    try {
        reader = new FileReader(file);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return;
    }
    BeanOne bean = gson.fromJson(reader, BeanOne.class);
    try {
        reader.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
```
5. case 4<br/><t>
將物件寫入成json
```java
    List<String> stringList1 = new LinkedList<String>();
    stringList1.add("listElement1");
    stringList1.add("listElement2");
    stringList1.add("listElement3");
    stringList1.add("listElement4");
    BeanOne beanOne = new BeanOne("StringOne", "StringTwo", 0, Integer.valueOf(112345), false,
            Boolean.valueOf(true), stringList1);

    File file = new File("./test/BeanOne.json");
    try {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        gson.toJson(beanOne, writer);
        writer.flush();
        writer.close();
    } catch (JsonIOException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
```