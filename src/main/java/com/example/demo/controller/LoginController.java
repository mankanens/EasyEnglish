package com.example.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.dao.AccountDao;
import com.example.demo.model.Account;

@Controller
public class LoginController {

	@Autowired
	private AccountDao accountDao;

	private Account account;

	@PostMapping(value = "/index")
	public String check(HttpServletResponse response, Model model, @RequestParam String login,
			@RequestParam String password) {
		account = accountDao.findByLogin(login);
		if (account == null || !account.getPassword().equals(password)) {
			model.addAttribute("error1", "The username or password is incorrect.");
			model.addAttribute("error2", "Please, try again.");
			return "index";
		} else {
			model.addAttribute("login", account.getLogin());
			model.addAttribute("password", account.getPassword());
			model.addAttribute("level", account.getLevel());
			model.addAttribute("role", account.getRole());
			Cookie cookie = new Cookie("login", account.getLogin());
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			cookie = new Cookie("password", account.getPassword());
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			cookie = new Cookie("role", account.getRole().name());
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			cookie = new Cookie("level", account.getLevel().name());
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			return "user";
		}
	}

	@GetMapping(value = "/index")
	public String index(Model model, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		boolean isFound = false;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ("login".equals(cookie.getName())) {
					model.addAttribute("login", cookie.getValue());
					isFound = true;
				} else {
					if ("password".equals(cookie.getName())) {
						model.addAttribute("password", cookie.getValue());
					} else {
						if ("role".equals(cookie.getName())) {
							model.addAttribute("role", cookie.getValue());
						} else {
							if ("level".equals(cookie.getName())) {
								model.addAttribute("level", cookie.getValue());
							}
						}
					}
				}
			}
			if (isFound) {
				return "user";
			}
		}
		return "index";
	}

	@GetMapping(value = "/exit")
	public String exit(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if ("login".equals(cookie.getName()) || "password".equals(cookie.getName())
					|| "role".equals(cookie.getName()) || "level".equals(cookie.getName())) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "index";
	}

}
