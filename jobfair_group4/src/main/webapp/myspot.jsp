<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header.jsp">
	<jsp:param value="Mijn plaats" name="title"/>
	<jsp:param value="current" name="myspot"/>
	<jsp:param value="Plaats   ${spotnr} - ${company}" name="h2"/>
</jsp:include>

<ul>
	<li>
		<p>Aantal stoelen: ${chairs}</p>
	</li>
	<li>
		<p>Aantal tafels: ${tables}</p>
	</li>
	<li>
		<p>Elektriciteit: ${electricity}</p>
	</li>
	<li>
		<p>Extra opmerkingen:</p>
		<p>${extra}</p>
	</li>
</ul>

<form method="POST" action="Controller?action=update">
	<p>
		<input type="hidden" name="spotnr" value="${spotnr}">
		<input type="submit" value="Wijzig voorkeuren">
	</p>
</form>
<form method="POST" action="Controller?action=delete">
	<p>
		<input type="hidden" name="spotnr" value="${spotnr}">
		<input type="submit" value="Annulleer reservering">
	</p>
</form>

</body>
</html>