package com.javamodacoco.spring.mysql.api.dao;

import java.util.List;

import com.javamodacoco.spring.mysql.api.model.Persoana;


public interface PersoanaDao {

	List<Persoana> getPersoane();
	boolean adaugaPersoana(String username, String firstname, String lastname, String age, String password, String role);
	boolean adaugaPersoana2(String username, String firstname, String lastname, String age, String password);
}
