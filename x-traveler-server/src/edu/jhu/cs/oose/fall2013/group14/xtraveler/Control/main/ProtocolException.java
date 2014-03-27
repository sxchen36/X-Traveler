package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

/**
 * ProtocolException class is responsible for the exception.
 * 
 * @author iamrick86
 */
public class ProtocolException extends Exception {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Reason reason;

	public ProtocolException(Exception cause, Reason reason) {
		super(cause);
		this.reason = reason;
	}

	/**
	 * 
	 * @author iamrick86
	 * 
	 */
	public static enum Reason {
		BAD_USERNAME_IN_REQUEST, MISSING_USERNAME_IN_REQUEST, BAD_PURPOSE_IN_REQUEST
	}
}
