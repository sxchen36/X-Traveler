package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands;

import org.json.JSONException;
import org.json.JSONObject;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.CommandException;

/**
 * Command is an interface which concrete command will implements It is extends
 * by an interface OpenCommand, which are Login Command, SignUp Command and so
 * on Also it is implemented by an abstract class SessionCommand, which are
 * SearchPlaceCommand and so on
 * 
 * @author iamrick86
 * @version java7
 */
public interface Command {

	public JSONObject execute(ExecutionContext context)
			throws CommandException, JSONException;
}
