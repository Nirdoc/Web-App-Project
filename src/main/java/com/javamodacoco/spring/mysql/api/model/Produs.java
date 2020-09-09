package com.javamodacoco.spring.mysql.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name = "Produs")

public class Produs {
//	@Id
//	@GeneratedValue
	private int id;
	private String denumire;
	private double pret;
	private int stoc;
	private String denumireTip;
	private String denumireProducator;
	
	public Produs(int id, String denumire, double pret, int stoc, String denumireTip, String denumireProducator) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.pret = pret;
		this.stoc = stoc;
		this.denumireTip = denumireTip;
		this.denumireProducator = denumireProducator;
	}
	
	public Produs(int id, String denumire, double pret, String denumireTip, String denumireProducator) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.pret = pret;
		this.denumireTip = denumireTip;
		this.denumireProducator = denumireProducator;
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

	public double getPret() {
		return pret;
	}

	public void setPret(double pret) {
		this.pret = pret;
	}

	public int getStoc() {
		return stoc;
	}

	public void setStoc(int stoc) {
		this.stoc = stoc;
	}

	public String getDenumireTip() {
		return denumireTip;
	}

	public void setDenumireTip(String denumireTip) {
		this.denumireTip = denumireTip;
	}

	public String getDenumireProducator() {
		return denumireProducator;
	}

	public void setDenumireProducator(String denumireProducator) {
		this.denumireProducator = denumireProducator;
	}


	@Override
	public String toString() {
		return "Produs [id=" + id + ", denumire=" + denumire + ", pret=" + pret + ", stoc=" + stoc + ", denumireTip="
				+ denumireTip + ", denumireProducator=" + denumireProducator + "]";
	}
	
	
}
