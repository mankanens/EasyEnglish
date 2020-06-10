package com.example.demo.controller;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.AccountDao;
import com.example.demo.dao.TestDao;
import com.example.demo.model.Account;
import com.example.demo.model.Answer;
import com.example.demo.model.Levels;
import com.example.demo.model.Question;
import com.example.demo.model.Roles;
import com.example.demo.model.Task;
import com.example.demo.model.Test;

@Controller
public class CreateController {

	@Autowired
	AccountDao accountDao;

	@Autowired
	TestDao testDao;

	private Test test;
	private Account account;
	private Task task;
	private Question question;
	private Answer answer;

	@PostMapping(value = "/createUser")
	public String create(Model model, @RequestParam String login, @RequestParam String password,
			@RequestParam Roles role, @RequestParam Levels level) {
		account = accountDao.findByLogin(login);
		if (account != null && account.getPassword().equals(password)) {
			switch (account.getLevel()) {
			case BEG: {
				account.setLevel(level);
				account.setRole(role);
				accountDao.save(account);
				model.addAttribute("update", "Account has been upgraded to a higher level");
				return "create";
			}
			case EL: {
				if (level == Levels.BEG) {
					model.addAttribute("error", "Account cannot be updated because it already has a higher level");
					return "create";
				} else {
					account.setLevel(level);
					account.setRole(role);
					accountDao.save(account);
					model.addAttribute("update", "Account has been upgraded to a higher level");
					return "create";
				}
			}
			case PI: {
				if (level == Levels.BEG || level == Levels.EL) {
					model.addAttribute("error", "Account cannot be updated because it already has a higher level");
					return "create";
				} else {
					account.setLevel(level);
					account.setRole(role);
					accountDao.save(account);
					model.addAttribute("update", "Account has been upgraded to a higher level");
					return "create";
				}
			}
			case INT: {
				if (level == Levels.INT || level == Levels.TL) {
					account.setLevel(level);
					account.setRole(role);
					accountDao.save(account);
					model.addAttribute("update", "Account has been upgraded to a higher level");
					return "create";
				} else {
					model.addAttribute("error", "Account cannot be updated because it already has a higher level");
					return "create";
				}
			}
			case TL: {
				if (level == Levels.TL) {
					account.setLevel(level);
					account.setRole(role);
					accountDao.save(account);
					model.addAttribute("update", "Account has been upgraded to a higher level");
					return "create";
				} else {
					model.addAttribute("error", "Account cannot be updated because it already has a higher level");
					return "create";
				}
			}
			}
		} else {
			account = new Account();
			account.setLogin(login);
			account.setPassword(password);
			account.setLevel(level);
			account.setRole(role);
			accountDao.save(account);
			model.addAttribute("msg", "Account created.");
		}
		return "create";
	}

	@GetMapping(value = "/createUser")
	public String createUser(Model model, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("role".equals(cookie.getName())) {
				model.addAttribute("role", cookie.getValue());
			}
		}
		model.addAttribute("msg", "");
		model.addAttribute("error", "");
		model.addAttribute("update", "");
		return "create";
	}

	@GetMapping(value = "/createTasks")
	public String createTest(Model model) {
		model.addAttribute("msg", "");
		return "createTask";
	}

	@PostMapping(value = "/createTask")
	public String createTasks(Model model, @RequestParam String testCode, @RequestParam String taskContent,
			@RequestParam(required = false) Boolean isReady, @RequestParam String... questions) {
		test = testDao.findByCode(testCode);
		if (test != null) {
			createTask(taskContent, questions);
			if (isReady != null && isReady) {
				test.setReady(true);
			}
			test.getTasks().add(task);
		} else {
			test = new Test();
			test.setCode(testCode);
			createTask(taskContent, questions);
			test.setTasks(new ArrayList<Task>());
			test.getTasks().add(task);
			if (isReady != null && isReady) {
				test.setReady(true);
			}
		}
		testDao.save(test);
		model.addAttribute("msg", "Task created successfully");
		return "createTask";
	}

	private void createTask(String taskContent, String... questions) {
		task = new Task();
		task.setTest(test);
		task.setContent(taskContent);
		task.setQuestions(new ArrayList<Question>());
		for (int i = 0; i < questions.length;) {
			question = new Question();
			question.setAnswers(new ArrayList<Answer>());
			question.setContent(questions[i++]);
			answer = new Answer();
			answer.setContent(questions[i++]);
			answer.setIsCorrect(true);
			answer.setQuestion(question);
			question.getAnswers().add(answer);
			answer = new Answer();
			answer.setContent(questions[i++]);
			answer.setIsCorrect(false);
			answer.setQuestion(question);
			question.getAnswers().add(answer);
			answer = new Answer();
			answer.setContent(questions[i++]);
			answer.setIsCorrect(false);
			answer.setQuestion(question);
			question.getAnswers().add(answer);
			task.getQuestions().add(question);
			question.setTask(task);
		}
	}
}
