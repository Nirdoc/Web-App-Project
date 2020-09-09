package com.javamodacoco.spring.mysql.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javamodacoco.spring.mysql.api.model.Produs;

@Component
public class ProdusDaoImpl implements ProdusDao{

	
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<Produs> getProduse(Set<Integer> productIds) {
		
			String sql = null;
			
			if (productIds == null) {
				
			sql="SELECT p.produs_id as produs_id, p.denumire as denumire, p.pret as pret, p.stoc as stoc, "
						+ "t.denumire as denumireTip, pr.denumire as denumireProducator "
						+ "FROM produs p "
						+ "JOIN tip t USING (tip_id) "
						+ "JOIN producator pr USING (producator_id);";
				try (Connection connection = dataSource.getConnection();
						PreparedStatement pstmt = connection.prepareStatement(sql)){
					
					ResultSet rs = pstmt.executeQuery();
					// rs este un fel de array de 5 producatori in cazul nostru.
					ArrayList<Produs> produse = new ArrayList<>();
					while(rs.next()) {
						int id = rs.getInt("produs_id");
						String denumire = rs.getString("denumire");
						double pret = rs.getDouble("pret");
						int stoc = rs.getInt("stoc");
						String denumireTip = rs.getString("denumireTip");
						String denumireProducator = rs.getString("denumireProducator");
						produse.add(new Produs(id,denumire,pret,stoc,denumireTip,denumireProducator));
					}
					return produse;
				} catch (Exception e) {
					e.printStackTrace();;
				} 
			}
				
			 else {
				
				// Aici obtinem numai detaiile produselor din cos, 
				// pentru ca trebuie calculam pretul total.
				//  SELECT Id, CompanyName, City, Country
			    //  FROM Supplier
			    //  WHERE Country IN ('USA', 'UK', 'Japan')
				 sql = "SELECT p.produs_id as produs_id, p.denumire as denumire, p.pret as pret, p.stoc as stoc, "
						   + "t.denumire as denumireTip, pr.denumire as denumireProducator "
						   + "FROM produs p "
						   + "JOIN tip t USING (tip_id) "
						   + "JOIN producator pr USING (producator_id) "
						   + "WHERE p.produs_id  IN ( ";
				StringBuilder sb  = new StringBuilder(sql);				
				
				for(int i = 0; i<productIds.size() - 1; i++) {
					sb.append("?, ");
				}
				sb.append("?); ");
				
				try (Connection connection = dataSource.getConnection();
						PreparedStatement pstmt = connection.prepareStatement(sb.toString())){
				
				int j = 1;
				for (int productId : productIds) {
					pstmt.setInt(j, productId);
					j++;
				}
				
				ResultSet rs = pstmt.executeQuery();
				// rs este un fel de array de 5 producatori in cazul nostru.
				ArrayList<Produs> produse = new ArrayList<>();
				while(rs.next()) {
					int id = rs.getInt("produs_id");
					String denumire = rs.getString("denumire");
					double pret = rs.getDouble("pret");
					int stoc = rs.getInt("stoc");
					String denumireTip = rs.getString("denumireTip");
					String denumireProducator = rs.getString("denumireProducator");
					produse.add(new Produs(id,denumire,pret,stoc,denumireTip,denumireProducator));
				}
				return produse;
				
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			}
			return null;
			
	}


	@Override
	public boolean adaugaProdus(String denumire, String pret, String stoc, String tipIdString, String producatorIdString) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("INSERT INTO produs (denumire, pret, stoc, tip_id, producator_id) VALUES (?, ?, ?, ?, ?);")){
			
			int tipId = Integer.parseInt(tipIdString);	
			int producatorId = Integer.parseInt(producatorIdString);

			pstmt.setString(1, denumire);
			pstmt.setString(2, pret);
			pstmt.setString(3, stoc);
			pstmt.setInt(4, tipId);
			pstmt.setInt(5, producatorId);
			
			// PASUL 3: executam statementul
			pstmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
	}

	@Override
	public boolean verifyThatStocIsGreaterThanZero(int produsId) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("SELECT stoc FROM produs WHERE produs_id = ?;")){
			
			System.out.println("The Connection Object is of Class: " + connection.getClass());
			
			pstmt.setInt(1, produsId);
			
			// PASUL 3: executam statementul
			ResultSet rs = pstmt.executeQuery();
			// rs este un fel de array de 5 producatori in cazul nostru.
			int id = -1;
			while(rs.next()) {
				id = rs.getInt("stoc");
			}
			return id > 0 ? true : false;
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public Integer getStoc(Integer produsId) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("SELECT stoc FROM produs WHERE produs_id = ?;")){
			
			
			pstmt.setInt(1, produsId);

			ResultSet rs = pstmt.executeQuery();        
			// ResultSet se aseamana aici cu un array de inturi.
			// Dar pentru ca avem clauza "WHERE produs_id = ?", arrayul va contine un singur element.
			int stoc = -1;
			if(rs.next()) {
				stoc = rs.getInt("stoc");
			}
			return stoc;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 	
		return null;
	}


	@Override
	public void setStocNou(Integer id, Integer stoc) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("UPDATE produs SET stoc = ? WHERE produs_id = ?")){
			
			pstmt.setInt(1, stoc);
			pstmt.setInt(2, id);
			
			// PASUL 3: executam statementul
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 	

	}

}
