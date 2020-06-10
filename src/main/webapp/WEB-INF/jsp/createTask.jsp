<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create task</title>
<link href="<c:url value="css/createTask.css"/>" rel="stylesheet" media="all">
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
		<c:url var="createUser" value="/createUser"></c:url>
		<a href="${createUser}" id="user">Create user</a>
	</header>
	<hr>
	<div id="left"></div>
    <div id="right"></div>
    <div id="mid">
    <div id="tip">When creating a test, specify the test code in accordance with the test level. 
    Beginner - BEGXX, Elementary - ELXX, Pre-intermediate - PIXX, Intermediate - INTXX, XX - number of test.</div>
	<form action="/createTask" method="post">
		<label><span class="navy">Test's code: <input type="text" name="testCode" required="required"></span></label><br>
		<label><span class="navy">Task's content: <input type="text" name="taskContent" id="taskCont" required="required"></span></label><br>
		<label><span class="navy">Ready for student: <input type="radio" name="isReady"value="true"></span></label>
		<br>
		<div class="navy">
			Questions:
			<div id="questions"></div>
		</div>
		<input type="submit" value="Create task" class="but">
	</form>
	<button onclick="create()" class="but" id="button">Add question</button>
	<div id="msg"><c:out value="${msg}"></c:out></div>
	</div>
	<script src="question.js"></script>
</body>
</html>