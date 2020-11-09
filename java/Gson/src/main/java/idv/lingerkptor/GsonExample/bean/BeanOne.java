package idv.lingerkptor.GsonExample.bean;

import java.util.List;

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
