package com.gcit.training.library.entity;

import java.util.Date;

public class BookLoan implements BaseEntity{

	private Book book;
	private Branch branch;
	private Borrower borrower;
	
	private Date dateOut;
	private Date dueDate;
	private Date dateIn;
	
	public BookLoan(){
		super();
	}
	

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date newDueDate) {
		this.dueDate = newDueDate;
	}
	
	public Date getDateIn() {
		return dateIn;
	}
	
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	
	public Date getDateOut() {
		return dateOut;
	}
	
	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}
}
