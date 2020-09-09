package com.javamodacoco.spring.mysql.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



//@Entity
//@Table(name = "Factura")

public class Factura {
//	@Id
//	@GeneratedValue
	private String cod;
	String dataFacturare;
	double pretTotal;
	String denumireSupermarket;
	
	public Factura(String cod, String dataFacturare, double pretTotal, String denumireSupermarket) {
		super();
		this.cod = cod;
		this.dataFacturare = dataFacturare;
		this.pretTotal = pretTotal;
		this.denumireSupermarket = denumireSupermarket;
	}

	public String getCod() {
		return cod;
	}

	public String getDataFacturare() {
		return dataFacturare;
	}

	public void setDataFacturare(String dataFacturare) {
		this.dataFacturare = dataFacturare;
	}

	public double getPretTotal() {
		return pretTotal;
	}

	public void setPretTotal(double pretTotal) {
		this.pretTotal = pretTotal;
	}

	public String getDenumireSupermarket() {
		return denumireSupermarket;
	}

	public void setDenumireSupermarket(String denumireSupermarket) {
		this.denumireSupermarket = denumireSupermarket;
	}

	@Override
	public String toString() {
		return "Factura [cod=" + cod + ", dataFacturare=" + dataFacturare + ", pretTotal=" + pretTotal
				+ ", denumireSupermarket=" + denumireSupermarket + "]";
	}
	
	
	
	
}
