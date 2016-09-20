package com.gcit.training.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Author implements BaseEntity {
	
	private int authorId;
	private String authorName;
	
	private List<Book> books;
	
	public Author(){
		super();
	}
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public void addBook(Book book){
		if(books == null) books = new ArrayList<Book>();
		books.add(book);
	}
	
	@Override
	public String toString(){
		return this.getAuthorName();
	}
}
