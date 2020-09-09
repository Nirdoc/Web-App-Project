package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.RaionDao;
import com.javamodacoco.spring.mysql.api.model.Raion;

@Service
public class RaionServiceImpl implements RaionService {

	@Autowired
	RaionDao raionDao;

	@Override
	public Map<String, Object> getRaioane() {

		List<Raion> raioane = raionDao.getRaioane();

		Map<String, Object> deReturnat = new HashMap<>();

		deReturnat.put("raioane", raioane);

		return deReturnat;
	}

	@Override
	public boolean adaugaRaion(String denumire) {
		if (raionDao.adaugaRaion(denumire))
			return true;
		else
			return false;
	}

}
