package com.javamodacoco.spring.mysql.api.dao;

import java.util.List;

import com.javamodacoco.spring.mysql.api.model.Tip;

public interface TipDao {
	List<Tip> getTipuri();
	boolean adaugaTip(String denumire, String raionIdString);
	
}
