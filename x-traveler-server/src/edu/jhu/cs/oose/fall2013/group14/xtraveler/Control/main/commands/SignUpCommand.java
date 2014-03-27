package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import java.util.UUID;

import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSession;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.UserSessionRegistry;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * 
 * @author iamrick86 SignUpCommand is a concrete command which send the
 *         information to client if user sign up
 */
public class SignUpCommand implements OpenCommand {

	private String feedback;
	private boolean userInDB;
	SessionFactory sessionFactory;
	JSONObject jsonObject = new JSONObject();
	JSONObject SignUpResponse = new JSONObject();

	public SignUpCommand(JSONObject jsonObject) {

		this.jsonObject = jsonObject;
	}

	/**
	 * Execute the sign up process. In the execution process, we need to add a
	 * session key for the user, and save it in the Singleton for future use.
	 */
	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException {
		int a = 0;
		UserDAO userdao = context.getUserDAO();
		String sessionKey = null;
		System.out.println("a");
		String username = jsonObject.getString(JsonKeys.CURRENT_USER);
		String password = jsonObject.getString(JsonKeys.PASSWORD);
		String age = jsonObject.getString(JsonKeys.AGE);
		String sex = jsonObject.getString(JsonKeys.SEX);
		String email = jsonObject.getString(JsonKeys.EMAIL);
		if (age != null) {
			a = Integer.valueOf(age);
		}

		userInDB = userdao.userInDatabase(username);
		if (username == null || password == null) {
			System.out.println("user don't input password or username");
			feedback = JsonKeyValues.SIGN_UP_INVALID_INPUT;
			SignUpResponse.put(JsonKeys.RES_SIGN_UP, feedback);

		} else if (userInDB) {

			System.out.println("exist2");
			feedback = JsonKeyValues.SIGN_UP_USERNAME_DUP;
			SignUpResponse.put(JsonKeys.RES_SIGN_UP, feedback);

		} else {
			userdao.insertUser(username, password, a, email, sex);
			// Integer.valueOf(age), email,
			// sex);
			User user = userdao.getUser(username);
			System.out.println("exist");
			long lastTime = System.currentTimeMillis();
			UUID key = UUID.randomUUID();
			sessionKey = key.toString();
			System.out.println(sessionKey);
			UserSessionRegistry usersf = UserSessionRegistry.SINGLETON;
			usersf.addSession(sessionKey, new UserSession(user, lastTime));
			feedback = JsonKeyValues.SIGN_UP_SUC;
			SignUpResponse.put(JsonKeys.RES_SIGN_UP, feedback);
			SignUpResponse.put(JsonKeys.SESSION_KEY, sessionKey);
			System.out.println(SignUpResponse.toString());

		}

		return SignUpResponse;

	}

}