<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="UTF-8" content="">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>${param.title}</title>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="css/bootstrap.css" >
		<link rel="stylesheet" href="css/font-awesome.css" >
		<link rel="stylesheet" href="css/style.css">
		<!-- jQuery -->
		<script src="http://code.jquery.com/jquery.js"></script>
		<!-- Bootstrap JavaScript -->
		<script src="js/bootstrap.js"></script>

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div>
		<header>
			<nav class="navbar navbar-default" role="navigation">
			    <div class="container-fluid">
			        <!-- Brand and toggle get grouped for better mobile display -->
			        <div class="navbar-header">
			            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
			                <span class="sr-only">Toggle navigation</span>
			                <span class="icon-bar"></span>
			                <span class="icon-bar"></span>
			                <span class="icon-bar"></span>
			            </button>
			            <h1 class="h1-normal"><a class="navbar-brand" href="Controller?action=home">Jobfair 2017</a></h1>
			        </div>
			
			        <!-- Collect the nav links, forms, and other content for toggling -->
			        <div class="collapse navbar-collapse navbar-ex1-collapse">
			            <ul class="nav navbar-nav"><li id="${param.home}"><a href="Controller?action=home">Home</a></li>
			                <li id="${param.spotoverview}"><a href="Controller?action=spots">Alle plaatsen</a></li>
			                
			                <c:choose>
								<c:when test="${userid!=null}">
					                <li id="${param.myspot}"><a href="Controller?action=myspot">Mijn plaats</a></li>
				                </c:when>
				                </c:choose>
			                <!--<li id="${param.myaccount}"><a href="Controller?action=home">Mijn account</a></li>-->
			            </ul>
			            
			            <ul class="nav navbar-nav navbar-right">	
		
							<c:choose>
								<c:when test="${userid!=null}">
					                <li><a href="Controller?action=logOut">${userid} uitloggen</a></li>
				                </c:when>
				                <c:otherwise>
					                <li><a href="login.jsp">login</a></li>
				                </c:otherwise>
			                </c:choose>
			            </ul>
			        </div><!-- /.navbar-collapse -->
			    </div>
			</nav>

		<h2>${param.h2}</h2>
		<c:if test="${errors!=null}">
			<div class="alert alert-danger">
				<ul>
					<c:forEach var="error" items="${errors}">
						<li>${error}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<c:if test="${success!=null}">
			<div class="alert alert-danger">
				<p>${success}</p>
			</div>
		</c:if>
		
	</header>