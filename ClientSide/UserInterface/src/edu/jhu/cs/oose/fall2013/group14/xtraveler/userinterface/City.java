package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetAttractionRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetAttractionResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetPersonalInfoRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetPersonalInfoResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Response;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface.TravelPlanView.ConnectServerThread;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.HttpUtil;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;
/**
 * City page which lists all the attractions in that city
 * @author angeld
 */
public class City extends Activity {
	SocketHelper helper = new SocketHelper();

	TextView cityname;
	JSONObject attractionlist;
	JSONObject jsonobj;
	JSONArray attractions;
	String sessionkey;

	ArrayList<String> attractionnames;
	ArrayList<String> attractionurls;
	ArrayList<String> attractionratings;
	ArrayList<Bitmap> downloadImages;
	int size;
	ListAdapter adapter;
	ListView listview;
	Button home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		downloadImages = new ArrayList<Bitmap>();
		Intent intent = getIntent();
		setContentView(R.layout.city);
		
		sessionkey = getSharedPreferences(JsonKeys.USER_INFOS, 0).getString(
				JsonKeys.SESSION_KEY, "");
		cityname = (TextView) findViewById(R.id.City);
		listview = (ListView) findViewById(R.id.Attractions);
		downloadImages = new ArrayList<Bitmap>();
		attractionnames = new ArrayList<String>();
		attractionurls = new ArrayList<String>();
		attractionratings = new ArrayList<String>();
		String recieve = intent.getStringExtra(JsonKeys.INFO_BETWEEN_PAGE);
		try {
			attractionlist = new JSONObject(recieve);
			attractions = attractionlist.getJSONArray("responseto search place");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		size = attractions.length();
		for (int i = 0; i < size; i++) {
			try {
				attractionnames.add((String) ((JSONArray) attractions.get(i)).get(0));
				attractionurls.add((String) ((JSONArray) attractions.get(i)).get(1));
				attractionratings.add((String) ((JSONArray) attractions.get(i)).get(2));
				} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		size = attractionnames.size();

		adapter = new AttractionAdapter(this,
				(String[]) attractionnames.toArray(new String[size]));

		String[] ratings = (String[]) attractionratings.toArray(new String[size]);
		((AttractionAdapter) adapter).initiaterating(ratings);
		listview.setItemsCanFocus(true);
		new DownloadFileTask().execute();
		
		/* Define how item reacts when being clicked*/
		listview.setOnItemClickListener( new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Request req = new GetAttractionRequest(sessionkey, cityname
						.getText().toString(), attractionnames.get(arg2));
				jsonobj = req.getJSON();
				new ConnectServerThread().start();

			}
		});
		
		home = (Button) findViewById(R.id.city_to_home);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Request req = new GetPersonalInfoRequest(sessionkey);
				jsonobj = req.getJSON();
				new HomeThread().start();
			}
		});
	}
	
	
	/**
	 * New thread to switch back to home
	 * @author angeld
	 *
	 */
	class HomeThread extends Thread {
		public void run() {
			try {
				helper.sendJSONToServer(jsonobj);
				JSONObject server_res = helper
						.toJSON(helper.getUTFFromServer());

				/* Get certain response from Server */
				String response = (String) server_res
						.get(JsonKeys.RES_PERSONAL_PAGE);
				if (response.equals(JsonKeyValues.PERSONAL_PAGE_SUC)) {
					GetPersonalInfoResponse rep = new GetPersonalInfoResponse(
							server_res);
					rep.run(City.this, PersonalPage.class);

				}
				helper.close();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	

	/**
	 * New thread to communicate with Server
	 * @author angeld
	 *
	 */
	class ConnectServerThread extends Thread {
		public void run() {
			try {
				helper.sendJSONToServer(jsonobj);
				JSONObject server_res = helper
						.toJSON(helper.getUTFFromServer());
				Response res = new GetAttractionResponse(server_res);
				res.run(City.this, Attraction.class);
				helper.close();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * New thread to download images
	 * @author angeld
	 */
	class DownloadFileTask extends AsyncTask<Void, Void, Void> {
		@Override
		public void onPostExecute(Void result) {
			// wishlistImageViews[1].setImageBitmap(downloadImages[1]);
			// wishlistImageViews[2].setImageBitmap(downloadImages[2]);

			((AttractionAdapter) adapter).initiatemap(downloadImages);
			listview.setAdapter(adapter);
		
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				for (int i = 0; i < size; i++) {
					HttpUtil httpUtil = new HttpUtil(attractionurls.get(i));
					InputStream inputStream = httpUtil.getInputStream();
					downloadImages.add(BitmapFactory.decodeStream(inputStream));
					// httpUtil = new HttpUtil(
					// "https://pbs.twimg.com/profile_images/1175032338/New_York_Daily_normal.jpg");
					// inputStream = httpUtil.getInputStream();
					// downloadImages[2] =
					// BitmapFactory.decodeStream(inputStream);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
