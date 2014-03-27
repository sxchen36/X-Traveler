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
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetPersonalInfoRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetPersonalInfoResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Response;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.HttpUtil;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;

/**
 * The main page displays a welcome page for the user.\n Shows recent activities
 * of user's friends Contains a button leading to Menu page for further
 * searching Appears right after user login
 * 
 * @author angeld
 * 
 */
public class MainPage extends Activity {
	private TextView myTextView = null;
	private JSONObject jsonobj;
	private SocketHelper helper;
	private String sessionkey;

	private String username;
	TextView peo1;
	TextView peo2;
	ImageView pla1;
	ImageView pla2;
	JSONObject rec;
	ArrayList<Bitmap> downloadimages;
	ArrayList<String> urls;
	// private Handler mHandler;
	// private String userKey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		downloadimages = new ArrayList<Bitmap>();
		urls = new ArrayList<String>();
		Intent intent = getIntent();
		String content = intent.getStringExtra(JsonKeys.INFO_BETWEEN_PAGE);
		sessionkey = getSharedPreferences(JsonKeys.USER_INFOS, 0).getString(
			JsonKeys.SESSION_KEY, "");
		username = getSharedPreferences(JsonKeys.USER_INFOS, 0).getString(
				JsonKeys.CURRENT_USER, "");
		helper = new SocketHelper();
		// userKey = getSharedPreferences(JsonKeys.USER_INFOS,
		// 0).getString(JsonKeys.USER_INFOS, "");
		// mHandler = new MainPageHandler();

		// Display welcome word
		String value =username+", Welcome to:" ;
		myTextView = (TextView) findViewById(R.id.main_page_welcomeTitle);
		peo1 = (TextView)findViewById(R.id.people1);
		peo2 = (TextView)findViewById(R.id.people2);
		pla1 = (ImageView)findViewById(R.id.place1);
		pla2 = (ImageView)findViewById(R.id.place2);
		myTextView.setText(value);
		try {
			rec = new JSONObject(content);
			JSONArray ps = (JSONArray) rec.get(JsonKeys.RES_LIST_COMMON_USER);
			peo1.setText((String)ps.get(0));
			peo2.setText((String)ps.get(1));
			JSONArray us = (JSONArray) rec.get(JsonKeys.RES_LIST_COMMON_PLACE);
			urls.add((String)us.get(0));
			urls.add((String)us.get(1));
				
			new DownloadFileTask().execute();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* Switch to Menu Page*/
		Button findCityButton = (Button) findViewById(R.id.find_city);
		findCityButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// No need to communicate with Server
				Intent intent = new Intent();
				intent.setClass(MainPage.this, Menu.class);
				MainPage.this.startActivity(intent);
			}
		});

		/* Switch to Personal Page*/
		Button personalPageButton = (Button) findViewById(R.id.main_to_personal_page);
		personalPageButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Request req = new GetPersonalInfoRequest(sessionkey);
				jsonobj = req.getJSON();
				new ConnectServerThread().start();
			}
		});
	}

	/* New thread to communicate with Server*/
	class ConnectServerThread extends Thread {
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
					rep.run(MainPage.this, PersonalPage.class);
				}
				helper.close();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	/* New thread to download pictures */
	class DownloadFileTask extends AsyncTask<Void, Void, Void> {
		@Override
		public void onPostExecute(Void result) {
			 pla1.setImageBitmap(downloadimages.get(0));
			pla2.setImageBitmap(downloadimages.get(1));

		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				for (int i = 0; i < 2; i++) {
					HttpUtil httpUtil = new HttpUtil(urls.get(i));
					InputStream inputStream = httpUtil.getInputStream();
					downloadimages.add(BitmapFactory.decodeStream(inputStream));
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}
}
