package com.javamodacoco.spring.mysql.api.dao;

import java.util.HashMap;
import java.util.List;

import com.javamodacoco.spring.mysql.api.dto.ProdusDePeFactura;
import com.javamodacoco.spring.mysql.api.model.Vanzare;

public interface VanzareDao {

	List < ProdusDePeFactura > getToateVanzarileDeLaOFactura(int codUltimaFactura);
	boolean adaugaVanzare(HashMap <Integer,Integer> produsIduriSiCantitati, int userId, int facturaCod);
	List <Vanzare> getToateVanzarileDeLaUnUser(String username);
	
}
