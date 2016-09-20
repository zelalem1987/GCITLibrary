package com.gcit.training.library.entity;


public class Book implements BaseEntity{

	private int bookId;
	private String title; 
	
	private Publisher publish = new Publisher();
	private Author authors = new Author();
	private Genre genres = new Genre();
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Publisher getPublish() {
		return publish;
	}
	public void setPublish(Publisher publish) {
		this.publish = publish;
	}
	public Author getAuthors() {
		return authors;
	}
	public void setAuthors(Author authors) {
		this.authors = authors;
	}
	public Genre getGenres() {
		return genres;
	}
	public void setGenres(Genre genres) {
		this.genres = genres;
	}

//	private List<Author> authors;
//	private List<Genre> genres;
	
	
}
