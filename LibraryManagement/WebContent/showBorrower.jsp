
<%@include file="index.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.training.library.entity.Borrower"%>
<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%@page import="com.gcit.training.library.client.AdministratorServlet" %>
<%@page import="java.util.List"%>
<%
	AdministratorService adminService = AdministratorService.getInstance();
	Integer totalCount = adminService.getBorrowerCount();
	Integer pageSize = 10;
	Integer pageCount = 0;
	
	if(totalCount % pageSize > 0){
		pageCount = totalCount / pageSize + 1;
	}
	else {
		pageCount = totalCount / pageSize;
	}
	
	List<Borrower> borrowers = new ArrayList<Borrower>();
	
	if (request.getAttribute("authors") != null){
		borrowers = (List<Borrower>) request.getAttribute("borrowers");
	} else {
		borrowers = adminService.getAllBorrowers(1, null);
	}

%>

<script>

	function search(pageNo){
		$.ajax({
			url: "showBorrower",
			datatype:"text",
			data: {
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#showBorrower').html(data.firstChild.outerHTML);
		})
		$.ajax({
			url: "searchBorrowers",
			data:{
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#borrowerTable').html(data);
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
		
		<nav aria-label="Page navigation" id="showBorrower">
				
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
					<table class="table table-striped" id="authorTable">					
						<tr>
							<th>#</th>
							<th>Borrower name</th>
							<th>Borrower address</th>
							<th>Borrower phone</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>				
						<% 
							for(Borrower borrower: borrowers) {
						%>
	 					<tr>
	 						<td><%=borrowers.indexOf(borrower) + 1%></td>  
							<td><%=borrower.getBorrowerName()%></td> 
							<td><%=borrower.getBorrowerAddress()%></td> 
							<td><%=borrower.getBorrowerPhone()%></td>
							<td><button class="btn btn-sm btn-primary" data-toggle="modal"	data-target="#myModal1"	
									href='editBorrower.jsp?borrowerCardNo=<%=borrower.getBorrowerCardNo()%>'>Edit</button></td>
							<td><form action="deleteBorrower" method="post">
									<button id="delete" value='<%=borrower.getBorrowerCardNo()%>' name="borrowerCardNo"
										class="btn btn-sm btn-danger"
										onclick="return confirm('Are you sure you want to delete Author <%=borrower.getBorrowerName()%>?');">Delete</button>
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
<!--main content end-->