<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="header.jsp">
	<jsp:param value="admin" name="type"/>
	<jsp:param value="sign up" name="title"/>
	<jsp:param value="Sign Up" name="h2"/>
</jsp:include>

<c:if test="${errors!=null}">
			<div class="alert alert-danger">
				<ul>
					<c:forEach var="error" items="${errors}">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
</c:if>

<div class="container">
	
<div class="row">
	
<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">

<form method="POST" action="Controller?action=signup" novalidate="novalidate" role="form">
	
	<legend class="sr-only">Sign up</legend>
	<div class="form-group">
		<label for="companyName">Naam van bedrijf: </label>
		<input type="text" class="form-control" id="companyName" name="companyName" value="<c:out value="${prevCompany}"/>">
	</div>
	<div class="form-group">
		<label for="contactName">Naam van contactpersoon: </label>
		<input type="text" class="form-control" id="contactName" name="contactName" value="<c:out value="${prevContactName}"/>">
	</div>
	<div class="form-group">
		<label for="email">Email van contactpersoon: </label>
		<input type="text" class="form-control" id="email" name="email" value="<c:out value="${prevEmail}"/>">
	</div>
	
		<button type="submit" class="btn btn-primary" id="submit" value="registreer bedrijf"> Registreer bedrijf </button>
	
</form>
</div>
</div>
</div>
</body>
</html>