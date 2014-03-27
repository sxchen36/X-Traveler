package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import org.json.JSONObject;

/**
 * Request interface, send from Client Side to Serverside
 * @author angeld
 *
 */
public interface Request {
	/**
	 * Get JSON Object ready to send to Servers for current Request
	 * @return
	 */
	public JSONObject getJSON();
}
