package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.entity.Book;

import sun.org.mozilla.javascript.internal.ObjArray;

public class BookDAO extends BaseDAO<Book>{

	public BookDAO(Connection conn) {
		
		super(conn);
	}

	private static final String SELECT_ALL = "select * from tbl_book";
	private static final String SELECT = "select * from tbl_book where bookId = ?";
	private static final String SELECT_ID_BY_TITLE = "select bookId from tbl_book where title = ?";
	private static final String SELECT_BOOKLIST = "SELECT tbl_book.bookId, tbl_book.title, tbl_author.authorName FROM tbl_book inner "
			+ "join tbl_author on tbl_book.authId = tbl_author.authorId";
	private static final String INSERT = "insert into tbl_book (title, pubId) values (?,?)";
	private static final String INSERT_IN_TO_BOOK_AUTHORS = "insert into tbl_book_authors (bookId, authorId) values (?,?)";
	private static final String INSERT_IN_TO_BOOK_GENRE = "insert into tbl_book_genres (genre_id, bookId) values (?,?)";
	private static final String DELETE = "delete from tbl_book where bookId = ?";
	private static final String UPDATE = "update tbl_book set title = ? where bookId = ?";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_book where title like ?";
	private static final String SELECT_ID_BY_NAME = "select * from tbl_book where title = ?";
	private static final String GET_COUNT = "select count(*) from tbl_book";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_book where title like ?";
	

	@Override
	public void create(Book be) throws SQLException {
		
		saveToDB(INSERT, new Object[] 
				{ be.getTitle(),  
						be.getPublish().getPublisherId()});		
		
	}
	
	public void createBookAuthorMapping(Book book) throws SQLException{
		saveToDB(INSERT_IN_TO_BOOK_AUTHORS, new Object[]{book.getBookId(), book.getAuthors().getAuthorId()});
	}
	
	public void createBookGenreMapping(Book book) throws SQLException{
		saveToDB(INSERT_IN_TO_BOOK_GENRE, new Object[]{book.getGenres().getGerneId(), book.getBookId()});
	}
	public int createAndGetId(Book be) throws SQLException { 

		return saveAndGetId(INSERT, new Object[] 
				{ be.getTitle(), 
						be.getPublish().getPublisherId()});		

	}

	

	@Override
	public void update(Book be) throws SQLException {
		
		saveToDB(UPDATE, new Object[]{be.getTitle(), be.getBookId()});
		
	}

	@Override
	public void delete(Book be) throws SQLException {

		saveToDB(DELETE, new Object[] {be.getBookId()});
		
	}

	@Override
	public Book read(int pk) throws SQLException {
		
		return readFromDB(SELECT , new Object[] {pk});
	}

	@Override
	public void save(Book be) throws SQLException {

		readAllFromDB(SELECT_ALL, new Object[]{});
		
	}

	@Override
	public List<Book> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {

		setPageNo(pageNo);
		setPageSize(pageSize);
		
		if(searchString != null && !"".equals(searchString)){
			
			searchString = "%" + searchString + "%";
			
			return readAllFromDB(SELECT_ALL_SEARCH, new Object[]{searchString});
		}	else{
				return readAllFromDB(SELECT_ALL, new Object[]{});
		}
	}

	@Override
	public List<Book> readResult(ResultSet rs) throws SQLException {
		
		List<Book> list = new ArrayList<>();
		
		while(rs.next()){
			Book book = new Book();
			book.setBookId(rs.getInt("bookId"));
			book.setTitle(rs.getString("title"));
			list.add(book);
		}
		
		return list;
	}

	@Override
	public Book readbyName(String title) throws SQLException {
		
		return readFromDB(SELECT_ID_BY_NAME, new Object[] {title});
	}
	
	@Override
	public List<Book> showBooksAuthorsPublishers() throws SQLException{
		 
		return readAllFromDB("SELECT tb.title, ta.authorName, tp.publisherName "
				+ "From tbl_book tb "
				+ "Join tbl_book_authors tba on tb.bookId = tba.bookId "
				+ "Join tbl_author ta on ta.authorId = tba.authorId "
				+ "Join tbl_publisher tp on tb.pubId = tp.publisherId ", new Object[] {});
	}

	@Override
	public Book readbyId(int entityId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCount() throws SQLException {

		return getCount(GET_COUNT, null);
	}

	@Override
	public Integer getCountByName(String searchString) throws SQLException {

if(searchString != null && !"".equals(searchString)){
			
			searchString = "%" + searchString + "%";
			
			return getCount(GET_COUNT_BY_NAME,new Object[]{searchString});
		}	
		else{
			return getCount(GET_COUNT, null);
		}
	}

}
