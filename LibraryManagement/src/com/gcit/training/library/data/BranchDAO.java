package com.gcit.training.library.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.training.library.entity.Branch;

public class BranchDAO extends BaseDAO<Branch>{

	private static final String SELECT_BY_NAME = "SELECT * FROM tbl_library_branch WHERE branchName = ?";
	private static final String SELECT_ALL = "SELECT * FROM tbl_library_branch";
	private static final String SELECT = "SELECT * FROM tbl_library_branch WHERE branchId = ?";
	private static final String DELETE = "DELETE FROM tbl_library_branch WHERE branchId = ? ";
	private static final String UPDATE = "UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?";
	private static final String INSERT = "INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?, ?)";
	private static final String GET_COUNT = "select count(*) from tbl_library_branch";
	private static final String SELECT_ALL_SEARCH = "select * from tbl_library_branch where branchName like ?";
	private static final String GET_COUNT_BY_NAME = "select count(*) from tbl_library_branch where branchName like ?";
	
	public BranchDAO(Connection conn) {
		
		super(conn);
	}

	@Override
	public void create(Branch be) throws SQLException {
		
		saveToDB(INSERT, new Object[] {be.getBranchName(), be.getBranchAddress()});
	}

	@Override
	public void update(Branch be) throws SQLException {

		saveToDB(UPDATE, new Object[] {be.getBranchName(),
				be.getBranchAddress(), be.getBranchId()});		
	}

	@Override
	public void delete(Branch be) throws SQLException {
		
		saveToDB(DELETE, new Object[] {be.getBranchId()});
	}

	@Override
	public Branch read(int pk) throws SQLException {
		
		return readFromDB(SELECT, new Object[] {pk});
	}

	@Override
	public List<Branch> readall(Integer pageNo, Integer pageSize, String searchString) throws SQLException {
		
		setPageNo(pageNo);
		setPageSize(pageSize);
		
		if(searchString != null && !"".equals(searchString)){
			
			searchString = "%" + searchString + "%";
			
			return readAllFromDB(SELECT_ALL_SEARCH, new Object[]{searchString});
		}
		else{
		
			return readAllFromDB(SELECT_ALL, new Object[] {});			
		}
	}

	@Override
	public void save(Branch be) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Branch> readResult(ResultSet rs) throws SQLException {
		
		List<Branch> list = new ArrayList<>();
		
		while(rs.next()){
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			list.add(branch);
		}
		return list;
	}

	@Override
	public Branch readbyName(String branchName) throws SQLException {
		
		return readFromDB(SELECT_BY_NAME, new Object[] {branchName});
	}

	@Override
	public Branch readbyId(int entityId) throws SQLException {
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
		else {
			return getCount(GET_COUNT, null);
		}
		
	}

}
