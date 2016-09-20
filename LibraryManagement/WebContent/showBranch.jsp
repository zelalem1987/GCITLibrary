
<%@include file="index.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.training.library.entity.Branch"%>
<%@page import="com.gcit.training.library.service.LibrarianService" %>
<%@page import="com.gcit.training.library.client.LibrarianServlet" %>
<%@page import="java.util.List"%>

<%
	LibrarianService librarianService = LibrarianService.getInstance();
	Integer totalCount = librarianService.getBranchCount();
	Integer pageSize = 10;
	Integer pageCount = 0;
	
	if(totalCount % pageSize > 0){
		pageCount = totalCount / pageSize + 1;
	}
	else {
		pageCount = totalCount / pageSize;
	}
	
	List<Branch> branches = new ArrayList<Branch>();
	
	if (request.getAttribute("branchs") != null){
		branches = (List<Branch>) request.getAttribute("branchs");
	} else {
		branches = librarianService.getAllBranches(1, null);
	}
%>

<script>

	function search(pageNo){
		$.ajax({
			url: "showBranch",
			data: {
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#showBranch').html(data);
		})
		$.ajax({
			url: "searchBranch",
			data:{
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#branchTable').html(data);
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

<html>
	<!--main content start-->
	<section id="main-content">
		<section class="wrapper">
		<!--overview start-->
			<div class="row">
				<div class="col-lg-12">
					<%
						String result = (String) request.getAttribute("resulBranch");
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
					<h3 class="page-header">Library Branches</h3>
						<form action="addBranch" method="post">
							<button type="submit" class="btn btn-sm btn-success" href='addBranch.jsp'>Add Branch</button>
						</form>
							<br/> <br/>
							
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
		
		<nav aria-label="Page navigation" id="showAuthor">
				
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
					<table class="table table-striped" id="branchTable">					
						<tr>
							<th>#</th>
							<th>Branch name</th>
							<th>Branch Address</th>
							<th>Edit</th>
							<th>Delete</th> 
						</tr>				
						<% 
							for(Branch branch: branches) {
						%>
	 					<tr>
	 						<td><%=branches.indexOf(branch) + 1%></td>  
							<td><%=branch.getBranchName()%></td>
							<td><%=branch.getBranchAddress()%></td>
							<td><button class="btn btn-sm btn-primary" data-toggle="modal"	data-target="#myModal1"	
									href='editBranch.jsp?branchId=<%=branch.getBranchId()%>'>Edit</button></td>
							<td><form action="deleteBranch" method="post">
									<button id="delete" value='<%=branch.getBranchId()%>'
										class="btn btn-sm btn-danger" href='deleteBranch.jsp?branchId=<%=branch.getBranchId()%>'
										onclick="return confirm('Are you sure you want to delete Branch <%=branch.getBranchName()%>?');">Delete</button>
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
</html>