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

import com.javamodacoco.spring.mysql.api.model.Producator;

@Component
public class ProducatorDaoImpl implements ProducatorDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<Producator> getProducatori() {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM producator")) {

			System.out.println("The Connection Object is of Class: " + connection.getClass());
			ResultSet resultSet = pstmt.executeQuery();
			ArrayList<Producator> producatori = new ArrayList<Producator>();

			while (resultSet.next()) {
				int id = resultSet.getInt("producator_id");
				String denumire = resultSet.getString("denumire");
				String adresa = resultSet.getString("adresa");
				producatori.add(new Producator(id, denumire, adresa));
			}
			return producatori;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// aici compilatorul va scrie singur blocul fianally.
		return null;
	}

	public List<Producator> getProducator(int id) {

		try (Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM producator where producator_id = \" + id + \";")) {

			System.out.println("The Connection Object is of Class: " + connection.getClass());
			ResultSet resultSet = pstmt.executeQuery();
			ArrayList<Producator> producatori = new ArrayList<Producator>();

			while (resultSet.next()) {
				int producatorId = resultSet.getInt("producator_id");
				String denumire = resultSet.getString("denumire");
				String adresa = resultSet.getString("adresa");
				producatori.add(new Producator(producatorId, denumire, adresa));
			}
			return producatori;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// aici compilatorul va scrie singur blocul fianally.
		return null;
	}

	@Override
	public boolean adaugaProducator(String denumire, String adresa) {

		try (Connection connection = dataSource.getConnection(); // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection
						.prepareStatement("INSERT INTO" + " producator (denumire, adresa) VALUES (?,?)")) {
			pstmt.setString(1, denumire);
			pstmt.setString(2, adresa);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
