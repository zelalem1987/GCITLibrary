package com.gcit.training.library.entity;

public class Borrower implements BaseEntity{

	private int borrowerCardNo;
	private String borrowerName;
	private String borrowerAddress;
	private String borrowerPhone;
	
	public Borrower(){
		super();
	}
	

	public int getBorrowerCardNo() {
		return borrowerCardNo;
	}
	
	public void setBorrowerCardNo(int cardNo) {
		this.borrowerCardNo = cardNo;
	}
	
	public String getBorrowerName() {
		return borrowerName;
	}
	
	public void setBorrowerName(String name) {
		this.borrowerName = name;
	}
	
	public String getBorrowerAddress() {
		return borrowerAddress;
	}
	
	public void setBorrowerAddress(String address) {
		this.borrowerAddress = address;
	}
	
	public String getBorrowerPhone() {
		return borrowerPhone;
	}
	
	public void setBorrowerPhone(String phone) {
		this.borrowerPhone = phone;
	}
	
	@Override
	public String toString(){
		return this.getBorrowerName() + ", " + this.getBorrowerAddress() + ", " + this.getBorrowerPhone();
	}
	
	
}
