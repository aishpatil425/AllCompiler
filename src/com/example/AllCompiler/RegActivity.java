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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegActivity extends Activity {

	JCGSQLiteRegister db = new JCGSQLiteRegister(this);
	EditText t1,t2,t3,t4,t5;
	RadioButton rb1,rb2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		t1 =(EditText) findViewById(R.id.editText1);
		t2 =(EditText) findViewById(R.id.EditText01);
		t3 =(EditText) findViewById(R.id.EditText02);
		t4 =(EditText) findViewById(R.id.editText2);
		t5 =(EditText) findViewById(R.id.editText3);
		
		rb1 =(RadioButton) findViewById(R.id.radioButton1);
		rb2 =(RadioButton) findViewById(R.id.radioButton2);
		
		Button b2 =(Button) findViewById(R.id.buttonreguser1);
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				try{	
					Toast.makeText(getBaseContext(),
							"Please wait, connecting to server.",
							Toast.LENGTH_LONG).show();
					
					String Fulnam = URLEncoder.encode(t1.getText().toString(), "UTF-8");
					String emailval = URLEncoder.encode(t2.getText().toString(), "UTF-8");
					String Mobno = URLEncoder.encode(t3.getText().toString(), "UTF-8");
					String passval = URLEncoder.encode(t4.getText().toString(), "UTF-8");
					String cpassval = URLEncoder.encode(t5.getText().toString(), "UTF-8");
					
					String Gen="M";
					if(rb2.isChecked()==true)
					{
						Gen="F";
					}else{
						Gen="M";
					}
						
					
					HttpClient Client = new DefaultHttpClient();
					String URL = "http://192.168.43.157/Cloudcompiler/Regpage.php?FullNam="+Fulnam+"&Emailid="+emailval+"&MobNo="+Mobno+"&Pass="+passval+"&Cpass="+cpassval+"&Gender="+Gen+"";
			    	HttpGet httpget = new HttpGet(URL);
			    	ResponseHandler<String> responseHandler = new BasicResponseHandler();
			    	String SetServerString = "";
			    	SetServerString = Client.execute(httpget, responseHandler);
			    	
			    	
			    	if(SetServerString.startsWith("Saved")==true)
			    	{
					
			    	db.onUpgrade(db.getWritableDatabase(), 1, 2);	
					db.createUser(new RegisterUser(t1.getText().toString(), t2.getText().toString(),t4.getText().toString()));

					Intent intent=new Intent(getApplicationContext(),OTPActivity.class);
					//intent.putExtra("Email", t2.getText().toString());
					//intent.putExtra("Pass", t4.getText().toString());
					startActivity(intent);
					finish();
			    	}
			    	else
			    	{
						Toast.makeText(getBaseContext(),
								"Login Fail..!"+SetServerString,
								Toast.LENGTH_LONG).show();
			    	}
			         
				}
				catch(Exception e){Toast.makeText(getBaseContext(),
						e.toString(),
						Toast.LENGTH_LONG).show();}		
				
			}
		});
		
		Button backButton =(Button) findViewById(R.id.buttonregback);
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
		getMenuInflater().inflate(R.menu.reg, menu);
		return true;
	}

}
