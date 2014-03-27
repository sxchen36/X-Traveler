package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetCityRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.GetCityResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Response;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;
/**
 * Enable user to choose State and City he wants to check
 * @author angeld
 *
 */
public class Menu extends Activity {
	/**
	 * Fields to contain the current position and display contents of the
	 * spinner
	 */
	protected int mPos;
	protected String mSelection;
	// ArrayAdapter connects the spinner widget to array-based data.
	private ArrayAdapter<CharSequence> adapter;
	private Spinner spinnerState;
	private TextView cityResult;
	private TextView stateResult;
	private Button confirmButton;
	private String userKey;
	private String username;
	SocketHelper helper;

	private JSONObject jsonobj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		userKey = getSharedPreferences(JsonKeys.USER_INFOS, 0).getString(
				JsonKeys.SESSION_KEY, "");
		username = getSharedPreferences(JsonKeys.USER_INFOS, 0).getString(
				JsonKeys.CURRENT_USER, "");
		helper = new SocketHelper();
		
		/*
		 * Create a backing mLocalAdapter for the Spinner from a list of the
		 * planets. The list is defined by XML in the strings.xml file.
		 */
		spinnerState = (Spinner) findViewById(R.id.spinner_state);
		this.adapter = ArrayAdapter.createFromResource(this, R.array.State,
				android.R.layout.simple_spinner_dropdown_item);
		spinnerState.setAdapter(this.adapter);
		spinnerState.setOnItemSelectedListener(new SpinnerSelectedListener(
				adapter, this));

		confirmButton = (Button) findViewById(R.id.confirm_city_selection);
		confirmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Request req = new GetCityRequest(userKey, stateResult
						.getText().toString(), cityResult.getText().toString());
				jsonobj = req.getJSON();
				new ConnectServerThread().start();
			}
		});

	}
	/* Communicate with Server*/
	class ConnectServerThread extends Thread {
		public void run() {
			try {
				helper.sendJSONToServer(jsonobj);
				JSONObject server_res = helper.toJSON(helper.getUTFFromServer());
				Response res = new GetCityResponse(server_res);
				res.run(Menu.this,City.class);
				helper.close();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Use array to implement the spinner
	 */
	public class SpinnerSelectedListener implements OnItemSelectedListener {
		ArrayAdapter<CharSequence> localAdapter;
		Activity localContext;

		/**
		 * Constructor
		 * 
		 * @param c The activity that displays the Spinner.
		 * @param ad The Adapter view that controls the Spinner. Instantiate
		 *            a new listener object.
		 */
		public SpinnerSelectedListener(ArrayAdapter<CharSequence> adapter,
				Activity activity) {
			this.localAdapter = adapter;
			this.localContext = activity;
		}

		/**
		 * When the user selects an item in the spinner, this method is invoked
		 * by the callback chain. Android calls the item selected listener for
		 * the spinner, which invokes the onItemSelected method.
		 * 
		 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView,
		 *      android.view.View, int, long)
		 * @param parent the AdapterView for this listener
		 * @param v the View for this listener
		 * @param pos the 0-based position of the selection in the
		 *            localAdapter
		 * @param row the 0-based row number of the selection in the View
		 */
		public void onItemSelected(AdapterView<?> parent, View v, int pos,
				long row) {
			Menu.this.mPos = pos;
			Menu.this.mSelection = parent.getItemAtPosition(pos).toString();
			// Set the value of the text field in the UI
			if (parent == spinnerState) {
				stateResult = (TextView) findViewById(R.id.spinner_state_result);
				stateResult.setText(Menu.this.mSelection);
				// Get list of city according to the chosen State
				int cityResource = getCityList(Menu.this.mSelection);
				if (cityResource == -1) {
					stateResult
							.setText("We don't have data for this State. Sorry");
				} else {
					Spinner spinnerCity = (Spinner) findViewById(R.id.spinner_city);
					ArrayAdapter<CharSequence> adapter = ArrayAdapter
							.createFromResource(
									Menu.this,
									cityResource,
									android.R.layout.simple_spinner_dropdown_item);
					spinnerCity.setAdapter(adapter);
					spinnerCity
							.setOnItemSelectedListener(new SpinnerSelectedListener(
									adapter, Menu.this));
				}
			} else {
				cityResult = (TextView) findViewById(R.id.spinner_city_result);
				cityResult.setText(Menu.this.mSelection);
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
		/*City list. Could be expended in future*/
		private int getCityList(String state) {
			if (state.equals("Maryland")) {
				return R.array.MDCity;
			} else if (state.equals("New York")) {
				return R.array.NYCity;
			} else if (state.equals("Florida")) {
				return R.array.FLCity;
			}
			return -1;
		}
	}

}
