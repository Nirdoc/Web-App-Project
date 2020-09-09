package com.javamodacoco.spring.mysql.api.dao;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javamodacoco.spring.mysql.api.model.Persoana;


@Component
public class PersoanaDaoImpl implements PersoanaDao {

	@Autowired
	DataSource dataSource;

	@Override
	public List<Persoana> getPersoane() {
		try (Connection connection = dataSource.getConnection(); // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement(
						"SELECT p.persoana_id as persoana_id, p.username as username, p.firstname as firstname, p.lastname as lastname, p.age as age, p.password as password, p.role as role "
								+ "FROM persoana p;")) {

			ResultSet rs = pstmt.executeQuery();
			// rs este un fel de array de 5 producatori in cazul nostru.
			ArrayList<Persoana> persoane = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("persoana_id");
				String username = rs.getString("username");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				int age = rs.getInt("age");
				String password = rs.getString("password");
				String role = rs.getString("role");
				persoane.add(new Persoana(id, username, firstname, lastname, age, password, role));
			}
			return persoane;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	@Override
	public boolean adaugaPersoana(String username, String firstname, String lastname, String ageString, String password,
			String role) {
		try (Connection connection = dataSource.getConnection(); // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement(
						"INSERT INTO persoana (username,firstname, lastname,age,password,role) VALUES (?,?,?,?,?,?);")) {

			int age = Integer.parseInt(ageString);
			pstmt.setString(1, username);
			pstmt.setString(2, firstname);
			pstmt.setString(3, lastname);
			pstmt.setInt(4, age);
			pstmt.setString(5, password);
			pstmt.setString(6, role);

			// PASUL 3: executam statementul
			pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean adaugaPersoana2(String username, String firstname, String lastname, String ageString, String password) {
		try (Connection connection = dataSource.getConnection(); // cerem o instanta de conexiune din ConnectionPool
				PreparedStatement pstmt = connection.prepareStatement(
						"INSERT INTO persoana (username,firstname, lastname,age,password) VALUES (?,?,?,?,?);")) {

			int age = Integer.parseInt(ageString);
			pstmt.setString(1, username);
			pstmt.setString(2, firstname);
			pstmt.setString(3, lastname);
			pstmt.setInt(4, age);
			pstmt.setString(5, password);

			// PASUL 3: executam statementul
			pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
