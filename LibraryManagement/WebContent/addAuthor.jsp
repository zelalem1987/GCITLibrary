<%@include file="index.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading"> Add Author </header>
				<div class="panel-body">
					<form action="addAuthor" method="post">
						<div class="form-group">
							<input type="text" name="authorName" class="form-control" placeholder="Author Name">
						</div>
						<button type="submit" class="btn btn-md btn-success">Add</button>
					</form>

				</div>
			</section>
		</div>
	</section>
</section>