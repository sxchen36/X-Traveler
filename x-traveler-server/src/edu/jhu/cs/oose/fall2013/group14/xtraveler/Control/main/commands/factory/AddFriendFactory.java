package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.AddFriend;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * AddFriendFactory generates new add friend command
 * 
 * @version java7
 * @author iamrick86
 * 
 */
public class AddFriendFactory implements CommandFactory {

	/**
	 * @return commandName add friends' command name
	 */
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.ADD_FRIEND;
	}

	/**
	 * @param j
	 *            JSONObject passed from client side
	 * @return AddFriend() return a new AddFriend command
	 */
	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new AddFriend(j);
	}

}
