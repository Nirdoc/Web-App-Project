package com.javamodacoco.spring.mysql.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javamodacoco.spring.mysql.api.model.Factura;

@Component
public class FacturaDaoImpl implements FacturaDao {

@Autowired
DataSource dataSource;
	
	@Override
	public List<Factura> getFacturi() {
		
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("SELECT f.factura_cod as factura_cod, f.data_facturare as data_facturare, f.pret_total as pret_total, s.locatie as locatieSupermarket "
						   												+ "FROM factura f "
						   												+ "JOIN supermarket s USING (supermarket_id);")){
					
						
			
						ResultSet rs = pstmt.executeQuery();
						ArrayList<Factura> facturi = new ArrayList<>();
						while(rs.next()) {
							String cod = rs.getString("factura_cod");
							String dataFacturare = rs.getString("data_facturare");
							double pretTotal = rs.getDouble("pret_total");
							String denumireSupermarket = rs.getString("locatieSuperMarket");
							facturi.add(new Factura(cod, dataFacturare,pretTotal, denumireSupermarket));
						}
						return facturi;
						
						
					} catch (Exception e) {
						System.out.println(e.getMessage());
					} 
		return null;
	}
	
	@Override
	public boolean adaugaFactura(int supermarketId) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO factura (supermarket_id) VALUES (?);")){
			
			
			pstmt.setInt(1, supermarketId);
			pstmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	@Override
	public int getCodFacturaDeLaUltimaFactura() {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM factura ORDER BY factura_cod DESC LIMIT 1")){
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				return rs.getInt("factura_cod");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		} 	
		return -1;
	}
	@Override
	public void setPretTotal(Integer id, Double pretTotal) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement("UPDATE factura SET pret_total = ? WHERE factura_cod = ?")){
		
			pstmt.setDouble(1, pretTotal);
			pstmt.setInt(2, id);
			
			// PASUL 3: executam statementul
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 			
	}
		
}
