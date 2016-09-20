
<%@include file="index.jsp" %>
<%@page import="com.gcit.training.library.entity.Branch"%>
<%@page import="com.gcit.training.library.service.LibrarianService" %>

<%
	Integer branchId = Integer.parseInt(request.getParameter("branchId"));
	LibrarianService librarianService = LibrarianService.getInstance();
	Branch branch = librarianService.getBranchById(branchId);
%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<form action="updateBranch" method="post">
			<input type="text" style="width: 400px;" name="branchName"
				value='<%=branch.getBranchName()%>' class="form-control"
				placeholder="Enter Author's name to Edit" required autofocus /><br />
			<input type="text" style="width: 400px;" name="branchAddress"
				value='<%=branch.getBranchAddress()%>' class="form-control"
				placeholder="Enter Author's name to Edit" required autofocus /><br />
			<input type="hidden" name="branchId" value=<%=branch.getBranchId()%>>
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