package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.javamodacoco.spring.mysql.api.dto.ProdusDePeFactura;
import com.javamodacoco.spring.mysql.api.model.Vanzare;

public interface VanzareService {

	List < ProdusDePeFactura > getToateVanzarileDeLaOFactura(int codUltimaFactura);
	Map<String,Object> adaugaVanzare(List<Integer> cos, int id);
	Map<String, Object> getToateVanzarileDeLaUnUser(String username);
}
