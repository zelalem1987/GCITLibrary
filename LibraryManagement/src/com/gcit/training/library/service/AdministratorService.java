package com.gcit.training.library.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import com.gcit.training.library.data.AuthorDAO;
import com.gcit.training.library.data.BookDAO;
import com.gcit.training.library.data.BookLoanDAO;
import com.gcit.training.library.data.BorrowerDAO;
import com.gcit.training.library.data.ConnectionUtils;
import com.gcit.training.library.data.GenreDAO;
import com.gcit.training.library.data.PublisherDAO;
import com.gcit.training.library.entity.Author;
import com.gcit.training.library.entity.Book;
import com.gcit.training.library.entity.BookLoan;
import com.gcit.training.library.entity.Borrower;
import com.gcit.training.library.entity.Genre;
import com.gcit.training.library.entity.Publisher;
import com.gcit.training.library.exceptions.LibraryExceptionCustom;

public class AdministratorService implements Serializable{

	private static final long serialVersionUID = 3005381009709748732L;

	private static AdministratorService instance = null;

	private AdministratorService(){

	}

	public static AdministratorService getInstance(){
		if(instance == null){
			synchronized (AdministratorService.class) {
				if(instance == null){
					instance = new AdministratorService();
				}				
			}
		}

		return instance;
	}
	
	/*
	 * Add entry to entities
	 */

	public void addAuthor(Author author) throws Exception{

		Connection conn = ConnectionUtils.getConnection();

		AuthorDAO authorDao = new AuthorDAO(conn);

		//		Business logic
		if(author == null || author.getAuthorName().trim().length() == 0){
			throw new LibraryExceptionCustom("Author cannot be null or blank");
		}else if(author.getAuthorName().length() > 45){
			throw new LibraryExceptionCustom("Author names cannot be more than 45 characters");
		}

		try{
			authorDao.create(author);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			throw e;
		}
	}
	
	public void addBorrower(Borrower borrower) throws Exception{

		Connection conn = ConnectionUtils.getConnection();

		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);

		//		Business logic
		if(borrower == null || borrower.getBorrowerName().trim().length() == 0){
			throw new LibraryExceptionCustom("Borrower name cannot be null or blank");
		}else if(borrower.getBorrowerName().length() > 45){
			throw new LibraryExceptionCustom("Borrower names cannot be more than 45 characters");
		}

		try{
			borrowerDAO.create(borrower);
			conn.commit();
		}catch(Exception e){
			conn.rollback();
			throw e;
		}
	}

	public void addPublisher(Publisher publisher) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();		
		PublisherDAO publisherDao = new PublisherDAO(conn);
		
//		TODO: add exceptions for null value of each column.
		
//		if(publisher == null || publisher.getPublisherName().trim().length() == 0){
//			throw new LibraryExceptionCustom("Author cannot be null or blank");
//		}else if(publisher.getPublisherName().length() > 45){
//			throw new LibraryExceptionCustom("Author names cannot be more than 45 characters");
//		}
		
		try{
			publisherDao.create(publisher);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}

	}

	public void addGenre(Genre genre) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		
		GenreDAO genreDao = new GenreDAO(conn);
		
		try{
			genreDao.create(genre);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}

	}	
	
	public void addBook(Book book) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		
		try{
			if(book != null){
				if(book.getTitle() == null || book.getTitle().length() < 0){
					conn.rollback();
					throw new Exception("Book title cannot be empty!");
				} else if(book.getTitle().length() > 45){
					conn.rollback();
					throw new Exception("Book title length cannot exceed 45 characters!");
				}
				
				BookDAO bookDAO = new BookDAO(conn);
				
				int lastBookAddedId= bookDAO.createAndGetId(book);
				
				bookDAO.create(book);
				book.setBookId(lastBookAddedId);
				bookDAO.createBookAuthorMapping(book);
				bookDAO.createBookGenreMapping(book);
				conn.commit();
			}
			else throw new Exception("No book title provided!");
		}
		catch(SQLException ex){
			conn.rollback();
		}
		finally {
			if(conn != null) conn.close();
		}
	}
	
	public void addBookLoan(BookLoan bookLoan) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		
		try{
			if(bookLoan != null){
//				if(book.getTitle() == null || book.getTitle().length() < 0){
//					conn.rollback();
//					throw new Exception("Book title cannot be empty!");
//				} else if(book.getTitle().length() > 45){
//					conn.rollback();
//					throw new Exception("Book title length cannot exceed 45 characters!");
//				}
				
				BookLoanDAO bookLoanDao = new BookLoanDAO(conn);				
				
				bookLoanDao.create(bookLoan);
				conn.commit();
			}
			else throw new Exception("No book title provided!");
		}
		catch(SQLException ex){
			conn.rollback();
		}
		finally {
			if(conn != null) conn.close();
		}
	}
	
	/*
	 * Update Entities 
	 */
	
	public void updateAuthor(Author author) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		
		AuthorDAO authorDAO = new AuthorDAO(conn);
		
		try{
			authorDAO.update(author);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}
	}
	
	public void updateBook(Book book) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		
		BookDAO bookDAO = new BookDAO(conn);
		
		try{
			bookDAO.update(book);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}
	}
	
	public void updateBorrower(Borrower borrower) throws Exception{

		Connection conn = ConnectionUtils.getConnection();

		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);

		try{
			borrowerDAO.update(borrower);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}
	}

	public void updateGenre(Genre genre) throws Exception{

		Connection conn = ConnectionUtils.getConnection();

		GenreDAO genreDAO = new GenreDAO(conn);

		try{
			genreDAO.update(genre);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}
	}
	
	/*
	 * Delete Entities
	 */
	
	public void deleteAuthor(Author author) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		
		AuthorDAO authorDAO = new AuthorDAO(conn);
		
		try{
			authorDAO.delete(author);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}
	}
	
	public void deleteForeignFromBookAuthor(Author author) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		AuthorDAO authorDAO = new AuthorDAO(conn);
		
		try{
			authorDAO.deleteFromBookAuthor(author);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}
	}
	
	
	/*
	 * Get all columns of Entities
	 */
	
	public List<Author> getAllAuthors(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();	
		AuthorDAO authorDAO = new AuthorDAO(conn);		
		return authorDAO.readall(pageNo, 10, searchString);
	}
	
	public List<BookLoan> getAllBookLoans(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		BookLoanDAO boookLoanDAO = new BookLoanDAO(conn);
		return boookLoanDAO.readall(pageNo, 10, searchString);
	}
	
	public List<Borrower> getAllBorrowers(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();	
		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);		
		return borrowerDAO.readall(pageNo, 10, searchString);
	}
	
	public List<Publisher> getAllPublishers(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();		
		PublisherDAO publisherDAO = new PublisherDAO(conn);		
		return publisherDAO.readall(pageNo, 10, searchString);
	}
	public List<Genre> getAllGenre(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();	
		GenreDAO genreDAO = new GenreDAO(conn);	
		return genreDAO.readall(pageNo, 10, searchString);
	}
	
	public List<Book> getAllBook(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();	
		BookDAO bookDAO = new BookDAO(conn);
		return bookDAO.readall(pageNo, 10, searchString);
	}
	
	/*
	 * Get Entities by their Ids
	 */
	
	public Author getAuthorById(Integer authorId) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		AuthorDAO authorDao = new AuthorDAO(conn);
		return authorDao.read(authorId);
	}
	
	public BookLoan getBookLoanById(BookLoan borrowerCardNo) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		BookLoanDAO bookLoanDAO = new BookLoanDAO(conn);
		return bookLoanDAO.readOne(borrowerCardNo);
	}
	
	public Borrower getBorrowerById(Integer borrowerCardNo) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
		return borrowerDAO.read(borrowerCardNo);
	}
	
	public Publisher getPubliserById(Integer publisherId) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		PublisherDAO publisherDAO = new PublisherDAO(conn);
		return publisherDAO.read(publisherId);
	}
	
	public Genre getGenreById(Integer genreId) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		GenreDAO genreDAO = new GenreDAO(conn);	
		return genreDAO.read(genreId);
	}
	
	public Book getBookById(Integer bookId) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		BookDAO bookDAO = new BookDAO(conn);
		return bookDAO.read(bookId);
	}
	
	/*
	 * Get Entity counts by row
	 */
	
	public Integer getAuthorCount() throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		AuthorDAO authorDAO = new AuthorDAO(conn);
		return authorDAO.getCount();
	}
	
	public Integer getBorrowerCount() throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
		return borrowerDAO.getCount();
	}
	
	public Integer getGenreCount() throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		GenreDAO genreDAO = new GenreDAO(conn);
		return genreDAO.getCount();
	}
	
	public Integer getPublisherCount() throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		PublisherDAO publisherDAO = new PublisherDAO(conn);
		return publisherDAO.getCount();
	}
	
	public Integer getBookCount() throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		BookDAO bookDAO = new BookDAO(conn);
		return bookDAO.getCount();
	}
	
	/*
	 * Get Count of Entities by names.
	 */
	
	public Integer getAuthorsCountByName(String searchString) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		AuthorDAO authDao = new AuthorDAO(conn);
		
		if(searchString != null && !searchString.equals("")){
			searchString="%"+searchString +"%";
		}
		return authDao.getCountByName(searchString);
	}
	
	public Integer getLoanersCountByName(String searchString) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		BookLoanDAO bookLoanDao = new BookLoanDAO(conn);
		
		if(searchString != null && !searchString.equals("")){
			searchString="%"+searchString +"%";
		}
		return bookLoanDao.getCountByName(searchString);
	}
	
	public Integer getBorrowerCountByName(String searchString) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		BorrowerDAO borrowerDAO = new BorrowerDAO(conn);
		
		if(searchString != null && !searchString.equals("")){
			searchString="%"+searchString +"%";
		}
		return borrowerDAO.getCountByName(searchString);
	}

	public Integer getPublisherCountByName(String searchString) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		PublisherDAO publisherDAO = new PublisherDAO(conn);
		
		if(searchString != null && !searchString.equals("")){
			searchString="%"+searchString +"%";
		}
		return publisherDAO.getCountByName(searchString);
	}
	
	public Integer getGenreCountByName(String searchString) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		GenreDAO genreDAO = new GenreDAO(conn);
		
		if(searchString != null && !searchString.equals("")){
			searchString="%"+searchString +"%";
		}
		return genreDAO.getCountByName(searchString);
	}
	
	public Integer getBookCountByName(String searchString) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		BookDAO bookDAO = new BookDAO(conn);
		
		if(searchString != null && !searchString.equals("")){
			searchString="%"+searchString +"%";
		}
		return bookDAO.getCountByName(searchString);
	}
	
	
}
