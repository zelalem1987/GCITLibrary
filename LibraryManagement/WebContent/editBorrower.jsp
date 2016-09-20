
<%@include file="index.jsp" %>
<%@page import="com.gcit.training.library.entity.Borrower"%>
<%@page import="com.gcit.training.library.service.AdministratorService" %>

<%
	Integer borrowerCardNo = Integer.parseInt(request.getParameter("borrowerCardNo"));
	AdministratorService adminService = AdministratorService.getInstance();
	Borrower borrower = adminService.getBorrowerById(borrowerCardNo);
%>

<div class="modal-content">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal">&times;</button>
		<form action="updateBorrower" method="post">
			<input type="text" style="width: 400px;" name="borrowerName"
				value='<%=borrower.getBorrowerName()%>' class="form-control"
				placeholder="Enter Borrower's name to Edit" required autofocus /><br />
			<input type="text" style="width: 400px;" name="borrowerAddress"
				value='<%=borrower.getBorrowerAddress()%>' class="form-control"
				placeholder="Enter Borrower's address to Edit" required autofocus /><br />
			<input type="text" style="width: 400px;" name="borrowerPhone"
				value='<%=borrower.getBorrowerPhone()%>' class="form-control"
				placeholder="Enter Borrower's phone to Edit" required autofocus /><br />
			<input type="hidden" name="authorId" value=<%=borrower.getBorrowerCardNo()%>>
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