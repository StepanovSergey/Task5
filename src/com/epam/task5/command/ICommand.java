package com.epam.task5.command;

import javax.servlet.http.HttpServletRequest;

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
     * @return name of page for forward
     */
    public String execute(HttpServletRequest request);
}
