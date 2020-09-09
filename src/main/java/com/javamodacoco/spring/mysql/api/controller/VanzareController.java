package com.javamodacoco.spring.mysql.api.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.ProdusService;
import com.javamodacoco.spring.mysql.api.service.UserService;
import com.javamodacoco.spring.mysql.api.service.VanzareService;

@RestController
public class VanzareController {
	
	@Autowired
	private VanzareService vanzareService;
	@Autowired
	private UserService userService;

	@GetMapping("/vanzari")
	// @GetMapping({"/getProduse", "/getProduse"})
	public ModelAndView getProduse(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = (Map<String, Object>) vanzareService.getToateVanzarileDeLaUnUser(username);

			if (map.containsKey("vanzari")) {
				model.addAttribute("vanzari", map.get("vanzari"));
			}

			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/istoricFacturi.jsp");
			}
		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	
}
