package com.javamodacoco.spring.mysql.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.GeneratedValue;
import javax.persistence.Table;

//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;

//@Entity
//@Table(name = "Producator")

public class Producator {
	
//	 @Id
//	 @GeneratedValue
	 private int id;
	 private String denumire;
	 private String adresa;
	 
	 
	public Producator(int id, String denumire, String adresa) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.adresa = adresa;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Override
	public String toString() {
		return "Producator [id=" + id + ", denumire=" + denumire + ", adresa=" + adresa + "]";
	}
	 
	 
}
