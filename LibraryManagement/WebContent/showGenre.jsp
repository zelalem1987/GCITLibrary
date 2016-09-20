
<%@include file="index.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.training.library.entity.Genre"%>
<%@page import="com.gcit.training.library.service.AdministratorService" %>
<%@page import="com.gcit.training.library.client.AdministratorServlet" %>
<%@page import="java.util.List"%>

<%
	AdministratorService adminService = AdministratorService.getInstance();
	Integer totalCount = adminService.getGenreCount();
	Integer pageSize = 10;
	Integer pageCount = 0;
	
	if(totalCount % pageSize > 0){
		pageCount = totalCount / pageSize + 1;
	}
	else {
		pageCount = totalCount / pageSize;
	}
	
	List<Genre> genres = new ArrayList<Genre>();
	
	if (request.getAttribute("genres") != null){
		genres = (List<Genre>) request.getAttribute("genres");
	} else {
		genres = adminService.getAllGenre(1, null);
	}

%>

<script>

	function search(pageNo){
		$.ajax({
			url: "showGenre",
			data: {
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#showGenre').html(data);
		})
		$.ajax({
			url: "searchGenre",
			data:{
				searchString: $('#searchString').val(),
				pageNo: pageNo
			}
		}).done(function(data){
			$('#genreTable').html(data);
		})
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
						String result = (String) request.getAttribute("resultGenre");
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
					<h3 class="page-header">Genre</h3>
					<%
						}
					%>
				</div>
			</div>
	
			<div class="input-group">
				<form action="searchGenre" method="post">
					<input type="text" class="form-control" placeholder="Search by name"
						name="searchString" aria-describedby="basic-addon1">
					<button type="submit">Search</button>
				</form>
			</div>

			<nav aria-label="Page navigation" id="showGenre">
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
						<div class="form-group">
							<table class="table table-striped" id="genreTable">					
								<tr>
									<th>#</th>
									<th>Genre name</th>
									<th>Edit</th>
									<th>Delete</th>
								</tr>				
								<% 
									for(Genre genre: genres) {
								%>
			 					<tr>
			 						<td><%=genres.indexOf(genre) + 1%></td>  
									<td><%=genre.getGenreName()%></td>
									<td><button class="btn btn-sm btn-primary" data-toggle="modal"	data-target="#myModal1"	
											href='editGenre.jsp?genre_id=<%=genre.getGerneId()%>'>Edit</button>
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
</html>
<!--main content end-->