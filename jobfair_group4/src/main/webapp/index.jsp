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
		<c:if test="${user!=null}">
			<h3>Welkom ${user.companyName}!</h3>
		</c:if>
		
		<c:if test="${action==reserveer}">
			<h3>Plaats ${spotnr} werd gereserveerd.</h3>
		</c:if>
		
		<c:if test="${action==update}">
			<h3>Plaats ${spotnr} werd geüpdatet.</h3>
		</c:if>
		
<!--  	<c:if test="${userid!=null}">
			<a href="imgmap.jsp">hemisfeer</a>
		</c:if>-->
		
			<!--  a href="Controller?action=imgmap">hemisfeer</a>-->
<!-- 			<a href="imgmap.jsp">hemisfeer</a> -->

		<jsp:include page="imgmap.jsp"></jsp:include>

	</body>
</html>