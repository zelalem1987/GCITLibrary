<%@include file="index.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading"> Add Library branch </header>
				<div class="panel-body">
					<form action="addBranch" method="post">
						<div class="form-group">
							<input type="text" name="branchName" class="form-control" placeholder="Library Branch Name">
							<input type="text" name="branchAddress" class="form-control" placeholder="Library Branch Address">
						</div>
						<button type="submit" class="btn btn-success">Add</button>
					</form>

				</div>
			</section>
		</div>
	</section>
</section>