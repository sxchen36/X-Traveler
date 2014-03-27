package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlanDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * WritePlanCommand is a SessionCommand's subclass, which need to be
 * authenticated By applying this command, user can achieve writing a plan.
 * 
 * @author iamrick86
 * @version java7
 */
public class WritePlanCommand extends SessionCommand {

	public WritePlanCommand(JSONObject j) {
		super(j);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Insert a plan into database. This command need to be authenticated by the
	 * session key.
	 * 
	 * @param context
	 *            contains DAO class and sessionFactory needed for the database
	 *            communication with JVM
	 * @return write plan JSONObject sent back to client side
	 */
	@Override
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		JSONObject writeplan = new JSONObject();
		// TODO Auto-generated method stub
		if (super.executeAuthenticated()) {
			UserDAO userdao = context.getUserDAO();
			PlanDAO plandao = context.getPlanDAO();
			String sessionkey = j.getString(JsonKeys.SESSION_KEY);
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			User user = usersf.getUserSession(sessionkey).getUser();
			String userName = userdao.getName(user);
			String planName = j.getString(JsonKeys.PLAN_NAME);
			String budget = j.getString(JsonKeys.PLAN_BUDGET);
			String planDiscription = j.getString(JsonKeys.PLAN_DESCRIPTION);
			double b = Double.parseDouble(budget);

			plandao.insertPlan(userName, planName, b, planDiscription);

			writeplan.put(JsonKeys.RES_WRITE_PLAN,
					JsonKeyValues.WRITE_YOUR_PLAN_SUC);

		} else {
			writeplan.put(JsonKeys.RES_WRITE_PLAN,
					JsonKeyValues.WRITE_YOUR_PLAN_FAIL);

		}
		return writeplan;
	}

}
