<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header.jsp">
	<jsp:param value="My Spot" name="title"/>
	<jsp:param value="current" name="myspot"/>
	<jsp:param value="Mijn plaats" name="h2"/>
</jsp:include>

<ul>
	<li>
		<p>Plaatsnr: ${spotnr}</p>
	</li>
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

</body>
</html>