package com.javamodacoco.spring.mysql.api.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.javamodacoco.spring.mysql.api.model.Produs;

public interface ProdusService {

	Map<String,Object> getProduse();
	boolean adaugaProdus(String denumire, String pret, String stoc, String tipIdString, String producatorIdString);
	boolean verifyThatStocIsGreaterThanZero(int produsId);
	public List<Produs> getProduse(Set<Integer> productIds);
}
