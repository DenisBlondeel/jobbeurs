<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="">
	<jsp:include page="header.jsp">
		<jsp:param value="Jobbeurs - Login" name="title"/>
		<jsp:param value="current" name="Login"/>
		<jsp:param value="Login" name="h2"/>
	</jsp:include>
	<body>
	
	

<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">


	


<form action="Controller?action=logIn" method="POST" role="form">
	<legend class="sr-only">Login Form</legend>

	<div class="form-group">
		<label for="username">username</label>
		<input type="text" class="form-control" id="username" placeholder="username" name="username" value="${userid}" autofocus>
	</div>
	<div class="form-group">
		<label for="password">password</label>
		<input type="password" class="form-control" id="password" placeholder="password" name="password">
	</div>

	

	<button type="submit" class="btn btn-primary">Submit</button>
</form>
</div></div></div>
	
	
		
		<!-- jQuery -->
		<script src="//code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.js"></script>
		
	</body>
</html>