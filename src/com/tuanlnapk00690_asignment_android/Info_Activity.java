package com.tuanlnapk00690_asignment_android;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Info_Activity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);
		
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		TabHost tabhost = getTabHost();
		TabSpec tab1 = tabhost.newTabSpec("first");
		TabSpec tab2 = tabhost.newTabSpec("second");
		
		tab1.setIndicator("About me");
		tab1.setContent(new Intent(this,AboutMe_Activity.class));
		
		tab2.setIndicator("About Application");
		tab2.setContent(new Intent(this,AboutApp_Activity.class));
		
		tabhost.addTab(tab1);
		tabhost.addTab(tab2);
	}

}
