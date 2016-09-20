<%@include file="index.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading"> Add Borrower </header>
				<div class="panel-body">
					<form action="addBorrower" method="post">
						<div class="form-group">
							<input type="text" name="borrowerName" class="form-control" placeholder="Borrower Name">
						</div>
						<div class="form-group">
							<input type="text" name="borrowerAddress" class="form-control" placeholder="Borrower Address">
						</div>
						<div class="form-group">
							<input type="text" name="borrowerPhone" class="form-control" placeholder="Borrower Phone">
						</div>
						<button type="submit" class="btn btn-md btn-success">Add</button>
					</form>

				</div>
			</section>
		</div>
	</section>
</section>