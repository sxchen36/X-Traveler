package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.WritePlanCommand;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * WritePlanFactory provides new writeplan command.
 * @author iamrick86
 * @version java7
 */
public class WritePlanFactory implements CommandFactory {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.WRITE_YOUR_PLAN;
	}

	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new WritePlanCommand(j);
	}

}
