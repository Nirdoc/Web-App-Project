package com.javamodacoco.spring.mysql.api.dao;

import java.util.List;

import com.javamodacoco.spring.mysql.api.model.Factura;

public interface FacturaDao {
	
	List<Factura> getFacturi();
	boolean adaugaFactura(int supermarketId);
	int getCodFacturaDeLaUltimaFactura();
	void setPretTotal(Integer id, Double pretTotal);
	
	
}
