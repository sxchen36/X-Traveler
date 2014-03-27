package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.WritePlaceComment;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * WritePlaceCommentFactory provides new WritePlaceComment command
 * @author iamrick86
 * @version java7
 */
public class WritePlaceCommentFactory implements CommandFactory{

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.WRITE_PLACE_COMMENT;
	}

	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new WritePlaceComment(j);
	}

}
