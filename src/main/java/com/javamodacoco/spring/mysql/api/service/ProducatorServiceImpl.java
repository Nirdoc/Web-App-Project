package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.ProducatorDao;
import com.javamodacoco.spring.mysql.api.model.Producator;

@Service
public class ProducatorServiceImpl implements ProducatorService {

	@Autowired
	ProducatorDao producatorDao;

	@Override
	public Map<String, Object> getProducatori() {

		List<Producator> producatori = producatorDao.getProducatori();

		Map<String, Object> deReturnat = new HashMap<>();

		deReturnat.put("producatori", producatori);

		return deReturnat;
	}

	@Override
	public List<Producator> getProducator(int id) {

		return producatorDao.getProducator(id);
	}

	@Override
	public boolean adaugaProducator(String denumire, String adresa) {
		if (producatorDao.adaugaProducator(denumire, adresa))
			return true;
		else
			return false;
	}

	@Override
	public List<Producator> getProducatoriList() {
		
		return producatorDao.getProducatori();
	}

}
