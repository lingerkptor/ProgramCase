package idv.lingerkptor.GsonExample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import idv.lingerkptor.GsonExample.bean.BeanOne;

public class Main {
	private Gson gson = new Gson();

	public static void main(String[] args) {
		Main main = new Main();
		main.writeObjectToJson();
		main.writeListToJson();
		main.readObjectFromJson();
		main.readListFromJson();
	}

	private void readListFromJson() {
		File file = new File("./test/List.json");
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
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("List read Start.");
		System.out.println("List=>{");
		for (String str : strList) {
			System.out.println(str + ", ");
		}
		System.out.println("}");
		System.out.println("List read End.");

	}

	private void readObjectFromJson() {
		File file = new File("./test/BeanOne.json");
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
		System.out.println("BeanOne read Start.");
		System.out.println("StringOne=>" + bean.getStringOne());
		System.out.println("StringTwo=>" + bean.getStringTwo());
		System.out.println("intValue=>" + bean.getNumberInt());
		System.out.println("IntegerValue=>" + bean.getNumberInteger());
		System.out.println("boolean =>" + bean.isBooleanValue());
		System.out.println("BooleanValue=>" + bean.getBooleanValueTwo());
		StringBuilder strBuilder = new StringBuilder("List=>{");
		for (String str : bean.getStringList()) {
			strBuilder.append(str);
			strBuilder.append(", ");
		}
		strBuilder.append("}");
		System.out.println(strBuilder.toString());
		System.out.println("BeanOne End.");
	}

	private void writeListToJson() {

		List<String> stringList1 = new LinkedList<String>();
		stringList1.add("listElement1");
		stringList1.add("listElement2");
		stringList1.add("listElement3");
		stringList1.add("listElement4");

		File file = new File("./test/List.json");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			gson.toJson(stringList1, writer);
			writer.flush();
			writer.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeObjectToJson() {
		List<String> stringList1 = new LinkedList<String>();
		stringList1.add("listElement1");
		stringList1.add("listElement2");
		stringList1.add("listElement3");
		stringList1.add("listElement4");
		BeanOne beanOne = new BeanOne("StringOne", "StringTwo", 0, Integer.valueOf(112345), false,
				Boolean.valueOf(true), stringList1);
		File file = new File("./test/BeanOne.json");
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			gson.toJson(beanOne, writer);
			writer.flush();
			writer.close();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
