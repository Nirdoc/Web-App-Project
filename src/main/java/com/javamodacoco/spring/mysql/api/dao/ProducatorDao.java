package com.javamodacoco.spring.mysql.api.dao;

import java.util.List;

import com.javamodacoco.spring.mysql.api.model.Producator;

public interface ProducatorDao {
	List<Producator> getProducatori();
	boolean adaugaProducator(String denumire, String adresa);
	List<Producator> getProducator(int id);
}
