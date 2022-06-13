package com.project1.user;

import java.util.Objects;

public class Request {
	private int employee_id;
	private String user_name;
	private String request_type;
	private float request_amount;
	private boolean approved;

//	@JsonIgnore //Jackson will not try to serialize into the JSON on the response.
//	private User myUser;
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Request(int employee_id, String user_name, String request_type, float request_amount, boolean approved) {
		super();
		this.employee_id = employee_id;
		this.user_name = user_name;
		this.request_type = request_type;
		this.request_amount = request_amount;
		this.approved = approved;
	}

	@Override
	public String toString() {
		return "Request [employee_id=" + employee_id + ", user_name=" + user_name + ", request_type=" + request_type
				+ ", request_amount=" + request_amount + ", approved=" + approved + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(approved, employee_id, request_amount, request_type, user_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		Request other = (Request) obj;
		return approved == other.approved && employee_id == other.employee_id
				&& Float.floatToIntBits(request_amount) == Float.floatToIntBits(other.request_amount)
				&& Objects.equals(request_type, other.request_type) && Objects.equals(user_name, other.user_name);
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getRequest_type() {
		return request_type;
	}

	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}

	public float getRequest_amount() {
		return request_amount;
	}

	public void setRequest_amount(float request_amount) {
		this.request_amount = request_amount;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

}