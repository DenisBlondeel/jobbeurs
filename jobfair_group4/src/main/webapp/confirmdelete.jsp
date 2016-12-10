<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="header.jsp">
	<jsp:param value="Confirm" name="title"/>
</jsp:include>
<h3>Reservering van plaats ${spotnr} voor ${user.companyName} annuleren?</h3>
<form method="POST" action="Controller?action=confirmdelete">
	<div class="form-group">
	<p>	<input type="hidden" name="spotnr" value="${spotnr }">
		<input type="submit" name="submit" value="ja" class="btn btn-primary"> 
		<input type="submit" name="submit" value="neen" class="btn btn-primary">
	</p>
	</div>
</form>

</body>
</html>