package com.project1.authentication;

import org.eclipse.jetty.http.HttpStatus;

import com.project1.user.User;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class P1_AuthenticationController {

	public static void authenticateByFormParam(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");

		// This checks for authentication with username and password
		boolean access = AuthenticationService.employeeAuthentication(username, password);

		if (access) {
			User user = AuthenticationService.findEmployee(username);
			ctx.result("You have access");
			ctx.status(HttpStatus.ACCEPTED_202);

			ctx.sessionAttribute("user", user);
		} else {
			ctx.result("Access denied");
			ctx.status(HttpCode.FORBIDDEN);
		}

	}

	// This just validates if a session exists. Use this for the request mapper
	// endpoints
	public static boolean verifyEmployee(Context ctx) {
		User user = ctx.sessionAttribute("user");
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

	public static User sessionUser(Context ctx) {
		// This method is simply returning the sessionAttribute so that our methods in
		// requestMapper know whos the current user
		User user = ctx.sessionAttribute("user");
		if(user == null) {
			return null;
		} else {
			return user;
		}
	}
	public static boolean verifyManager(Context ctx) {
		//First we check if there is a valid session (ie. the user was able to login)
		User user = ctx.sessionAttribute("user");
		
		if (user == null) {
			return false;
		} else {
			Boolean managerStatus= AuthenticationService.managerAuthentication(user.getUsername());
			if (managerStatus) {
				return true;
			} else {
				return false;
			}
			
		}
		
	}

}



//public static void authenticateByFormParam(Context ctx) {
//
//	String username = ctx.formParam("username");
//	String password = ctx.formParam("password");
//
////	User u = new User(ctx.formParam("username"), ctx.formParam("password"));
//
//	boolean access = AuthenticateService.authenticateUser(username, password);
//
//	if (access) {
//		ctx.result("You have access");
//		ctx.status(HttpStatus.ACCEPTED_202);
//
//		ctx.sessionAttribute("user", u);
////		ctx.sessionAttribute("role", "super user");
//	} else {
//		ctx.result("You don't have access!");
//		ctx.status(HttpCode.FORBIDDEN);
////		ctx.sessionAttribute("role", "hacker");
//	}
//
//}
////Change this up. Could verify depending if the user is employee or manager
//public static boolean verifyUser(Context ctx) {
//	User u = ctx.sessionAttribute("user");
//
//	if (u == null) {
//		return false;
//	} else {
//		return true;
//	}

