package com.javamodacoco.spring.mysql.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.dao.RaionDaoImpl;
import com.javamodacoco.spring.mysql.api.model.Raion;
import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.RaionService;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
//@RequestMapping("/raioane")
public class RaionController {

	@Autowired
	private RaionService raionService;
	@Autowired
	private UserService userService;

	@GetMapping("/raioane")
	public ModelAndView getRaioane(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = raionService.getRaioane();

			if (map.containsKey("raioane")) {
				model.addAttribute("raioane", map.get("raioane"));
			}

			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/getRaioane.jsp");
			}
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@GetMapping("/addRaioane")
	public ModelAndView addRaion(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = raionService.getRaioane();

			if (map.containsKey("raioane")) {
				model.addAttribute("raioane", map.get("raioane"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addRaioane.jsp");
			}

		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@PostMapping("/raioane")
	public ModelAndView addRaion(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request) {

		if (username != null) {
			String denumire = request.getParameter("denumire");

			boolean result = raionService.adaugaRaion(denumire);
			Persoana existingUser = userService.findByUsername(username);
			if (result && existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addRaioane.jsp", "adaugat", "Raionul a fost adaugat cu succes");
			}

			else
				return new ModelAndView("/WEB-INF/jsp/addRaioane.jsp", "neadaugat",
						"Raionul nu a fost adaugat cu succes");
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

}
