package com.javamodacoco.spring.mysql.api.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javamodacoco.spring.mysql.api.dto.ProdusDePeFactura;
import com.javamodacoco.spring.mysql.api.model.Vanzare;


@Component
public class VanzareDaoImpl implements VanzareDao{

	
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<ProdusDePeFactura> getToateVanzarileDeLaOFactura(int codUltimaFactura) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("SELECT p.produs_id as produsId, p.denumire as denumire, p.pret as pretUnitar, "
						 												+ " prod.denumire as denumireProducator, v.cantitate as cantitate "
						 												+ " FROM produs p "
						 												+ " JOIN producator prod USING (producator_id) "
						 												+ " JOIN vanzare v USING (produs_id) "
						 												+ " WHERE v.factura_cod = ?;")){
				
				System.out.println("The Connection Object is of Class: " + connection.getClass());

				pstmt.setInt(1,  codUltimaFactura);
				
				ResultSet resultSet = pstmt.executeQuery();
				ArrayList<ProdusDePeFactura> produseVandute = new ArrayList<ProdusDePeFactura>();
				
				while (resultSet.next()) {
					int produsId = resultSet.getInt("produsId");
					String denumire = resultSet.getString("denumire");
					double pretUnitar = resultSet.getDouble("pretUnitar");
					String denumireProducator = resultSet.getString("denumireProducator");
					int cantitate = resultSet.getInt("cantitate");
					double pretUnitarOriCantitate = pretUnitar * cantitate;
					produseVandute.add(new ProdusDePeFactura(produsId, denumire, pretUnitar, denumireProducator, cantitate, pretUnitarOriCantitate)) ;
				}
				return produseVandute;

			} catch (SQLException e) {
				e.printStackTrace();
			} 
			// aici compilatorul va scrie singur blocul fianally.
		return null;
			
	}
	@Override
	public List<Vanzare> getToateVanzarileDeLaUnUser(String username) {
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("select v.factura_cod as facturaCod,v.produs_id as produsId,v.cantitate as cantitate "
						 												+ " FROM vanzare v "
						 												+" join persoana pe using (persoana_id) "
						 												+ " WHERE pe.username = ?;")){
				
				System.out.println("The Connection Object is of Class: " + connection.getClass());
				
				pstmt.setString(1,  username);
				
				ResultSet resultSet = pstmt.executeQuery();
				ArrayList<Vanzare> produseVandute = new ArrayList<Vanzare>();
				while (resultSet.next()) {
					String facturaCod = resultSet.getString("facturaCod");
					int produsId = resultSet.getInt("produsId");
					int cantitate = resultSet.getInt("cantitate");
					produseVandute.add(new Vanzare(facturaCod, produsId, cantitate)) ;
				}
				
				
				return produseVandute;

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		// aici compilatorul va scrie singur blocul fianally.
	return null;
	}
	@Override
	public boolean adaugaVanzare(HashMap<Integer, Integer> produsIduriSiCantitati, int userId, int facturaCod) {

		StringBuilder sb = new StringBuilder("INSERT INTO vanzare (produs_id, persoana_id, factura_cod, cantitate ) VALUES ");
		
		for(int i = 0; i<produsIduriSiCantitati.size()-1; i++) {
			sb.append("(?,?,?,?), ");
		}
		sb.append("(?,?,?,?) ;");
		
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
			PreparedStatement pstmt = connection.prepareStatement(sb.toString())){
			
			Set<Integer> keySet = produsIduriSiCantitati.keySet();
			
			int i = 0;
			for(Integer key : keySet) {
							
				pstmt.setInt(i * 4 + 1, key);      //produsId e chiar cheia din HashMap
				pstmt.setInt(i * 4 + 2, userId);
				pstmt.setInt(i * 4 + 3, facturaCod);
				pstmt.setInt(i * 4 + 4, produsIduriSiCantitati.get(key));   // cantiatea este valoarea din HashMap
				
				i++;
			}
			
			pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		
		return true;
		
	}
	

}
