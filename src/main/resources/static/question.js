let questNumber = 0;
function create() {
	questNumber++;
	document.getElementById("questions").innerHTML += "<div class=question>Question " + questNumber + ":<input type=\"text\" name=\"questions\" class=qInp required=required></div>";
	document.getElementById("questions").innerHTML += "<div class=question>True answer: <input type=\"text\" name=\"questions\" class=qInp required=required></div>";
	document.getElementById("questions").innerHTML += "<div class=question>Wrong answer: <input type=\"text\" name=\"questions\" class=qInp required=required></div>";
	document.getElementById("questions").innerHTML += "<div class=question>Wrong answer: <input type=\"text\" name=\"questions\" class=qInp required=required></div>";
}