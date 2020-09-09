package com.javamodacoco.spring.mysql.api.dao;

import java.util.List;

import com.javamodacoco.spring.mysql.api.model.Raion;

public interface RaionDao {

	List <Raion> getRaioane();
	boolean adaugaRaion(String denumire);
	
}
