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
import com.javamodacoco.spring.mysql.api.dao.TipDaoImpl;
import com.javamodacoco.spring.mysql.api.model.Raion;
import com.javamodacoco.spring.mysql.api.model.Tip;
import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.RaionService;
import com.javamodacoco.spring.mysql.api.service.TipService;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
//@RequestMapping("tipuri")
public class TipController {

	@Autowired
	private TipService tipService;
	@Autowired
	private UserService userService;

	@GetMapping("/tipuri")
	ModelAndView getTipuri(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = tipService.getTipuri();

			if (map.containsKey("tipuri")) {
				model.addAttribute("tipuri", map.get("tipuri"));
			}

			if (map.containsKey("raioane")) {
				model.addAttribute("raioane", map.get("raioane"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/getTipuri.jsp");

			}
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@GetMapping("/addTipuri")
	ModelAndView addTip(@RequestParam(name = "username", required = false) String username, HttpServletRequest request,
			Model model) {

		if (username != null) {
			Map<String, Object> map = tipService.getTipuri();

			if (map.containsKey("tipuri")) {
				model.addAttribute("tipuri", map.get("tipuri"));
			}

			if (map.containsKey("raioane")) {
				model.addAttribute("raioane", map.get("raioane"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addTipuri.jsp");

			}

		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@PostMapping("/tipuri")
	ModelAndView addTipuri(@RequestParam(name = "username", required = false) String username,HttpServletRequest request) {
		
		if(username!=null) {
			String denumireTip = request.getParameter("denumire");
			String raionId = request.getParameter("raionId");

			boolean result = tipService.adaugaTip(denumireTip, raionId);
			Persoana existingUser = userService.findByUsername(username);
			if (result && existingUser!=null) {
				request.setAttribute("username", existingUser.getUsername());
			    request.setAttribute("role", existingUser.getRole());
			    return new ModelAndView("/WEB-INF/jsp/addTipuri.jsp", "adaugat",
						"Tipul de produs a fost adaugat cu succes");
			}
				
			else
				return new ModelAndView("/WEB-INF/jsp/addTipuri.jsp", "neadaugat",
						"Tipul de produs nu a fost adaugat cu succes");
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
		

	}

}
