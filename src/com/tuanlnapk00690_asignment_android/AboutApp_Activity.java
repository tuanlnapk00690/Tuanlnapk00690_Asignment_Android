package com.tuanlnapk00690_asignment_android;
import android.app.Activity;
import android.app.TabActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutApp_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_app);
		
		String doc = "https://drive.google.com/file/d/0B5T9OA3s5A52ZGE2NFgzYnpkNlk/view?usp=sharing";
		
		WebView wv = (WebView) findViewById(R.id.webViewAboutapp);
		
		wv.getSettings().setJavaScriptEnabled(true); 
		wv.getSettings().setAllowFileAccess(true);
		wv.getSettings().setLoadsImagesAutomatically(true);
		wv.loadUrl(doc);
	}

}
