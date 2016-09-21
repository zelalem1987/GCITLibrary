<%@include file="index.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.training.library.entity.Book"%>
<%@page import="com.gcit.training.library.entity.Author"%>
<%@page import="com.gcit.training.library.entity.Publisher"%>
<%@page import="com.gcit.training.library.service.AdministratorService" %>
<%@page import="com.gcit.training.library.client.AdministratorServlet" %>
<%@page import="java.util.List"%>

<%
	AdministratorService adminService = AdministratorService.getInstance();
	System.out.println("*******Test*********");
	Integer totalCount = adminService.getBookCount();
	Integer pageSize = 10;
	Integer pageCount = 0;
	
	if(totalCount % pageSize > 0){
		pageCount = totalCount / pageSize + 1;
	}
	else {
		pageCount = totalCount / pageSize;
	}
	
	List<Book> books = new ArrayList<Book>();
	List<Author> authors = new ArrayList<Author>();
	
	if (request.getAttribute("books") != null){
		books = (List<Book>) request.getAttribute("books");
	} else {
		books = adminService.getAllBook(1, null);
	}

%>

<script>

	function search(pageNo){
		$.ajax({
			url: "showBook",
			datatype:"text",
			data: {
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#showAuthor').html(data.firstChild.outerHTML);
		})
		$.ajax({
			url: "searchBooks",
			data:{
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#bookTable').html(data);
		})
	}
	
	function searchKeyPress(e)
	{
	    // look for window.event in case event isn't passed in
	    e = e || window.event;
	    if (e.keyCode == 13)
	    {
	        document.getElementById('btnSearch').click();
	        return false;
	    }
	    return true;
	}
</script>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<!--overview start-->
		<div class="row">
			<div class="col-lg-12">
				<%
					String result = (String) request.getAttribute("resultBook");
				%>
				<%
					if (result != null) {
				%>
				<div class="col-lg-3 col-md-8 col-s-12 col-xs-12">
					<div class="info-box-result blue-bg">
						<div class="count"><%=result%></div>
					</div>
					<!--/.info-box-->
				</div>

				<%
					} else {
				%>
				<h3 class="page-header">Books</h3>
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

		<nav aria-label="Page navigation" id="showBook">

			<ul class="pagination">
				<%
					for (int i = 1; i <= pageCount; i++) {
				%>
				<li><a href='javascript:search(<%=i%>)'><%=i%></a></li>
				<%
					}
				%>
			</ul>
		</nav>
		<div class="col-md-12">
			<section class="panel">
				<div class="table-responsive">
					<table class="table table-striped" id="bookTable">					
						<tr>
							<th>#</th>
							<th>Book title</th>
							<th>Publisher</th>
							<th>Author</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>				
						<% 
							for(Book book: books) {
						%>
	 					<tr>
	 						<td><%=books.indexOf(book) + 1%></td>  
							<td><%=book.getTitle()%></td>
							<% if(book.getPublish()!=null && !book.getPublish().getPublisherName().equals(null) && !book.getPublish().getPublisherName().equals("")) {%>
								<td><%=book.getPublish().getPublisherName()%></td>
							<%} else {%>
								<td>--</td>
							<%} %>
								<td><%=book.getAuthors()%></td>
							
							<td><button class="btn btn-sm btn-primary" data-toggle="modal"	data-target="#myModal1"	
									href='editBook.jsp?bookId=<%=book.getBookId()%>'>Edit</button>
							<td>
								<form action="deleteBook" method="post">
									<button id="delete" value='<%=book.getBookId()%>' name="bookId"
										class="btn btn-sm btn-danger"
										onclick="return confirm('Are you sure you want to delete Author <%=book.getTitle()%>?');">Delete</button>
								</form>
							</td>  
	
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
		<!--/.row-->
	</section>
</section>
<!--main content end-->