<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp">
	<jsp:param value="Wijzig Plaats ${spotnr}" name="title"/>
</jsp:include>

<main>
	<h1>Opties voor plaats ${spotnr}: </h1>
	<form method="POST" action="Controller?action=confirmupdate&id=${spotnr}" novalidate="novalidate">
		<label for="chairs">Aantal stoelen: </label><br>
			<input type="radio" name="chairs" value="0" ${ch0}> 0<br>
			<input type="radio" name="chairs" value="1" ${ch1}> 1<br>
			<input type="radio" name="chairs" value="2" ${ch2}> 2<br>
		<label for="tables">Aantal tafels: </label><br>
			<input type="radio" name="tables" value="0" ${tb0}> 0<br>
			<input type="radio" name="tables" value="1" ${tb1}> 1<br>
		<input type="checkbox" name="electricity" value="Elektriciteit" ${el}> Elektriciteit<br>
		<label for="extra">Extra opmerkingen: </label>
			<input type="text" name="extra" value="${extra}">
		<input type="hidden" name="spotnr" value="${spotnr}">
		<input type="submit" value="Toepassen">
	</form>
</main>

</body>
</html>