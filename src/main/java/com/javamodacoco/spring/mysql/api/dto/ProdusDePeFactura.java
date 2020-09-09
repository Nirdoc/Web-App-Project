package com.javamodacoco.spring.mysql.api.dto;

public class ProdusDePeFactura {

	private int id;
	private String denumire;
	private double pretUnitar;
	private String denumireProducator;
	private int cantitate;
	private double pretulUnitarOriCantitate;
	
	
	public ProdusDePeFactura(int id, String denumire, double pretUnitar, String denumireProducator, int cantitate,
			double pretulUnitarOriCantitate) {
		super();
		this.id = id;
		this.denumire = denumire;
		this.pretUnitar = pretUnitar;
		this.denumireProducator = denumireProducator;
		this.cantitate = cantitate;
		this.pretulUnitarOriCantitate = pretulUnitarOriCantitate;
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
	public double getPretUnitar() {
		return pretUnitar;
	}
	public void setPretUnitar(double pretUnitar) {
		this.pretUnitar = pretUnitar;
	}
	public String getDenumireProducator() {
		return denumireProducator;
	}
	public void setDenumireProducator(String denumireProducator) {
		this.denumireProducator = denumireProducator;
	}
	public int getCantitate() {
		return cantitate;
	}
	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
	public double getPretulUnitarOriCantitate() {
		return pretulUnitarOriCantitate;
	}
	public void setPretulUnitarOriCantitate(double pretulUnitarOriCantitate) {
		this.pretulUnitarOriCantitate = pretulUnitarOriCantitate;
	}
	
}
