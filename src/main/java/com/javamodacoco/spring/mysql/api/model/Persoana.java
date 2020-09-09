package com.javamodacoco.spring.mysql.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "persoana")
public class Persoana {

	Persoana(){
		
	}
	@Id
	private int persoana_id;
	private String username;
	private String firstname;
	private String lastname;
	private int age;
	private String password;
	private String role;

	public Persoana(int persoana_id, String username, String firstname, String lastname, int age, String password, String role) {
		super();
		this.persoana_id = persoana_id;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.password = password;
		this.role = role;
	}



	public Persoana(String username, String firstname, String lastname, int age, String password) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.password = password;
	}





	public int getId() {
		return persoana_id;
	}

	public void setId(int persoana_id) {
		this.persoana_id = persoana_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [persoana_id=" + persoana_id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", age=" + age + ", password=" + password + ", role=" + role + "]";
	}

}
