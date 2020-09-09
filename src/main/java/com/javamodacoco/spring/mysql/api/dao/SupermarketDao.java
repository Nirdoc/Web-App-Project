package com.javamodacoco.spring.mysql.api.dao;

import java.util.List;

import com.javamodacoco.spring.mysql.api.model.Supermarket;

public interface SupermarketDao {

	List<Supermarket> getSupermarkete();
	boolean adaugaLocatie(String locatie);
}
