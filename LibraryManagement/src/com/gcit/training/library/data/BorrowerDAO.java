package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.entity.Author;
import com.gcit.training.library.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower>{

	private static final String SELECT_ID_BY_NAME = "SELECT * FROM tbl_borrower WHERE borrowerName = ?";
	private static final String SELECT_ALL = "SELECT * FROM tbl_borrower";
	private static final String SELECT = "SELECT * FROM tbl_borrower WHERE borrowerCardNo = ?";
	private static final String DELETE = "DELETE FROM tbl_borrower WHERE borrowerCardNo = ? ";
	private static final String UPDATE = "UPDATE tbl_borrower SET borrowerName = ?, borrowerAddress = ?, borrowerPhone = ? WHERE borrowerCardNo = ?";
	private static final String INSERT = "INSERT INTO tbl_borrower (borrowerName, borrowerAddress, borrowerPhone) VALUES (?, ?, ?)";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_borrower where borrowerName like ?";
	private static final String GET_COUNT = "select count(*) from tbl_borrower";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_borrower where borrowerName like ?";
	
	
	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Borrower be) throws SQLException {
		
		saveToDB(INSERT, 
				new Object[] {be.getBorrowerName(), be.getBorrowerAddress(), be.getBorrowerPhone()});
	}

	@Override
	public void update(Borrower be) throws SQLException {
		
		saveToDB(UPDATE, 
				new Object[] {be.getBorrowerName(), be.getBorrowerAddress(), be.getBorrowerPhone(), be.getBorrowerCardNo()});
	}

	@Override
	public void delete(Borrower be) throws SQLException {
		
		saveToDB(DELETE, new Object[] {be.getBorrowerCardNo()});
	}

	@Override
	public Borrower read(int pk) throws SQLException {
		
		return readFromDB(SELECT, new Object[] {pk});
	}
	
	public Borrower readOne(Borrower be) throws SQLException {
		
		List<Borrower> list = readAllFromDB(SELECT, new Object[]{be.getBorrowerCardNo()});
		
		if (list != null && list.size() > 0)
			return (Borrower) list.get(0);
		else
			return null;
	}

	@Override
	public List<Borrower> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {
		
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
	public void save(Borrower be) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Borrower> readResult(ResultSet rs) throws SQLException {
		
		List<Borrower> list = new ArrayList<>();
		
		while(rs.next()){
			Borrower borrower = new Borrower();
			borrower.setBorrowerCardNo(rs.getInt("borrowerCardNo"));
			borrower.setBorrowerName(rs.getString("borrowerName"));
			borrower.setBorrowerAddress(rs.getString("borrowerAddress"));
			borrower.setBorrowerPhone(rs.getString("borrowerPhone"));
			list.add(borrower);
		}
		return list;
	}

	@Override
	public Borrower readbyName(String entityName) throws SQLException {
		
		return readFromDB(SELECT_ID_BY_NAME, new Object[]{entityName});
	}

	@Override
	public Borrower readbyId(int entityId) throws SQLException {
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

			return getCount(GET_COUNT_BY_NAME, new Object[]{searchString});
		}	
		else{
			return getCount(GET_COUNT, null);
		}
	}
	
//	public boolean checkIfForeignKey(int pk) throws SQLException {
//		Author author = new Author();
//		author = readFromDB(GET_BORROWER_FOREIGN_KEY, new Object[]{pk});
//		if(author != null){
//			return true;
//		}
//		else return false;
//	}
//	
//	public void deleteFromBookAuthor(Author author) throws SQLException{
//		
//		saveToDB(DELETE_FROM_BOOK_LOANS_TABLE, new Object[] {author.getAuthorId()});
//	}

}
