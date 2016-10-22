package com.Adapter;

import java.util.List;

import com.model.UserDB;
import com.tuanlnapk00690_asignment_android.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ConntactAdapter extends ArrayAdapter<UserDB>{

	List<UserDB> ar;
	public ConntactAdapter(Context context, int resource, List<UserDB> objects) {
		super(context, resource, objects);
		ar=objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		LayoutInflater inflater = LayoutInflater.from(getContext());
		view = inflater.inflate(R.layout.contact_item, null);
		
		UserDB item = new UserDB();
		item = ar.get(position);
		TextView ten = (TextView) view.findViewById(R.id.txtName);
		TextView sdt = (TextView) view.findViewById(R.id.txtPhone);
		
		ten.setText(item.getUser_Name());
		sdt.setText(item.getUser_SDT());
		
		return view;
	}
}
