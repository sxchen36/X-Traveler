package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.SignUpCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * SignUpCommandFactory class generates new SignUpCommand
 * 
 * @author iamrick86
 * @version java7
 */
public class SignUpCommandFactory implements CommandFactory {
	/**
	 * get the sign up command name
	 */
	@Override
	public String getCommandName() {
		return JsonKeyValues.SIGNUP;
	}

	/**
	 * make a new sign up command
	 */
	@Override
	public Command makeCommand(JSONObject j) {
		return new SignUpCommand(j);
	}
}