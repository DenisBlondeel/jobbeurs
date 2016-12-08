<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="header.jsp">
	<jsp:param value="admin" name="type" />
	<jsp:param value="Admin" name="title" />
	<jsp:param value="Welkom, admin" name="h2" />
</jsp:include>
<h3>Meerdere bedrijven toevoegen</h3>

<form method="POST" action="Controller?action=upload"
	enctype="multipart/form-data">
	File: <input type="file" name="file" accept=".csv" /> <input
		type="submit" value="Upload file" />
</form>

<form method="POST" action="Controller?action=setdate">
	Eind datum: <input type="date" name="datum">
	<input type="submit" name="submit" value="submit"><br>
</form>
</body>
</html>