package edu.jhu.cs.oose.fall2013.group14.xtraveler.userinterface;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.TextView;

import com.example.userinterface.R;

import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.LoginRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.LoginSucResponse;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Request;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.Response;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.command.SignUpRequest;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeyValues;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.main.JsonKeys;
import edu.jhu.cs.oose.fall2013.group14.xtraveler.util.SocketHelper;

/**
 * The first Page when user opens the app
 * @author angeld
 * 
 */
public class LogInActivity extends Activity {
	TextView test;
	Handler mHandler;
	EditText username;
	EditText password;
	Button loginButton;
	Button signupButton;
	JSONObject jsonobj;
	SocketHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        username = (EditText)findViewById(R.id.usernameInput);
        password = (EditText)findViewById(R.id.passwordInput);
        test = (TextView)findViewById(R.id.testView);
        mHandler = new MessageHandler();
        helper = new SocketHelper();
        /* New a page to communicate with Server for login action*/
        loginButton = (Button)findViewById(R.id.login);
        loginButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Request req = new LoginRequest(username.getText().toString()
						,password.getText().toString() );
				jsonobj = req.getJSON();
				new LoginServerThread().start();
			}
		});
        /* New a page to communicate with Server for sign up action*/
        signupButton = (Button)findViewById(R.id.signup);
        signupButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				Intent t = new Intent();
				t.setClass(LogInActivity.this, Signup.class);
				startActivity(t);
			}
		});
	}

    /* Handle the error response getting from Server, print the corresponding error message on screen*/
	class MessageHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			try {
				String response = (helper.toJSON(msg.obj)).getString(JsonKeys.RES_LOGIN_ACT);
				// Just print a message, too trifle to refactor it here.
				if (response.equals(JsonKeyValues.LOGIN_SUC) || response.equals(JsonKeyValues.SIGN_UP_SUC)){
					test.setText("");
			    } else if (response.equals(JsonKeyValues.SIGN_UP_INVALID_INPUT)){
					test.setText("Invalid username or password to sign up.");	
				} else if (response.equals(JsonKeyValues.SIGN_UP_USERNAME_DUP)){
					test.setText("Username exists. Please select another one.");
				} else if (response.equals(JsonKeyValues.LOGIN_FAIL)){
					test.setText("Username / password doesn't match.");
				} else {
					test.setText("Unknown Response.");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    /* New a thread to communicate with Server*/
    class LoginServerThread extends Thread {
    	public void run() {
    		try {
    			helper.sendJSONToServer(jsonobj);   			
                /* Enable getting message from Server */
    			Message msg = mHandler.obtainMessage();
                msg.obj = helper.getUTFFromServer();   

                JSONObject resJSON = helper.toJSON(msg.obj);
                 
                /* Get certain response from Server */
                String server_res = (String) resJSON.getString(JsonKeys.RES_LOGIN_ACT);            
                if (server_res.equals(JsonKeyValues.LOGIN_SUC)) {
					SharedPreferences infos = getSharedPreferences(JsonKeys.USER_INFOS, 0);
//                	/*Store user session key into USER_INFOS.XML*/
					infos.edit().putString(JsonKeys.SESSION_KEY, resJSON.getString(JsonKeys.SESSION_KEY)).commit();
					
                	/*Store username into USER_INFOS.XML*/
					infos.edit().putString(JsonKeys.CURRENT_USER, username.getText().toString()).commit();  
					
					// No need to pass anything to next page
					Response res = new LoginSucResponse(resJSON);
					res.run(LogInActivity.this, MainPage.class);
					finish();
                }
                mHandler.sendMessage(msg);
                helper.close();
    		} catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
