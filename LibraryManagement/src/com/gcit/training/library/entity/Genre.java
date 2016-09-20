package com.gcit.training.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Genre implements BaseEntity{

	private int gerneId;
	private String genreName;
	
	private List<Book> books;
	
	public Genre(){
		super();
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}	

	public boolean addBook(Book e) {
		if(books == null) books = new ArrayList<Book>();
		return books.add(e);
	}

	public int getGerneId() {
		return gerneId;
	}
	
	public void setGerneId(int gerneId) {
		this.gerneId = gerneId;
	}
	
	public String getGenreName() {
		return genreName;
	}
	
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	@Override
	public String toString(){
		return this.getGenreName();
	}
	
}
