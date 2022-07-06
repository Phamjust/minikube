package com.project1.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project1.services.P1_Sqlconnection;
import com.project1.user.User;



public class AuthenticationDaoImpl implements AuthenticationDao {

	@Override
	public String findUsernameExist(String username) {
		String sqlcommand = "select user_name from p1_employee where user_name = ?;";
		Connection connection = P1_Sqlconnection.getConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String un = "";
		try {
			stmt = connection.prepareStatement(sqlcommand);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			while (rs.next()) {
				un = rs.getString(1);

//				System.out.println("id: "+ id);
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return un;
	
	}

	@Override
	public String findPasswordExist(String password) {
		String sqlcommand = "select password from p1_employee where password = ?;";
		Connection connection = P1_Sqlconnection.getConnection();
		PreparedStatement stmt;
		ResultSet rs;
		String un = "";
		try {
			stmt = connection.prepareStatement(sqlcommand);
			stmt.setString(1, password);
			rs = stmt.executeQuery();
			while (rs.next()) {
				un = rs.getString(1);

//				System.out.println("id: "+ id);
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return un;
	}

	@Override
	public User findEmployeeByUsername(String un) {
		//This method creates a whole user object that we use throughout authentication/program
		String sqlcommand = "select * from p1_employee where user_name = ?;";
		Connection connection = P1_Sqlconnection.getConnection();
		PreparedStatement stmt;
		ResultSet rs;
		User u = new User();
		try {
			stmt = connection.prepareStatement(sqlcommand);
			stmt.setString(1, un);
			rs = stmt.executeQuery();
			while (rs.next()) {
//				User u =new User(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), null);
				Integer id = rs.getInt(1);
				String first_name = rs.getString(2);
				String last_name = rs.getString(3);
				Boolean manager = rs.getBoolean(4);
				String user_name = rs.getString(5);
				String password = rs.getString(6);
				
				u.setEmployee_id(id);
				u.setFrist_name(first_name);
				u.setLast_name(last_name);
				u.setFinance_manager(manager);
				u.setUsername(user_name);
				u.setPassword(password);
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return u;
	}
	
	@Override
	public Boolean verifyManagerStatus(String un) {
		String sqlcommand = "select finance_manager from p1_employee where user_name = ?;";
		Connection connection = P1_Sqlconnection.getConnection();
		PreparedStatement stmt;
		ResultSet rs;
		Boolean manager = null;
		try {
			stmt = connection.prepareStatement(sqlcommand);
			stmt.setString(1, un);
			rs = stmt.executeQuery();
			while (rs.next()) {
				manager = rs.getBoolean(1);
			}
			rs.close();
			stmt.close();
			connection.close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return manager;
	
	}

}
