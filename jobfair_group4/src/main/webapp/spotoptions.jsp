<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Plaats ${spotnr}</title>
</head>
<body>

<main>
	<h1>Opties voor plaats ${spotnr}: </h1>
	<form method="POST" action="Controller?action=spotoptions&id=${spotnr}" novalidate="novalidate">
		<label for="chairs">Aantal stoelen: </label><br>
			<input type="radio" name="chairs" value="0" checked> 0<br>
			<input type="radio" name="chairs" value="1"> 1<br>
			<input type="radio" name="chairs" value="2"> 2<br>
		<label for="tables">Aantal tafels: </label><br>
			<input type="radio" name="tables" value="0" checked> 0<br>
			<input type="radio" name="tables" value="1"> 1<br>
		<input type="checkbox" name="electricity" value="Elektriciteit"> Elektriciteit<br>
		<label for="extra">Extra opmerkingen: </label>
			<input type="text" name="extra" value="">
		<input type="submit" value="reserveer plaats">
	</form>
</main>

</body>
</html>