
<%@include file="index.jsp" %>
<%@page import="com.gcit.training.library.entity.Book"%>
<%@page import="com.gcit.training.library.service.AdministratorService" %>

<%
	Integer bookId = Integer.parseInt(request.getParameter("bookId"));
	AdministratorService adminService = AdministratorService.getInstance();
	Book book = adminService.getBookById(bookId);
%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<form action="updateBook" method="post">
			<input type="text" style="width: 400px;" name="bookTitle"
				value='<%=book.getTitle()%>' class="form-control"
				placeholder="Enter Book name to Edit" required autofocus /><br />
<!-- 			<input type="text" style="width: 400px;" name="authorName" -->
<%-- 				value='<%=book.getAuthors().getAuthorName()%>' class="form-control" --%>
<!-- 				placeholder="Enter Author's name to Edit" required autofocus /><br /> -->
<!-- 			<input type="text" style="width: 400px;" name="publisherName" -->
<%-- 				value='<%=book.getPublish().getPublisherName()%>' class="form-control" --%>
<!-- 				placeholder="Enter Publisher's name to Edit" required autofocus /><br /> -->
<!-- 			<input type="text" style="width: 400px;" name="genreName" -->
<%-- 				value='<%=book.getGenres().getGenreName()%>' class="form-control" --%>
<!-- 				placeholder="Enter Genre's name to Edit" required autofocus /><br /> -->
			<input type="hidden" name="bookId" value=<%=book.getBookId()%>>
			<input type="submit" class="btn btn-primary" />
		</form>
	</div>
</div>
<script>
$(document).ready(function()
		{
		    $('.modal').on('hidden.bs.modal', function(e)
		    { 
		        $(this).removeData();
		    }) ;
		});
</script>