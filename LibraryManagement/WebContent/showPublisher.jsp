
<%@include file="index.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.training.library.entity.Publisher"%>
<%@page import="com.gcit.training.library.service.AdministratorService" %>
<%@page import="com.gcit.training.library.client.AdministratorServlet" %>
<%@page import="java.util.List"%>

<%
	AdministratorService adminService = AdministratorService.getInstance();
	Integer totalCount = adminService.getPublisherCount();
	Integer pageSize = 10;
	Integer pageCount = 0;
	
	if(totalCount % pageSize > 0){
		pageCount = totalCount / pageSize + 1;
	}
	else {
		pageCount = totalCount / pageSize;
	}
	
	List<Publisher> publishers = new ArrayList<Publisher>();
	
	if (request.getAttribute("publishers") != null){
		publishers = (List<Publisher>) request.getAttribute("publishers");
	} else {
		publishers = adminService.getAllPublishers(1, null);
	}

%>

<script>

	function search(pageNo){
		$.ajax({
			url: "showPublisher",
			datatype:"text",
			data: {
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#showPublisher').html(data.firstChild.outerHTML);
		})
		$.ajax({
			url: "searchPublishers",
			data:{
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#publisherTable').html(data);
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
					String result = (String) request.getAttribute("resultPublisher");
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
				<h3 class="page-header">Publishers</h3>
				<%
					}
				%>
			</div>
		</div>

		<div class="input-group">
			<form action="searchPublishers" method="post">
				<input type="text" id="searchString" class="form-control" placeholder="Search by name"
					name="searchString" aria-describedby="basic-addon1"
					onkeypress="return searchKeyPress(event);">
				<button type="submit" id="btnSearch" onclick="search(1)">Search</button>
			</form>
		</div>

		<nav aria-label="Page navigation" id="showPublisher">
				
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
					<table class="table table-striped" id="publisherTable">					
						<tr>
							<th>#</th>
							<th>Publisher name</th>
							<th>Publisher address</th>
							<th>Publisher phone</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>				
						<% 
							for(Publisher publisher: publishers) {
						%>
	 					<tr>
	 						<td><%=publishers.indexOf(publisher) + 1%></td>  
							<td><%=publisher.getPublisherName()%></td>
							<td><%=publisher.getPublisherAddress()%></td>
							<td><%=publisher.getPunlisherPhone()%></td>
							<td><button class="btn btn-sm btn-primary" data-toggle="modal"	data-target="#myModal1"	
									href='editPublisher.jsp?publisherId=<%=publisher.getPublisherId()%>'>Edit</button>
							<td><button class="btn btn-sm btn-danger">Delete</button> 
	
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