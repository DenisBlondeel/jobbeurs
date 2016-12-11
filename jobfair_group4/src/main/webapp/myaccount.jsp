<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp">
	<jsp:param value="Jobbeurs - ${sessionScope.user.companyName}" name="title"/>
</jsp:include>


<main>


<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">

	<h3>Instellingen voor ${sessionScope.user.companyName}</h3>
	<form method="POST" action="Controller?action=updateaccount" novalidate="novalidate" role="form">
		<fieldset>
		<legend>Gebruikersnaam</legend>
			<div class="form-group">
			  <p>${sessionScope.user.userID}</p>
			</div>
		</fieldset>
		
		<fieldset>
		<legend>Bedrijfsnaam</legend>
			<div class="form-group">
			  <p>${sessionScope.user.companyName}</p>
			</div>
		</fieldset>
		
		<fieldset>
		<legend>Contactpersoon</legend>
			<div class="form-group">
				<label for="contactname">Naam</label>
				<input type="text" name="contactname" value="${sessionScope.user.contactName}">
			</div>
			<div class="form-group">
				<label for="email">Email</label>
				<input type="text" name="email" value="${sessionScope.user.email}">
			</div>
		</fieldset>
		
		<fieldset>
			<legend>Wachtwoord</legend>
			<div class="form-group">
				<label for="currpass">Huidige wachtwoord</label>
				<input type="password" name="currpass" value="">
			</div>
			<div class="form-group">
				<label for="newpass">Nieuw wachtwoord</label>
				<input type="password" name="newpass" value="">
			</div>
			<div class="form-group">
				<label for="reppass">Herhaal nieuwe wachtwoord</label>
				<input type="password" name="reppass" value="">
			</div>
		</fieldset>
		
		<input type="submit" class="btn btn-primary" name="submit" value="Wijzigingen opslaan">

		
	</div></div></div>
</main>

</body>
</html>