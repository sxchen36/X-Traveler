package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;

/**
 * Page that shows all information about the login user Includes user image,
 * username, wish/visited-list, personal traveling plan and so on
 * 
 * @author angeld
 * 
 */
public class PersonalPage extends Activity {
	ArrayList<Bitmap> downloadImages;
	ImageView[] visitedImageViews;
	ArrayList<TextView> wishlistTextViews;
	ArrayList<TextView> visitlistTextViews;
	Button goFindCity;
	Button newPlan;
	JSONObject json_from_server;
	JSONArray wish_list_JSONArray;
	JSONArray visit_list_JSONArray;
	ArrayList<String> wish_list_name;
	ArrayList<String> visit_list_name;
	JSONArray visited_list_JSONArray;
	int wish_size;
	int visit_size;
	TextView username_view;
	public static final String USER_INFOS = "USERInfos";
	public static final String NAME = "NAME";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_page);
		Intent intent = getIntent();
		String recieve = intent.getStringExtra(JsonKeys.INFO_BETWEEN_PAGE);
		try {
			json_from_server = new JSONObject(recieve);
			wish_list_name = new ArrayList<String>();
			visit_list_name = new ArrayList<String>();
			wishlistTextViews = new ArrayList<TextView>();
			visitlistTextViews = new ArrayList<TextView>();
			JSONObject temp = json_from_server
					.getJSONObject(JsonKeys.RES_WISH_LIST);
			wish_list_JSONArray = temp.getJSONArray(JsonKeys.RES_WISH_LIST);
			visit_list_JSONArray = json_from_server.getJSONObject(
					JsonKeys.RES_VISITED_LIST).getJSONArray(
					JsonKeys.RES_VISITED_LIST);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Maximun 3 places to show
		wishlistTextViews.add((TextView) this.findViewById(R.id.wish_to_go_1));
		wishlistTextViews.add((TextView) this.findViewById(R.id.wish_to_go_2));
		wishlistTextViews.add((TextView) this.findViewById(R.id.wish_to_go_3));
		wish_size = wish_list_JSONArray.length();
		for (int i = 0; i < wish_size; i++) {
			try {
				wish_list_name.add((String) wish_list_JSONArray.get(i));

				TextView test = wishlistTextViews.get(i);
				test.setText(wish_list_name.get(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Maximun 3 places to show
		visitlistTextViews.add((TextView) this
				.findViewById(R.id.have_been_to_1));
		visitlistTextViews.add((TextView) this
				.findViewById(R.id.have_been_to_1));
		visitlistTextViews.add((TextView) this
				.findViewById(R.id.have_been_to_1));
		visit_size = visit_list_JSONArray.length();
		for (int i = 0; i < visit_size; i++) {
			try {
				visit_list_name.add((String) visit_list_JSONArray.get(i));
				// Maxmum 3
				TextView test = visitlistTextViews.get(i);
				test.setText(visit_list_name.get(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Get username from sharedpreferences
		SharedPreferences infos = getSharedPreferences(JsonKeys.USER_INFOS, 0);
		String username = infos.getString(JsonKeys.CURRENT_USER, "");
		username_view = (TextView) findViewById(R.id.personalPage_UserName);
		username_view.setText(username);
		goFindCity = (Button) findViewById(R.id.personal_page_goto_CityAcctraction);
		goFindCity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PersonalPage.this, Menu.class);
				PersonalPage.this.startActivity(intent);
			}
		});
		newPlan = (Button) findViewById(R.id.personal_page_new_travel_plan);
		newPlan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(PersonalPage.this, CreateTravelPlan.class);
				PersonalPage.this.startActivity(intent);
			}
		});
		final ListView listview = (ListView) findViewById(R.id.personalPage_travelplan_list);
		String[] values = new String[] { "Summer New York", "Winter PA",
				"2015 Winter Florida", "Next Year Maryland",
				"California-- Next year with Angel", "Getech Visit Plan" };

		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < values.length; ++i) {
			list.add(values[i]);
		}
		final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, list);
		listview.setAdapter(myAdapter);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				final String item = (String) parent.getItemAtPosition(position);
			}

		});

	}
}