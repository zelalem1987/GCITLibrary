package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.entity.Author;

public class AuthorDAO extends BaseDAO<Author>{

	private static final String SELECT_ID_BY_NAME = "select * from tbl_author where authorName = ?";
	private static final String SELECT_ALL = "select * from tbl_author";
	private static final String SELECT = "select * from tbl_author where authorId = ?";
	private static final String DELETE = "delete from tbl_author where authorId = ?";
	private static final String UPDATE = "update tbl_author set authorName = (?) where authorId = (?)";
	private static final String INSERT = "insert into tbl_author(authorName) values (?)";
	private static final String GET_COUNT = "select count(*) from tbl_author";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_author where authorName like ?";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_author where authorName like ?";
	private static final String GET_AUTHOR_FOREIGN_KEY = "select tba.authorId from tbl_book_authors tba "
			+ "join tbl_author ta  on tba.authorId = ta.authorId"; 
	private static final String DELETE_FROM_BOOK_AUTHOR_TABLE = "delete from tbl_book_authors "
			+ "using tbl_book_authors inner join tbl_author on tbl_book_authors.authorId = tbl_author.authorId "
			+ "where (tbl_author.authorId = 1)";
	
	public AuthorDAO(Connection conn) {
		
		super(conn);
	}
	

	@Override
	public void create(Author be) throws SQLException {
		
		saveToDB(INSERT, new Object[] { be.getAuthorName() });		
	}

	@Override
	public void update(Author be) throws SQLException {
		
		saveToDB(UPDATE, new Object[] { be.getAuthorName(), be.getAuthorId() });		
	}

	@Override
	public void delete(Author be) throws SQLException {
		
		saveToDB(DELETE, new Object[] { be.getAuthorId() });		
	}

	@Override
	public Author read(int pk) throws SQLException {
				
		return readFromDB(SELECT, new Object[] {pk});
	}

	@Override
	public List<Author> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {
		
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
	public List<Author> readResult(ResultSet rs) throws SQLException {
		List<Author> list = new ArrayList<>();
		
		while(rs.next()){
			Author author = new Author();
			author.setAuthorId(rs.getInt("authorId"));
			author.setAuthorName(rs.getString("authorName"));
			list.add(author);
		}
		System.out.println("Author readResult");
		System.out.println("AAAAA " + list.get(0).getAuthorName());
		return list;
	}

	@Override
	public Author readbyName(String authorName) throws SQLException {
		
		return readFromDB(SELECT_ID_BY_NAME, new Object[] {authorName});
	}

	@Override
	public void save(Author be) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Author readbyId(int entityId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer getCount() throws SQLException{
		return getCount(GET_COUNT, null);
	}


	@Override
	public Integer getCountByName(String searchString) throws SQLException {
		
		if(searchString != null && !"".equals(searchString)){
			
			searchString = "%" + searchString + "%";
			
			return getCount(GET_COUNT_BY_NAME, new Object[]{searchString});
		}	
		else{
			return getCount(GET_COUNT, null);
		}
		
		
	}
	
	public boolean checkIfForeignKey(int pk) throws SQLException {
		Author author = new Author();
		author = readFromDB(GET_AUTHOR_FOREIGN_KEY, new Object[]{pk});
		if(author != null){
			return true;
		}
		else return false;
	}
	
	public void deleteFromBookAuthor(Author author) throws SQLException{
		
		saveToDB(DELETE_FROM_BOOK_AUTHOR_TABLE, new Object[] {author.getAuthorId()});
	}

}
