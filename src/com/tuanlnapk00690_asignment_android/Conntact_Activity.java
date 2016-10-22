package com.tuanlnapk00690_asignment_android;

import java.util.ArrayList;

import com.Adapter.ConntactAdapter;
import com.Adapter.UserAdapter;
import com.model.Connect_DB;
import com.model.UserDB;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class Conntact_Activity extends Activity {

	ListView listViewLienhe;
	ArrayList<UserDB> danhsachuser=null;
	ConntactAdapter useradpter;
	Connect_DB db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conntact_);
		
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		db=new Connect_DB(this);
		listViewLienhe = (ListView) findViewById(R.id.listViewLienhe);
		danhsachuser=db.GetAllUserDB();
		useradpter = new ConntactAdapter(getApplicationContext(), R.layout.contact_item, danhsachuser); 
		listViewLienhe.setAdapter(useradpter);
	}

}
