package com.project1.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Statement;

public class P1_Sqlconnection {

//	public static void main (String[] args) {
//		// 5/19 Lectures on connecting java to database
//		//final String URL = jdbc:postgresql://{host}[:{port}]/[{database}]
//		final String URL = "jdbc:postgresql://database-1.cujgkusgasn5.us-east-1.rds.amazonaws.com:5432/postgres";
//		final String USERNAME = "java_login";
//		final String PASSWORD = "p4ssw0rd";
//
//		Connection connection;
//
//		try {
//			// First we connect to our database
//			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
//			System.out.println("Im Connected!");
//
//			//Second we try to insert something in.
//			String insert = "insert into bankuser (first_name, last_name, birthdate, checking, savings, user_name, pss_word) values ('Jerry', 'Berry', '05/14/2020', 32000, 10000, 'MilkMan', 'ladybug')";
//
//			//We instantiate a statement from the connection.
//			Statement statement = connection.createStatement();
//
//			//Execute the string by passing it to the statement
//			statement.execute(insert);
//
//			//Now we try to retrieve the data back
//			String selection = "select * from bankuser";
//			//create an object to collect all the records we recieve
//			ResultSet rs = statement.executeQuery(selection);
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	// setting up connection factory
	private static String URL = System.getenv("db_url");
	private static String USERNAME = System.getenv("db_username");
	private static String PASSWORD = System.getenv("db_password");

	private static Connection connection;

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
