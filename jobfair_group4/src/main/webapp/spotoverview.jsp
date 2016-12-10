<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp">
	<jsp:param value="current" name="spotoverview" />
	<jsp:param value="Overzicht van alle plaatsen" name="h2" />
</jsp:include>


<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">

<table class="table table-striped">
	<tr>
		<th>Plaats </th>
		<th>Bedrijfsnaam </th>
		<th> </th>
		
		<th><a href="Controller?action=spots">Alle plaatsen </a></th>
		<th><a href="Controller?action=vrijelijst">Vrije plaatsen </a></th>
		<th><a href="Controller?action=bezetlijst">Bezette plaatsen</a></th>
		<th><a href="Controller?action=alfabetischelijst">Bezette plaatsen op alfabetische volgorde</a></th>

	</tr>
	<c:forEach var="spot" items="${spots}">
		<tr>
			<td>${spot.spotID}</td>
			<td>${spot.user.companyName}</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>

		</tr>
	</c:forEach>
</table>

</div></div></div>
</body>
</html>