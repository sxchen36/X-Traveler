package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.LogOutCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * LogOutCommandFactory make new log out command
 * 
 * @version java7
 * @author iamrick86
 */
public class LogOutCommandFactory implements CommandFactory {
	/**
	 * get log in command name from json key which is sent from client side
	 */
	@Override
	public String getCommandName() {
		return JsonKeyValues.LOGOUT;
	}

	/**
	 * make a concrete log in command
	 */
	@Override
	public Command makeCommand(JSONObject j) {
		return new LogOutCommand(j);
	}
}
