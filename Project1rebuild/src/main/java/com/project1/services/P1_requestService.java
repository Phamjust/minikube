package com.project1.services;

import java.util.List;

import com.project1.authentication.P1_AuthenticationController;
import com.project1.dao.RequestDao;
import com.project1.dao.RequestDaoImpl;
import com.project1.user.Request;
import com.project1.user.User;




public class P1_requestService {
	private static RequestDao rDao = new RequestDaoImpl();
	private P1_AuthenticationController P1_AuthenticationController = new P1_AuthenticationController();

	public List<User> getAllRequestByEmployee(String un){
		
		List<User>  requestList= rDao.selectEmployee(un);

		for(User r: requestList) { //First we are iterating through the employee list

			//we select one employee at a time to add their requests
			r.setMyRequests(rDao.selectAllRequestsByEmployee(r));

			//we populate the employee with their requests
		}

		return requestList;

	}

	public List<User> getAllRequestByEmployeePending(String un){
		List<User>  requestList= rDao.selectEmployee(un);

		for(User r: requestList) { //First we are iterating through the employee list

			//we select one employee at a time to add their requests
			r.setMyRequests(rDao.selectAllPendingRequestsByEmployee(r));

			//we populate the employee with their requests
		}

		return requestList;
	}




	public static void createRequest(Request u) {
		Integer employee_id = rDao.findIdByUsername(u.getUser_name());
		Request request = new Request(employee_id,u.getUser_name(),u.getRequest_type(),u.getRequest_amount(),false);
//		System.out.println(request);
		rDao.insertRequest(request);

	}
	
	public List<User> getAllRequests() {//this is for the manager to get all the requests from every employee
		List<User>  requestList= rDao.selectAllEmployees();

		for(User r: requestList) { //First we are iterating through the employee list

			//we select one employee at a time to add their requests
			r.setMyRequests(rDao.selectAllRequestsByEmployee(r)); //<<DO not change this

			//we populate the employee with their requests
		}

		return requestList;

	}

	public List<Request> getOnlyRequestsPending(){
		List<Request> requestList=rDao.selectAllPendingRequests();
		return requestList;
	}
	
	public List<Request> getOnlyRequestsApproved(){
		List<Request> requestList=rDao.selectAllApprovedRequests();
		return requestList;
	}

	public void approveRequest(String un, Float amount) {
		rDao.approverequest(un, amount);
	}
}

	
