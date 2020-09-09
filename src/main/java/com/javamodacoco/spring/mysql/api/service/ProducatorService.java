package com.javamodacoco.spring.mysql.api.service;

import java.util.List;
import java.util.Map;

import com.javamodacoco.spring.mysql.api.model.Producator;

public interface ProducatorService {

	Map<String, Object> getProducatori();
	List<Producator> getProducator(int id);
	boolean adaugaProducator(String denumire, String adresa);
	public List<Producator> getProducatoriList();
	
}
