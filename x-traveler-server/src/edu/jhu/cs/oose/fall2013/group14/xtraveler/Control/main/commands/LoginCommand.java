package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSession;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * Give user access to his main page, also generate a session key for client.
 * Every time, user request, session key's attribute time will be updated
 * 
 * 
 * @version java7
 * @author Group14
 */
public class LoginCommand implements OpenCommand {
	private JSONObject jsonObject;

	public LoginCommand(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject execute(ExecutionContext context)
			throws NullPointerException, JSONException {
		User user;

		String feedback, sessionKey = null;

		JSONObject logResponse = new JSONObject();

		String username = jsonObject.getString(JsonKeys.CURRENT_USER);
		String password = jsonObject.getString(JsonKeys.PASSWORD);

		System.out.println("empty");

		boolean userInDB = context.getUserDAO().userInDatabase(username);

		System.out.println("before indb");
		if (userInDB) {

			UserDAO userdao = context.getUserDAO();
			String expPassword = userdao.getPassword(username);
			System.out.println(expPassword);
			boolean logInResult = expPassword.equals(password);

			if (logInResult) {
				feedback = JsonKeyValues.LOGIN_SUC;
				// user = new User();
				user = userdao.getUser(username);
				long lastTime = System.currentTimeMillis();
				UUID key = UUID.randomUUID();
				sessionKey = key.toString();
				System.out.println(sessionKey);
				UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
				usersf.addSession(sessionKey, new UserSession(user, lastTime));
				List<String> list1 = new ArrayList<String>();
				List<String> list2 = new ArrayList<String>();
				list1 = userdao.threeAttraction(username);
				list2 = userdao.threeUser(username);
				try {
					logResponse.put(JsonKeys.RES_LOGIN_ACT, feedback);
					logResponse.put(JsonKeys.SESSION_KEY, sessionKey);
					System.out.println(sessionKey);
					logResponse.put(JsonKeys.RES_LIST_COMMON_PLACE, list2);
					logResponse.put(JsonKeys.RES_LIST_COMMON_USER, list1);
					System.out.println(feedback);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else
				feedback = JsonKeyValues.LOGIN_FAIL;
			try {
				logResponse.put(JsonKeys.RES_LOGIN_ACT, feedback);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			feedback = JsonKeyValues.LOGIN_FAIL;

			try {
				logResponse.put(JsonKeys.RES_LOGIN_ACT, feedback);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return logResponse;
	}

}
