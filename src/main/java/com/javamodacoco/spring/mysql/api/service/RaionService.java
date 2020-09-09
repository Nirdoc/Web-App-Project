package com.javamodacoco.spring.mysql.api.service;

import java.util.List;
import java.util.Map;

import com.javamodacoco.spring.mysql.api.model.Raion;

public interface RaionService {

	Map<String,Object> getRaioane();
	boolean adaugaRaion(String denumire);
}
