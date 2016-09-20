
<%@include file="index.jsp" %>
<%@page import="com.gcit.training.library.entity.Author"%>
<%@page import="com.gcit.training.library.service.AdministratorService" %>

<%
	Integer authorId = Integer.parseInt(request.getParameter("authorId"));
	AdministratorService adminService = AdministratorService.getInstance();
	Author author = adminService.getAuthorById(authorId);
%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<form action="updateAuthor" method="post">
			<input type="text" style="width: 400px;" name="authorName"
				value='<%=author.getAuthorName()%>' class="form-control"
				placeholder="Enter Author's name to Edit" required autofocus /><br />
			<input type="hidden" name="authorId" value=<%=author.getAuthorId()%>>
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