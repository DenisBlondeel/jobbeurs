<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<jsp:include page="header.jsp">
		<jsp:param value="Jobbeurs - Reset" name="title"/>
		<jsp:param value="Wachtwoord vergeten" name="h2"/>
	</jsp:include>

<div class="row">
	<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
		<form action="Controller?action=confirmresetpw" method="POST" role="form">
			<legend class="sr-only">Wachtwoord vergeten</legend>
			<div class="form-group">
			<p>Geef hieronder je gebruikersnaam door om een nieuw wachtwoord via mail te verkrijgen.</p>
			</div>
			<div class="form-group">
				<label for="userID">Gebruikersnaam: </label>
				<input type="text" id="userID" name="userID" value='<c:out value="${prevUserID}"></c:out>'>
				<input class="btn btn-primary" type="submit" name="submit" value="Reset">
				<input class="btn btn-primary" type="submit" name="submit" value="Annuleer">
			</div>
		</form>
	</div>
</div>

<jsp:include page="footer.jsp"/>