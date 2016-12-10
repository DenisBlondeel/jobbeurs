<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="header.jsp">
	<jsp:param value="admin" name="type" />
	<jsp:param value="Delete admin" name="title" />
	<jsp:param value="Verwijder beheerder" name="h2" />
</jsp:include>


<div class="container">
	
<div class="row">

<div class="col-xs-12 col-sm-12 col-md-8 col-lg-6">
	<p>Selecteer hieronder de beheerder die je wenst te verwijderen.</p>
	<form method="POST" action="Controller?action=deleteAdmin">
		<div class="form-group">
			<label for="adminID">Beheerders: </label>
			<select class="form-control" name="adminID" id="adminID">
				<c:forEach var="admin" items="${admins}">
					<option value="<c:out value="${admin.userID}"/>"><c:out value="${admin.userID}"/></option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="password">Je Wachtwoord: </label>
			<input type="password" class="form-control" id="password" placeholder="password" name="password">
		</div>
		<div class="form-group">
			<button type="submit" class="btn btn-primary" value="Verwijder admin">Verwijder beheerder</button>
		</div>
	</form>
</div>
</div></div>
</body>
</html>