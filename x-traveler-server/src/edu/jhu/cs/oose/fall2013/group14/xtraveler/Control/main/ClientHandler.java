package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.hibernate.SessionFactory;
import org.json.JSONException;
import org.json.JSONObject;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.Command;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.ExecutionContext;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main.commands.factory.GeneralCommandFactory;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceCommentDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlaceDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.PlanDAO;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.Model.main.UserDAO;

/**
 * Opens a port to make connections between server and client side.
 * ClientHandler deal with user's input (which is serialized as JSOBObject) and
 * sent it to each concrete command. And also it can sent the information that
 * user needed back to client side.
 * 
 * @author iamrick86
 * @version java7
 */
public class ClientHandler implements Runnable {
	private Socket socket;
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param s
	 * 
	 * @param sessionFactory
	 *            passed from server. Since sessionFactory just can be built
	 *            once to avoid conflict, sessionFactory is passed from server
	 *            class
	 */
	public ClientHandler(Socket s, SessionFactory sessionFactory) {
		this.socket = s;
		this.sessionFactory = sessionFactory;
	}

	/**
	 * This method start the server in general. It receive request, and make a
	 * concrete command, then execute it and pass the execution result back to
	 * client side.
	 * 
	 * @exception IOException
	 * @exception JSONException
	 * @exception CommandException
	 */
	public void run() {
		GeneralCommandFactory generalCommandFactory = new GeneralCommandFactory();
		ExecutionContext context = new ExecutionContext(sessionFactory,
				new UserDAO(sessionFactory), new PlaceDAO(sessionFactory),
				new PlanDAO(sessionFactory),
				new PlaceCommentDAO(sessionFactory));

		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			JSONObject jsonObject = new JSONObject(dis.readUTF());
			// call exception to deal with illegal purpose command
			Command command = generalCommandFactory.create(jsonObject);
			DataOutputStream dos = new DataOutputStream(
					socket.getOutputStream());
			JSONObject response = command.execute(context);
			dos.writeUTF(response.toString());
		} catch (IOException | JSONException | CommandException
				| NullPointerException e) {

			e.printStackTrace();
		}
	}
}
