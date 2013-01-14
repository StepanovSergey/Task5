package com.epam.task5.controller;

import static com.epam.task5.resource.Constants.BACK_PAGE;
import static com.epam.task5.resource.Constants.ERROR_PAGE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

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
    private static final Logger logger = Logger.getLogger(XsltController.class);
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
	HttpSession session = request.getSession();
	try {
	    ICommand command = CommandFactory.getCommand(request);
	    command.execute(request, response);
	    if (!(command instanceof NoCommand)) {
		String URL = request.getRequestURL() + "?"
			+ request.getQueryString();
		session.setAttribute(BACK_PAGE, URL);
	    }
	} catch (Exception e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	    response.sendRedirect(ERROR_PAGE);
	}
    }
}
