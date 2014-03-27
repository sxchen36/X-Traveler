package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.GetVisitedListCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * GetVisitedListCommandFactory generates a new get visited list command
 * 
 * @version java7
 * @author iamrick86
 */
public class GetVisitedListCommandFactory implements CommandFactory {

	/**
	 * @return get visited list command name
	 */
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.GET_VISITED_LIST;
	}

	/**
	 * @param j
	 *            JSONObject sent from client sides
	 */
	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new GetVisitedListCommand(j);
	}
}
