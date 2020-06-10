<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create user</title>
<link href="<c:url value="css/createUser.css"/>" rel="stylesheet" media="all">
</head>
<body>
	<header>
		<img src="css/images/ee_small.png"> 
		<c:url var="exit" value="/exit"></c:url>
		<a href="${exit}" id="exit">Exit</a>
		<c:url var="back" value="/index"></c:url>
		<a href="${back}" id="main">To main</a>
		<c:url var="tests" value="/tests"></c:url>
		<a href="${tests}/TL" id="test">Tests</a>
		<c:url var="createTasks" value="/createTasks"></c:url>
		<a href="${createTasks}" id="task">Create task</a>
	</header>
	<hr>
	<div id="left"></div>
    <div id="right"></div>
    <div id="mid">
    	<div id="tip">Teacher's level - the level at which absolutely all tests are available for viewing.
    	This is level recommended for "Administrator" and "Teacher".</div>
		<form action="/createUser" method="post">
			<span class="bungur">Login:<input type="text" name="login" required="required"></span><br> 
			<span class="bungur">Password:<input type="password" name="password" required="required"></span><br> 
			<span class="bungur">Role:</span>
			<span class="navy"><input type="radio" name="role" value="STUDENT" required="required">Student</span> 
			<span class="navy"><input type="radio" name="role" value="TEACHER" required="required">Teacher</span>
			<c:if test="${role=='ADMIN'}"> 
				<span class="navy"><input type="radio" name="role" value="ADMIN" required="required">Administrator</span> 
			</c:if>
			<br>
			<div id="lvl">
				<span class="bungur">Level:</span>
				<br> 
				<span class="navy"><input type="radio" name="level" value="BEG" required="required">Begginer</span><br>
				<span class="navy"><input type="radio" name="level" value="EL" required="required">Elementary</span><br>
				<span class="navy"><input type="radio" name="level" value="PI" required="required">Pre-Intermediate</span><br>
				<span class="navy"><input type="radio" name="level" value="INT" required="required">Intermediate</span><br>
				<span class="navy"><input type="radio" name="level" value="TL" required="required">Teacher's level</span><br>
			</div>
			<input type="submit" value="Create">
		</form>
		<div id="msg"><c:out value="${msg}"></c:out></div>
		<div id="update"><c:out value="${update}"></c:out></div>
		<div id="error"><c:out value="${error}"></c:out></div>
	</div>
</body>
</html>