package com.javamodacoco.spring.mysql.api.service;

import java.util.List;
import java.util.Map;

import com.javamodacoco.spring.mysql.api.model.Tip;

public interface TipService {
	Map<String,Object> getTipuri();
	boolean adaugaTip(String denumire, String raionIdString);
	public List<Tip> getTipuriList();
}
