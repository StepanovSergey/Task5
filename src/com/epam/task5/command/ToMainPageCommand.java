/**
 * 
 */
package com.epam.task5.command;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Siarhei_Stsiapanau
 *
 */
public class ToMainPageCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request) {
	return Constants.ERROR_PAGE;
    }

}
