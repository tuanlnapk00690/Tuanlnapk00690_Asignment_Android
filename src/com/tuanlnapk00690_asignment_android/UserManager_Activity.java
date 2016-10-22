package com.tuanlnapk00690_asignment_android;

import java.util.ArrayList;

import com.Adapter.ClassAdapter;
import com.Adapter.UserAdapter;
import com.model.Connect_DB;
import com.model.UserDB;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class UserManager_Activity extends Activity {

	ListView listViewUser;
	ArrayList<UserDB> danhsachuser=null;
	UserAdapter useradpter;
	Connect_DB db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_manager);
		
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		db=new Connect_DB(this);
		listViewUser = (ListView) findViewById(R.id.listViewUser);
		danhsachuser=db.GetAllUserDB();
		useradpter = new UserAdapter(getApplicationContext(), R.layout.user_item, danhsachuser); 
		listViewUser.setAdapter(useradpter);
	}
	
	
}
