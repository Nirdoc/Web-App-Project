package com.javamodacoco.spring.mysql.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.dao.ProducatorDaoImpl;
import com.javamodacoco.spring.mysql.api.dao.ProdusDaoImpl;
import com.javamodacoco.spring.mysql.api.dao.TipDaoImpl;
import com.javamodacoco.spring.mysql.api.model.Producator;
import com.javamodacoco.spring.mysql.api.model.Produs;
import com.javamodacoco.spring.mysql.api.model.Tip;
import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.service.ProdusService;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
//@RequestMapping("/produse")
public class ProdusController {

	@Autowired
	private ProdusService produsService;
	@Autowired
	private UserService userService;

	@GetMapping("/mesaj")
	public String mesaj() {
		return "Salutare";
	}

	@GetMapping("/produse")
	// @GetMapping({"/getProduse", "/getProduse"})
	public ModelAndView getProduse(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = produsService.getProduse();

			if (map.containsKey("produse")) {
				model.addAttribute("produse", map.get("produse"));
			}

			if (map.containsKey("tipuri")) {
				model.addAttribute("tipuri", map.get("tipuri"));
			}

			if (map.containsKey("producatori")) {
				model.addAttribute("producatori", map.get("producatori"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/getProduse.jsp");
			}
		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@GetMapping("/addProduse")
	// @GetMapping({"/addProduse", "/addProdus"})
	public ModelAndView addProdus(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = produsService.getProduse();

			if (map.containsKey("produse")) {
				model.addAttribute("produse", map.get("produse"));
			}

			if (map.containsKey("tipuri")) {
				model.addAttribute("tipuri", map.get("tipuri"));
			}

			if (map.containsKey("producatori")) {
				model.addAttribute("producatori", map.get("producatori"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addProduse.jsp");
			}
		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@PostMapping("/produse")
	// @PostMapping("/produse")
	public ModelAndView addProdus(@RequestParam(name = "username", required = false) String username,HttpServletRequest request) {
		
		if(username!=null) {
			String denumireProdus = request.getParameter("denumire");
			String pretProdus = request.getParameter("pret");
			String stocProdus = request.getParameter("stoc");
			String tipId = request.getParameter("tipId");
			String producatorId = request.getParameter("producatorId");

			boolean result = produsService.adaugaProdus(denumireProdus, pretProdus, stocProdus, tipId, producatorId);
			Persoana existingUser = userService.findByUsername(username);
			
			if (result && existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
			    request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/addProduse.jsp", "adaugat", "Produsul a fost adaugat cu succes");
			}
			else
				return new ModelAndView("/WEB-INF/jsp/addProduse.jsp", "neadaugat", "Produsul nu a fost adaugat cu succes");
			}			
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");
		
	}

}

//thimeleaf
