<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
	<jsp:param value="admin" name="type" />
	<jsp:param value="Admin" name="title" />
	<jsp:param value="Welkom, admin" name="h2" />
</jsp:include>
	
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<form method="POST" action="Controller?action=upload" enctype="multipart/form-data">
			<legend>Meerdere bedrijven toevoegen</legend>
			<div class="form-group form-inline">
				<label for="file">File: </label>
				<input type="file" class="form-control" id="file" name="file" value="Upload file">
				<button type="submit" class="btn btn-primary" value="Upload file">Upload file</button>
			</div>
		</form>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<form method="POST" action="Controller?action=setdate">
			<legend>Verander eind datum</legend>
			<div class="form-group form-inline">
				<label for="datum">Eind datum:  </label>
				<input type="text" name="datum" class="form-control" id="datum">
				<button type="submit" class="btn btn-primary" value="Submit">Submit</button>
			</div>
		</form>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<h4 class="bordboth4">Bedrijf Manager</h4>
		<a class="btn btn-primary" href = "Controller?action=gotosignup">Voeg Bedrijf toe</a>
		<a class="btn btn-danger" href = "Controller?action=getBedrijven">Verwijder Bedrijf</a>
		<a class="btn btn-danger" href="Controller?action=drop">Verwijder alle Bedrijven</a>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<h4 class="bordboth4">Link bedrijven aan vrije plaatsen</h4>
		<a class="btn btn-primary" href = "Controller?action=linkSpot">Link bedrijven aan vrije plaatsen</a>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<h4 class="bordboth4">Beheerders</h4>
		<a class="btn btn-primary" href = "Controller?action=toAddAdmin">Voeg een beheerder toe</a>
		<a class="btn btn-danger" href = "Controller?action=getAdmin">Verwijder een beheerder</a>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<form method="POST" action="Controller?action=endMail">
			<legend>Stuur een herinneringsmail naar bedrijven zonder spot</legend>
			<div class="form-group form-inline">
				<button type="submit" class="btn btn-primary" value="Submit">Stuur herinneringsmail</button>
			</div>
		</form>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<h4 class="bordboth4">Toon alle bedrijven</h4>
		<a class="btn btn-primary" href = "Controller?action=companies">Lijst met alle bedrijven</a>
		<a class="btn btn-primary" href = "Controller?action=download">Download lijst met alle bedrijven</a>
	</div>
</div>
<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<h4 class="bordboth4">Handleiding</h4>
		<a class="btn btn-primary" href="files/Readme.pdf" target="_blank">Bekijk de handleiding</a>
	</div>
</div>

<jsp:include page="footer.jsp"/>