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
	<jsp:param value="Tools voor beheerders" name="h2" />
</jsp:include>


<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">


	<form method="POST" action="Controller?action=upload" enctype="multipart/form-data">
		<legend><i class="fa fa-upload"></i> Bedrijven importeren</legend>
		<div class="form-group form-inline">
			<p>Gebruik deze tool om meerdere bedrijven tegelijk te importeren uit een '.csv' bestand.</p>
			<p>Kijk ook na of het juist is ingedeeld; 'bedrijfsnaam;contactpersoon;email;...' 
				(deze indeling is vereist voor het correct weergeven van de informatie op deze website).</p>
			<label for="file">Bestand: </label>
			<input type="file" class="form-control" id="file" name="file" value="Bestand uploaden">
			<button type="submit" class="btn btn-primary" value="Bestand uploaden"><i class="fa fa-upload"></i> Bestand uploaden</button>
		</div>
	</form>
</div>
</div><div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
	<form method="POST" action="Controller?action=setdate">
		<legend><i class="fa fa-clock-o"></i> Verander eind datum</legend>
		<div class="form-group form-inline">
			<p>Vanaf deze datum zullen geregistreerde bedrijven geen wijzigingen meer kunnen toepassen op hun reservering.</p>
			<label for="datum">Eind datum:  </label>
			<input type="text" name="datum" class="form-control" id="datum" placeholder="dd-mm-yyyy">
			<button type="submit" class="btn btn-primary" value="Toepassen">Toepassen</button>
		</div>
	</form>
	</form>
	<legend><i class="fa fa-wrench"></i> Bedrijf Manager</legend>
		<div class="form-group form-inline">
			<p>Deze tool geeft je de mogelijkheid om bedrijven toe te voegen en te verwijderen.
				Na afloop van de jobbeurs kunnen ook alle bedrijven verwijderd worden.</p>
			<a class="btn btn-primary" href="Controller?action=gotosignup">Voeg Bedrijf toe</a>
			<a class="btn btn-primary" href="Controller?action=getBedrijven">Verwijder Bedrijf</a>
			<a class="btn btn-primary" href="confirmdeleteAll.jsp">Verwijder alle Bedrijven</a>
		</div>
	<legend><i class="fa fa-link"></i> Link bedrijven aan vrije plaatsen</legend>
		<p>Met deze tool kan je een bedrijf zonder plaats een plaats toekennen.</p>
		<div class="form-group form-inline">
	<a class="btn btn-primary" href = "Controller?action=linkSpot">Link bedrijven aan vrije plaatsen</a>
	</div>
	<legend><i class="fa fa-lock"></i> Beheerders</legend>
		<p>Gebruik deze tool om nieuwe beheerders toe te voegen of om een beheerder te verwijderen</p>
		<div class="form-group form-inline">
	<a class="btn btn-primary" href = "Controller?action=toAddAdmin">Voeg een beheerder toe</a>
	<a class="btn btn-primary" href = "Controller?action=getAdmin">Verwijder een beheerder</a>
	</div>
	
	<form method="POST" action="Controller?action=endMail">
		<legend><i class="fa fa-bell"></i> Herinnering</legend>
		<p>Als u merkt dat een aantal bedrijven nog geen plaats gekozen hebben en de deadline nadert, 
			kan u deze tool gebruiken om naar die bedrijven een herinneringsmail te sturen</p>
		<div class="form-group form-inline">
			<button type="submit" class="btn btn-primary" value="Submit">Stuur herinneringsmail</button>
		</div>
	</form>
	<legend><i class="fa fa-list-ul"></i> Alle bedrijven</legend>
		<p>Deze tool toont u een overzicht van alle bedrijven en biedt ook de mogelijkheid om een '.csv' 
			bestand te downloaden in hetzelfde formaat dat u nodig heeft voor de upload-tool.</p>
		<div class="form-group form-inline">
	<a class="btn btn-primary" href = "Controller?action=companies">Lijst met alle bedrijven</a>
	<a class="btn btn-primary" href = "Controller?action=download"><i class="fa fa-download"></i> Download lijst</a>
	</div>
	<legend><i class="fa fa-file-text"></i> Handleiding</legend>
		<p>Niet meer zeker hoe u deze site optimaal kan benutten? Gebruik dan deze tool om de handleiding te bekijken.</p>
		<div class="form-group form-inline">
			<a class="btn btn-primary" href="files/Readme.pdf" target="_blank">Bekijk de handleiding</a>
		</div>
	</div></div></div>
	
</body>
</html>