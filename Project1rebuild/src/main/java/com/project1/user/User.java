package com.project1.user;

import java.util.List;
import java.util.Objects;

//This is a POJO
//This is just for storage
public class User {
	private int employee_id;
	private String frist_name;
	private String last_name;
	private Boolean finance_manager;
	private String username;
	private String password;
	private List<Request> myRequests;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int employee_id, String frist_name, String last_name, Boolean finance_manager, String username,
			String password, List<Request> myRequests) {
		super();
		this.employee_id = employee_id;
		this.frist_name = frist_name;
		this.last_name = last_name;
		this.finance_manager = finance_manager;
		this.username = username;
		this.password = password;
		this.myRequests = myRequests;
	}

	@Override
	public String toString() {
		return "User [employee_id=" + employee_id + ", frist_name=" + frist_name + ", last_name=" + last_name
				+ ", finance_manager=" + finance_manager + ", username=" + username + ", password=" + password
				+ ", myRequests=" + myRequests + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employee_id, finance_manager, frist_name, last_name, myRequests, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		User other = (User) obj;
		return employee_id == other.employee_id && Objects.equals(finance_manager, other.finance_manager)
				&& Objects.equals(frist_name, other.frist_name) && Objects.equals(last_name, other.last_name)
				&& Objects.equals(myRequests, other.myRequests) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getFrist_name() {
		return frist_name;
	}

	public void setFrist_name(String frist_name) {
		this.frist_name = frist_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Request> getMyRequests() {
		return myRequests;
	}

	public void setMyRequests(List<Request> myRequests) {
		this.myRequests = myRequests;
	}

	public Boolean getFinance_manager() {
		return finance_manager;
	}

	public void setFinance_manager(Boolean finance_manager) {
		this.finance_manager = finance_manager;
	}

}
