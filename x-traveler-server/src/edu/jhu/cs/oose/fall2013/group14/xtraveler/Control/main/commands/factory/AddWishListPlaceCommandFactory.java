package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.AddWishPlaceCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * AddWishListPlaceCommandFactory generates new add wish list command
 * 
 * @version java7
 * @author iamrick86
 */
public class AddWishListPlaceCommandFactory implements CommandFactory {

	/**
	 * @return String command_name
	 */
	public String getCommandName() {
		return JsonKeyValues.ADD_TO_WISHLIST;
	}

	/**
	 * @param j
	 *            JSONObject send from client side
	 * @return AddWishPlaceCommand
	 */
	@Override
	public Command makeCommand(JSONObject j) {
		return new AddWishPlaceCommand(j);
	}

}
