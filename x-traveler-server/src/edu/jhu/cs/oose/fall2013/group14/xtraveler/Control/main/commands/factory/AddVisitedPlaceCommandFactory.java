package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.AddVisitedPlaceCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * AddVisitedPlaceCommandFactory generates a new add visited place command
 * 
 * @version java 7
 * @author iamrick86
 */
public class AddVisitedPlaceCommandFactory implements CommandFactory {
	/**
	 * @return add visited place command name
	 */
	@Override
	public String getCommandName() {
		return JsonKeyValues.ADD_TO_VISITED;
	}

	/**
	 * @param j
	 *            JSONObject send from client side
	 * @return AddVisitedPlaceCommand
	 */
	@Override
	public Command makeCommand(JSONObject j) throws CommandException {

		return new AddVisitedPlaceCommand(j);

	}

}