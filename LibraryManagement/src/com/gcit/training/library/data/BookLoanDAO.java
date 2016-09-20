package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.gcit.training.library.entity.Book;
import com.gcit.training.library.entity.BookCopies;
import com.gcit.training.library.entity.BookLoan;

public class BookLoanDAO extends BaseDAO<BookLoan>{

	public BookLoanDAO(Connection conn) {
		super(conn);
	}
	
	private static final String SELECT_ALL = "select * from tbl_book_loans";
	private static final String SELECT_ONE = "select * from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?";
	private static final String INSERT = "insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?,?,?,?,?,?)";
	private static final String DELETE = "delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?";
	private static final String UPDATE = "update tbl_book_loans set bookId = ?, branchId = ?, dateOut = ?, dueDate = ?  dateIn = ? where cardNo = ?";
	private static final String UPDATE_DATEOUT = "update tbl_book_loans set dateOut = ?  where bookId = ? and branchId = ? and cardNo = ?";
	private static final String UPDATE_DUEDATE = "update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_book_loans where cardNo like ?";
	private static final String SELECT_BY_DUEDATE = "select * from tbl_book_loans where dueDate = ?";
	private static final String SELECT_OVER_DUEDATE = "select * from tbl_book_loans where Date(tbl_book_loans.dueDate) > curDate()";
	private static final String GET_COUNT = "select count(*) from tbl_book_loans";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_book_loans where title like ?";
	
	@Override
	public void create(BookLoan be) throws SQLException {
		
		saveToDB(INSERT, new Object[]{be.getBook().getBookId(), be.getBranch().getBranchId(),
				be.getBorrower().getBorrowerCardNo(), be.getDateOut(), be.getDueDate(),
				be.getDateIn()});
		
	}
	
	public void insertWeekLoan(BookLoan be) throws SQLException{

		Date dateOut = be.getDateOut();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOut);
		cal.add(Calendar.DATE, 7);
		Date dueDate = cal.getTime();
		
		saveToDB(INSERT, new Object[]{be.getBook().getBookId(), be.getBranch().getBranchId(),
				be.getBorrower().getBorrowerCardNo(), be.getDateOut(), dueDate,
				be.getDateIn()});
	}

	@Override
	public void update(BookLoan be) throws SQLException {

		saveToDB(UPDATE, new Object[]{be.getBook().getBookId(), be.getBranch().getBranchId(),
				be.getDateOut(), be.getDueDate(), be.getDateIn(), 
				be.getBorrower().getBorrowerCardNo()});
		
	}

	
	public void updateCopies(BookCopies be) throws SQLException {

		saveToDB(UPDATE, new Object[]{ be.getNoOfCopies(), be.getBook().getBookId(), be.getBranch().getBranchId()} );
		
	}
	
	public void updateDateOut(BookLoan be) throws SQLException {
		
		saveToDB(UPDATE_DATEOUT, new Object[]{be.getDateOut(), be.getBook().getBookId(),
				be.getBranch().getBranchId(), be.getBorrower().getBorrowerCardNo()});
	}
	
	public void updateDuedate(BookLoan be) throws SQLException {
		
		saveToDB(UPDATE_DUEDATE, new Object[]{be.getDueDate(), be.getBook().getBookId(),
				be.getBranch().getBranchId(), be.getBorrower().getBorrowerCardNo()});
	}

	@Override
	public void delete(BookLoan be) throws SQLException {

		saveToDB(DELETE, new Object[]{ be.getBook().getBookId(), 
				be.getBranch().getBranchId(), be.getBorrower().getBorrowerCardNo()});
		
	}

	@Override
	public BookLoan read(int pk) throws SQLException {

		return readFromDB(SELECT_ONE, new Object[]{pk});
	}
	
	public BookLoan readOne(BookLoan bookLoan) throws SQLException {

		return readFromDB(SELECT_ONE, new Object[]{ bookLoan.getBook().getBookId(), 
				bookLoan.getBranch().getBranchId(), bookLoan.getBorrower().getBorrowerCardNo()});
	}

	@Override
	public void save(BookLoan be) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookLoan> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {

		setPageNo(pageNo);
		setPageSize(pageSize);
		
		if(searchString != null && !"".equals(searchString)){
			
			searchString = "%" + searchString + "%";
			
			return readAllFromDB(SELECT_ALL_SEARCH, new Object[]{searchString});
		}	else{
				return readAllFromDB(SELECT_ALL, new Object[]{});
		}
	}
	
	public List<BookLoan> readByCardNo(BookLoan be) throws SQLException{
		return readAllFromDB(SELECT_ALL_SEARCH, new Object[]{be.getBorrower().getBorrowerCardNo()});
	}

	@Override
	public List<BookLoan> readResult(ResultSet rs) throws SQLException {
		List<BookLoan> list = new ArrayList<>();
		Connection con=ConnectionUtils.getConnection();
		try{
			BookDAO bdao=new BookDAO(con);
			BranchDAO brdao=new BranchDAO(con);
			BorrowerDAO borrdao = new BorrowerDAO(con);
			while(rs.next()){
				BookLoan bookLoan = new BookLoan();
				bookLoan.setBook(bdao.read(rs.getInt("bookId")));
				bookLoan.setBranch(brdao.read(rs.getInt("branchId")));
				bookLoan.setBorrower(borrdao.read(rs.getInt("cardNo")));
				bookLoan.setDateOut(rs.getDate("dateOut"));
				bookLoan.setDueDate(rs.getDate("dueDate"));
				bookLoan.setDateIn(rs.getDate("dateIn"));
				list.add(bookLoan);
			}
			
			return list;
		}finally{
			
		}
		
		
	}

	@Override
	public BookLoan readbyName(String entityName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BookLoan radbyDueDate(BookLoan be) throws SQLException{
		
		return readFromDB(SELECT_BY_DUEDATE, new Object[]{be.getDueDate()});
	}
	
	public BookLoan readOverDueDate(BookLoan be) throws SQLException{
		
		return readFromDB(SELECT_OVER_DUEDATE,  new Object[]{be.getDueDate()});
	}
	

	@Override
	public BookLoan readbyId(int entityId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
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
