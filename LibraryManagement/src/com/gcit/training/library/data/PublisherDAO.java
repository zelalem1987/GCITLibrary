package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.gcit.training.library.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher>{
	
	private static final String GET_COUNT = "select count(*) from tbl_publisher";
	private static final String SELECT_ID_BY_NAME = "SELECT * FROM tbl_publisher WHERE publisherName = ?";
	private static final String SELECT_ALL = "SELECT * FROM tbl_publisher";
	private static final String SELECT = "SELECT * FROM tbl_publisher WHERE publisherId = ?";
	private static final String DELETE = "DELETE FROM tbl_publisher WHERE publisherId = ? ";
	private static final String UPDATE = "UPDATE tbl_publisher SET publisherName = ?, "
			+ "publisherAddress = ?, publisherPhone = ? " + "WHERE publisherId = ?";
	private static final String INSERT = "INSERT INTO tbl_publisher (publisherName, "
			+ "publisherAddress, publisherPhone) VALUES (?, ?, ?)";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_publisher where publisherName like ?";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_publisher where publisherName like ?";

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	@Override
	public void create(Publisher be) throws SQLException {
		
		saveToDB(INSERT, new Object[] {be.getPublisherName(), be.getPublisherAddress(), be.getPunlisherPhone()});		
	}

	@Override
	public void update(Publisher be) throws SQLException {
		
		saveToDB(UPDATE, new Object[] {be.getPublisherId(), be.getPublisherName(), 
						be.getPublisherAddress(), be.getPunlisherPhone()});		
	}

	@Override
	public void delete(Publisher be) throws SQLException {
		
		saveToDB(DELETE, new Object[] {be.getPublisherId()});		
	}

	@Override
	public Publisher read(int pk) throws SQLException {
		
		return readFromDB(SELECT, new Object[] {pk});
	}

	@Override
	public List<Publisher> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {
		
		setPageNo(pageNo);
		setPageSize(pageSize);
		
		if(searchString != null && !"".equals(searchString)){
			searchString = "%" + searchString + "%";			
			return readAllFromDB(SELECT_ALL_SEARCH, new Object[]{searchString});
		} else {
			return readAllFromDB(SELECT_ALL, new Object[] {});
		}		
		
	}

	@Override
	public List<Publisher> readResult(ResultSet rs) throws SQLException {
		
		List<Publisher> list = new ArrayList<>();
		
		while(rs.next()){
			Publisher publisher = new Publisher();
			publisher.setPublisherId(rs.getInt("publisherId"));
			publisher.setPublisherName(rs.getString("publisherName"));
			publisher.setPublisherAddress(rs.getString("publisherAddress"));
			publisher.setPunlisherPhone(rs.getString("publisherPhone"));
			list.add(publisher);
		}
		
		return list;
	}

	@Override
	public void save(Publisher be) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public Publisher readbyName(String publisherName) throws SQLException {
		
		return readFromDB(SELECT_ID_BY_NAME, new Object[]{publisherName});
	}

	@Override
	public Publisher readbyId(int entityId) throws SQLException {
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
