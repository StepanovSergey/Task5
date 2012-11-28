package com.epam.task5.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is main app controller
 * @author Siarhei_Stsiapanau
 *
 */
public class Controller extends HttpServlet {
    private static final long serialVersionUID = -8219522518629860330L;
    
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	process(request, response);
    }

    private void process(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html");

	String page = "xml/products.xml";
	response.sendRedirect(page);
    }

}
