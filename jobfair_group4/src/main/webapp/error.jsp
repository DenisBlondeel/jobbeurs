<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="header.jsp">
	<jsp:param value="Error" name="title" />
</jsp:include>
<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
			<article>
				<h1>Oh dear!</h1>
				<p>Something went wrong :(</p>
				
				<p><a href="Controller">Home</a></p>
			</article>
			<article>
				<h3>Error ${pageContext.errorData.statusCode}</h3>
				<p>URL: ${pageContext.errorData.requestURI}</p>
				<p>${pageContext.exception}</p>
			</article>
</div></div></div>
</body>
</html> 