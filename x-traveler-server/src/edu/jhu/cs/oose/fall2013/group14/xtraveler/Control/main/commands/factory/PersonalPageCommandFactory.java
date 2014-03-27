package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.PersonalPageCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * Generate a new PersonalPageCommand
 * 
 * @version java7
 * @author iamrick86
 */
public class PersonalPageCommandFactory implements CommandFactory {
	/**
	 * return a command name
	 */
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.GET_PERSONAL_INFO;
	}

	/**
	 * make a new personal page command
	 */
	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new PersonalPageCommand(j);
	}

}
