package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.User;

/**
 * Record request time of a user. It will verify whether user has been time out.
 * 
 * @author iamrick86
 * @version java7
 */
public class UserSession {

	private User user;
	private long lastTime;
	long maxSessionTime;

	public UserSession(User user, long lastTime) {

		this.user = user;
		this.lastTime = lastTime;
		maxSessionTime = 600000;
	}

	public void setCurrentTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public long getPastTime() {
		return lastTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 
	 * @param currentTime
	 *            sent from server which is the current request time
	 * @return feedback if true, session is out. If false, session is not out.
	 */
	public boolean isSessionOut(long currentTime) {
		long intervalTime;
		boolean feedback;
		intervalTime = currentTime - lastTime;
		if (intervalTime < maxSessionTime) {
			feedback = false;
		} else {
			feedback = true;

		}
		return feedback;
	}

}
