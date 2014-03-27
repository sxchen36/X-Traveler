package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlanDAO;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * PlanComment insert a plan comment in the databse.
 * 
 * @author iamrick86
 * @version java7
 */
public class PlanComment extends SessionCommand {

	public PlanComment(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * execution this command, insert plan comment into database. Also from
	 * JSONObject, we need to deserialize it to get the user who have commented
	 * on the plan, and the content of the comment.
	 * 
	 * @param context
	 *            Provides the necessary execution environment for the command.
	 * 
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject commentR = new JSONObject();
		// TODO Auto-generated method stub
		if (super.executeAuthenticated()) {
			PlanDAO plandao = context.getPlanDAO();

			String planName = j.getString(JsonKeys.PLAN_NAME);
			String friendName = j.getString(JsonKeys.FRIEND_NAME);
			String comment = j.getString(JsonKeys.PLAN_COMMENT_CONTENT);
			plandao.insertPlanComment(planName, friendName, comment);
			commentR.put(JsonKeys.RES_PLAN_COMMENT,
					JsonKeyValues.PLAN_COMMENT_SUC);
		} else {
			commentR.put(JsonKeys.RES_PLAN_COMMENT,
					JsonKeyValues.PLAN_COMMENT_FAIL);
		}
		return commentR;
	}

}
