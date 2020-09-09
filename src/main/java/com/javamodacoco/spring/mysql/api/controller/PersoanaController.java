package com.javamodacoco.spring.mysql.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.PersoanaService;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
//@RequestMapping("/persoane")
public class PersoanaController {

	@Autowired
	private PersoanaService persoanaService;
	@Autowired
	private UserService userService;

	@GetMapping("/persoane")
	public ModelAndView getPersoane(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = persoanaService.getPersoane();

			if (map.containsKey("persoane")) {
				model.addAttribute("persoane", map.get("persoane"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/getPersoane.jsp");
			}
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@GetMapping("/addPersoane")
	public ModelAndView addPersoane(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = persoanaService.getPersoane();

			if (map.containsKey("persoane")) {
				model.addAttribute("persoane", map.get("persoane"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addPersoane.jsp");
			}

		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	
	@PostMapping("/persoane")
	public ModelAndView addPersoana(@RequestParam(name = "username", required = false) String username,HttpServletRequest request) {

		System.out.println("a intrat");
		if(username!=null) {
			String username2 = request.getParameter("username2");
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String age = request.getParameter("age");
			String password = request.getParameter("password");
			String role = request.getParameter("role");

			boolean result = persoanaService.adaugaPersoana(username2, firstname, lastname, age, password,role);
			Persoana existingUser = userService.findByUsername(username);

			if (result && existingUser!=null) {
				request.setAttribute("username", existingUser.getUsername());
			    request.setAttribute("role", existingUser.getRole());
			    return new ModelAndView("/WEB-INF/jsp/addPersoane.jsp", "adaugat", "Persoana a fost adaugat cu succes");
			}
				
			else
				return new ModelAndView("/WEB-INF/jsp/addPersoane.jsp", "neadaugat",
						"Persoana nu a fost adaugat cu succes");

		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
		
	}

}
