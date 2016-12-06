<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp">
	<jsp:param value="current" name="spotoverview" />
	<jsp:param value="Overzicht van alle plaatsen" name="h2" />
</jsp:include>

<table>
	<thead>plaatsid
	</thead>
	<thead>companyname
	</thead>
	<thead>reserveer
	</thead>
	<thead>
		<a href="Controller?action=vrijelijst">Vrije Plaatsen</a>
	</thead>
	<thead>
		<a href="Controller?action=bezetlijst">Bezette Plaatsen</a>
	</thead>

	<c:forEach var="spot" items="${spots}">
		<tr>
			<td>${spot.spotID}</td>
			<td>${spot.electricity}</td>

		</tr>
	</c:forEach>
</table>


</body>
</html>