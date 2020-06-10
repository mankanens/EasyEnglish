<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
<link href="<c:url value="/css/test.css"/>" rel="stylesheet" media="all">
</head>
<body>
	<header>
		<img src="css/images/ee_small.png"> 
		<c:url var="exit" value="/exit"></c:url>
		<a href="${exit}" id="exit">Exit</a>
		<c:url var="back" value="/index"></c:url>
		<a href="${back}" id="main">To main</a>
	</header>
	<hr>
	<div id="left"></div>
    <div id="right"></div>
    <div id="mid">
		<c:if test="${mark == \"yes\"}">
			<h2>Test ${resTest.getCode()}</h2>
			<form action="/check" method="post">
				<input type="text" value="${resTest.getCode()}" name="testCode" hidden="true">
				<c:forEach var="task" items="${resTest.getTasks()}">
					<label class="tasks">${task.getContent()}</label>
					<br>
					<c:forEach var="question" items="${task.getQuestions()}">
						<label class="questions">${question.getContent()}</label>
						<br>
						<c:set var="answer1" value="${question.getAnswers().get(0).getContent()}"/>
						<c:set var="answer2" value="${question.getAnswers().get(1).getContent()}"/>
						<c:set var="answer3" value="${question.getAnswers().get(2).getContent()}"/>
						<c:set var="random" value="${rnd.nextInt(3)}"/>
						<c:if test="${random == 0 }"> 
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer1}">
							<label class="answers">${answer1}</label>
							<br>
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer2}">
							<label class="answers">${answer2}</label>
							<br>
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer3}">
							<label class="answers">${answer3}</label>
						</c:if>
						<c:if test="${random == 1 }"> 
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer2}">
							<label class="answers">${answer2}</label>
							<br>
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer1}">
							<label class="answers">${answer1}</label>
							<br>
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer3}">
							<label class="answers">${answer3}</label>
						</c:if>
						<c:if test="${random == 2 }"> 
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer3}">
							<label class="answers">${answer3}</label>
							<br>
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer1}">
							<label class="answers">${answer1}</label>
							<br>
							<input type="radio" class="in" name="ans${question.getId()}" value="${answer2}">
							<label class="answers">${answer2}</label>
						</c:if>
						<br>
					</c:forEach>
				</c:forEach>
				<input type="submit" value="Check">
			</form>
		</c:if>
		<c:if test="${mark != \"yes\" && !check}">
			<div id="tip"> Select the desired test and start execution. 
    		The test will be passed if you answer correctly 70% of the questions</div>
    		<h1>List of tests</h1>
			<c:forEach var="num" items="${tests}">
				<c:url var="test" value="/tests/${level}/${num.getCode()}"></c:url>
				<div><a href="${test}" class="testCode">${num.getCode()}</a></div>
			</c:forEach>
		</c:if>
		<div id = "res"><c:out value="${result}"></c:out></div>
	</div>
</body>
</html>