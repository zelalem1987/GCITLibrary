<%@include file="index.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading"> Borrower card number </header>
				<div class="panel-body">
					<form action="borrowPage" method="post">
						<div class="form-group">
							<input type="text" name="borrowerCardNo" class="form-control" placeholder="Card Number">
						</div>
						<button type="submit" class="btn btn-md btn-success">Add</button>
					</form>

				</div>
			</section>
		</div>
	</section>
</section>