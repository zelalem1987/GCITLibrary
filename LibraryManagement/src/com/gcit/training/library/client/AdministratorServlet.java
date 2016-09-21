package com.gcit.training.library.client;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.training.library.data.AuthorDAO;
import com.gcit.training.library.data.ConnectionUtils;
import com.gcit.training.library.entity.Author;
import com.gcit.training.library.entity.Book;
import com.gcit.training.library.entity.BookLoan;
import com.gcit.training.library.entity.Borrower;
import com.gcit.training.library.entity.Genre;
import com.gcit.training.library.entity.Publisher;
import com.gcit.training.library.service.AdministratorService;

/**
 * Servlet implementation class AdministratorServlet
 */
@WebServlet(name="AdministratorServlet", 
		urlPatterns={ "/addAuthor", "/addPublisher", "/addBook", "/addGenre", "/addBorrower", "/admin/addLoan",
				"/showAuthor", "/showPublisher", "/showBook", "/showGenre", "/showBorrower", "/admin/showLoan",
				"/updateAuthor", "/updatePublisher", "/updateBook", "/updateGenre", "/updateBorrower","/admin/upadateLoan",
				"/deleteAuthor", "/deletePublisher", "/deleteBook", "/deleteGenre", "/deleteBorrower", "/admin/deleteLoan",
				"/searchAuthors", "/searchPublishers", "/searchGenres", "/searchBooks", "/searchBorrowers", "/admin/searchLoans",})

public class AdministratorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministratorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String function = request.getServletPath();
		String forwardpage = "/index.jsp";
		Boolean ajax = false;
		
		try{
			switch (function) {
			case "/showAuthor":{			
				showAuthorList(request, response);
				ajax = true;
				break;
			}
			case "/showBorrower":{			
				showBorrowerList(request, response);
				ajax = true;
				break;
			}
			case "/showBook":{			
//				showBookList(request, response);
				ajax = true;
				break;
			}
			case "/admin/showLoan":{			
				showLoanList(request, response);
				ajax = true;
				break;
			}
			case "/showPublisher":{
				showPublisherList(request, response);
				ajax = true;
				break;
			}
			case "/showGenre":{
				showGenreList(request, response);
				ajax = true;
				break;
			}
			case "/searchGenres": {
				searchGenre(request, response);
				ajax = true;
				break;
			}
			case "/searchAuthors": {
				searchAuthors(request, response);
				ajax = true;
				break;
			}
			case "/searchBorrowers": {
				searchBorrowers(request, response);
				ajax = true;
				break;
			}
			case "/searchPublishers": {
				searchPublishers(request, response);
				ajax = true;
				break;
			}
			case "/searchBooks": {
				searchBooks(request, response);
				ajax = true;
				break;
			}
			case "/admin/searchLoans": {
				searchLoans(request, response);
				ajax = true;
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", "Operation Failed!" + e.getMessage());
		}
		
		if(!ajax){
			RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardpage);
			rd.forward(request, response);
		}
	}

	



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String function = request.getServletPath();
		String forwardpage = "/index.jsp";
		try{
			switch (function) {
			case "/addAuthor":{
				addAuthor(request);
				request.setAttribute("resultAuthor", "Author Added Succesfully!");
				forwardpage = "/showAuthor.jsp";
				break;
			}
			case "/addBorrower":{
				addBorrower(request);
				request.setAttribute("resultBorrower", "Borrower Added Succesfully!");
				forwardpage = "/showBorrower.jsp";
				break;
			}
			case "/addPublisher":{	
				addPublisher(request);
				request.setAttribute("resultPublisher", "Publisher Added Succesfully!");
				forwardpage = "/showPublisher.jsp";
				break;
			}
			case "/addGenre":{
				addGenre(request);
				request.setAttribute("resultGenre", "Genre Added Successfully!");
				forwardpage = "/showGenre.jsp";
			}
			case "/addBook":{
				addBook(request);
				request.setAttribute("resultBook", "Book Added Succesfully!");
				forwardpage = "/showBooks.jsp";
			}
//			case "/admin/addLoan":{
//				addLoans(request);
//				request.setAttribute("resultLoan", "Book Loan Added Succesfully!");
//				forwardpage = "/showLoan.jsp";
//			}
			case "/searchAuthors": {
				searchAuthors(request, response);
				forwardpage = "/showAuthor.jsp";
				break;
			}
			case "/searchBorrower": {
				searchBorrowers(request, response);
				forwardpage = "/showBorrower.jsp";
				break;
			}
			case "/searchPublishers": {
				searchPublishers(request, response);
				forwardpage = "/showPublisher.jsp";
				break;
			}
			case "/searchGenres": {
				searchGenre(request, response);
				forwardpage = "/showGenre.jsp";
				break;
			}
			case "/searchBooks": {
				searchBooks(request, response);
				forwardpage = "/showBooks.jsp";
				break;
			}
			case "/admin/searchLoans": {
				searchLoans(request, response);
				forwardpage = "/showLoan.jsp";
				break;
			}
			case "/updateAuthor": {
				upadateAuthor(request, response);
				forwardpage = "/showAuthor.jsp";
				break;
			}
			case "/updateBook": {
				upadateBook(request, response);
				forwardpage = "/showBooks.jsp";
				break;
			}
			case "/updateBorrower": {
				upadateBorrower(request, response);
				forwardpage = "/showBorrower.jsp";
				break;
			}
			case "/updateGenre": {
				updateGenre(request, response);
				forwardpage = "/showGenre.jsp";
				break;
			}
			case "/deleteAuthor": {
				deleteAuthor(request, response);
				forwardpage = "/showAuthor.jsp";
				break;
			}
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher(forwardpage);
		rd.forward(request, response); 
		// @formatter:on

	}

	/*
	 * Show methods for all entities
	 */

	private void showBookList(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		AdministratorService adminService = AdministratorService.getInstance();
		Integer totalCount = 0;
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer stringBuffer = new StringBuffer();
		String searchString = request.getParameter("searchString");
		if(searchString==null || searchString.length() < 0){
			searchString = null;
		}
		totalCount = adminService.getBookCountByName(searchString);
		Integer pageSize = 10;
		Integer pageCount = 0;
		
		if(totalCount % pageSize > 0){
			pageCount = totalCount / pageSize + 1;
		}
		else{
			pageCount = totalCount / pageSize;
		}
		
		stringBuffer.append("<ul class='pagination'>");
		for (int i = 1; i <= pageCount; i++) {
			stringBuffer.append("<li><a href='javascript:search("+i+")'>"+i+"</a></li>");
		}
		stringBuffer.append("</ul>");
		response.getWriter().write(stringBuffer.toString());
	}
	
	private void showLoanList(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		AdministratorService adminService = AdministratorService.getInstance();
		Integer totalCount = 0;
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer stringBuffer = new StringBuffer();
		String searchString = request.getParameter("searchString");
		if(searchString==null || searchString.length() < 0){
			searchString = null;
		}
		totalCount = adminService.getLoanersCountByName(searchString);
		Integer pageSize = 10;
		Integer pageCount = 0;
		
		if(totalCount % pageSize > 0){
			pageCount = totalCount / pageSize + 1;
		}
		else{
			pageCount = totalCount / pageSize;
		}
		
		stringBuffer.append("<ul class='pagination'>");
		for (int i = 1; i <= pageCount; i++) {
			stringBuffer.append("<li><a href='javascript:search("+i+")'>"+i+"</a></li>");
		}
		stringBuffer.append("</ul>");
		response.getWriter().write(stringBuffer.toString());
	}

	private void showAuthorList(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		AdministratorService adminService = AdministratorService.getInstance();
		Integer totalCount = 0;
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer stringBuffer = new StringBuffer();
		String searchString = request.getParameter("searchString");
		if(searchString==null || searchString.length() < 0){
			searchString = null;
		}
		totalCount = adminService.getAuthorsCountByName(searchString);
		Integer pageSize = 10;
		Integer pageCount = 0;
		
		if(totalCount % pageSize > 0){
			pageCount = totalCount / pageSize + 1;
		}
		else{
			pageCount = totalCount / pageSize;
		}
		
		stringBuffer.append("<ul class='pagination'>");
		for (int i = 1; i <= pageCount; i++) {
			stringBuffer.append("<li><a href='javascript:search("+i+")'>"+i+"</a></li>");
		}
		stringBuffer.append("</ul>");
		response.getWriter().write(stringBuffer.toString());
	}
	
	private void showBorrowerList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdministratorService adminService = AdministratorService.getInstance();
		Integer totalCount = 0;
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer stringBuffer = new StringBuffer();
		String searchString = request.getParameter("searchString");
		if(searchString==null || searchString.length() < 0){
			searchString = null;
		}
		totalCount = adminService.getBorrowerCountByName(searchString);
		Integer pageSize = 10;
		Integer pageCount = 0;
		
		if(totalCount % pageSize > 0){
			pageCount = totalCount / pageSize + 1;
		}
		else{
			pageCount = totalCount / pageSize;
		}
		
		stringBuffer.append("<ul class='pagination'>");
		for (int i = 1; i <= pageCount; i++) {
			stringBuffer.append("<li><a href='javascript:search("+i+")'>"+i+"</a></li>");
		}
		stringBuffer.append("</ul>");
		response.getWriter().write(stringBuffer.toString());
		
	}
	
	
	private void showGenreList(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		AdministratorService adminService = AdministratorService.getInstance();
		Integer totalCount = 0;
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		StringBuffer stringBuffer = new StringBuffer();
		String searchString = request.getParameter("searchString");
		if(searchString==null || searchString.length() < 0){
			searchString = null;
		}
		totalCount = adminService.getGenreCountByName(searchString);
		Integer pageSize = 10;
		Integer pageCount = 0;
		
		if(totalCount % pageSize > 0){
			pageCount = totalCount / pageSize + 1;
		}
		else{
			pageCount = totalCount / pageSize;
		}
		
		stringBuffer.append("<ul class='pagination'>");
		for (int i = 1; i <= pageCount; i++) {
			stringBuffer.append("<li><a href='javascript:search("+i+")'>"+i+"</a></li>");
		}
		stringBuffer.append("</ul>");
		response.getWriter().append(stringBuffer);
	}

	private void showPublisherList(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
//				Integer pageNo=1;
//				if(request.getParameter("pageNo")!=null){
//					 pageNo = Integer.parseInt(request.getParameter("pageNo"));
//				}
//				
//				List<Publisher> publishers = AdministratorService.getInstance().getAllPublishers(pageNo, null); 
//				request.setAttribute("publishers", publishers);
//				forwardpage = "/showPublisher.jsp";
//				break;
			AdministratorService adminService = AdministratorService.getInstance();
			Integer totalCount = 0;
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			StringBuffer stringBuffer = new StringBuffer();
			String searchString = request.getParameter("searchString");
			if(searchString==null || searchString.length() < 0){
				searchString = null;
			}
			totalCount = adminService.getPublisherCountByName(searchString);
			Integer pageSize = 10;
			Integer pageCount = 0;
			
			if(totalCount % pageSize > 0){
				pageCount = totalCount / pageSize + 1;
			}
			else{
				pageCount = totalCount / pageSize;
			}
			
			stringBuffer.append("<ul class='pagination'>");
			for (int i = 1; i <= pageCount; i++) {
				stringBuffer.append("<li><a href='javascript:search("+i+")'>"+i+"</a></li>");
			}
			stringBuffer.append("</ul>");
			response.getWriter().append(stringBuffer);
	}
	
	/*
	 * Search methods for all entities
	 */

	private void searchGenre(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService adminService = AdministratorService.getInstance();
		
		if(searchString == null || searchString.length() < 0){
			searchString = null;
		}
		List<Genre> genres = new ArrayList<>();
		genres = adminService.getAllGenre(pageNo, searchString);
		request.setAttribute("genres", genres);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><th>#</th><th>Genre Name</th><th>Edit</th><th>Delete</th></tr>");
		for(Genre genre: genres){
			stringBuffer.append("<tr><td>" + (genres.indexOf(genre) + 1 + (pageNo - 1) * 10) + "</td>");
			stringBuffer.append("<td>" + genre.getGenreName() + "</td>");
			stringBuffer.append("<td><button class='btn btn-sm btn-success' data-toggle='modal' "
					+ "data-target='#myModal1' href='editGenre.jsp?genre_id=" + genre.getGerneId() + "'>Edit</button>");
			stringBuffer.append("<td><button class='btn btn-sm btn-danger'>Delete</button></tr>");
		}
		
		response.getWriter().append(stringBuffer);
	}

	private void searchPublishers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService adminService = AdministratorService.getInstance();
		
		if(searchString == null || searchString.length() < 0){
			searchString = null;
		}
		List<Publisher> publishers = new ArrayList<>();
		publishers = adminService.getAllPublishers(pageNo, searchString);
		request.setAttribute("publishers", publishers);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><th>#</th><th>Genre Name</th><th>Edit</th><th>Delete</th></tr>");
		for(Publisher publihser: publishers){
			stringBuffer.append("<tr><td>" + (publishers.indexOf(publihser) + 1 + (pageNo - 1) * 10) + "</td>");
			stringBuffer.append("<td>" + publihser.getPublisherName() + "</td>");
			stringBuffer.append("<td><button class='btn btn-sm btn-primary' data-toggle='modal' "
					+ "data-target='#myModal1' href='editGenre.jsp?genre_id=" + publihser.getPublisherId() + "'>Edit</button>");
			stringBuffer.append("<td><button class='btn btn-sm btn-danger'>Delete</button></tr>");
		}
		
		response.getWriter().append(stringBuffer);
		
	}

	private void searchLoans(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService adminService = AdministratorService.getInstance();
		
		if(searchString == null || searchString.length() < 0){
			searchString = null;
		}
		List<BookLoan> bookLoans = new ArrayList<>();
		bookLoans = adminService.getAllBookLoans(pageNo, searchString);
		request.setAttribute("bookLoans", bookLoans);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><th>#</th><th>Author Name</th><th>Edit</th><th>Delete</th></tr>");
		for(BookLoan bookLoan: bookLoans){
			stringBuffer.append("<tr><td>" + (bookLoans.indexOf(bookLoan) + 1 + (pageNo - 1) * 10) + "</td>");
			stringBuffer.append("<td>" + bookLoan.getBook().getTitle() + "</td>");
			stringBuffer.append("<td>" + bookLoan.getBranch().getBranchName() + "</td>");
			stringBuffer.append("<td>" + bookLoan.getBorrower().getBorrowerName() + "</td>");
			stringBuffer.append("<td>" + bookLoan.getDateOut() + "</td>");
			stringBuffer.append("<td>" + bookLoan.getDueDate() + "</td>");
			stringBuffer.append("<td>" + bookLoan.getDateIn() + "</td>");
			stringBuffer.append("<td><button class='btn btn-sm btn-primary' data-toggle='modal' "
					+ "data-target='#myModal1' href='editBrookLoan.jsp?bookId, branchId, cardNo=" + bookLoan.getBook().getBookId() + "'>Edit</button>");
			stringBuffer.append("<td><form action='deleteBookLoan' method='post'><button class='btn btn-sm btn-danger' value=" + bookLoan.getBook().getBookId() +
					"onclick='return confirm('Are you sure you want to delete Loan" + bookLoan.getBook().getBookId() + "?')';>Delete</button></form></tr>");
		}
		
		response.getWriter().append(stringBuffer);
		
	}
	
	private void searchAuthors(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		String searchString = request.getParameter("searchString");
//		AdministratorService adminService = AdministratorService.getInstance();
//		request.setAttribute("authors", adminService.getAllAuthors(1, searchString));
		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService adminService = AdministratorService.getInstance();
		
		if(searchString == null || searchString.length() < 0){
			searchString = null;
		}
		List<Author> authors = new ArrayList<>();
		authors = adminService.getAllAuthors(pageNo, searchString);
		request.setAttribute("authors", authors);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><th>#</th><th>Author Name</th><th>Edit</th><th>Delete</th></tr>");
		for(Author author: authors){
			stringBuffer.append("<tr><td>" + (authors.indexOf(author) + 1  + (pageNo - 1) * 10) + "</td>");
			stringBuffer.append("<td>" + author.getAuthorName() + "</td>");
			stringBuffer.append("<td><button class='btn btn-sm btn-primary' data-toggle='modal' "
					+ "data-target='#myModal1' href='editAuthor.jsp?authorId=" + author.getAuthorId() + "'>Edit</button>");
			stringBuffer.append("<td><form action='deleteAuthor' method='post'><button class='btn btn-sm btn-danger' value=" + author.getAuthorId() + 
					"name='authorId' onclick='return confirm('Are you sure you want to delete Author" + author.getAuthorName() + "?')';>Delete</button></form></tr>");
		}
		
		response.getWriter().append(stringBuffer);
		
	}
	
	private void searchBorrowers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService adminService = AdministratorService.getInstance();
		
		if(searchString == null || searchString.length() < 0){
			searchString = null;
		}
		List<Borrower> borrowers = new ArrayList<>();
		borrowers = adminService.getAllBorrowers(pageNo, searchString);
		request.setAttribute("borrowers", borrowers);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><th>#</th><th>Borrower Name</th><th>Edit</th><th>Delete</th></tr>");
		for(Borrower borrower: borrowers){
			stringBuffer.append("<tr><td>" + (borrowers.indexOf(borrower) + 1 + (pageNo - 1) * 10) + "</td>");
			stringBuffer.append("<td>" + borrower.getBorrowerCardNo() + "</td>");
			stringBuffer.append("<td><button class='btn btn-sm btn-primary' data-toggle='modal' "
					+ "data-target='#myModal1' href='editBorrower.jsp?borrowerCardNo=" + borrower.getBorrowerCardNo() + "'>Edit</button>");
			stringBuffer.append("<td><form action='deleteAuthor' method='post'><button class='btn btn-sm btn-danger' value=" + borrower.getBorrowerCardNo() + 
					"onclick='return confirm('Are you sure you want to delete Author" + borrower.getBorrowerName() + "?')';>Delete</button></form></tr>");
		}
		
		response.getWriter().append(stringBuffer);
		
	}


	private void searchBooks(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String searchString = request.getParameter("searchString");
		Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
		AdministratorService adminService = AdministratorService.getInstance();
		
		if(searchString == null || searchString.length() < 0){
			searchString = null;
		}
		List<Book> books = new ArrayList<>();
		books = adminService.getAllBook(pageNo, searchString);
		request.setAttribute("books", books);
		
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><th>#</th><th>Book title</th><th>Publisher</th><th>Author</th><th>Edit</th><th>Delete</th></tr>");
		for(Book book: books){
			stringBuffer.append("<tr><td>" + (books.indexOf(book) + 1 + (pageNo - 1) * 10) + "</td>");
			stringBuffer.append("<td>" + book.getTitle() + "</td>");			
			if(book.getPublish()!=null && !book.getPublish().getPublisherName().equals(null) && !book.getPublish().getPublisherName().equals("")) {
				stringBuffer.append("<td>" + book.getPublish().getPublisherName() + "</td>");
			} else {
				stringBuffer.append("<td> -- </td>");
			}
			stringBuffer.append("<td>" + book.getAuthors() + "</td>");
			stringBuffer.append("<td><button class='btn btn-sm btn-primary' data-toggle='modal' "
					+ "data-target='#myModal1' href='editBook.jsp?bookId=" + book.getBookId() + "'>Edit</button>");
			stringBuffer.append("<td><form action='deleteBook' method='post'><button class='btn btn-sm btn-danger' value=" + book.getBookId() + 
					"onclick='return confirm('Are you sure you want to delete Book" + book.getTitle() + "?')';>Delete</button></form></tr>");
		}
		
		response.getWriter().append(stringBuffer);
		
	}
	
	/*
	 * Add Entities to the DB
	 */

	private void addAuthor(HttpServletRequest request) throws Exception {

		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);
		
		AdministratorService.getInstance().addAuthor(author);
		
	}
	
	private void addBookLoan(HttpServletRequest request) throws Exception {

		String authorName = request.getParameter("authorName");
		Author author = new Author();
		author.setAuthorName(authorName);
		
		AdministratorService.getInstance().addAuthor(author);
		
	}
	
	private void addBorrower (HttpServletRequest request) throws Exception {

		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		Borrower borrower = new Borrower();
		borrower.setBorrowerName(borrowerName);
		borrower.setBorrowerAddress(borrowerAddress);
		borrower.setBorrowerPhone(borrowerPhone);
		
		AdministratorService.getInstance().addBorrower(borrower);
		
	}

	private void addGenre(HttpServletRequest request) throws Exception {
		
		String genreName = request.getParameter("genre_name");
		Genre genre = new Genre();
		genre.setGenreName(genreName);
		
		AdministratorService.getInstance().addGenre(genre);
		
	}


	private void addPublisher(HttpServletRequest request) throws Exception {
		
		String publisherName = request.getParameter("publisherName");
		String publisherAddress = request.getParameter("publisherAddress");
		String publisherPhone = request.getParameter("publisherPhone");
		
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPunlisherPhone(publisherPhone);
		
		AdministratorService.getInstance().addPublisher(publisher);
		
	}

	private void addBook(HttpServletRequest request) throws Exception {

		String bookTitle = request.getParameter("bookTitle");
		String[] authorId = request.getParameterValues("authorId");		
		String pubId = request.getParameter("pubId");
		String genre_id = request.getParameter("genre_id");
		
		List<Author> authorList = new ArrayList<>();
		List<Publisher> publisherList = new ArrayList<>();
		List<Genre> genreList = new ArrayList<>();
		
		request.setAttribute("authorList", authorList);
		request.setAttribute("publisherList", publisherList);
		request.setAttribute("genreList", genreList);
		
		Book book = new Book();
		Publisher publisher = new Publisher();
		Genre genre = new Genre();
		
		List<Author> authors = new ArrayList<>();

		book.setTitle(bookTitle);
		
		if(authorId != null && authorId.length > 0){
			for(String id: authorId){
				Author author = new Author();
				author.setAuthorId(Integer.parseInt(id));
				authors.add(author);
			}
		}

		publisher.setPublisherId(Integer.parseInt(pubId));
		genre.setGerneId(Integer.parseInt(genre_id));
		
		
		book.setAuthors(authors);
		book.setGenres(genre);
		book.setPublish(publisher);
		
		AdministratorService.getInstance().addBook(book);
		
	}
	
	/*
	 * Edit and update tables for each entity
	 */	

	
	private void upadateAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String authorName = request.getParameter("authorName");
		int authorId = Integer.parseInt(request.getParameter("authorId"));  
		Author author = new Author();
		author.setAuthorName(authorName);
		author.setAuthorId(authorId);
		
		AdministratorService.getInstance().updateAuthor(author);
		
	}
	
	private void upadateBook(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String bookTitle = request.getParameter("bookTitle");
		int bookId = Integer.parseInt(request.getParameter("bookId"));  
		Book book = new Book();
		book.setTitle(bookTitle);
		book.setBookId(bookId);
		
		AdministratorService.getInstance().updateBook(book);
		
	}
	
	private void upadateBorrower(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String borrowerName = request.getParameter("borrowerName");
		String borrowerAddress = request.getParameter("borrowerAddress");
		String borrowerPhone = request.getParameter("borrowerPhone");
		Borrower borrower = new Borrower();
		borrower.setBorrowerName(borrowerName);
		borrower.setBorrowerAddress(borrowerAddress);
		borrower.setBorrowerPhone(borrowerPhone);
		
		AdministratorService.getInstance().updateBorrower(borrower);
		
	}


	private void updateGenre(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String genre_name = request.getParameter("genre_name");
		int genre_id = Integer.parseInt(request.getParameter("genre_id"));  
		Genre genre = new Genre();
		genre.setGenreName(genre_name);
		genre.setGerneId(genre_id);
		
		AdministratorService.getInstance().updateGenre(genre);		
		
	}

	/*
	 * Delete tables for each entity
	 */	


	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Connection conn = ConnectionUtils.getConnection();
		
		int authorId = Integer.parseInt(request.getParameter("authorId"));
		Author author = new Author();
		author.setAuthorId(authorId);
		AdministratorService administratorService = AdministratorService.getInstance();
		AuthorDAO authorDAO = new AuthorDAO(conn);		
		
		if(authorDAO.checkIfForeignKey(authorId)){
			administratorService.deleteForeignFromBookAuthor(author);
			administratorService.deleteAuthor(author);
		} else administratorService.deleteAuthor(author);
		
	}
}
