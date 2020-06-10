<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test your knowledge</title>
<link href="<c:url value="css/user.css"/>" rel="stylesheet" media="all">
</head>
<body>
	<header>
		<img src="css/images/ee_small.png"> 
		<c:url var="exit" value="/exit"></c:url>
		<a href="${exit}" id="exit">Exit</a>
		<c:url var="tests" value="/tests"></c:url>
		<a href="${tests}/${level}" id="test">Tests</a>
		<c:if test="${role=='ADMIN' || role=='TEACHER'}">
		<c:url var="createTasks" value="/createTasks"></c:url>
		<a href="${createTasks}" id="task">Create task</a>
		<c:url var="createUser" value="/createUser"></c:url>
		<a href="${createUser}" id="user">Create user</a>
	</c:if>
	</header>
	<hr>
    <div id="left"></div>
    <div id="right"></div>
    <div id="mid">Hello! You entered as <c:out value="${role}">.</c:out><br>	
		<c:if test="${role == 'ADMIN' || role == 'TEACHER'}">
			<c:out value="On our website you can register your students and create tests for them."></c:out>
			<br>
			<c:out value= "Click on 'Create user' to create student's account."></c:out>
			<br>
			<c:out value="Click on 'Create task' to create new tests and tasks to your students."></c:out>
			<br>
			<c:out value="Click on 'Tests' to see all existing tests."></c:out>
		</c:if>
		<c:if test="${role=='STUDENT'}">
			<c:out value="On our site you can take tests according to your level of knowledge."></c:out>
			<br> 
			<c:out value="Click on 'Tests' and starts the test. If there are no tests, contact your teacher."></c:out>
		</c:if>
		<br>
    </div>
</body>
</html>