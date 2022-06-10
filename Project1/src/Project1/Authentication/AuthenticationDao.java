package Project1.Authentication;

import Project1.users.User;

public interface AuthenticationDao {
	public String findUsernameExist(String username);
	
	public String findPasswordExist(String password);
	
	public User findEmployeeByUsername(String un);
}
