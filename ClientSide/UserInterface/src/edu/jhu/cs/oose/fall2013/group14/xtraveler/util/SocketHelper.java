package edu.jhu.cs.oose.fall2013.group14.xtraveler.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;

public class SocketHelper{
	public static final String USER_INFOS = "USERInfos";
	public static final String NAME = "NAME";
	
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	/**
	 * Constructor
	 */
	public SocketHelper(){
	}
	
	public void sendJSONToServer(JSONObject req) throws UnknownHostException, IOException{
		// 10.0.2.2 for the emulator
		// IPv4 for laptop when connect into the same wifi network
		socket = new Socket("10.164.236.242", 4444); 
        dos = new DataOutputStream(
                socket.getOutputStream());
        dos.writeUTF(req.toString());
	}
	/**
	 * Get UTF information from Server
	 * @return String of the information getting from Server
	 * @throws IOException the connection fails
	 */
	public String getUTFFromServer() throws IOException{
		/* Enable getting message from Server */
        dis = new DataInputStream(
                socket.getInputStream());
		return dis.readUTF();
	}
	/**
	 * Change a String to JSONObject
	 * @param o String needed to build a new JSONObject; should from JSONObject.toString() needs to change to JSON
	 * @return JSONObject the JSONObject built by the given string
	 * @throws JSONException wrong input string
	 */
	public JSONObject toJSON(Object o) throws JSONException{
		return new JSONObject((String)o);
	}
	
	/**
	 * Close the connection with Server
	 * @throws IOException the action for the conection fails
	 */
	public void close() throws IOException{
		dis.close();
        dos.close();
        socket.close();
	}

	
}
