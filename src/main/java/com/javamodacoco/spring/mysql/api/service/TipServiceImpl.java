package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.RaionDao;
import com.javamodacoco.spring.mysql.api.dao.TipDao;
import com.javamodacoco.spring.mysql.api.model.Raion;
import com.javamodacoco.spring.mysql.api.model.Tip;

@Service
public class TipServiceImpl implements TipService {

	@Autowired
	TipDao tipDao;
	@Autowired
	RaionDao raionDao;

	@Override
	public Map<String, Object> getTipuri() {

		List<Tip> tipuri = tipDao.getTipuri();
		List<Raion> raioane = raionDao.getRaioane();

		Map<String, Object> deReturnat = new HashMap<>();
		deReturnat.put("tipuri", tipuri);
		deReturnat.put("raioane", raioane);

		return deReturnat;
	}

	@Override
	public boolean adaugaTip(String denumire, String raionIdString) {
		if (tipDao.adaugaTip(denumire, raionIdString))
			return true;
		else
			return false;
	}

	@Override
	public List<Tip> getTipuriList() {
		return tipDao.getTipuri();
	}
}
