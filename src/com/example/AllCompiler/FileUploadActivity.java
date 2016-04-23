package com.example.AllCompiler;

import com.example.myproperty.R;
import com.example.myproperty.R.layout;
import com.example.myproperty.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebView;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.PluginState;
import android.widget.Toast;


public class FileUploadActivity extends Activity {

		JCGSQLiteRegister db = new JCGSQLiteRegister(this);
		
		private WebView wv;

		private ValueCallback<Uri> mUploadMessage;
		private final static int FILECHOOSER_RESULTCODE = 1;

		@Override
		public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);

		    setContentView(R.layout.activity_file_upload);
		    wv = (WebView) findViewById(R.id.webView1);
		    wv = new WebView(this);
		    wv.setWebViewClient(new WebViewClient());


		    wv.getSettings().setJavaScriptEnabled(true);
	        
	        // Other webview options
		    wv.getSettings().setLoadWithOverviewMode(true);
	        
	        //webView.getSettings().setUseWideViewPort(true);
	        
	        //Other webview settings
		    wv.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		    wv.setScrollbarFadingEnabled(false);
		    wv.getSettings().setBuiltInZoomControls(true);
		    wv.getSettings().setPluginState(PluginState.ON);
		    wv.getSettings().setAllowFileAccess(true);
		    wv.getSettings().setSupportZoom(true); 
	        	
		    wv.setWebChromeClient(new WebChromeClient() {


		        // The undocumented magic method override
		        // Eclipse will swear at you if you try to put @Override here


		        // For Android < 3.0
		        public void openFileChooser(ValueCallback uploadMsg) {

		            Log.i("For Android < 3.0", "called");

		            mUploadMessage = uploadMsg;
		            Intent i = new Intent(Intent.ACTION_GET_CONTENT);


		            i.addCategory(Intent.CATEGORY_OPENABLE);

		            i.setType("*/*");
		            FileUploadActivity.this.startActivityForResult(
		                    Intent.createChooser(i, "File Browser"),
		                    FILECHOOSER_RESULTCODE);
		        }

		        // For Android 3.0+
		        public void openFileChooser(ValueCallback uploadMsg,
		                String acceptType) {

		            Log.i("For Android 3.0+", "called");

		            mUploadMessage = uploadMsg;
		            Intent i = new Intent(Intent.ACTION_GET_CONTENT);

		            i.addCategory(Intent.CATEGORY_OPENABLE);

		            i.setType("*/*");
		            FileUploadActivity.this.startActivityForResult(
		                    Intent.createChooser(i, "File Browser"),
		                    FILECHOOSER_RESULTCODE);
		        }

		         public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
		             openFileChooser(uploadMsg);

		             Log.i("For Android Jellybeans", "called");

		            mUploadMessage = uploadMsg;
		            Intent i = new Intent(Intent.ACTION_GET_CONTENT);

		            i.addCategory(Intent.CATEGORY_OPENABLE);

		            i.setType("*/*");
		            FileUploadActivity.this.startActivityForResult(
		                    Intent.createChooser(i, "File Browser"),
		                    FILECHOOSER_RESULTCODE);

		         } 

		    });

		    setContentView(wv);
		    
		    RegisterUser ru=db.readBook(1);
			try {
				String emailval = URLEncoder.encode(ru.getemail().toString(), "UTF-8");
				String passval = URLEncoder.encode(ru.getPass().toString(), "UTF-8");
				wv.loadUrl("http://192.168.43.157/cloudcompiler/upd.php?UserEmail="+emailval+"&UserPass="+passval+"");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		protected void onActivityResult(int requestCode, int resultCode,
		        Intent intent) {

		    Log.i("onActivityResult", "called");

		    if (requestCode == FILECHOOSER_RESULTCODE) {
		        if (null == mUploadMessage){

		            Log.i("if", "return called");
		            return;
		        }else{

		            Uri result = intent == null || resultCode != RESULT_OK ? null
		                    : intent.getData();
		            mUploadMessage.onReceiveValue(result);
		            mUploadMessage = null;
		            Log.i("else", "inner Called");

		        }

		    } else {

		        Log.i("Else", "Called");

		        // super.onActivityResult(requestCode, resultCode, intent);
		        // IPlugin callback = this.activityResultCallback;
		        // if (callback != null) {
		        // callback.onActivityResult(requestCode, resultCode, intent);
		        // }
		    }
		}

		@Override
		public void onBackPressed() {
		    if (wv.canGoBack() == true) {
		        wv.goBack();
		    } else {
		    	FileUploadActivity.this.finish();
		    }
		}

		}