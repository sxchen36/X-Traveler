package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.LoginCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * LoginCommandFactory class generates LoginCommand class
 * 
 * @author iamrick86
 */
public class LoginCommandFactory implements CommandFactory {

	/**
	 * get the log in command name
	 */
	@Override
	public String getCommandName() {
		return JsonKeyValues.LOGIN;
	}

	/**
	 * make a concrete log in command
	 */
	@Override
	public Command makeCommand(JSONObject j) {
		return new LoginCommand(j);
	}

}
