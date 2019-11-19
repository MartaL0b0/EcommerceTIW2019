package es.uc3m.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import es.uc3m.ecommerce.manager.UserManager;
import es.uc3m.ecommerce.model.Appuser;
import es.uc3m.ecommerce.model.Product;

public class ShowProfileHandler implements IHandler {
	
	@Override 
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		System.out.println("SHOW PROFILE HANDLER");

		// Retrieve current user data by accession session attribute	
		HttpSession session = request.getSession();
		Appuser appuser = (Appuser) session.getAttribute("user");
		String viewURL = null; 
		
		if(session.getAttribute("user") == null) {
			
			System.out.println("No user logged in - SHOW PROFILE REQUEST HANDLER");
			
			viewURL = new ForbiddenPageHandler().handleRequest(request, response);
			
		} else {		
			session.setAttribute("user", appuser);
			viewURL = "profile.jsp";
			
		}
		
		return viewURL;
		
	}
	

}