package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.SupermarketDao;
import com.javamodacoco.spring.mysql.api.model.Supermarket;

@Service
public class SupermarketServiceImpl implements SupermarketService {

	@Autowired
	SupermarketDao supermarketDao;

	@Override
	public Map<String, Object> getSupermarkete() {

		List<Supermarket> supermarkete = supermarketDao.getSupermarkete();

		Map<String, Object> deReturnat = new HashMap<>();

		deReturnat.put("supermarkete", supermarkete);

		return deReturnat;
	}

	@Override
	public boolean adaugaLocatie(String locatie) {
		if (supermarketDao.adaugaLocatie(locatie))
			return true;
		else
			return false;
	}
}
