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

import com.javamodacoco.spring.mysql.api.dao.SupermarketDaoImpl;
import com.javamodacoco.spring.mysql.api.model.Supermarket;
import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.SupermarketService;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
//@RequestMapping("/supermarkete")
public class SupermarketController {

	@Autowired
	private SupermarketService supermarketService;
	@Autowired
	private UserService userService;

	@GetMapping("/supermarkete")
	ModelAndView getSupermarkete(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {
		if (username != null) {
			Map<String, Object> map = supermarketService.getSupermarkete();

			if (map.containsKey("supermarkete")) {
				model.addAttribute("supermarkete", map.get("supermarkete"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/getSupermarkete.jsp");
			}
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@GetMapping("/addSupermarkete")
	ModelAndView addSupermarket(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = supermarketService.getSupermarkete();

			if (map.containsKey("supermarkete")) {
				model.addAttribute("supermarkete", map.get("supermarkete"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addSupermarkete.jsp");
			}

		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@PostMapping("/supermarkete")
	ModelAndView addTipuri(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request) {

		if (username != null) {
			String locatie = request.getParameter("locatie");

			boolean result = supermarketService.adaugaLocatie(locatie);
			Persoana existingUser = userService.findByUsername(username);
			if (result && existingUser!=null) {
				request.setAttribute("username", existingUser.getUsername());
			    request.setAttribute("role", existingUser.getRole());
			    return new ModelAndView("/WEB-INF/jsp/addSupermarkete.jsp", "adaugat",
						"Supermarketul a fost adaugat cu succes");
			}
				
			else
				return new ModelAndView("/WEB-INF/jsp/addSupermarkete.jsp", "neadaugat",
						"Supermarketul nu a fost adaugat cu succes");
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

}
