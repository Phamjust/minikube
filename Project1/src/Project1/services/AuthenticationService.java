package Project1.services;

import Project1.Authentication.AuthenticationDao;
import Project1.Authentication.AuthenticationDaoImpl;
import Project1.users.User;

public class AuthenticationService {
	public static boolean employeeAuthentication(String un, String pw) {
		AuthenticationDao authentication= new AuthenticationDaoImpl();
		
		//we are looking at the database to see if username and password exist
		String employee_un = authentication.findUsernameExist(un);
		String employee_pw = authentication.findPasswordExist(pw);
		
		//These check to see if the username and passwords exist
		if(employee_un.equals("")) return false;
		if(employee_un.equals("")) return false;
		
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
}
	
	
	

