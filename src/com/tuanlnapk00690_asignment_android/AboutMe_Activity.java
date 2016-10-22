package com.tuanlnapk00690_asignment_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class AboutMe_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_me);
		
		String doc = "https://drive.google.com/file/d/0B5T9OA3s5A52bG8td2pialI1Tmc/view?usp=sharing";
		
		WebView wv = (WebView) findViewById(R.id.webViewAboutme);
		
		wv.getSettings().setJavaScriptEnabled(true); 
		wv.getSettings().setAllowFileAccess(true);
		wv.getSettings().setLoadsImagesAutomatically(true);
		wv.loadUrl(doc);
	}

}
