package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.ShowCommonFeaturePlace;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * ShowCommonFeaturePlaceFactory generate new ShowCommonFeaturePlace command
 * 
 * @author iamrick86
 * @version java7
 */
public class ShowCommonFeaturePlaceFactory implements CommandFactory {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.SHOW_COMMON_FEATURE_PLACE;
	}

	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new ShowCommonFeaturePlace(j);
	}

}
