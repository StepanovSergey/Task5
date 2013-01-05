package com.epam.task5.controller;

import static com.epam.task5.resource.Constants.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.task5.command.CommandFactory;
import com.epam.task5.command.ICommand;
import com.epam.task5.command.NoCommand;

/**
 * This is main app controller
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class XsltController extends HttpServlet {
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
	ICommand command = CommandFactory.getCommand(request);
	command.execute(request, response);
	if (!(command instanceof NoCommand)) {
	    String URL = request.getRequestURL() + "?"
		    + request.getQueryString();
	    request.getSession().setAttribute(BACK_PAGE, URL);
	}
    }
}
