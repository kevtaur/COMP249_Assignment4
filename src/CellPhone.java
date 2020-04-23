// -----------------------------------------------------
// Assignment 4
// Part 1: ArrayList & File I/O
// Written by: Kevin Ve 40032669
// -----------------------------------------------------

import java.util.Scanner;

/**CellPhone is a an instance class used to handle any data associated with a cellphone.
 * @author KevinVe
 */
public class CellPhone {
	//attributes
	private long serialNum;	//unique ID
	private String brand;
	private int year;
	private double price;
	
	public long getSerialNum() {return serialNum;}
	public void setSerialNum(long serialNum) {this.serialNum = serialNum;}
	
	public String getBrand() {return brand;}
	public void setBrand(String brand) {this.brand = brand;}
	
	public int getYear() {return year;}
	public void setYear(int year) {this.year = year;}
	
	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;}
	
	/**Parameterized Constructor for every attribute. serialNum not assumed to be unique here.
	 * @param serialNum
	 * @param brand
	 * @param year
	 * @param price
	 */
	public CellPhone(long serialNum, String brand, int year, double price) {
		this.serialNum = serialNum;
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
	
	
	/**Copy Constructor that creates a deep copy of a given cellphone. new CellPhone must have its own serialNum
	 * @param cell
	 * @param serialNum
	 */
	public CellPhone(CellPhone cell, long serialNum) {
		this.serialNum = serialNum;
		this.brand = cell.brand;
		this.year = cell.year;
		this.price = cell.price;
	}
	
	/**clone method asks user for new serialNum, creates a new CellPhone and returns it.
	 * serialNum not assumed to be unique or not
	 */
	public CellPhone clone() {
		Scanner keyIn = new Scanner(System.in);
		System.out.print("Please enter a new unique serial number: ");
		return new CellPhone(this, keyIn.nextLong());
	}
	
	/**equals method returns true if all of member attributes are equal in value. Otherwise false.
	 * @param obj object to be compared with
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) //if object is self
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		
		CellPhone other = (CellPhone) obj;
		if (!brand.equals(other.brand) || price != other.price || year != other.year) //if other attribute values equal this objects attribute values
			return false;
		
		return true;
	}
	
	/**toString method returns a human readable string with all of this CellPhone's member attributes
	 * @return human readable string
	 */
	@Override
	public String toString() {
		return "["+String.format("%1$-7s", serialNum)+"] "+year+" "+String.format("%1$-13s", brand)+"($"+String.format("%1$-6s", price).replace(' ', '0')+")";
	}
}
