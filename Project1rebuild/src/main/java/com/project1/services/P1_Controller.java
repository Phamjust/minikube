package com.project1.services;

import java.util.List;

import com.project1.authentication.P1_AuthenticationController;
import com.project1.user.Request;
import com.project1.user.User;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class P1_Controller {

	private P1_requestService requestService = new P1_requestService();//Reference to the next layer
	private P1_AuthenticationController P1_AuthenticationController = new P1_AuthenticationController();

	// This will be for employee to grab all their pending requests
	public void getAllRequestsPendingByEmployee(Context ctx) {
		User employee = P1_AuthenticationController.sessionUser(ctx);
		List<User> employeeList = requestService.getAllRequestByEmployeePending(employee.getUsername());
		
		ctx.json(employeeList);
	}

	// This will be for employee to grab all their approved requests
	public void getAllRequestsApprovedByEmployee(Context ctx) {

	}

	// This will be for employee to grab all their requests
	public void getAllRequestsByEmployee(Context ctx) {
		User employee = P1_AuthenticationController.sessionUser(ctx);
//		Request jsonRequest = ctx.bodyAsClass(Request.class);
//		
//		P1_requestService.getAllRequestByEmployee(jsonRequest);
//		
//		ctx.status(201);

		List<User> employeeList = requestService.getAllRequestByEmployee(employee.getUsername());

		ctx.json(employeeList);

	}

	// This will be for manager to grab all approved requests
	public void getAllRequestsApproved(Context ctx) {
		List<Request> requestList = requestService.getOnlyRequestsApproved();
		ctx.json(requestList);
	}

	// This will be for manager to grab all pending requests
	public void getAllRequestsPending(Context ctx) {
		List<Request> requestList = requestService.getOnlyRequestsPending();
		ctx.json(requestList);
	}

	// This will be for manager to grab all requests
	public void getAllRequests(Context ctx) {

		// this is what this method should look like
		List<User> requestList = requestService.getAllRequests();

		ctx.json(requestList);

	}

	// This will be for creating a new reimbursement request
	public void createRequest(Context ctx) {
		System.out.println(ctx.body()); // reading from the HTTP Request body

		// Jackson (the dependency I have in maven) will parse the json into a java
		// object!
		Request p = ctx.bodyAsClass(Request.class);
		ctx.sessionAttribute("Request",p);
//		p.setApproved(false);// The user does not set the approval state when sending a request, so we set it to
//								// false here
		P1_requestService.createRequest(p);
		ctx.status(HttpCode.CREATED); // 201

		// might have to do this for this method
//		Planet jsonPlanet = ctx.bodyAsClass(Planet.class);
//		planetService.createPlanet(jsonPlanet.getName());

		ctx.status(201);

	}

	//This is for managers to approve pending requests
	public void approveRequest(Context ctx) {
		String username = ctx.formParam("username");
		//formParam cannot take float as an input so we are converting string to float
		Float password = Float.parseFloat(ctx.formParam("request_amount"));
		
		
		requestService.approveRequest(username, password);
	}
}

//Methods needed:
// - submit request for reimburstment
// - check all pending requests (manager)
// - check all previous requests (manager and user)
// - ability to approve or deny requests