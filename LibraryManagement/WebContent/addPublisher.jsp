<%@include file="index.jsp"%>
<section id="main-content">
	<section class="wrapper">
		<div class="col-lg-6">
			<section class="panel">
				<header class="panel-heading"> Add Publisher </header>
				<div class="panel-body">
					<form action="addPublisher" method="post">
						<div class="form-group">
							<input type="text" name="publisherName" class="form-control" placeholder="Publisher Name">
							<input type="text" name="publisherAddress" class="form-control" placeholder="Publisher Address">
							<input type="text" name="publisherPhone" class="form-control" placeholder="Publisher Phone">
						</div>
						<button type="submit" class="btn btn-primary">Add</button>
					</form>

				</div>
			</section>
		</div>
	</section>
</section>