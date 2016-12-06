<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp">
	<jsp:param value="current" name="spotoverview"/>
	<jsp:param value="Overzicht van alle plaatsen" name="h2"/>
</jsp:include>

	<table>
		<thead>plaatsid
		</thead>
		<thead>companyname
		</thead>
		<thead>reserveer
		</thead>
		<thead><a href="Controller?action=vrijelijst">Vrije Plaatsen</a>
		</thead>
		<thead><a href="Controller?action=bezetlijst">Bezette Plaatsen</a></thead>
		
		<c:foreach var="spot" items="${spots}">
			<td>${spot["spotid"]}</td>
			<td>${spot["companyName"]}</td>
			<td><a href="Controller?action=spotoptions&id=${spot.spotid}">
					reserveer plaats </a></td>
		</c:foreach>
	</table>


</body>
</html>