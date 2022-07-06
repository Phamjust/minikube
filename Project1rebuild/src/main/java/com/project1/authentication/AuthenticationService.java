package com.project1.authentication;

import com.project1.user.User;

public class AuthenticationService {
	public static boolean employeeAuthentication(String un, String pw) {
		AuthenticationDao authentication= new AuthenticationDaoImpl();
		
		//we are looking at the database to see if username and password exist
		String employee_un = authentication.findUsernameExist(un);
		String employee_pw = authentication.findPasswordExist(pw);
		
		//These check to see if the username and passwords exist
		if(employee_un.equals("")) return false;
//		if(employee_un.equals("")) return false;
		
		if(employee_un.equals(un) && employee_pw.equals(pw)) {
			return true;
		}
		return false;
		
	}
	
	public static User findEmployee(String un) {
		AuthenticationDao authenticationdao = new AuthenticationDaoImpl();
		User employee = authenticationdao.findEmployeeByUsername(un);
		
		return employee;
	}
	
	public static boolean managerAuthentication(String un) {
		AuthenticationDao authentication= new AuthenticationDaoImpl();
		boolean manager = authentication.verifyManagerStatus(un);
		return manager;
	}
}
	
	
	

