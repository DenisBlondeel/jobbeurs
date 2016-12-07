<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="">
	<jsp:include page="header.jsp">
		<jsp:param value="Jobbeurs - Home" name="title"/>
		<jsp:param value="current" name="home"/>
		<jsp:param value="UCLL Jobbeurs 2017" name="h2"/>
	</jsp:include>
	<body>
		<!-- <h1 class="text-center">Hello World</h1>
		<h1>test ! </h1> -->
		<c:if test="${spotnr!=null}">
			<h3>Plaats ${spotnr} werd gereserveerd.</h3>
		</c:if>

		<a href="index.jsp"><i class="fa fa-home" aria-hidden="true"></i></a>
		<a href="imgmap.jsp">hemisfeer</a>

		<!-- jQuery -->
		<script src="//code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.js"></script>
		
	</body>
</html>