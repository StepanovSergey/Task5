package com.epam.task5.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.epam.task5.resource.Constants;

/**
 * This class provides command factory
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private static Map<String, ICommand> commands;
    private static String realPath;

    private CommandFactory() {
	commands = new HashMap<String, ICommand>();
	commands.put(Constants.NO_COMMAND, null);
	commands.put(Constants.SHOW_CATEGORIES_COMMAND,
		new ShowCategoriesCommand());
	commands.put(Constants.SHOW_SUBCATEGORIES_COMMAND,
		new ShowSubcategoriesCommand());
    }

    /**
     * @return the instance
     */
    public static CommandFactory getInstance() {
	return instance;
    }

    public static ICommand getCommand(HttpServletRequest request) {
	if (realPath == null) {
	    realPath = request.getSession().getServletContext().getRealPath("");
	}
	String commandName = request.getParameter(Constants.COMMAND_PARAMETER);
	System.out.println("Current Command: " + commandName);
	ICommand command = commands.get(commandName);
	if (command == null) {
	    command = commands.get(Constants.NO_COMMAND);
	}
	return command;
    }

    /**
     * @return the realPath
     */
    public static String getRealPath() {
        return realPath;
    }

}
