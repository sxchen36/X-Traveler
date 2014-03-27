package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;

/**
 * CommandFactory should be implemented by each concrete command factory
 * 
 * @version java 7
 * @author iamrick86
 */
public interface CommandFactory {
	public String getCommandName();

	public Command makeCommand(JSONObject j) throws CommandException;

}
