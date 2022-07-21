<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Apache Kafka POC</title>
</head>
<meta charset="ISO-8859-1">
<%@include file="./base.jsp"%>
<body>
	<h2 style="text-align : center; margin : 50px 0px ">${message }</h2>
	<div class="container mt-3" style="display:flex; align-items:center;justify-content:center">
		<a href="employeeReport" class="btn btn-primary">Employee</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="studentReport" class="btn btn-primary">Student</a>
	</div>
</body>
</html>