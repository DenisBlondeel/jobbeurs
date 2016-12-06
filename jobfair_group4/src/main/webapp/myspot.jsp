<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header.jsp">
	<jsp:param value="My Spot" name="title"/>
	<jsp:param value="current" name="myspot"/>
	<jsp:param value="Mijn plaats" name="h2"/>
</jsp:include>

<table>
	<tr>
		<th>Plaatsnr</th>
		<th>Aantal stoelen</th>
		<th>Aantal tafels</th>
		<th>Electriciteit</th>
		<th>Opmerkingen</th>
	</tr>
	<tr>
		<td>${spotnr}</td>
		<td>${chairs}</td>
		<td>${tables}</td>
		<td>${electricity}</td>
		<td>${extra}</td>
	</tr>
</table>

</body>
</html>