package com.project1.authentication;

import com.project1.user.User;

public interface AuthenticationDao {
	public String findUsernameExist(String username);
	
	public String findPasswordExist(String password);
	
	public User findEmployeeByUsername(String un);
}
