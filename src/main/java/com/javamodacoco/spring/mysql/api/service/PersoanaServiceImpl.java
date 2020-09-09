package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.PersoanaDao;
import com.javamodacoco.spring.mysql.api.model.Persoana;

@Service
public class PersoanaServiceImpl implements PersoanaService{

	
	@Autowired
	PersoanaDao persoanaDao;
	

	@Override
	public Map<String, Object> getPersoane() {
		List<Persoana> persoane = persoanaDao.getPersoane();
	
		Map<String, Object> deReturnat = new HashMap<>();

		deReturnat.put("persoane", persoane);


		return deReturnat;
	}

	@Override
	public boolean adaugaPersoana(String username, String firstname, String lastname, String age, String password,String role) {
		if (persoanaDao.adaugaPersoana(username, firstname, lastname, age, password,role))
			return true;
		else
			return false;
	}

	

}
