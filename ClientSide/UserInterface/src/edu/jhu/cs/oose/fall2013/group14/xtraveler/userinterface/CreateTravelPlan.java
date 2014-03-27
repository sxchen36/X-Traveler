package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.NewTravelPlanRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.NewTravelPlanResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Response;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;

/**
 * Page for user to create a travel plan
 * @author angeld
 * 
 */
public class CreateTravelPlan extends Activity {
	JSONObject jsonobj;
	EditText planName;
	EditText travelerNum;
	EditText Days;
	EditText budget;
	EditText discription;
	TextView hint;
	String username;
	String sessionkey;
	SocketHelper helper;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_travel_plan);
		
		helper = new SocketHelper();
		
		SharedPreferences infos = getSharedPreferences(JsonKeys.USER_INFOS, 0);
		username = infos.getString(JsonKeys.CURRENT_USER, "");
		sessionkey = infos.getString(JsonKeys.SESSION_KEY, "");
		
		planName = (EditText) findViewById(R.id.new_travel_plan);
		travelerNum = (EditText) findViewById(R.id.traveler_number_edit);
		Days = (EditText) findViewById(R.id.traveler_days_edit);
		budget = (EditText) findViewById(R.id.traveler_budget_edit);
		discription = (EditText) findViewById(R.id.other_plan_description_edit);
		hint = (TextView) findViewById(R.id.wrong_message_when_create_plan);
		Intent intent= getIntent();
		Button confirmButton = (Button) findViewById(R.id.create_plan_confirm);

		confirmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (!isValidPlan()) {
					hint.setText("Please fill all the blankets.");
				} else {
					Request req = new NewTravelPlanRequest(sessionkey, planName.getText().toString(), 
							travelerNum.getText().toString(), Days.getText().toString(),
							budget.getText().toString(),discription.getText().toString());
					jsonobj = req.getJSON();
					new createPlanThread().start();
				}
			}
		});

	}

	/* Check whether the input is valid*/
	private boolean isValidPlan() {
		return !(planName.getText().toString().equals("")
				|| travelerNum.getText().toString().equals("")
				|| Days.getText().toString().equals("")
				|| budget.getText().toString().equals(""));
	}
	/**
	 * New thread to communicate with Server
	 * @author angeld
	 *
	 */
	class createPlanThread extends Thread {
		public void run() {
			try {
				helper.sendJSONToServer(jsonobj);
				JSONObject resJSON = helper.toJSON(helper.getUTFFromServer());
				String res = resJSON.getString(JsonKeys.RES_WRITE_PLAN);
				if (res.equals(JsonKeyValues.WRITE_YOUR_PLAN_SUC)){
					Response response = new NewTravelPlanResponse(jsonobj);
					response.run(CreateTravelPlan.this, TravelPlanView.class);
					helper.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



