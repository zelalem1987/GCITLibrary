package com.gcit.training.library.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.library.entity.Author;
import com.gcit.training.library.entity.Branch;
import com.gcit.training.library.service.AdministratorService;
import com.gcit.training.library.service.LibrarianService;

/**
 * Servlet implementation class LibrarianServlet
 */
@WebServlet(name="LibrarianServlet", urlPatterns={"/addBranch", "/showBranch", "/updateBranch", "/deleteBranch", "/searchBranch"})
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LibrarianServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String function = request.getServletPath();
		String forwardPage = "/index.jsp";
		Boolean ajax = false;
		
		try{
			switch(function){
			case "/showBranch":{
				showLibraryBranchs(request, response);
				ajax = true;
				break;
			}
			case "/searchBranch": {
				searchBranch(request, response);
				ajax = true;
				break;
			}
			default:
				break;
			}
		} catch (Exception e){
			e.printStackTrace();
			request.setAttribute("result", "Operation failed!" + e.getMessage());
		}
		
		if(!ajax){
			RequestDispatcher rd = getServletContext().getNamedDispatcher(forwardPage);
			rd.forward(request, response);
		}
		
		
		
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String function = request.getServletPath();
		String forwardPage = "/index.jsp";
		
		try{
			switch (function){
			case "/addBranch":{
				addLibraryBranch(request);
				request.setAttribute("resulBranch", "Library branch added successfully!");
				forwardPage = "/showBranch.jsp";
				break;
			}
			case "/searchBranch": {
				searchBranch(request, response);
				forwardPage = "/showBranch.jsp";
				break;
			}
			case "/updateBranch": {
				upadateBranch(request, response);
				forwardPage = "/showBranch.jsp";
				break;
			}
			case "/deleteBranch": {
				deleteBranch(request, response);
				forwardPage = "/showBranch.jsp";
				break;
			}
			default:
				break;
			} 
		}catch(Exception e){
				e.printStackTrace();
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardPage);
		rd.forward(request, response);
	}

	/*
	 * Librarian branch data manipulation.
	 */
	
	private void deleteBranch(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void upadateBranch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		int branchId = Integer.parseInt(request.getParameter("branchId"));  
		Branch branch = new Branch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
		branch.setBranchId(branchId);
		
		LibrarianService.getInstance().updateBranch(branch);
		
	}

	private void addLibraryBranch(HttpServletRequest request) throws Exception {

		String branchName = request.getParameter("branchName");
		String branchAddress = request.getParameter("branchAddress");
		Branch branch = new Branch();
		branch.setBranchName(branchName);
		branch.setBranchAddress(branchAddress);
		
		LibrarianService.getInstance().addLibraryBranch(branch);
		
	}

	private void showLibraryBranchs(HttpServletRequest request, HttpServletResponse response) {
			
		
		
	}

	private void searchBranch(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		LibrarianService librarianService = LibrarianService.getInstance();
		
		if(searchString == null || searchString.length() < 0){
			searchString = null;
		}
		List<Branch> branches = new ArrayList<>();
		branches = librarianService.getAllBranches(pageNo, searchString);
		request.setAttribute("branches", branches);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><th>#</th><th>Branch Name</th><th>Edit</th><th>Delete</th></tr>");
		for(Branch branch: branches){
			stringBuffer.append("<tr><td>" + branches.indexOf(branch) + 1 + "</td>");
			stringBuffer.append("<td>" + branch.getBranchName() + "</td>");
			stringBuffer.append("<td>" + branch.getBranchAddress() + "</td>");
			stringBuffer.append("<td><button class='btn btn-sm btn-primary' data-toggle='modal' "
					+ "data-target='#myModal1' href='editGenre.jsp?genre_id=" + branch.getBranchId() + "'>Edit</button>");
			stringBuffer.append("<td><button class='btn btn-sm btn-danger'>Delete</button></tr>");
		}
		
		response.getWriter().append(stringBuffer);
	}
		
}

