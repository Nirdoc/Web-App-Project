package com.javamodacoco.spring.mysql.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Entity
//@Table(name = "Raion")

public class Raion {
//	@Id
//	@GeneratedValue
	private int id;
	private String denumire;
	
	
	public Raion(int id, String denumire) {
		super();
		this.id = id;
		this.denumire = denumire;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDenumire() {
		return denumire;
	}
	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}
	@Override
	public String toString() {
		return "Raion [id=" + id + ", denumire=" + denumire + "]";
	}
	
	
}
