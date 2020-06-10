package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.TestDao;
import com.example.demo.model.Levels;
import com.example.demo.model.Question;
import com.example.demo.model.Task;
import com.example.demo.model.Test;

@Controller
public class TestController {

	@Autowired
	TestDao testDao;

	@GetMapping(value = "/tests/{level}")
	public String showTests(Model model, @PathVariable Levels level) {
		List<Test> tests;
		tests = testDao.findAll();
		List<Test> parametr = new ArrayList<Test>();
		model.addAttribute("level", level);
		switch (level) {
		case BEG: {
			for (int i = 0; i < tests.size(); i++) {
				if (tests.get(i).getCode().substring(0, 3).equals("BEG") && tests.get(i).isReady()) {
					parametr.add(tests.get(i));
				}
			}

			model.addAttribute("tests", parametr);
			break;
		}
		case EL: {
			for (int i = 0; i < tests.size(); i++) {
				if (tests.get(i).getCode().substring(0, 2).equals("EL") && tests.get(i).isReady()) {
					parametr.add(tests.get(i));
				}
			}
			model.addAttribute("tests", parametr);
			break;

		}
		case PI: {
			for (int i = 0; i < tests.size(); i++) {
				if (tests.get(i).getCode().substring(0, 2).equals("PI") && tests.get(i).isReady()) {
					parametr.add(tests.get(i));
				}
			}
			model.addAttribute("tests", parametr);
			break;

		}
		case INT: {
			for (int i = 0; i < tests.size(); i++) {
				if (tests.get(i).getCode().substring(0, 3).equals("INT") && tests.get(i).isReady()) {
					parametr.add(tests.get(i));
				}
			}
			model.addAttribute("tests", parametr);
			break;

		}
		case TL: {
			model.addAttribute("tests", tests);
			break;
		}
		}
		return "tests";
	}

	@GetMapping(value = "/tests/{level}/{code}")
	public String showTest(@PathVariable String code, Model model) {
		Test test = testDao.findByCode(code);
		model.addAttribute("resTest", test);
		model.addAttribute("mark", "yes");
		Random rnd = new Random();
		model.addAttribute("rnd", rnd);
		return "tests";
	}

	@PostMapping(value = "/check")
	public String check(Model model, @RequestParam Map<String, String> allParams) {
		Set<String> keys = allParams.keySet();
		Iterator<String> it = keys.iterator();
		int res = 0, questCount = 0;
		String answer;
		Collection<Task> tasks = testDao.findByCode(allParams.get(it.next())).getTasks();
		for (Task task : tasks) {
			for (Question question : task.getQuestions()) {
				questCount++;
				answer = allParams.get(it.next());
				if (question.getAnswers().iterator().next().getContent().equals(answer)) {
					res++;
				}
			}
		}
		if ((double) res / (double) questCount * 100 >= 70) {
			model.addAttribute("result", "Congratulations! You have successfully passed the test!");
		} else {
			model.addAttribute("result",
					"Sorry, you failed the test. You answered correctly less then 70%. Read theoretical material and try again.");
		}
		model.addAttribute("check", true);
		return "tests";
	}
}
