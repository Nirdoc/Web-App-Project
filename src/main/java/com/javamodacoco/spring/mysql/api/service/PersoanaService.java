package com.javamodacoco.spring.mysql.api.service;

import java.util.Map;

public interface PersoanaService {

	Map<String,Object> getPersoane();
	boolean adaugaPersoana(String username, String firstname, String lastname, String age, String password,String role);

	
}
