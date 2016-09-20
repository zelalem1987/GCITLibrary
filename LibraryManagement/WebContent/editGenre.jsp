
<%@include file="index.jsp" %>
<%@page import="com.gcit.training.library.entity.Genre"%>
<%@page import="com.gcit.training.library.service.AdministratorService" %>

<%
	Integer genre_id = Integer.parseInt(request.getParameter("genre_id"));
	Genre genre = AdministratorService.getInstance().getGenreById(genre_id);
	
%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<form action="updateGenre" method="post">
			<input type="text" style="width: 400px;" name="genre_name"
				value='<%=genre.getGenreName()%>' class="form-control"
				placeholder="Enter Genre's name to Edit" required autofocus /><br />
			<input type="hidden" name="genre_id" value=<%=genre.getGerneId()%>>
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