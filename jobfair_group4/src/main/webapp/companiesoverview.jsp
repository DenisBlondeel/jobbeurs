<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
	<jsp:param value="current" name="companiesoverview" />
	<jsp:param value="Overzicht" name="title"/>
	<jsp:param value="Overzicht van alle bedrijven" name="h2" />
</jsp:include>

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">

		<table class="table table-striped">
			<tr>
				<th><a href="Controller?action=companies">Bedrijfsnaam</a></th>
				<th><a href="Controller?action=companiesContact">Contactnaam</a></th>
				<th><a href="Controller?action=companiesEmail">Email</a></th>
				<th><a href="Controller?action=companiesSpotid">Plaats</a></th>

			</tr>
			<c:forEach var="company" items="${companies}" varStatus="status">
				<tr>
					<td>${company.companyName}</td>
					<td>${company.contactName}</td>
					<td>${company.email}</td>
					<td>${spotsTaken[status.index]}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</div>

<jsp:include page="footer.jsp"/>