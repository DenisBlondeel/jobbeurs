<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp">
	<jsp:param value="current" name="companiesoverview" />
	<jsp:param value="Overzicht van alle bedrijven" name="h2" />
</jsp:include>


<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">

<table class="table table-striped">
	<tr>
		<th>Bedrijfsnaam </th>
		<th>Contactnaam </th>
		<th>Email </th>
		<th>Plaats </th>

	</tr>
	<c:forEach var="spot" items="${spotsTaken}">
		<tr>
			<td>${spot.user.companyName}</td>
			<td>${spot.user.contactName}</td>
			<td>${spot.user.email}</td>
			<td>${spot.spotID}</td>
		</tr>
	</c:forEach>
</table>

</div></div></div>
</body>
</html>