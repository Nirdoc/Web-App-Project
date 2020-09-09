package com.javamodacoco.spring.mysql.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


//@Entity
//@Table(name = "Supermarket")

public class Supermarket {
//	@Id
//	@GeneratedValue
	private int id;
	private String locatie;
	
	public Supermarket(int id, String locatie) {
		super();
		this.id = id;
		this.locatie = locatie;
	}
	public int getId() {
		return id;
	}
	
	public String getLocatie() {
		return locatie;
	}
	public void setLocatie(String locatie) {
		this.locatie = locatie;
	}
	@Override
	public String toString() {
		return "Supermarket [id=" + id + ", locatie=" + locatie + "]";
	}
	
	
}
