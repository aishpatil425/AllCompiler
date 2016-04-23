package com.example.AllCompiler;

import com.example.myproperty.R;
import com.example.myproperty.R.layout;
import com.example.myproperty.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.Button;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		Button b3 =(Button) findViewById(R.id.buttonexit3);
		b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				finish();
		        System.exit(0);
			}
		});

		Button b1 =(Button) findViewById(R.id.buttonUf1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(getApplicationContext(),FileUploadActivity.class);
				startActivity(intent);
				finish();
			}
		});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
