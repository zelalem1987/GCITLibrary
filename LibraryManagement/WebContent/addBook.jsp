<%@page import="com.gcit.training.library.service.AdministratorService"%>
<%@include file="index.jsp"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.training.library.entity.Publisher" %>
<%@page import="com.gcit.training.library.entity.Author" %>
<%@page import="com.gcit.training.library.entity.Genre" %>

<%
	List<Author> authors = (List<Author>) AdministratorService.getInstance().getAllAuthors(1, null);
	List<Publisher> publishers = (List<Publisher>) AdministratorService.getInstance().getAllPublishers(1, null);
	List<Genre> genres = (List<Genre>) AdministratorService.getInstance().getAllGenre(1, null);
%>

<section id="main-content">
	<section class="wrapper">
		<div class="col-lg-6">authorList
			<section class="panel">
				<header class="panel-heading"> Add Books </header>
				<div class="panel-body">
					<form action="addBook" method="post">
						<div class="form-group">
							<input type="text" name="bookTitle" class="form-control" placeholder="Book title">
							<br/>
							<div class="panel-heading">
								<label class="control-label col-lg-2">Author:</label> <select name="authorId">
									<%
										for (Author author : authors) {
									%>
									<option value=<%=author.getAuthorId()%>><%=author.getAuthorName()%></option>
									<%
										}
									%>
								</select> <label class="control-label col-lg-2">Publisher:</label> <select
									name="pubId">
									<%
										for (Publisher publisher : publishers) {
									%>
									<option value="<%=publisher.getPublisherId()%>"><%=publisher.getPublisherName()%></option>
									<%
										}
									%>
								</select> <label class="control-label col-lg-2">Genre:</label> <select
									name="genre_id">
									<%
										for (Genre genre : genres) {
									%>
									<option value="<%=genre.getGerneId()%>"><%=genre.getGenreName()%></option>
									<%
										}
									%>
								</select><br />
							</div>
						</div>
						<button type="submit" class="btn btn-md btn-success">Add</button>
					</form>

				</div>
			</section>
		</div>
	</section>
</section>