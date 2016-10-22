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

public class UserAdapter extends ArrayAdapter<UserDB>{

	List<UserDB> ar;
	public UserAdapter(Context context, int resource, List<UserDB> objects) {
		super(context, resource, objects);
		ar=objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = convertView;
		LayoutInflater inflater = LayoutInflater.from(getContext());
		view = inflater.inflate(R.layout.user_item, null);
		
		UserDB item = new UserDB();
		item = ar.get(position);
		TextView tk = (TextView) view.findViewById(R.id.Taikhoan);
		TextView ten = (TextView) view.findViewById(R.id.nguoidung);
		
		tk.setText(item.getUser());
		ten.setText(item.getUser_Name());
		
		return view;
	}

}
