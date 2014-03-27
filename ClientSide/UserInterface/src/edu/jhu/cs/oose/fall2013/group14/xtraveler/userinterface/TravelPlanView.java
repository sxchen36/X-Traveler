package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetPersonalInfoRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetPersonalInfoResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface.MainPage.ConnectServerThread;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;

/**
 * Display the user-created travel plan.\n Get all the user input from
 * CreateTravelPlan Activity via intent
 * 
 * @author angeld
 * 
 */
public class TravelPlanView extends Activity {
	Button home;
	TextView plan_name;
	TextView Num_people;
	TextView days;
	TextView budget;
	TextView discrip;
	private JSONObject jsonobj;
	private SocketHelper helper;
	private String sessionkey;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.travel_plan_view);
		plan_name = (TextView) findViewById(R.id.plan_title0);
		Num_people = (TextView) findViewById(R.id.traveler_number_edit0);
		days = (TextView) findViewById(R.id.traveler_days_edit0);
		budget = (TextView) findViewById(R.id.traveler_budget_edit0);
		discrip = (TextView) findViewById(R.id.other_plan_description_edit0);
		helper = new SocketHelper();
		sessionkey = getSharedPreferences(JsonKeys.USER_INFOS, 0).getString(
				JsonKeys.SESSION_KEY, "");
		Intent intent = getIntent();
		JSONObject obj;
		try {
			obj = new JSONObject((String) intent.getExtras().get(
					JsonKeys.INFO_BETWEEN_PAGE));
			plan_name.setText((String) obj.get(JsonKeys.PLAN_NAME));
			Num_people.setText((String) obj.get(JsonKeys.PLAN_PEOPLE));
			days.setText((String) obj.get(JsonKeys.PLAN_DAYS));
			budget.setText((String) obj.get(JsonKeys.PLAN_BUDGET));
			discrip.setText((String) obj.get(JsonKeys.PLAN_DESCRIPTION));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		home = (Button) findViewById(R.id.travelplan_to_home);
		home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Request req = new GetPersonalInfoRequest(sessionkey);
				jsonobj = req.getJSON();
				new ConnectServerThread().start();
			}
		});
	}

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
					rep.run(TravelPlanView.this, PersonalPage.class);

				}
				helper.close();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}