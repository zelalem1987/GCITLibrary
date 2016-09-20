<%@include file="index.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading"> Add Genre </header>
				<div class="panel-body">
					<form action="addGenre" method="post">
						<div class="form-group">
							<input type="text" name="genre_name" class="form-control" placeholder="Genre Name">
						</div>
						<button type="submit" class="btn btn-primary">Add</button>
					</form>

				</div>
			</section>
		</div>
	</section>
</section>