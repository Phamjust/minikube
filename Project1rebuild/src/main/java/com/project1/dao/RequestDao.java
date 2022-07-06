package com.project1.dao;

import java.util.List;

import com.project1.user.Request;
import com.project1.user.User;


public interface RequestDao {
	//we will put methods for employee and requests in this class instead of create two classes

	public void insertRequest (Request r);

	public Integer findIdByUsername(String u);

	public List<User> selectAllEmployees();

	public List<Request> selectAllPendingRequests();

	public List<Request> selectAllApprovedRequests();

	// This is to select one employee
	public List<User> selectEmployee(String u);

	// This will grab all records for one employee
	public List<Request> selectAllRequestsByEmployee(User u);

	// This will grab all Approved records for one employee
	public List<Request> selectAllApprovedRequestsByEmployee(User u);

	// This will grab all Pending records for one employee
	public List<Request> selectAllPendingRequestsByEmployee(User u);
	
	public void approverequest(String un, Float amount);


	//These are all the updates

//	//Do not change this
//	public String findFirstName(String u);
//
//	public double checkChecking(String un);
//
//	public double checkSavings(String un);
//	//Do not change this
//	public boolean updateUserChecking(String un, double depositamount);
//	//Do not change this
//	public boolean updateUserSaving(String un, double depositamount);
//	//Do not change this
//	public String findUserUserName(String fn,String ln);
//	//Do not change this
//	public String checkPassword(String u);
//	//Do not change this
//	public java.util.Date findUserBirthdate(String fn, String ln);
//	//Do not change this
//	public void moneyTransfer(String un, double amount, String direction);
}

//Methods needed:
//- submit request for reimburstment
//- view all previous requests (user) (can be a combination of method 3 & 4)
//- check all pending requests (manager)
//- check all previous requests (manager and user)
//- ability to approve or deny requests