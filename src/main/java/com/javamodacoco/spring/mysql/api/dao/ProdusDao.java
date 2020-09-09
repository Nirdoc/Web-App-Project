package com.javamodacoco.spring.mysql.api.dao;

import java.util.List;
import java.util.Set;

import com.javamodacoco.spring.mysql.api.model.Produs;

public interface ProdusDao{
	List<Produs> getProduse(Set<Integer> productIds);
	boolean adaugaProdus(String denumire, String pret, String stoc, String tipIdString, String producatorIdString);
	boolean verifyThatStocIsGreaterThanZero(int produsId);
	Integer getStoc(Integer produsId);
	void setStocNou(Integer id, Integer stoc);
	
}
