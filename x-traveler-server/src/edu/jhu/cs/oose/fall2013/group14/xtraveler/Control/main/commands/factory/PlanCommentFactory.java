package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory;

import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.PlanComment;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;

/**
 * Generates a new PlanCommentCommand
 * 
 * @version java7
 * @author iamrick86
 * 
 */
public class PlanCommentFactory implements CommandFactory {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return JsonKeyValues.PLAN_COMMENT;
	}

	@Override
	public Command makeCommand(JSONObject j) throws CommandException {
		// TODO Auto-generated method stub
		return new PlanComment(j);
	}

}
