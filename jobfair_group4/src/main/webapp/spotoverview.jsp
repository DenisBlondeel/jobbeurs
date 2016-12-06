<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Overzicht van plaatsen</title>
</head>
<body>


	<table>
	<thead> plaatsid </thead>
	<thead> reserveer </thead>
	<c:foreach var="spot" items="${spots}">
	<td>${spot["spotid"]}</td>
	<td>${spot["companyName"]}</td>
	<td> <a href="Controller?action=spotoptions&id=${spot.spotid}"> reserveer plaats </a> </td>
	</c:foreach>
	</table>


</body>
</html>