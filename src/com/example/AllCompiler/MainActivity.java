package com.example.AllCompiler;

import java.net.URLEncoder;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.myproperty.R;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.view.View;

public class MainActivity extends Activity {

	JCGSQLiteRegister db = new JCGSQLiteRegister(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		Button b1 =(Button) findViewById(R.id.buttonreg2);
		Button b2 =(Button) findViewById(R.id.buttoncon1);
		Button b3 =(Button) findViewById(R.id.logbutton1);
		
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(getApplicationContext(),RegActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		
		
		b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				try{	
			
					RegisterUser ru=db.readBook(1);
					String emailval = URLEncoder.encode(ru.getemail().toString(), "UTF-8");
					String passval = URLEncoder.encode(ru.getPass().toString(), "UTF-8");
	
					
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
			    		
						Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
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
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
