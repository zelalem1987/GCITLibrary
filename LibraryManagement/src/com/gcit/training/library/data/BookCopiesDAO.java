package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.entity.Book;
import com.gcit.training.library.entity.BookCopies;
import com.gcit.training.library.entity.BookLoan;

public class BookCopiesDAO extends BaseDAO<BookCopies>{

	public BookCopiesDAO(Connection conn) {
		
		super(conn);
	}
	
	private static final String SELECT_ALL = "select * from tbl_book_copies";
	private static final String SELECT_ONE = "select * from tbl_book_copies where bookId = ? and branchId = ?";
	private static final String INSERT = "insert into tbl_book_copies (bookId, branchId, noOfCopies) values (?,?,?)";
	private static final String DELETE = "delete from tbl_book_copies where bookId = ? and branchId = ?";
	private static final String UPDATE = "update tbl_book_copies set noOfCopies = ? where bookId = ? branchId = ?";
	private static final String TABLE_NAME = "tbl_book_copies";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_book_copies where bookId = ?";
	private static final String GET_COUNT = "select count(*) from tbl_book_copies";
	
	
	
	@Override
	public void create(BookCopies be) throws SQLException {
		
		saveToDB(INSERT, new Object[] {be.getBook().getBookId(), be.getBranch().getBranchId(), be.getNoOfCopies()});		
	}

	@Override
	public void update(BookCopies be) throws SQLException {

		saveToDB(UPDATE, new Object[]{ be.getNoOfCopies(), be.getBook().getBookId(), be.getBranch().getBranchId()} );
		
	}

	@Override
	public void delete(BookCopies be) throws SQLException {

		saveToDB(DELETE, new Object[]{be.getBook().getBookId(), be.getBranch().getBranchId()});
		
	}

	@Override
	public BookCopies read(int pk) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BookCopies readOne(BookCopies be) throws SQLException {

		return readFromDB(SELECT_ONE, new Object[]{be.getBook().getBookId(), be.getBranch().getBranchId()});
	}

	@Override
	public void save(BookCopies be) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookCopies> readResult(ResultSet rs) throws SQLException {

		List<BookCopies> list = new ArrayList<>();
		
		while(rs.next()){
			BookCopies bookCopies = new BookCopies();
			bookCopies.getBook().setBookId(rs.getInt("bookId"));
			bookCopies.getBranch().setBranchId(rs.getInt("branchId"));
			bookCopies.setNoOfCopies(rs.getInt("noOfCopies"));
			list.add(bookCopies);
		}
		
		return list;
	}

	@Override
	public BookCopies readbyName(String entityName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookCopies readbyId(int entityId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCopies> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {
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
	public Integer getCount() throws SQLException {
		
		return getCount(GET_COUNT, null);
	}

	@Override
	public Integer getCountByName(String searchString) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
