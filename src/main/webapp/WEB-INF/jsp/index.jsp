<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test your knowledge</title>
 <link href="<c:url value="css/index.css"/>" rel="stylesheet" media="all">
</head>
<body>
	<header></header>
    <div class="head"><span class="burgun">Test your </span><span class="navyblue">knowledge</span></div>
    <div class="head"><span class="navyblue">Log</span><span class="burgun">In</span></div>
	<form action="/index" method="post">
		<input type="text" name="login">
		<input type="password"name="password">
		<input type="submit" value="Log In">
	</form>
	<div class="error"><c:out value="${error1}"></c:out></div>
	<div class="error"><c:out value="${error2}"></c:out></div>
</body>
</html>