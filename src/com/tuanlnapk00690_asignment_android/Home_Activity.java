package com.tuanlnapk00690_asignment_android;

import java.util.ArrayList;

import com.model.ClassDB;
import com.model.Connect_DB;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home_Activity extends Activity {

	ImageButton btnThemLop, btnXemDS, btnQLSV, btnLienhe, btnShare, btnInfo;
	Connect_DB db;
	final ClassDB lop=new ClassDB();
	ListView listViewClass;
	ArrayList<ClassDB> danhsachlop=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		//nút back actionbar
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
		btnXemDS = (ImageButton)findViewById(R.id.imgbtnXemDS);
		btnQLSV = (ImageButton)findViewById(R.id.imgbtnQLSV);
		btnThemLop = (ImageButton)findViewById(R.id.imgbtnThemLop);
		btnLienhe = (ImageButton) findViewById(R.id.imgbtnConnect);
		btnShare = (ImageButton) findViewById(R.id.imgbtnShare);
		btnInfo = (ImageButton) findViewById(R.id.imgbtnInfo);
		
		btnThemLop.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				final Dialog dialog = new Dialog(Home_Activity.this);
				dialog.setContentView(R.layout.dialog_themlop);
				dialog.setTitle("Thêm Lớp");
				
				final EditText txtMaLop = (EditText) dialog.findViewById(R.id.txtMaLop);
				final EditText txtTenLop = (EditText) dialog.findViewById(R.id.txtTenLop);
				final TextView thongbao = (TextView)dialog.findViewById(R.id.txtAlert);
				Button btnAddClass = (Button) dialog.findViewById(R.id.btnAddClass);
				Button btnThoat = (Button) dialog.findViewById(R.id.btnThoat);
				
				btnAddClass.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {	
						
						if(txtMaLop.getText().toString().equals("")){
							thongbao.setText("Bạn chưa nhập mã lớp");
							txtMaLop.requestFocus();
						}else if(txtTenLop.getText().toString().equals("")){
							thongbao.setText("Bạn chưa nhập tên lớp");
							txtTenLop.requestFocus();
						}
						else{
							//putExtra
							Intent themLop = new Intent(Home_Activity.this,MainActivity_DanhSachLop.class);
							themLop.putExtra("maLop",txtMaLop.getText().toString());
							themLop.putExtra("tenLop",txtTenLop.getText().toString());
							startActivity(themLop);
							
							Toast.makeText(Home_Activity.this,"Thêm thành công", Toast.LENGTH_LONG).show();
							dialog.dismiss();
						}
					}
				});
				
				btnThoat.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});	
				dialog.show();
			}
		});
		
		
		btnXemDS.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent =  new Intent(Home_Activity.this,MainActivity_DanhSachLop.class);
				startActivity(intent);
			}
		});
		
		btnQLSV.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View arg0) {
				Intent denQLSV = new Intent(Home_Activity.this, MainActivity_QLSV.class);
				startActivity(denQLSV);
			}
		});
		
		btnLienhe.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home_Activity.this,Conntact_Activity.class);
				startActivity(intent);
			}
		});
		btnInfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Home_Activity.this,Info_Activity.class);
				startActivity(intent);
			}
		});
		btnShare.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Share();
			}
		});
	}
	
	public void Share(){
		Dialog share = new Dialog(Home_Activity.this);
		share.setContentView(R.layout.activity_share);
		share.setTitle("Liên kết");
		
		ImageButton facebook = (ImageButton) share.findViewById(R.id.imgbtnFacebook);
		ImageButton google = (ImageButton) share.findViewById(R.id.imgbtnGoogle);
		ImageButton tumblr = (ImageButton) share.findViewById(R.id.imgbtnTumblr);
		ImageButton rss = (ImageButton) share.findViewById(R.id.imgbtnRss);
		ImageButton twitter = (ImageButton) share.findViewById(R.id.imgbtnTwitter);
		ImageButton yahoo = (ImageButton) share.findViewById(R.id.imgbtnYahoo);
		ImageButton friendfeed = (ImageButton) share.findViewById(R.id.imgbtnFriendfeed);
		ImageButton pinterest = (ImageButton) share.findViewById(R.id.imgbtnPinterest);
		
		
		
		share.show();
	}
	
}
