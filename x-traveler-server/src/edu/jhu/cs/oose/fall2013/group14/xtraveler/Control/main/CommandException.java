package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

/**
 * CommandException extends from Exception, which will output some message due
 * the Command's problem.
 * 
 * @author iamrick86 CommandException will be thrown if it happens in command
 * @version java7
 */
public class CommandException extends Exception {

	private static final long serialVersionUID = -1819224819599600688L;

	public CommandException(String msg) {
		super(msg);
	}

}
