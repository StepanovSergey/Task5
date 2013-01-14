package com.epam.task5.command;

import static com.epam.task5.resource.Constants.ADD_PRODUCT_COMMAND;
import static com.epam.task5.resource.Constants.COMMAND_PARAMETER;
import static com.epam.task5.resource.Constants.NO_COMMAND;
import static com.epam.task5.resource.Constants.SHOW_CATEGORIES_COMMAND;
import static com.epam.task5.resource.Constants.SHOW_PRODUCTS_COMMAND;
import static com.epam.task5.resource.Constants.SHOW_SUBCATEGORIES_COMMAND;
import static com.epam.task5.resource.Constants.XML_PATH;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;

/**
 * This class provides command factory
 * 
 * @author Siarhei_Stsiapanau
 * 
 */
public final class CommandFactory {
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final CommandFactory instance = new CommandFactory();
    private static Map<String, ICommand> commands;
    private static String realPath;
    private static File xmlFile;

    private CommandFactory() {
	commands = new HashMap<String, ICommand>();
	commands.put(SHOW_CATEGORIES_COMMAND, new ShowCategoriesCommand());
	commands.put(SHOW_SUBCATEGORIES_COMMAND, new ShowSubcategoriesCommand());
	commands.put(SHOW_PRODUCTS_COMMAND, new ShowProductsCommand());
	commands.put(ADD_PRODUCT_COMMAND, new AddProductCommand());
	commands.put(NO_COMMAND, new NoCommand());
    }

    /**
     * @return the instance
     */
    public static CommandFactory getInstance() {
	return instance;
    }

    /**
     * Get command instance from factory
     * 
     * @param request
     *            stores command name to return
     * @return command instance
     */
    public static ICommand getCommand(HttpServletRequest request) {
	if (realPath == null) {
	    realPath = request.getSession().getServletContext().getRealPath("");
	}
	if (xmlFile == null) {
	    xmlFile = new File(realPath + XML_PATH);
	}
	String commandName = request.getParameter(COMMAND_PARAMETER);
	System.out.println("Current Command: " + commandName);
	ICommand command = commands.get(commandName);
	if (command == null) {
	    command = commands.get(NO_COMMAND);
	}
	return command;
    }

    /**
     * @return the realPath
     */
    public static String getRealPath() {
	return realPath;
    }

    /**
     * @return the xmlFile
     */
    public static File getXmlFile() {
	return xmlFile;
    }

    /**
     * @return the lock
     */
    public static ReadWriteLock getLock() {
	return lock;
    }

}
