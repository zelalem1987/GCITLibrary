<%@include file="index.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.training.library.entity.BookLoan"%>
<%@page import="com.gcit.training.library.service.BorrowerService"%>
<%@page import="com.gcit.training.library.client.BorrowerServlet" %>
<%@page import="java.util.List"%>

<%	
	BorrowerService borrowerService = BorrowerService.getInstance();

	List<BookLoan> bookLoans = new ArrayList<BookLoan>();
	
	if (request.getAttribute("borrowerCardNo") != null){
		//bookLoans = (List<BookLoan>) request.getAttribute("borrowerCardNo");
	}
	if (request.getAttribute("loansView") != null){
		bookLoans = (List<BookLoan>) request.getAttribute("loansView");
	}

%>


<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!--overview start-->
		<div class="row">
			<div class="col-lg-12">
				<%
					String result = (String) request.getAttribute("resultBorrower");
				%>
				<%
					if (result != null) {
				%>
				<div class="col-lg-3 col-md-8 col-s-12 col-xs-12">
					<div class="info-box-result blue-bg">
						<div class="count"><%=result%></div>
					</div>
				</div>

				<%
					} else {
				%>
				<h3 class="page-header">Borrowers</h3>
				<%
					}
				%>
			</div>
		</div>

		<div class="input-group">
			<form action="searchString"  method="post">
				<input type="text" id="searchString" class="form-control" placeholder="Search by name"
					name="searchString" aria-describedby="basic-addon1"  
					onkeypress="return searchKeyPress(event);" >
				<button type="button" id="btnSearch" onclick="search(1)">Search</button>
			</form>
		</div>
		
		<div class="col-md-12">
			<section class="panel">
				<div class="table-responsive">
					<table class="table table-striped" id="authorTable">					
						<tr>
							<th>#</th>
							<th>Borrower name</th>
							<th>Branch name</th>
							<th>Book title</th>
							<th>Checked out</th>
							<th>Due date</th>
							<th>Edit Due date</th>
							<th>Return a book</th>
						</tr>				
						<% 
							for(BookLoan bookLoan: bookLoans) {
						%>
	 					<tr>
	 						<td><%=bookLoans.indexOf(bookLoan) + 1%></td>  
							<td><%=bookLoan.getBorrower().getBorrowerName()%></td> 
							<td><%=bookLoan.getBranch().getBranchName()%></td> 
							<td><%=bookLoan.getBook().getTitle()%></td>
							<td><%=bookLoan.getDateOut()%></td>
							<td><%=bookLoan.getDueDate()%></td>
							<td><button class="btn btn-sm btn-primary" data-toggle="modal"	data-target="#myModal1"	
									href='editLoan.jsp?cardNo=<%=bookLoan.getDueDate()%>'>Edit Due date</button></td>
							<td><form action="deleteBorrower" method="post">
									<button id="delete" value='<%=bookLoan.getBorrower().getBorrowerCardNo()%>' name="borrowerCardNo"
										class="btn btn-sm btn-danger"
										onclick="return confirm('Are you sure you want to return <%=bookLoan.getBook().getTitle()%>?');">Return a book</button>
								</form></td> 
	
	 					</tr>
	 					<%
							}
						%>		
					</table>
				</div>
			</section>
		</div>
		
		<div id="myModal1" class="modal fade bs-example-modal-lg" tabindex="-1"	role="dialog" aria-labelledby="myLargeModalLabel">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content"></div>
			</div>
		</div>
		
	</section>
</section>
