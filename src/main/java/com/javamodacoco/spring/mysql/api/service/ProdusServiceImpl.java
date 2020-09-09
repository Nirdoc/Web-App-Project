package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.ProducatorDao;
import com.javamodacoco.spring.mysql.api.dao.ProdusDao;
import com.javamodacoco.spring.mysql.api.dao.TipDao;
import com.javamodacoco.spring.mysql.api.model.Producator;
import com.javamodacoco.spring.mysql.api.model.Produs;
import com.javamodacoco.spring.mysql.api.model.Tip;

@Service
public class ProdusServiceImpl implements ProdusService {

	@Autowired
	ProdusDao produsDao;
	@Autowired
	TipDao tipDao;
	@Autowired
	ProducatorDao producatorDao;

	@Override
	public Map<String, Object> getProduse() {

		List<Produs> produse = produsDao.getProduse(null);
		List<Tip> tipuri = tipDao.getTipuri();
		List<Producator> producatori = producatorDao.getProducatori();

		Map<String, Object> deReturnat = new HashMap<>();

		deReturnat.put("produse", produse);
		deReturnat.put("tipuri", tipuri);
		deReturnat.put("producatori", producatori);

		return deReturnat;
	}

	@Override
	public boolean adaugaProdus(String denumire, String pret, String stoc, String tipIdString,
			String producatorIdString) {
		if (produsDao.adaugaProdus(denumire, pret, stoc, tipIdString, producatorIdString))
			return true;
		else
			return false;
	}

	@Override
	public boolean verifyThatStocIsGreaterThanZero(int produsId) {
		return produsDao.verifyThatStocIsGreaterThanZero(produsId);
	}

	@Override
	public List<Produs> getProduse(Set<Integer> productIds) {
		return produsDao.getProduse(productIds);
	}
	

}
