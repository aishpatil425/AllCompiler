package com.example.AllCompiler;

import java.net.URLEncoder;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.myproperty.R;
import com.example.myproperty.R.layout;
import com.example.myproperty.R.menu;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	JCGSQLiteRegister db = new JCGSQLiteRegister(this);
	EditText t1,t2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		t1 =(EditText) findViewById(R.id.LogEditText01);
		t2 =(EditText) findViewById(R.id.logeditText2);

		Button b2 =(Button) findViewById(R.id.buttonloguser1);
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				try{	
			
					
					String emailval = URLEncoder.encode(t1.getText().toString(), "UTF-8");
					String passval = URLEncoder.encode(t2.getText().toString(), "UTF-8");
	
					
					Toast.makeText(getBaseContext(),
							"Please wait, connecting to server.",
							Toast.LENGTH_LONG).show();
					
					
					HttpClient Client = new DefaultHttpClient();
					String URL = "http://192.168.43.157/Cloudcompiler/Logpage.php?UserEmail="+emailval+"&UserPass="+passval+"";
			    	HttpGet httpget = new HttpGet(URL);
			    	ResponseHandler<String> responseHandler = new BasicResponseHandler();
			    	String SetServerString = "";
			    	SetServerString = Client.execute(httpget, responseHandler);
			    	
			    	if(SetServerString.startsWith("Saved")==true)
			    	{
				    	db.onUpgrade(db.getWritableDatabase(), 1, 2);	
						db.createUser(new RegisterUser("", t1.getText().toString(),t2.getText().toString()));

			    		
						Intent intent=new Intent(getApplicationContext(),FileUploadActivity.class);
						//intent.putExtra("Email", ru.getemail().toString());
						//intent.putExtra("Pass", ru.getPass().toString());
						startActivity(intent);
						finish();
			    	}
			    	else
			    	{
						Toast.makeText(getBaseContext(),
								"Login Fail..!",
								Toast.LENGTH_LONG).show();
			    	}
			         
				}
				catch(Exception e){
					
					Toast.makeText(getBaseContext(),
							e.toString(),
							Toast.LENGTH_LONG).show();
				}		
				
			}
		});
		
		Button backButton =(Button) findViewById(R.id.buttonLogBack);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				Intent intent=new Intent(getApplicationContext(),MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
