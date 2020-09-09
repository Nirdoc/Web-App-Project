package com.javamodacoco.spring.mysql.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javamodacoco.spring.mysql.api.dao.FacturaDao;
import com.javamodacoco.spring.mysql.api.dao.SupermarketDao;
import com.javamodacoco.spring.mysql.api.model.Factura;
import com.javamodacoco.spring.mysql.api.model.Supermarket;

@Service
public class FacturaServiceImpl implements FacturaService{
	
	@Autowired
	FacturaDao facturaDao;
	
	@Autowired
	SupermarketDao supermarketDao;
	
	@Override
	public Map<String,Object> getFacturi() {
		
		List<Factura> facturi = facturaDao.getFacturi();
		List<Supermarket> supermarkete = supermarketDao.getSupermarkete();
		
		Map<String,Object> deReturnat = new HashMap<>();
		
		deReturnat.put("facturi", facturi);
		deReturnat.put("supermarkete", supermarkete);
		
		return deReturnat;
	}
}
