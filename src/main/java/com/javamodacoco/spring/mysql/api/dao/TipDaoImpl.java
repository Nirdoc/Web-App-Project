package com.javamodacoco.spring.mysql.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javamodacoco.spring.mysql.api.model.Tip;

@Component
public class TipDaoImpl implements TipDao {

//	@Value("${spring.datasource.username}")
//	private String databaseUsername;
//	@Value("${spring.datasource.password}")
//	private String databasepassword;
//	@Value("${spring.datasource.url}")
//	private String databaseurl;
//	@Value("${spring.datasource.driver-class-name}")
//	private String databasedriver;
//	
//	Connection conn = null;
//	PreparedStatement ps = null;
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public List<Tip> getTipuri() {

		
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("SELECT t.tip_id as tip_id, t.denumire as denumire, r.denumire as denumireRaion " 
						 												+ "FROM tip t " 
						 												+ "JOIN raion r USING (raion_id);")){
				
				System.out.println("The Connection Object is of Class: " + connection.getClass());

				ResultSet resultSet = pstmt.executeQuery();
				ArrayList<Tip> tipuri = new ArrayList<Tip>();
				while (resultSet.next()) {
					int id = resultSet.getInt("tip_id");
					String denumire = resultSet.getString("denumire");
					String denumireRaion = resultSet.getString("denumireRaion");
					tipuri.add(new Tip(id, denumire, denumireRaion));
				}
				return tipuri;

			} catch (SQLException e) {
				e.printStackTrace();
			} 
			// aici compilatorul va scrie singur blocul fianally.
		return null;
	}
	


	@Override
	public boolean adaugaTip(String denumire, String raionIdString) {
		
		try (Connection connection = dataSource.getConnection();  // cerem o instanta de conexiune din ConnectionPool
				 PreparedStatement pstmt = connection.prepareStatement("INSERT INTO tip (denumire, raion_id) VALUES (?, ?);")){
			pstmt.setString(1, denumire);
			pstmt.setString(2, raionIdString);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} 
		return true;	
	}
	
	

}



