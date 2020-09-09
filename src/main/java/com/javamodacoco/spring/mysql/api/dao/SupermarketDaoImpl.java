package com.javamodacoco.spring.mysql.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.javamodacoco.spring.mysql.api.model.Supermarket;

@Component
public class SupermarketDaoImpl implements SupermarketDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<Supermarket> getSupermarkete() {
		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM supermarket;")) {

			ResultSet rs = pstmt.executeQuery();

			System.out.println("The Connection Object is of Class: " + connection.getClass());

			ArrayList<Supermarket> supermarkete = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("supermarket_id");
				String locatie = rs.getString("locatie");
				supermarkete.add(new Supermarket(id, locatie));
			}
			return supermarkete;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public boolean adaugaLocatie(String locatie) {
		try (Connection connection = dataSource.getConnection(); // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection
						.prepareStatement("INSERT INTO supermarket (locatie) VALUES (?);")) {

			pstmt.setString(1, locatie);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
