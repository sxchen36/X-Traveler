package edu.jhu.cs.oose.fall2013.group14.xtraveler.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * Util class enable other class to connect with Server via http
 * @author angeld
 *
 */
public class HttpUtil {
	private static String urlPath;

	/**
	 * Constructor
	 * @param URL URL where can connect and get content
	 */
	public HttpUtil(String URL) {
		urlPath = URL;
		// TODO Auto-generated constructor stub
	}
	/**
	 * Save image to a default local disk
	 * @throws IOException when the local disk is non-exist
	 */
	public void saveImageToDisk() throws IOException{
		InputStream inputStream = getInputStream();
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream("/home/angeld/test.png");
			while ((len = inputStream.read(data)) != -1){
				fileOutputStream.write(data,0,len);
			}
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream!=null){
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(fileOutputStream!=null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
	}

	/**
	 * Get Image from URL
	 * @return InputStream containing the information of the image
	 * @throws IOException 
	 */
	public InputStream getInputStream() throws IOException {
		HttpURLConnection httpURLConnection;
		InputStream inputStream = null;
			URL url = new URL(urlPath);
			if (url != null) {
				httpURLConnection = (HttpURLConnection) url.openConnection();
				// Set time out
				httpURLConnection.setConnectTimeout(3000);
				httpURLConnection.setDoInput(true);
				// GET request
				httpURLConnection.setRequestMethod("GET");
				int responseCode = httpURLConnection.getResponseCode();
				if (responseCode == 200) {
					inputStream = httpURLConnection.getInputStream();
				}
			}
		return inputStream;

	}
}
