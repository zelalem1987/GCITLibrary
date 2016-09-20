package com.gcit.training.library.client;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.library.entity.Author;
import com.gcit.training.library.entity.Book;
import com.gcit.training.library.entity.BookLoan;
import com.gcit.training.library.entity.Borrower;
import com.gcit.training.library.entity.Publisher;
import com.gcit.training.library.service.BorrowerService;

/**
 * Servlet implementation class BorrowerServlet
 */
@WebServlet(name = "BorrowerServlet", 
		urlPatterns={"/borrowPage", "/bo/showAuthors"})

public class BorrowerServlet extends HttpServlet implements Serializable{
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -1097286770896630676L;


	/**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String function = request.getServletPath();
		String forwardpage = "/index.jsp";
		
		try{
			switch(function){
			case "/showBooks":{
				showBooks(request);
				forwardpage ="/showBooks.jsp";
				request.setAttribute("result", "");
			}
			case "/borrowPage":{
				if(showBorrowPage(request)) 
					forwardpage = "/BorrowerPage.jsp";
				else{
					forwardpage = "/index.jsp";
					request.setAttribute("resultBorrower", "Please provide a valid card number");
				}
				
				
			}

			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardpage);
		rd.forward(request, response); 
	}

	private boolean showBorrowPage(HttpServletRequest request) throws Exception {
		
		int cardNo = Integer.parseInt(request.getParameter("borrowerCardNo"));
		Borrower borrower = new Borrower();
		borrower.setBorrowerCardNo(cardNo);
		
		BorrowerService borrowerService = BorrowerService.getInstance();
		if(borrowerService.checkCardValidity(borrower)){

			List<BookLoan> list = new ArrayList<>();			
			list = borrowerService.getBookLoans(cardNo);
			if(list!=null){
				request.setAttribute("loansView", list);
			}
			return true;
			
		} else return false;
	}

	private void showBooks(HttpServletRequest request) throws Exception {
		
		List<Book> bookList = new ArrayList<>();
		
		BorrowerService borrowerService = BorrowerService.getInstance();
		bookList = (List<Book>) borrowerService.showBooks();
		
		if(bookList != null){
			request.setAttribute("BookList", bookList);
		}
		
	}

	
	private void showAddBook(HttpServletRequest request) throws Exception {
		
		BorrowerService borrowerService = new BorrowerService();
		
//		List<Author> authorList = AdministratorService.getInstance().getAllAuthors(); 
//		List<Publisher> pubList = AdministratorService.getInstance().getAllPublishers();
		List<Book> bookList = new ArrayList<>();
		List<Author> authorList = new ArrayList<>();
		List<Publisher> publisherList = new ArrayList<>();
		
		bookList = (List<Book>) borrowerService.showBooks();
		
		if(bookList != null){
			request.setAttribute("BookList", bookList);
			request.setAttribute("authorList", authorList);
			request.setAttribute("publisherList", publisherList);
			}
	}

}
