package com.javamodacoco.spring.mysql.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.UserService;

@Controller
public class ApplicationController {

	@Autowired
	UserService userService;

	@RequestMapping("/logout")
	public ModelAndView Welcome(@RequestParam(name = "username", required = false) String username) {
		//request.setAttribute("mode", "MODE_HOME");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
	}
	
	@RequestMapping("/welcome")
	public ModelAndView Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
	}

	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "/WEB-INF/jsp/welcomepage.jsp";
	}

	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute Persoana user, BindingResult bindingResult, HttpServletRequest request) {
		userService.saveMyUser(user);
		request.setAttribute("mode", "MODE_HOME");
		return "/WEB-INF/jsp/welcomepage.jsp";
	}

	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "/WEB-INF/jsp/welcomepage.jsp";
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		userService.deleteMyUser(id);
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "/WEB-INF/jsp/welcomepage.jsp";
	}

	@RequestMapping("/edit-user")
	public String editUser(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("user", userService.editUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "/WEB-INF/jsp/welcomepage.jsp";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "/WEB-INF/jsp/welcomepage.jsp";
	}

	@RequestMapping("/home")
	public String loginUser(@ModelAttribute Persoana user, HttpServletRequest request) {
		Persoana existingUser = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (existingUser != null) {
			request.setAttribute("username", existingUser.getUsername());
			request.setAttribute("role", existingUser.getRole());
			return "/WEB-INF/jsp/home.jsp";
		}

		else {
			request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "/WEB-INF/jsp/welcomepage.jsp";

		}

	}

//	@RequestMapping("/")
//	public ModelAndView home(@RequestParam("username") String username,HttpServletRequest request) {
//		User existingUser = userService.findByUsername(username);
//		request.setAttribute("username", existingUser);
//		return new ModelAndView("/WEB-INF/jsp/home.jsp");
//	}

	@RequestMapping("/")
	public String home(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request) {
		//System.out.println(username);
		if (username != null) {
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return "/WEB-INF/jsp/home.jsp";
			}
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return "/WEB-INF/jsp/welcomepage.jsp";

	}

}
