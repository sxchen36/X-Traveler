package edu.jhu.cs.oose.fall2013.group14.xtraveler.command;

import android.content.Context;
/**
 * Response Interface.Define the actions after getting positive response from Server
 * @author angeld
 *
 */
public interface Response {
	/**
	 * Switch from 'from' page to 'to' page
	 * @param from the page switch from
	 * @param to the page switch to
	 */
	public void run(Context from, Class to);
}
