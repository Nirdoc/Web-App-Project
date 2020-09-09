package com.javamodacoco.spring.mysql.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
@RequestMapping("/produseFavorit")
public class ProduseFavoritController {

	@Autowired
	private UserService userService;
	
	ServletRequestAttributes attr;

	@GetMapping("")
//@GetMapping({"/getProduse", "/getProduse"})
	public ModelAndView getProduse(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			HttpSession session = request.getSession();
			List<Integer> prFav = (List<Integer>) session.getAttribute("produseFavorite");
			model.addAttribute("produseFav", prFav);

			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/produseFavorite.jsp");
			}
		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}
}
