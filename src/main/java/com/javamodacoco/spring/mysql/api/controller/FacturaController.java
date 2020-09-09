package com.javamodacoco.spring.mysql.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.dao.FacturaDaoImpl;
import com.javamodacoco.spring.mysql.api.dao.ProdusDaoImpl;
import com.javamodacoco.spring.mysql.api.dao.SupermarketDaoImpl;
import com.javamodacoco.spring.mysql.api.dao.VanzareDaoImpl;
import com.javamodacoco.spring.mysql.api.dto.ProdusDePeFactura;
import com.javamodacoco.spring.mysql.api.model.Factura;
import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.model.Produs;
import com.javamodacoco.spring.mysql.api.model.Supermarket;
import com.javamodacoco.spring.mysql.api.service.FacturaService;
import com.javamodacoco.spring.mysql.api.service.UserService;
import com.javamodacoco.spring.mysql.api.service.VanzareService;
import com.javamodacoco.spring.mysql.api.util.Utils;

@RestController
//@RequestMapping("/factura")
public class FacturaController {

	@Autowired
	private VanzareService vanzareService;
	@Autowired
	private FacturaService facturaService;
	@Autowired
	private UserService userService;

	@GetMapping("/getAddFacturi")
	public ModelAndView getAddFacturi(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			Map<String, Object> map = facturaService.getFacturi();

			if (map.containsKey("facturi")) {
				model.addAttribute("facturi", map.get("facturi"));
			}

			if (map.containsKey("supermarkete")) {
				model.addAttribute("supermarkete", map.get("supermarkete"));
			}
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/getAddfacturi.jsp");
			}
		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}
	

	@PostMapping("/facturi") // TODO: de modificat endpoint-ul
	public ModelAndView vanzare(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request) {

		if (username != null) {
			HttpSession session = request.getSession();
			List<Produs> cos = (List<Produs>) session.getAttribute("cosCumparaturi");
			List<Integer> iduri = new ArrayList<>();
			for(int i =0 ; i<cos.size(); i++) {
				iduri.add(cos.get(i).getId());
			}
			
			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
			}
			
			Map<String, Object> deReturnat = vanzareService.adaugaVanzare(iduri, existingUser.getId());
			
			if (deReturnat.containsKey("e")) {
				request.setAttribute("e", deReturnat.get("e"));
				return new ModelAndView("/WEB-INF/jsp/cos.jsp");
			}

			else {
				request.setAttribute("produseVandute", deReturnat.get("produseVandute"));
				request.setAttribute("codFactura", deReturnat.get("codFactura"));
				request.setAttribute("pretTotal", deReturnat.get("pretTotal"));

				return new ModelAndView("/WEB-INF/jsp/factura.jsp");
			}

		}
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

}
