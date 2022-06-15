package com.project1.services;

import com.project1.authentication.P1_AuthenticationController;
import com.project1.user.User;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class P1_RequestMapper {
	private P1_Controller P1_Controller = new P1_Controller();//reference to the next layer
	private User u = new User();
	private P1_AuthenticationController P1_AuthenticationController = new P1_AuthenticationController();
	private PrometheusMeterRegistry pMR = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
	
	public void configureRoutes(Javalin app) {
		
		app.get("/hello", ctx -> {
			ctx.result("hi Dad!");
		});

		// This will be for employee to view all their approved requests
		app.get("/Request/approved", ctx -> {
			P1_Controller.getAllRequestsApprovedByEmployee(ctx);
		});

		// This will be for employee to view all their pending requests
		app.get("/Request/pending", ctx -> {
			if(P1_AuthenticationController.verifyEmployee(ctx)) {
				P1_Controller.getAllRequestsPendingByEmployee(ctx);
			} else {
				ctx.status(HttpCode.FORBIDDEN);
			}
			
		});

		// This will be for employee to view all their requests
		app.get("/Requests", ctx -> {
			if(P1_AuthenticationController.verifyEmployee(ctx)) {
				P1_Controller.getAllRequestsByEmployee(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
			
			
		});

		// This will be for managers to view everyones requests
		app.get("/Manager/Requests", ctx -> {//Done
			if(P1_AuthenticationController.verifyEmployee(ctx)) {
				P1_Controller.getAllRequests(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
			
		});

		// This will be for managers to view everyones pending requests
		app.get("/Manager/Requests/Pending", ctx -> {//Done
			if(P1_AuthenticationController.verifyEmployee(ctx)) {
				P1_Controller.getAllRequestsPending(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
			
		});

		// This will be for managers to view everyones approved requests
		app.get("/Manager/Requests/Approved", ctx -> {//Done
			if(P1_AuthenticationController.verifyEmployee(ctx)) {
				P1_Controller.getAllRequestsApproved(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
		
		});
		//This is used to create a reimbursement request
		app.post("/CreateRequest", ctx -> {
			
			if(P1_AuthenticationController.verifyEmployee(ctx)) {
				P1_Controller.createRequest(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
			
		});
		//This is used by manager to approve requests
		app.put("/Manager/ApproveRequest", ctx -> {
			if(P1_AuthenticationController.verifyEmployee(ctx)) {
				P1_Controller.approveRequest(ctx);
			}else {
				ctx.status(HttpCode.FORBIDDEN);
			}
		});


		//This is used to login
		app.post("/login", ctx -> {
			P1_AuthenticationController.authenticateByFormParam(ctx);
//			System.out.println("I want to keep track of how many people has logged in");
//			total++;
//			counter.increment(1);
		});

		// This is used to log out
		app.get("/session/invalidate", ctx-> {
			ctx.consumeSessionAttribute("user");
		});

//		app.get("/metrics", ctx -> {
//			ctx.result(pMR.scrape());
//		});
				
	}
}

//Methods needed:
//- submit request for reimbursement
//- check all pending requests (manager)
//- check all previous requests (manager and user)
//- ability to approve or deny requests