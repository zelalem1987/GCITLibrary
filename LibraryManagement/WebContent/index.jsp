<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Creative - Bootstrap 3 Responsive Admin Template">
<meta name="author" content="GeeksLabs">
<meta name="keyword"
	content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
<link rel="shortcut icon" href="img/favicon.png">

<title>GCIT Library</title>

<!-- Bootstrap CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- bootstrap theme -->
<link href="css/bootstrap-theme.css" rel="stylesheet">
<!--external css-->
<!-- font icon -->
<link href="css/elegant-icons-style.css" rel="stylesheet" />
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!-- owl carousel -->
<link rel="stylesheet" href="css/owl.carousel.css" type="text/css">
<link href="css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
<!-- Custom styles -->
<link rel="stylesheet" href="css/fullcalendar.css">
<link href="css/widgets.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet" />
<link href="css/xcharts.min.css" rel=" stylesheet">
<link href="css/jquery-ui-1.10.4.min.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
</head>

<body>
	<!-- container section start -->
	<section id="container" class="">


		<header class="header dark-bg">
			<div class="toggle-nav">
				<div class="icon-reorder tooltips"
					data-original-title="Toggle Navigation" data-placement="bottom">
					<i class="icon_menu"></i>
				</div>
			</div>

			<!--logo start-->
			<a href="home.jsp" class="logo">GCIT <span class="lite">Library</span></a>
			<!--logo end-->

			<div class="nav search-row" id="top_menu">
				<!--  search form start -->
				<ul class="nav top-menu">
					<li>
						<form class="navbar-form">
							<input class="form-control" placeholder="Search" type="text">
						</form>
					</li>
				</ul>
				<!--  search form end -->
			</div>
		</header>
		<!--header end-->

		<!--sidebar start-->
		<aside>
			<div id="sidebar" class="nav-collapse ">
				<!-- sidebar menu start-->
				<ul class="sidebar-menu">
					<li class="active"><a class="" href="home.jsp"> <i
							class="icon_house_alt"></i> <span>Home</span></a></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_genius"></i> <span>Administrator</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">

							<li class="sub-menu"><a href="javascript:;" class=""> <i
									class="icon_documents_alt"></i> <span>Author</span> <span
									class="menu-arrow arrow_carrot-right"></span>
							</a>
								<ul class="sub-menu">
									<li><a class="" href="showAuthor.jsp">Show Author</a></li>
									<li><a class="" href="addAuthor.jsp">Add Author</a></li>
								</ul>
							<li class="sub-menu"><a href="javascript:;" class=""> <i
									class="icon_documents_alt"></i> <span>Publisher</span> <span
									class="menu-arrow arrow_carrot-right"></span>
							</a>
								<ul class="sub-menu">
									<li><a class="" href="showPublisher.jsp">Show Publisher</a></li>
									<li><a class="" href="addPublisher.jsp">Add Publisher</a></li>
								</ul></li>
							<li class="sub-menu"><a href="javascript:;" class=""> <i
									class="icon_documents_alt"></i> <span>Book</span> <span
									class="menu-arrow arrow_carrot-right"></span>
							</a>
								<ul class="sub-menu">
									<li><a class="" href="showBooks.jsp">Show Book</a></li>
									<li><a class="" href="addBook.jsp">Add Book</a></li>
								</ul>
							</li>
							<li class="sub-menu"><a href="javascript:;" class=""> <i
									class="icon_documents_alt"></i> <span>Genre</span> <span
									class="menu-arrow arrow_carrot-right"></span>
							</a>
								<ul class="sub-menu">
									<li><a class="" href="showGenre.jsp">Show Genre</a></li>
									<li><a class="" href="addGenre.jsp">Add Genre</a></li>
								</ul>
							</li>
							<li class="sub-menu"><a href="javascript:;" class=""> <i
									class="icon_documents_alt"></i> <span>Borrower</span> <span
									class="menu-arrow arrow_carrot-right"></span>
							</a>
								<ul class="sub-menu">
									<li><a class="" href="showBorrower.jsp">Show Borrower</a></li>
									<li><a class="" href="addBorrower.jsp">Add Borrower</a></li>
								</ul>
							</li>
						</ul></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_genius"></i> <span>Librarian</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li class="sub-menu"><a href="javascript:;" class=""> <i
									class="icon_documents_alt"></i> <span>Branch</span> <span
									class="menu-arrow arrow_carrot-right"></span>
							</a>
								<ul class="sub">
									<li><a class="" href="showBranch.jsp">Show Branches</a></li>
									<li><a class="" href="addBranch.jsp">Add Branches</a></li>
								</ul></li>
						</ul></li>
					<li class="sub-menu"><a href="javascript:;" class=""> <i
							class="icon_genius"></i> <span>Borrower</span> <span
							class="menu-arrow arrow_carrot-right"></span>
					</a>
						<ul class="sub">
							<li><a class="" href="checkOutBook.jsp">Book Checkout</a></li>
							<li><a class="" href="showBook.jsp">Books</a></li>
							<li><a class="" href="showBranch.jsp">Branches</a></li>
						</ul></li>
				</ul>
				<!-- sidebar menu end-->
			</div>
		</aside>
		<!--sidebar end-->


	</section>
	<!-- container section start -->

	<!-- javascripts -->
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.10.4.min.js"></script>
	<script src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<!-- bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- nice scroll -->
	<script src="js/jquery.scrollTo.min.js"></script>
	<script src="js/jquery.nicescroll.js" type="text/javascript"></script>
	<!-- charts scripts -->
	<script src="js/jquery.sparkline.js" type="text/javascript"></script>
	<script src="js/owl.carousel.js"></script>
	<!-- jQuery full calendar -->
	<script src="js/fullcalendar.min.js"></script>
	<!-- Full Google Calendar - Calendar -->
	<!--script for this page only-->
	<script src="js/calendar-custom.js"></script>
	<script src="js/jquery.rateit.min.js"></script>
	<!-- custom select -->
	<script src="js/jquery.customSelect.min.js"></script>

	<!--custome script for all page-->
	<script src="js/scripts.js"></script>
	<!-- custom script for this page-->
	<script src="js/sparkline-chart.js"></script>
	<script src="js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="js/xcharts.min.js"></script>
	<script src="js/jquery.autosize.min.js"></script>
	<script src="js/jquery.placeholder.min.js"></script>
	<script src="js/gdp-data.js"></script>
	<script src="js/morris.min.js"></script>
	<script src="js/sparklines.js"></script>
	<script src="js/charts.js"></script>
	<script src="js/jquery.slimscroll.min.js"></script>
	<script>

		//carousel
		$(document).ready(function() {
			$("#owl-slider").owlCarousel({
				navigation : true,
				slideSpeed : 300,
				paginationSpeed : 400,
				singleItem : true

			});
		});

		//custom select box

		$(function() {
			$('select.styled').customSelect();
		});

		/* ---------- Map ---------- */
		$(function() {
			$('#map').vectorMap({
				map : 'world_mill_en',
				series : {
					regions : [ {
						values : gdpData,
						scale : [ '#000', '#000' ],
						normalizeFunction : 'polynomial'
					} ]
				},
				backgroundColor : '#eef3f7',
				onLabelShow : function(e, el, code) {
					el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
				}
			});
		});
	</script>

</body>
</html>
