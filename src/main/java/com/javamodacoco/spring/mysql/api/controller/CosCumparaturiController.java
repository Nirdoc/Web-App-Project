package com.javamodacoco.spring.mysql.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.javamodacoco.spring.mysql.api.dao.ProducatorDaoImpl;
import com.javamodacoco.spring.mysql.api.dao.ProdusDaoImpl;
import com.javamodacoco.spring.mysql.api.dao.TipDaoImpl;
import com.javamodacoco.spring.mysql.api.model.Persoana;
import com.javamodacoco.spring.mysql.api.model.Producator;
import com.javamodacoco.spring.mysql.api.model.Produs;
import com.javamodacoco.spring.mysql.api.model.Tip;
import com.javamodacoco.spring.mysql.api.service.ProducatorService;
import com.javamodacoco.spring.mysql.api.service.ProdusService;
import com.javamodacoco.spring.mysql.api.service.TipService;
import com.javamodacoco.spring.mysql.api.service.UserService;

@RestController
@RequestMapping("/cosCumparaturi")
public class CosCumparaturiController {

	@Autowired
	private ProducatorService producatorService;
	@Autowired
	private ProdusService produsService;
	@Autowired
	private TipService tipService;
	@Autowired
	private UserService userService;

	ServletRequestAttributes attr;

	@GetMapping("")
//@GetMapping({"/getProduse", "/getProduse"})
	public ModelAndView getProduse(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request, Model model) {

		if (username != null) {
			HttpSession session = request.getSession();
			List<Produs> cos = (List<Produs>) session.getAttribute("cosCumparaturi");
			model.addAttribute("cosJsp", cos);

			Persoana existingUser = userService.findByUsername(username);
			if (existingUser != null) {
				request.setAttribute("username", existingUser.getUsername());
				request.setAttribute("role", existingUser.getRole());
				return new ModelAndView("/WEB-INF/jsp/cos.jsp");
			}
		}

		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return new ModelAndView("/WEB-INF/jsp/welcomepage.jsp");

	}

	@PostMapping("")
	public ModelAndView addProdus(@RequestParam(name = "username", required = false) String username,
			HttpServletRequest request) {
		
		if (username != null) {
			
			// obtinem parametrul idProdusParam de pe request
			Integer idProdus = Integer.parseInt(request.getParameter("idProdusParam"));
			String denumireProdus = request.getParameter("denumireProdusParam");
			double pretProdus = Double.parseDouble(request.getParameter("pretProdusParam"));
			String denumireTip = request.getParameter("denumireTipProdusParam");
			String denumireProducator = request.getParameter("denumireProducatorProdusParam");
			
		
			
			// obtinem ArrayListul CosDeCumparaturi de pe Sesiune
			// Daca nu e creat, il cream acum
			HttpSession session = request.getSession();
			List<Produs> cos = (List<Produs>) session.getAttribute("cosCumparaturi");
			if (cos == null) {
				cos = new ArrayList<Produs>();
			}

			// Verificam stocul. Daca e mai mare decat 1,
			// adaugam produsul (id-ul) in arrayList
			if (produsService.verifyThatStocIsGreaterThanZero(idProdus)) {
				Produs p = new Produs(idProdus, denumireProdus, pretProdus, denumireTip, denumireProducator);
				cos.add(p);
				// Reactualizam Cosul de cumparaturi pe Sesiune
				session.setAttribute("cosCumparaturi", cos);

				// Afisam un mesaj cum ca produsul a fost adaugat in cos.
				// Sau ca produsul nu e pe stoc
				request.setAttribute("adaugat", "Produsul cu id " + idProdus + " a fost adaugat in cos");
			} else {
				request.setAttribute("neadaugat", "Produsul cu id " + idProdus + " nu este pe stoc");
			}

			List<Produs> produse = produsService.getProduse(null);
			request.setAttribute("produse", produse);

			List<Tip> tipuri = tipService.getTipuriList();
			System.out.println(tipuri);
			request.setAttribute("tipuri", tipuri);

			List<Producator> producatori = producatorService.getProducatoriList();
			System.out.println(producatori);
			request.setAttribute("producatori", producatori);
			
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
		

		// In momentul in care un user intra pe un site nou,
		// automat se creaza o sesiune pentru el. Cand serverul returneaza pagina
		// ii adauga un cookie (String) in browser.
		// Cand userul navigheaza pe o alta pagina,
		// la fiecare request browserul va trimite si cookie-ul specific site-ului
		// In acest mod, serverul il recunoaste pe user dupa cookie.

		// Sesiunea este unica pentru fiecare user.
		// request.getSession() va returna mereu acelasi obiect sesiune pentru fiecare
		// user

		// Serverele web sunt STATELESS. (adica nu STATEFUL)
		// STATELESS: nu le intereseaza cine le apeleaza. Nu tin minte.
		// Adica nu se pastreaza o conexiune deschisa pentru fiecare request.
		// Serverele web primesc un request si raspund cu o pagina (un response)
		// Daca s-ar pastra conexiunile deschise cu toti userii, serverele ar ramane
		// fara memorie.

		// Serverele recunosc userul dupa cookie, nu tin conexiunea deschisa.

	}

}
