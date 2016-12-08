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


<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
	<h3>Meerdere bedrijven toevoegen</h3>

	<form method="POST" action="Controller?action=upload" enctype="multipart/form-data">
		<legend>Admin upload</legend>
		<div class="form-group form-inline">
			<label for="file">File: </label>
			<input type="file" class="form-control" id="file" name="file" value="Upload file">
			<button type="submit" class="btn btn-primary" value="Upload file">Upload file</button>
		</div>
	</form>
</div>
</div><div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
	<form method="POST" action="Controller?action=setdate">
		<legend>Verander eind datum</legend>
		<div class="form-group form-inline">
			<label for="datum">Eind datum:  </label>
			<input type="date" name="datum" class="form-control" id="datum">
			<button type="submit" class="btn btn-primary" value="Submit">Submit</button>
		</div>
	</form>
	<form method="POST" action="Controller?action=drop">
		<legend>Verwijder alle klanten</legend>
		<div class="form-group form-inline">
			<button type="submit" class="btn btn-primary" value="Submit">Verwijder</button>
		</div>
	</form>

	</div></div></div>
</body>
</html>