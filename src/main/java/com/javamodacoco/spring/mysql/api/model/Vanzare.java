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
//@Table(name = "Vanzare")

public class Vanzare {
//	@Id
//	@GeneratedValue
	int idProdus;
	int idUser;
	String codFactura;
	int cantitate;
	
	public Vanzare(int idProdus, int idUser, String codFactura, int cantitate) {
		super();
		this.idProdus = idProdus;
		this.idUser = idUser;
		this.codFactura = codFactura;
		this.cantitate = cantitate;
	}
	
	

	public Vanzare(String codFactura,int idProdus,int cantitate) {
		super();
		this.codFactura = codFactura;
		this.idProdus = idProdus;
		this.cantitate = cantitate;
	}



	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getCodFactura() {
		return codFactura;
	}

	public void setCodFactura(String codFactura) {
		this.codFactura = codFactura;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}

	@Override
	public String toString() {
		return "Vanzare [idProdus=" + idProdus + ", idUser=" + idUser + ", codFactura=" + codFactura + ", cantitate="
				+ cantitate + "]";
	}


	
	
}
