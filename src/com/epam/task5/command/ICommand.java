package com.epam.task5.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerConfigurationException;

/**
 * This interface provides command pattern
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public interface ICommand {
    /**
     * Do some actions depend on command that call this method
     * 
     * @param request
     *            request from page
     * @param response
     *            servlet response
     * @throws TransformerConfigurationException
     * @throws Exception
     *             if something wrong
     */
    public void execute(HttpServletRequest request, HttpServletResponse response)
	    throws Exception;
}
