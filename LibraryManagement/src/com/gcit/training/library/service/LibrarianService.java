package com.gcit.training.library.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import com.gcit.training.library.data.AuthorDAO;
import com.gcit.training.library.data.BranchDAO;
import com.gcit.training.library.data.ConnectionUtils;
import com.gcit.training.library.entity.Author;
import com.gcit.training.library.entity.Branch;
import com.gcit.training.library.exceptions.LibraryExceptionCustom;

public class LibrarianService implements Serializable{
	
	private static final long serialVersionUID = -5966692832107824968L;
	
	private static LibrarianService instance = null;
	
	private LibrarianService(){
		
	}
	
	public static LibrarianService getInstance(){
		if(instance == null){
			synchronized (LibrarianService.class) {
				if(instance == null){
					instance = new LibrarianService();
				}
			}
		}
		
		return instance;
	}
	
	/*
	 * Add entry to entities
	 */
	
	public void addLibraryBranch(Branch branch) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();		
		
		BranchDAO branchDAO = new BranchDAO(conn);
		
		if(branch == null || branch.getBranchName().trim().length() == 0){
			throw new LibraryExceptionCustom("Branch name can not be blanck!");
		} else if(branch.getBranchName().length() > 45){
			throw new LibraryExceptionCustom("Branch name can not be more than 45 characters!");
		} 
		//TODO: Add exception for the branch address and phone
		
		try{
			branchDAO.create(branch);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}		
		
	}
	/*
	 * Get all columns of Entities
	 */
	
	public List<Branch> getAllBranches(Integer pageNo, String searchString) throws Exception{
		
		Connection conn = ConnectionUtils.getConnection();	
		BranchDAO branchDAO = new BranchDAO(conn);	
		return branchDAO.readall(pageNo, 10, searchString);
	}
	
	/*
	 * Get Entities by their Ids
	 */
	
	public Branch getBranchById(Integer branchId) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		BranchDAO branchDAO = new BranchDAO(conn);
		return branchDAO.read(branchId);
	}
	
	/*
	 * Get Entity counts by row
	 */
	
	public Integer getBranchCount() throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		BranchDAO branchDAO = new BranchDAO(conn);
		return branchDAO.getCount();
	}
	
	/*
	 * Get Count of Entities by names.
	 */
	
	public Integer getBranchCountByName(String searchString) throws Exception{
		Connection conn = ConnectionUtils.getConnection();
		
		BranchDAO branchDAO = new BranchDAO(conn);
		
		if(searchString != null && !searchString.equals("")){
			searchString="%"+searchString +"%";
		}
		return branchDAO.getCountByName(searchString);
	}
	
	/*
	 * Update Entities
	 */
	
	public void updateBranch(Branch branch) throws Exception{

		Connection conn = ConnectionUtils.getConnection();

		BranchDAO branchDAO = new BranchDAO(conn);

		try{
			branchDAO.update(branch);
			conn.commit();
		} catch(Exception e){
			conn.rollback();
			throw e;
		}
	}

}
