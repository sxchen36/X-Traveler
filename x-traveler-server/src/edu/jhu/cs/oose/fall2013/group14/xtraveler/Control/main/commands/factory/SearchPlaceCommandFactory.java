package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.SearchPlaceCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * SearchPlaceCommandFactory generates new search place command
 * 
 * @author iamrick86
 */
public class SearchPlaceCommandFactory implements CommandFactory {
	/**
	 * return a search place command name
	 */
	@Override
	public String getCommandName() {
		return JsonKeyValues.GET_CITY_ATTRACTION;
	}

	/**
	 * make a new SearchPlaceCommand
	 */
	@Override
	public Command makeCommand(JSONObject j) {

		return new SearchPlaceCommand(j);

	}

}
