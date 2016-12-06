<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<h1>
				<span>Jobbeurs 2017</span>
			</h1>
		<nav>
			<ul>
				<li id="${param.home}"><a href="Controller?action=home">Home</a></li>
				<li id="${param.spotoverview}"><a href="Controller?action=home">Alle plaatsen</a></li>
				<li id="${param.myspot}"><a href="Controller?action=home">Mijn plaats</a></li>
				<li id="${param.myaccount}"><a href="Controller?action=home">Mijn account</a></li>
			</ul>
		</nav>
		<h2>${param.h2}</h2>
		</header>