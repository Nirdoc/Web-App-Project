package com.javamodacoco.spring.mysql.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.javamodacoco.spring.mysql.api.model.Persoana;

@Service
@Transactional
public class UserService{

private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public void saveMyUser(Persoana user ) {
		user.setRole("user");
		userRepository.save(user);
	}
	
	public List<Persoana> showAllUsers(){
		List<Persoana> users = new ArrayList<Persoana>();
		for(Persoana user : userRepository.findAll()) {
			users.add(user);
		}
		
		return users;
	}
	
	public void deleteMyUser(int id) {
		userRepository.deleteById(id);
	}
	
	public Optional<Persoana> editUser(int id) {
		return userRepository.findById(id);
	}
	
	public Persoana findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}
	
	public Persoana findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	
}
