<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">
	<jsp:include page="header.jsp">
		<jsp:param value="Wachtwoord vergeten" name="title"/>
		<jsp:param value="Wachtwoord vergeten" name="h2"/>
	</jsp:include>
	<body>
	
	

<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">


	


<form action="Controller?action=confirmresetpw" method="POST" role="form">
	<legend class="sr-only">Nieuw wachtwoord</legend>
	<div class="form-group">
	<p>${result}</p>
	</div>
	<div class="form-group">
		<c:if test="${showReset!=null}">
			<input type="hidden" name="email" value="${email}">
			<input type="hidden" name="userid" value="${userid}">
			<input class="btn btn-primary" type="submit" name="submit" value="Reset">
		</c:if>
		<input class="btn btn-primary" type="submit" name="submit" value="Annuleer">
	</div>
</form>
</div></div></div>
	
	
		
		<!-- jQuery -->
		<script src="//code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.js"></script>
		
	</body>
</html>
</html>