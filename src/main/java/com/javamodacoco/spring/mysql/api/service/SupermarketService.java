package com.javamodacoco.spring.mysql.api.service;

import java.util.List;
import java.util.Map;

import com.javamodacoco.spring.mysql.api.model.Supermarket;

public interface SupermarketService {

	Map<String,Object> getSupermarkete();
	boolean adaugaLocatie(String locatie);
}
