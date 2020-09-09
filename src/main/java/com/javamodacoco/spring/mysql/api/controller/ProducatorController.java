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

import com.javamodacoco.spring.mysql.api.model.Producator;
import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.ProducatorService;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
//@RequestMapping("/producatori")
public class ProducatorController {
	@Autowired
	private ProducatorService producatorService;
	@Autowired
	private UserService userService;

	@GetMapping("/producatori")
	public ModelAndView getProducatori(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if(username!=null) {
			Map<String, Object> map = producatorService.getProducatori();

			if (map.containsKey("producatori")) {
				model.addAttribute("producatori", map.get("producatori"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/getProducatori.jsp");
			}
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
		
		
	}

	@GetMapping("/addProducatori")
	public ModelAndView addProducator(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if(username!=null) {
			Map<String, Object> map = producatorService.getProducatori();

			if (map.containsKey("producatori")) {
				model.addAttribute("producatori", map.get("producatori"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addProducatori.jsp");
			}
			
		}
		
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
		
	}

	@PostMapping("/producatori")
	public ModelAndView addProducator(@RequestParam(name = "username", required = false) String username,HttpServletRequest request) {

		if(username!=null) {
			String denumire = request.getParameter("denumire");
			String adresa = request.getParameter("adresa");

			boolean result = producatorService.adaugaProducator(denumire, adresa);
			Persoana existingUser = userService.findByUsername(username);
			if (result && existingUser!=null)
			{
				request.setAttribute("username", existingUser.getUsername());
			    request.setAttribute("role", existingUser.getRole());
			    return new ModelAndView("/WEB-INF/jsp/addProducatori.jsp", "adaugat",
						"Producatorul a fost adaugat cu succes");
			}
				
			else
				return new ModelAndView("/WEB-INF/jsp/addProducatori.jsp", "neadaugat",
						"Producatorul nu a fost adaugat cu succes");

		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
		
	}

}
