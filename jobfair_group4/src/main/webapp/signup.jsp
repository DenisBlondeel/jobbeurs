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
			<div">
				<ul>
					<c:forEach var="error" items="${errors}">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
</c:if>

<form method="POST" action="Controller?action=signup" novalidate="novalidate">
	<p>
		<label for="companyName">Naam van bedrijf: </label>
		<input type="text" id="companyName" name="companyName" required value="<c:out value="${prevCompany}"/>">
	</p>
	<p>
		<label for="contactName">Naam van contactpersoon: </label>
		<input type="text" id="contactName" name="contactName" required value="<c:out value="${prevContactName}"/>">
	</p>
	<p>
		<label for="email">Email van contactpersoon: </label>
		<input type="text" id="email" name="email" required value="<c:out value="${prevEmail}"/>">
	</p>
	<p>
		<input type="submit" id="submit" value="registreer bedrijf">
	</p>
</form>

</body>
</html>