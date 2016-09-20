package com.gcit.training.library.service;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gcit.training.library.entity.Book;
import com.gcit.training.library.entity.BookCopies;
import com.gcit.training.library.entity.BookLoan;
import com.gcit.training.library.entity.Borrower;
import com.gcit.training.library.data.AuthorDAO;
import com.gcit.training.library.data.BookCopiesDAO;
import com.gcit.training.library.data.BookDAO;
import com.gcit.training.library.data.BookLoanDAO;
import com.gcit.training.library.data.BorrowerDAO;
import com.gcit.training.library.data.BranchDAO;
import com.gcit.training.library.data.ConnectionUtils;
import com.gcit.training.library.data.GenreDAO;
import com.gcit.training.library.entity.Author;
import com.gcit.training.library.entity.Branch;
import com.gcit.training.library.entity.Genre;

public class BorrowerService implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6116817573730927282L;
	
	private static BorrowerService instance = null;
	
	private AuthorDAO authorDAO;
	private BranchDAO branchDAO;
	private GenreDAO genreDAO;
	private Connection conn = null;
	protected static List<Author> list = new ArrayList<Author>();
	protected ResultSet rst = null;
	protected Scanner scan = new Scanner(System.in);
	static BorrowerService b = new BorrowerService();
	
	public static BorrowerService getInstance(){
		if(instance == null){
			synchronized (BorrowerService.class) {
				if(instance == null){
					instance = new BorrowerService();
				}
			}
		}
		
		return instance;
	}

	public BorrowerService(){
		try {
			conn = ConnectionUtils.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		authorDAO = new AuthorDAO(conn);
		branchDAO = new BranchDAO(conn);
		genreDAO = new GenreDAO(conn);
	}
	
	public List<Book> showBooks() throws Exception{
		
		List<Book> list = new ArrayList<>();
		
		conn = ConnectionUtils.getConnection();
		
		BookDAO bookDao = new BookDAO(conn);
		
		try{
//			list = bookDao.showBooksAuthorsPublishers();
			list = bookDao.readall(null, null, null);
		} catch (Exception e) {
			throw e;
		}
		
		return list;
	}

	public List<Author> seeAuthors() throws SQLException{

		List<Author> authlist = new ArrayList<Author>();
		try {

			Author auth = authorDAO.read(1);
			authlist = authorDAO.readall(null, null, null);
			System.out.println(auth);
			System.out.println(authlist);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
		return authlist;

	}

	public List<Branch> seeBranches() throws SQLException{

		List<Branch> branchlist = new ArrayList<Branch>();
		try {

			Branch branch = branchDAO.read(1);
			branchlist = branchDAO.readall(1, 10, null);
			System.out.println(branch);
			System.out.println(branchlist);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
		return branchlist;

	}

	public List<Genre> seeGenre() throws SQLException{

		List<Genre> genrelist = new ArrayList<Genre>();
		try {

			Genre genre = genreDAO.read(1);
			genrelist = genreDAO.readall(null, null, null);
			System.out.println(genre);
			System.out.println(genrelist);
			conn.commit();
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}
		return genrelist;

	}

	public void addAuthtor(Author author) throws Exception{

		try{
			authorDAO.create(author);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}


	public List<BookLoan> getAllBookLoans(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();
		BookLoanDAO boookLoanDAO = new BookLoanDAO(conn);
		return boookLoanDAO.readall(pageNo, 10, searchString);
	}
	
	public List<BookLoan> getBookLoans(int cardNo) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		BookLoanDAO boookLoanDAO = new BookLoanDAO(conn);
		BookLoan bookloan = new BookLoan();
		Borrower bor=new Borrower();
		bor.setBorrowerCardNo(cardNo);
		bookloan.setBorrower(bor);
		return boookLoanDAO.readByCardNo(bookloan);
	}

	public void addInTo() throws Exception {
		System.out.print("Give the author name: ");
		String name = null;		
		name = scan.nextLine();

		Author author = new Author();
		author.setAuthorName(name);
		b.addAuthtor(author);

		System.out.println(author);

		System.out.println("author " + name + " hass been added!");

	}

	public void updateInto() throws Exception {
		System.out.print("Please give author name you would like to edit: ");
		String name = null;		
		name = scan.nextLine();
		Author author = authorDAO.readbyName(name);


		System.out.println(author);

		int idNumber = 0;
		idNumber = author.getAuthorId();

		System.out.println(idNumber);

		System.out.print("Please give the new name you would like to edit to: ");
		name = scan.nextLine();


		Author newAuthor = new Author();
		newAuthor.setAuthorName(name); 
		newAuthor.getAuthorId();

		System.out.println(newAuthor);

		//		authorDAO.update(newAuthor.getAuthorName(), newAuthor.getAuthorId());
	}
	
	public void checkOutBook(Book book, Branch branch, Borrower borrower) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();

		try {
			BookLoan bookloan = new BookLoan();
			bookloan.setBook(book);
			bookloan.setBranch(branch);
			bookloan.setBorrower(borrower);
			BookLoanDAO bookLoanDao = new BookLoanDAO(conn);
			
			if(bookLoanDao.readOne(bookloan) != null){
				//add 7 days
				Date currentDueDate = bookLoanDao.readOne(bookloan).getDueDate();
				Calendar cal = Calendar.getInstance();  
				cal.setTime(currentDueDate);  
				cal.add(Calendar.DATE, 7); // add 10 days  
				Date newDueDate = cal.getTime();
				
				bookloan.setDueDate(newDueDate);
				bookLoanDao.update(bookloan);
				
				conn.commit();
			}
			//2)check there is at least one copy
			
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBook(book);
			bookCopies.setBranch(branch);
			BookCopiesDAO bcDao = new BookCopiesDAO(conn);
			int noOfCopies = bcDao.readOne(bookCopies).getNoOfCopies();
			if(noOfCopies <= 0) {
				conn.rollback();
				throw new Exception("Sorry, there are no more book copies available in this branch!");
			}
			
			//3) insert loan entry into loans
			
			DateFormat dateFormat = new SimpleDateFormat("MM-DD-YYYY HH:mm:ss");
			//Where do i get the date from?
			Date dateOut = new Date();
			dateFormat.format(dateOut);
			bookloan.setDateOut(dateOut);
			
			Date dueDate = new Date();
			dateFormat.format(dueDate);
			bookloan.setDateOut(dueDate);
			
			bookLoanDao.create(bookloan);
			
			//4)
			int updatedNoOfCopies = noOfCopies - 1;
			bookCopies.setNoOfCopies(updatedNoOfCopies);
			bookLoanDao.updateCopies(bookCopies);

			conn.commit();
		} catch(Exception e) {
			conn.rollback();
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public boolean returnBook(Book book, Borrower borrower, Branch branch) throws Exception {
		Connection conn = ConnectionUtils.getConnection();

		try {
			//1) delete from loan table
			
			BookLoan bookLoan = new BookLoan();
			bookLoan.setBook(book);
			bookLoan.setBorrower(borrower);
			bookLoan.setBranch(branch);
			BookLoanDAO bookloanDao = new BookLoanDAO(conn);
			bookloanDao.delete(bookLoan);
			
			//2) add book to branch and increment copies
			
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBook(book);
			bookCopies.setBranch(branch);
			BookCopiesDAO bookDAO = new BookCopiesDAO(conn);
			//int noOfCopies = bcDao.selectOne(bookCopies).getNumberOfCopies();
			int noOfCopies = 0;
			int updatedNoOfCopies;
			if(bookDAO.readOne(bookCopies) == null) {
				updatedNoOfCopies = noOfCopies + 1;
				bookCopies.setNoOfCopies(updatedNoOfCopies);
				bookDAO.create(bookCopies);
			} else {
				noOfCopies = bookDAO.readOne(bookCopies).getNoOfCopies();
				updatedNoOfCopies = noOfCopies + 1;
				bookCopies.setNoOfCopies(updatedNoOfCopies);
				bookDAO.update(bookCopies);
			}

			conn.commit();
			
		} catch(Exception e) {
			conn.rollback();
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
		return true;
	}
	
	public boolean checkCardValidity(Borrower borrower) throws Exception {
		Connection conn = ConnectionUtils.getConnection();

		try {

			if(new BorrowerDAO(conn).readOne(borrower) == null) {
				conn.rollback();
				throw new Exception("Card number does not exist!");
			}
			conn.commit();
			
		} catch(Exception e) {
			conn.rollback();
		} finally {
			if(conn != null) {
				conn.close();
			}
		}
		return true;
	}
	

}

