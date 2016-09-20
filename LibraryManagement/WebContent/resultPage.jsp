<%@include file="index.jsp"%>
<!--main content start-->

<section id="main-content">
	<section class="wrapper">
		<!--overview start-->
		<div class="row">
			<div class="col-lg-12">
				<h3 class="page-header">Dashboard</h3>
			</div>
		</div>

		<div class="row">

			<%
				String result = (String) request.getAttribute("result");
			%>
				<%
					if (result != null) {
				%>
				<div class="col-lg-3 col-md-8 col-s-12 col-xs-12">
					<div class="info-box-result blue-bg">
						<div class="count"><%=result%></div>
					</div>
				</div>
	
				<%
					}
				%>
		</div>
		<!--/.row-->
	</section>
</section>
<!--main content end-->