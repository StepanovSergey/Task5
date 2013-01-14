package com.epam.task5.command;

import static com.epam.task5.resource.Constants.ERROR_PAGE;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This command shows products list
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class NoCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(NoCommand.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.epam.task5.command.ICommand#execute(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
	try {
	    response.sendRedirect(ERROR_PAGE);
	} catch (IOException e) {
	    if (logger.isEnabledFor(Level.ERROR)) {
		logger.error(e.getMessage(), e);
	    }
	}
    }

}
