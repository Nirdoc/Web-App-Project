package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.FacturaDao;
import com.javamodacoco.spring.mysql.api.dao.ProdusDao;
import com.javamodacoco.spring.mysql.api.dao.VanzareDao;
import com.javamodacoco.spring.mysql.api.dto.ProdusDePeFactura;
import com.javamodacoco.spring.mysql.api.model.Produs;
import com.javamodacoco.spring.mysql.api.model.Vanzare;
import com.javamodacoco.spring.mysql.api.util.Utils;

@Service
public class VanzareServiceImpl implements VanzareService{

	@Autowired
	VanzareDao vanzareDao;
	@Autowired
	private FacturaDao facturaDao;
	@Autowired
	private ProdusDao produsDao;
	
	@Override
	public List<ProdusDePeFactura> getToateVanzarileDeLaOFactura(int codUltimaFactura) {
		vanzareDao.getToateVanzarileDeLaOFactura(codUltimaFactura);
		return null;
	}
	
	@Override
	public Map<String, Object> getToateVanzarileDeLaUnUser(String username) {
		List <Vanzare> vanzari = vanzareDao.getToateVanzarileDeLaUnUser(username);
		Map<String, Object> deReturnat = new HashMap<>();
		deReturnat.put("vanzari", vanzari);
		return deReturnat;
	}

	@Override
	@Transactional
	public Map<String,Object> adaugaVanzare(List<Integer> cos, int idPersoana){
		// Aici incepe tranzactia
		
		
					facturaDao.adaugaFactura(Utils.SUPERMARKET_ID);
					int codUltimaFactura = facturaDao.getCodFacturaDeLaUltimaFactura();
			
					HashMap<Integer, Integer> produsIdsSiCantitati = new HashMap<>();
					// session.getAtribute
					for (int produsId : cos) {
						// in cos avem produsele: 5, 104, 34, 1302, 34, 56, 5, 34.
						// hashMapul va contine perechile:
						// 5,2
						// 104, 1
						// 1302, 1
						// 34, 3
						// 56, 1
						// Cum functioneaza metoda put:
						// Daca cheia nu exista, adauga cheia si valoarea.
						// Daca cheia exista deja, inlocuieste valoarea cu noua valoare.
			
						// produsIdsSiCantitati.put(2, 2);
						// produsIdsSiCantitati.put(3, 1);
			
						if (produsIdsSiCantitati.containsKey(produsId)) {
							int cantitate = produsIdsSiCantitati.get(produsId);
							produsIdsSiCantitati.put(produsId, ++cantitate);
						} else
							produsIdsSiCantitati.put(produsId, 1);
					}
			
					// cu cheile: produsId si valorile: cantitati.
					// Trebuie verificat ca cantitatile sa fie mai mici sau egale decat stocul,
					// pentru fiecare produs.
			
					Map<String,Object> deReturnat = new HashMap<String, Object>();
			
					for (Integer key : produsIdsSiCantitati.keySet()) {
						Integer cantitateaProdusuluiDinCos = produsIdsSiCantitati.get(key);
						Integer stoculProdusuluiDinCos = produsDao.getStoc(key);
			
						if (cantitateaProdusuluiDinCos > stoculProdusuluiDinCos) {
							// TODO returnam un mesaj de anulare a facturarii
						
							deReturnat.put("e", "Ne pare rau, intre timp altcineva a cumparat un produs. " + "Cantitatea produsului cu id-ul "
											+ key + " din cosul de cumparatori " + "este mai mica decat stocul.");
							
							return deReturnat;
						}
					}
			
					vanzareDao.adaugaVanzare(produsIdsSiCantitati, idPersoana, codUltimaFactura);
			
			//		Se sterg toate produsele din cosul de cumparaturi. list.clear()
					cos.clear();
			
			//		Pretul total al facturii:  Trebuie obtinut pretul fiecarui produs din baza de date.		
			//		//SELECT ..... FROM vanzare
			//		UPDATE facturi SET prettotal = ... WHERE factura_id = ....
			//		Ultimul lucru: trebuie scazut stocul.
			
					Set<Integer> productIds = produsIdsSiCantitati.keySet();
			
					List<Produs> produseleDinBazaDeDatePuseInCos = produsDao.getProduse(productIds);
					double pretTotalFactura = 0;
					for (Produs produs : produseleDinBazaDeDatePuseInCos) {
						// pretTtoal = pretTotal + pretProdus * Cantitate.
						pretTotalFactura = pretTotalFactura + produs.getPret() * produsIdsSiCantitati.get(produs.getId());
					}
			
			//		UPDATE facturi SET prettotal = ... WHERE factura_id = ....
			
					facturaDao.setPretTotal(codUltimaFactura, pretTotalFactura);
			
					// String denumire = request.getParameter("denumire");
					// String adresa = request.getParameter("adresa");
			
					// producatorDao.adaugaProducator(denumire,adresa);
					// Ultimul lucru: trebuie scazut stocul pentru fiecare produs din cosul din
					// cumparaturi.
					for (Produs produs : produseleDinBazaDeDatePuseInCos) {
						int stoculVechiAlProdusului = produs.getStoc();
						int idProdus = produs.getId();
						int cantitate = produsIdsSiCantitati.get(idProdus);
						int stoculNouAlProduslui = stoculVechiAlProdusului - cantitate;
						produsDao.setStocNou(idProdus, stoculNouAlProduslui);
					}
			
			//		returnam factura.jsp
			
					// ne trebuie toate vanzarile care au acest cod de factura.
					List<ProdusDePeFactura> produseVandute = vanzareDao.getToateVanzarileDeLaOFactura(codUltimaFactura);
			
					
					deReturnat.put("produseVandute", produseVandute);
					deReturnat.put("codFactura", codUltimaFactura);
			
					String pretTotalFacturaFormatatLaDouaZecimale = String.format("%.2f", pretTotalFactura);
					
					deReturnat.put("pretTotal", pretTotalFacturaFormatatLaDouaZecimale);
					
			
			//				boolean result = facturaDao.adaugaFactura(nume, prenume);
			//				if(result)
			//					return new ModelAndView("/WEB-INF/jsp/addPersoane.jsp", "adaugat", "Persoana a fost adaugat cu succes");
			//				else
			//					return new ModelAndView("/WEB-INF/jsp/addPersoane.jsp", "neadaugat", "Persoana nu a fost adaugat cu succes");
					
					// Aici se face commit sau rollback		
					
			
					return deReturnat;
	}

	

}
