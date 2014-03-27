package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.LoginSucResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Response;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.SignUpRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.LoginSucResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Response;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.SignUpRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;
/**
 * Signup page to allow a new user to enter his/her personal information
 * @author angeld
 *
 */
public class Signup extends Activity {
	EditText username;
	EditText nickname;
	EditText password;
	EditText confirm;
	EditText age;
	EditText email;
	// EditText sex;
	TextView exceptionmsg;
	Handler mHandler;
	Button submit;
	JSONObject jsonobj;
	RadioGroup gender;
	RadioButton radioSexButton;
	SocketHelper helper;
	TextView wrongmsg;
	Button clear;
	String genderstring ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);
		nickname = (EditText) findViewById(R.id.Nickname);
		username = (EditText) findViewById(R.id.Realname);
		password = (EditText) findViewById(R.id.Password);
		confirm = (EditText) findViewById(R.id.ConfirmPassword);
		email = (EditText) findViewById(R.id.Email);
		gender = (RadioGroup) findViewById(R.id.gendergroup);
		wrongmsg = (TextView) findViewById(R.id.Wrongmsg);
		clear = (Button) findViewById(R.id.Clear);
		age = (EditText) findViewById(R.id.Age);
		exceptionmsg = (TextView) findViewById(R.id.exceptionmsg);
		mHandler = new MessageHandler();
		helper = new SocketHelper();
		submit = (Button) findViewById(R.id.SignupSubmit);
		submit.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				int selectedId = gender.getCheckedRadioButtonId();
				radioSexButton = (RadioButton) findViewById(selectedId);
				
				if (radioSexButton != null){
					genderstring = (String) radioSexButton.getText();
				}
				boolean check = true;
				/* Validate the input*/
//					if (!password.getText().toString().equals(confirm.getText().toString())){
//						
//						wrongmsg.setText("Make sure password and confirm are the same");
//						check = false;
//					}
//					else if (!email.getText().toString().contains("@")){
//						wrongmsg.setText("Make sure an valid email");
//						check = false;
//					}
//					else if (Integer.valueOf(age.getText().toString())>150 ||Integer.valueOf(age.getText().toString())<0){
//						wrongmsg.setText("Make sure age is valid");
//						check = false;
//					}		
					
//						check = true;
					
//						wrongmsg.setText("");

						if (check == true){
						Request req = new SignUpRequest(username.getText().toString(),
								password.getText().toString(), genderstring, age
										.getText().toString(), email.getText()
										.toString());
						jsonobj = req.getJSON();
						new ConnectServerThread().start();
					}
				
			}
		});
		
		clear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				nickname.setText("");
				username.setText("");
				password.setText("");
				confirm.setText("");
				email.setText("");
				age.setText("");
			}
		});

	}

	/* Print error message if the response from server is negative*/
	class MessageHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			try {
				String response = (helper.toJSON(msg.obj))
						.getString(JsonKeys.RES_SIGN_UP);
				if (response.equals(JsonKeyValues.SIGN_UP_INVALID_INPUT)) {
					exceptionmsg
							.setText("Invalid username or password to sign up.");
				} else if (response.equals(JsonKeyValues.SIGN_UP_USERNAME_DUP)) {
					exceptionmsg
							.setText("Username exists. Please select another one.");
				} else if (response.equals(JsonKeyValues.LOGIN_FAIL)) {
					exceptionmsg.setText("Username / password doesn't match.");
				} else {
					exceptionmsg.setText("Unknown Response.");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/* Communicate with Server*/
	class ConnectServerThread extends Thread {
		public void run() {
			try {
				helper.sendJSONToServer(jsonobj);
				Message msg = mHandler.obtainMessage();
				msg.obj = helper.getUTFFromServer();
				JSONObject resJSON = helper.toJSON(msg.obj);
				String response = (String) resJSON
						.getString(JsonKeys.RES_SIGN_UP);
				if (response.equals(JsonKeyValues.LOGIN_SUC)
						|| response.equals(JsonKeyValues.SIGN_UP_SUC)) {
					SharedPreferences infos = getSharedPreferences(
							JsonKeys.USER_INFOS, 0);
					infos.edit().clear();
					/* Store user session key into USER_INFOS.XML */
					infos.edit()
							.putString(JsonKeys.SESSION_KEY,
									resJSON.getString(JsonKeys.SESSION_KEY))
							.commit();

					/* Store username into USER_INFOS.XML */
					infos.edit()
							.putString(JsonKeys.CURRENT_USER,
									username.getText().toString()).commit();
					Intent intent = new Intent();
					intent.setClass(Signup.this, LogInActivity.class);
					startActivity(intent);
					finish();
				} else {
					mHandler.sendMessage(msg);
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
