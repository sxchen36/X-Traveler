package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.ListFriend;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;


/**
 * Provide a new ListFriend Command.
 * @author iamrick86
 * @version java7
 */
public class ListFriendFactory implements CommandFactory{

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.LIST_FIREND;
	}

	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new ListFriend(j);
	}

}
