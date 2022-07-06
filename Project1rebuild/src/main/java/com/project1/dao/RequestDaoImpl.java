package com.project1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project1.services.P1_Sqlconnection;
import com.project1.user.Request;
import com.project1.user.User;



public class RequestDaoImpl implements RequestDao {

	@Override
	public void insertRequest(Request r) {
		
//		//we need to find employee id
//		User w = new User();
//		Request r = new Request();
//		int a = findIdByUsername(w);//this passes the User u to the findIdByUsername method above, and looks for the employee id
//		r.setEmployee_id(a);//this sets the employee id to the Requests pojo

		String sql = "INSERT INTO p1_reimbursement (employee_id, user_name, request_type, request_amount, approved) VALUES (?,?,?,?,false);";

		Connection connection = P1_Sqlconnection.getConnection();

		// try with resources, can be used with anything that implements AutoClosable,
		// e.g. a connection.
		try (PreparedStatement ps = connection.prepareStatement(sql)) { // connection will be closed after we are done!

			ps.setInt(1, r.getEmployee_id());
			ps.setString(2, r.getUser_name());
			ps.setString(3, r.getRequest_type());
			ps.setDouble(4, r.getRequest_amount());

			ps.execute(); // We use execute when we DONT expect anything back
			// ps.executeQuery(); //WE use use we DO expect something back!

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Integer findIdByUsername(String u) {
		// This will find the users employee id from the username entered earlier in the
		// program
		String sqlcommand = "select employee_id from p1_employee where user_name = ?;";
		Connection connection = P1_Sqlconnection.getConnection();
		PreparedStatement stmt;
		ResultSet rs;
		int un = 0;
		try {
			stmt = connection.prepareStatement(sqlcommand);
			stmt.setString(1, u);
			rs = stmt.executeQuery();
			while (rs.next()) {
				un = rs.getInt(1);

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
	public List<Request> selectAllPendingRequests() { //this is complete
		String sqlcommand = "Select * from p1_reimbursement where approved = false;";
		Connection connection = P1_Sqlconnection.getConnection();

		List<Request> requestList = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(sqlcommand);

			while (rs.next()) {
				// create a user object from the rows and then add to the list
				Request r = new Request(rs.getInt("employee_id"), rs.getString("user_name"),
						rs.getString("request_type"), rs.getFloat("request_amount"), rs.getBoolean("approved"));
				requestList.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestList;
	}

	@Override
	public List<Request> selectAllApprovedRequests() { //this is complete. Need to change this to prepared statement
		String sqlcommand = "Select * from p1_reimbursement where approved = true;";
		Connection connection = P1_Sqlconnection.getConnection();

		List<Request> requestList = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(sqlcommand);

			while (rs.next()) {
				// create a user object from the rows and then add to the list
				Request r = new Request(rs.getInt("employee_id"), rs.getString("user_name"),
						rs.getString("request_type"), rs.getFloat("request_amount"), rs.getBoolean("approved"));
				requestList.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestList;
	}

	@Override
	public List<User> selectAllEmployees() { //this is complete. need to change this to prepared statement
		String sqlcommand = "Select * from p1_employee;";
		Connection connection = P1_Sqlconnection.getConnection();

		List<User> employeeList = new ArrayList<>();

		try {
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(sqlcommand);

			while (rs.next()) {
				// create a user object from the rows and then add to the list
				User u = new User(rs.getInt("employee_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getBoolean("finance_manager"), rs.getString("user_name"), rs.getString("password"), null);
				employeeList.add(u);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employeeList;

	}

	@Override
	public List<User> selectEmployee(String u) {//This is to find employee info from their username
		String sqlcommand = "Select * from p1_employee where user_name = ?;";
		Connection connection = P1_Sqlconnection.getConnection();

		List<User> requestList = new ArrayList<>();
		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(sqlcommand);
			stmt.setString(1, u);
			rs = stmt.executeQuery();

			while (rs.next()) {
				// create a user object from the rows and then add to the list
				User d = new User(rs.getInt("employee_id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getBoolean("finance_manager"), rs.getString("user_name"), rs.getString("password"), null);
				requestList.add(d);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestList;

	}

	@Override
	public List<Request> selectAllRequestsByEmployee(User u) { // This will be used by employee and manager to look up
																// requests
		String sql = "SELECT * FROM p1_reimbursement WHERE employee_id = "
				+ "(SELECT employee_id FROM p1_employee WHERE user_name = ?);";

		Connection connection = P1_Sqlconnection.getConnection();
		List<Request> requestList = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, u.getUsername());
			ResultSet rs = ps.executeQuery(); // 

			while (rs.next()) {
				Request m = new Request(rs.getInt("employee_id"), rs.getString("user_name"),
						rs.getString("request_type"), rs.getFloat("request_amount"), rs.getBoolean("approved"));

				requestList.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestList;

	}

	@Override
	public List<Request> selectAllApprovedRequestsByEmployee(User u) {
		String sql = "Select * from p1_reimbursement where approved = 'true' and user_name=?;";
		Connection connection = P1_Sqlconnection.getConnection();
		List<Request> requestList = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, u.getUsername());
			ResultSet rs = ps.executeQuery(); 

			while (rs.next()) {
				Request m = new Request(rs.getInt("employee_id"), rs.getString("user_name"),
						rs.getString("request_type"), rs.getFloat("request_amount"), rs.getBoolean("approved"));

				requestList.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestList;
	}

	@Override
	public List<Request> selectAllPendingRequestsByEmployee(User u) {
		String sql = "Select * from p1_reimbursement where approved = 'false' and user_name=?;";
		Connection connection = P1_Sqlconnection.getConnection();
		List<Request> requestList = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, u.getUsername());
			ResultSet rs = ps.executeQuery(); 

			while (rs.next()) {
				Request m = new Request(rs.getInt("employee_id"), rs.getString("user_name"),
						rs.getString("request_type"), rs.getFloat("request_amount"), rs.getBoolean("approved"));

				requestList.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return requestList;
		
	}

	@Override
	public void approverequest(String un, Float amount) {
		String sql = "UPDATE p1_reimbursement set approved = true WHERE user_name=? and request_amount=?;";

		Connection connection = P1_Sqlconnection.getConnection();

		// try with resources, can be used with anything that implements AutoClosable,
		// e.g. a connection.
		try (PreparedStatement ps = connection.prepareStatement(sql)) { // connection will be closed after we are done!

			ps.setString(1, un);
			ps.setFloat(2, amount);
			

			ps.execute(); // We use execute when we DONT expect anything back
			// ps.executeQuery(); //WE use use we DO expect something back!
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}



