package com.gcit.training.library.entity;

public class BookCopies implements BaseEntity{
	
	private int noOfCopies;
	
	private Branch branch;
	private Book book;
	
	public BookCopies(){
		super();
	}
	
		
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public int getNoOfCopies() {
		return noOfCopies;
	}
	
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
}
