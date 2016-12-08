<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="header.jsp">
	<jsp:param value="Spot ${spotnr}" name="title"/>
</jsp:include>

<main>
	<h1>Opties voor plaats ${spotnr}: </h1>
	<form method="POST" action="Controller?action=spotoptions&id=${spotnr}" novalidate="novalidate">
		<p><label for="chairs">Aantal stoelen: </label><br>
			<input type="radio" name="chairs" value="0"> 0<br>
			<input type="radio" name="chairs" value="1"> 1<br>
			<input type="radio" name="chairs" value="2" checked> 2
		</p>
		<p><label for="tables">Aantal tafels: </label><br>
			<input type="radio" name="tables" value="0"> 0<br>
			<input type="radio" name="tables" value="1" checked> 1
		</p>
		<p><input type="checkbox" name="electricity" value="Elektriciteit" checked> Elektriciteit
		</p>
		<p><label for="extra">Extra opmerkingen: </label>
		</p>
			<p><input type="text" name="extra" value=""></p>
		<input type="submit" value="reserveer plaats">
	</form>
</main>

</body>
</html>