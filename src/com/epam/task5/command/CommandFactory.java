package com.epam.task5.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides command factory
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private static Map<String, ICommand> commands;

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
	String commandName = request.getParameter(Constants.COMMAND_PARAMETER);
	System.out.println("Current Command: " + commandName);
	ICommand command = commands.get(commandName);
	if (command == null) {
	    command = commands.get(Constants.NO_COMMAND);
	}
	return command;
    }

}
