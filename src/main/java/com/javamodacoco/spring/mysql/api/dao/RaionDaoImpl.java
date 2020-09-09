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
import com.javamodacoco.spring.mysql.api.model.Raion;

@Component
public class RaionDaoImpl implements RaionDao {

	@Autowired
	DataSource dataSource;

	public List<Raion> getRaioane() {

		try (Connection connection = dataSource.getConnection(); // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM raion;")) {

			ResultSet resultSet = pstmt.executeQuery();

			System.out.println("The Connection Object is of Class: " + connection.getClass());

			ArrayList<Raion> raioane = new ArrayList<>();
			while (resultSet.next()) {
				int id = resultSet.getInt("raion_id");
				String denumire = resultSet.getString("denumire");
				raioane.add(new Raion(id, denumire));
			}
			return raioane;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		// aici compilatorul va scrie singur blocul fianally.
		return null;
	}

	@Override
	public boolean adaugaRaion(String denumire) {

		try (Connection connection = dataSource.getConnection(); // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO raion (denumire) VALUES (?);")) {

			pstmt.setString(1, denumire);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
