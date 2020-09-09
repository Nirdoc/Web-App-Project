package com.javamodacoco.spring.mysql.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "Tip")

public class Tip {
//	@Id
//	@GeneratedValue
	private int id;
	private String denumire;
	private String denumireRaion;
	
	public Tip(int id, String denumire, String denumireRaion) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.denumireRaion = denumireRaion;
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

	public String getDenumireRaion() {
		return denumireRaion;
	}

	public void setDenumireRaion(String denumireRaion) {
		this.denumireRaion = denumireRaion;
	}

	@Override
	public String toString() {
		return "Tip [id=" + id + ", denumire=" + denumire + ", denumireRaion=" + denumireRaion + "]";
	}
	
	
}
