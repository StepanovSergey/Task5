package com.epam.task5.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     */
    public void execute(HttpServletRequest request, HttpServletResponse response);
}
