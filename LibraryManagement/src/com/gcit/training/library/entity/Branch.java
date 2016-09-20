package com.gcit.training.library.entity;

public class Branch implements BaseEntity{

	private int branchId;
	private String branchName;
	private String branchAddress;
	
	public Branch(){
		super();
	}
	

	public int getBranchId() {
		return branchId;
	}
	
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getBranchAddress() {
		return branchAddress;
	}
	
	public void setBranchAddress(String address) {
		branchAddress = address;
	}
	
	@Override
	public String toString(){
		return this.getBranchAddress() + ", " + this.getBranchName();
	}
}
