package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.json.JSONException;

/**
 * SocektManager manage all sockets, apply thread to each
 *         socket
 * @author group14 Class 
 * @version java7
 */
public class SocketManager {

	private SessionFactory sessionFactory;
	private ServerSocket serverSocket;
	private Socket socket;
	

	public SocketManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
		serverSocket = null;
		socket = null;
	}

	/**
	 * createSocketManager method start the server
	 * 
	 * @throws IOException
	 * @throws JSONException
	 */
	public void createSocketManager(Scanner inputPortNumber) throws IOException, JSONException {
		
		int cur_port = inputPortNumber.nextInt();
		serverSocket = new ServerSocket(cur_port);
		
		while (true) {
			try {

				System.out.println("S: Receiving...");
				socket = serverSocket.accept();

				ClientHandler clientHandler = new ClientHandler(socket,
						sessionFactory);
				// This thread will do the talking
				Thread t = new Thread(clientHandler);
				t.start();

			} catch (IOException ioe) {
				System.out.println("IOException on socket listen: " + ioe);
				ioe.printStackTrace();
			}

		}
	}
}
