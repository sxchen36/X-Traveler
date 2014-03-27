package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.DeleteFriend;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * DeleteFriendFactory provides a new DeletaFriend command for each user.
 * @author iamrick86
 * @version java7
 */
public class DeleteFriendFactory implements CommandFactory {

	@Override
	public String getCommandName() {

		// TODO Auto-generated method stub
		return JsonKeyValues.DELETE_FRIEND;
	}

	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new DeleteFriend(j);
	}

}
