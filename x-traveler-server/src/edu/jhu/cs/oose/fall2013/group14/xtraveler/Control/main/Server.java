package edu.jhu.cs.oose.fall2013.group14.xtraveler.Control.main;

import java.io.IOException;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONException;

/**
 * Server class contains the main method of the application. Server class
 * contains the main class to start the server.
 * 
 * @author Group14
 * @version java7
 */
public class Server {
	@SuppressWarnings("deprecation")
	static SessionFactory sessionFactory = new Configuration().configure()
			.buildSessionFactory();

	static SocketManager socketManager = new SocketManager(sessionFactory);

	/**
	 * input an integer which is the service port you want to open. Call
	 * SocketManager class to open that port. Also it can catch the IOException
	 * and JSONException
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		InitialData i = new InitialData(sessionFactory);
		i.insertData();
		Scanner input = new Scanner(System.in);

		System.out.println("input your java servicesocket port, for ex:4444");
		try {
			socketManager.createSocketManager(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
