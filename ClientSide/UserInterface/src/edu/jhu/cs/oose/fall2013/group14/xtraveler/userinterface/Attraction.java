package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.io.IOException;
import java.io.InputStream;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.AddtoVisitedListRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.AddtoWishListRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.HttpUtil;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;
/**
 * The page showing a specific attraction site in the city
 * @author angeld
 *
 */
public class Attraction extends Activity {

	private ImageView attractionpic;
	private TextView attractionname;
	private TextView attractiondetail;
	private RatingBar attractionrating;
	Button addtowishlist;
	Button addtovisited;
	String url;
	Bitmap pic;
	String sessionkey;
	JSONObject send;
	SocketHelper helper;
	JSONObject server_res;
	TextView msg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.attraction);
		helper = new SocketHelper();
		/* Get Attraction details from previous page*/
		Intent intent = getIntent();
		String content = intent.getStringExtra(JsonKeys.INFO_BETWEEN_PAGE);
		JSONObject jo;
		sessionkey = getSharedPreferences(JsonKeys.USER_INFOS, 0).getString(
				JsonKeys.SESSION_KEY, "");
		/* Get View from UI*/
		attractionname = (TextView) findViewById(R.id.Attraction_Name);
		attractiondetail = (TextView) findViewById(R.id.AttractionDetail);
		attractionpic = (ImageView) findViewById(R.id.AttractionImage);
		attractionrating = (RatingBar) findViewById(R.id.attractionratingBar);
		addtowishlist = (Button) findViewById(R.id.AddtoWishList);
		addtovisited = (Button) findViewById(R.id.AddtoVisitedList);
		msg = (TextView) findViewById(R.id.attractionmsg);
		
		/* Initialize Attraction Details, prepare url to communicate to Server to get more 
		 * details about this site*/
		try {
			jo = new JSONObject(content);
			attractionname.setText(jo.getString(JsonKeys.PLACE_NAME));
			attractiondetail.setText(jo.getString(JsonKeys.PLACE_DES));
			attractionrating.setRating(Float.valueOf(jo
					.getString(JsonKeys.PLACE_RATE)));
			url = jo.getString(JsonKeys.PLACE_URI);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Start new thread to download pictures*/
		new DownloadFileTask().execute();
		
		/* Button: Add to wishlist*/
		addtowishlist.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Request req = new AddtoWishListRequest(sessionkey,
						attractionname.getText().toString());
				send = req.getJSON();
				new Sendrequest().execute();

			}
		});
		
		/* Button: Add to visitedlist*/
		addtovisited.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Request req = new AddtoVisitedListRequest(sessionkey,
						attractionname.getText().toString());
				send = req.getJSON();
				new Sendrequest().execute();

			}
		});
	}

	/**
	 * Multi-thread to communicate with Server
	 * @author angeld
	 *
	 */
	class Sendrequest extends AsyncTask<Void, Void, Void> {
		@Override
		public void onPostExecute(Void result) {
			try {
				if(server_res.getString(JsonKeys.RES_ADD_VISIT).equals(JsonKeyValues.ADD_TO_VISITEDLIST_SUC)||server_res.getString(JsonKeys.RES_ADD_WISH).equals(JsonKeyValues.ADD_TO_WISH_SUC)){
					msg.setText("add sucessful");
				}
				else
				{
					msg.setText("add fail");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				helper.sendJSONToServer(send);

				try {
					server_res = helper.toJSON(helper.getUTFFromServer());
					// Response res = new AddtoWishListResponse(server_res);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// res.run(City.this, Attraction.class);
				helper.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * Multi-thread to download pictures
	 * @author angeld
	 *
	 */
	class DownloadFileTask extends AsyncTask<Void, Void, Void> {
		@Override
		public void onPostExecute(Void result) {
			// wishlistImageViews[1].setImageBitmap(downloadImages[1]);
			// wishlistImageViews[2].setImageBitmap(downloadImages[2]);
			attractionpic.setImageBitmap(pic);
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				HttpUtil httpUtil = new HttpUtil(url);
				InputStream inputStream = httpUtil.getInputStream();
				pic = (BitmapFactory.decodeStream(inputStream));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}

}
