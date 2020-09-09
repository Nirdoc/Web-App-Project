package com.javamodacoco.spring.mysql.api.service;

import org.springframework.data.repository.CrudRepository;
import com.javamodacoco.spring.mysql.api.model.Persoana;


public interface UserRepository extends CrudRepository<Persoana,Integer>{

	public Persoana findByUsernameAndPassword(String username, String password);
	public Persoana findByUsername(String username);
	
}
