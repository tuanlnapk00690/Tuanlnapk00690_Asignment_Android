package com.tuanlnapk00690_asignment_android;



import java.util.ArrayList;

import com.model.Connect_DB;
import com.model.UserDB;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	BroadcastReceiver receiver = new BroadcastReceiver(){

		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
			if(connectivityManager.getActiveNetworkInfo()!=null){
				btnLogin.setEnabled(true);
				Toast.makeText(getBaseContext(),"wifi True", Toast.LENGTH_LONG).show();
			}
			else{
				btnLogin.setEnabled(false);
				Toast.makeText(getBaseContext(),"wifi False", Toast.LENGTH_LONG).show();
			}
		}
		
	};
	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter filterWifi = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(receiver, filterWifi);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if(receiver!=null)
			unregisterReceiver(receiver);
	}
	
	Button btnLogin;
	TextView textViewDK;
	Connect_DB db;
	ArrayList<UserDB>dsuser = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db=new Connect_DB(this);		
		btnLogin=(Button) findViewById(R.id.btnLogin);
		TextView DK= (TextView) findViewById(R.id.textViewDK);
		TextView QMK = (TextView) findViewById(R.id.textViewQMK);
		final EditText User = (EditText) findViewById(R.id.editTextUser);
		final EditText Pass = (EditText) findViewById(R.id.editTextPass);
		
//		Boolean flag1=db.GetUserDB("quoctuan","123");
		
		
		DK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogDK();
			}
		});
		
		QMK.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogQMK();
			}
		});
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(User.getText().toString().equals("")||Pass.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Bạn chưa nhập tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();
				}
				else if(db.GetUserDB(User.getText().toString()).equals(false) || db.GetUserDB(Pass.getText().toString()).equals(false)){
					Toast.makeText(getApplicationContext(), "Sai mật khẩu hoặc tài khoản", Toast.LENGTH_LONG).show();
				}
//				else if(User.getText().toString().equals("admin") && Pass.getText().toString().equals("admin")){
//					Toast.makeText(getApplicationContext(), "Bạn là quản trị viên", Toast.LENGTH_LONG).show();
//					Intent intent = new Intent(MainActivity.this,Home_Activity.class);
//					startActivity(intent);
//				}
				else if(db.GetUserDB(User.getText().toString())== true && db.GetUserDB(Pass.getText().toString())== true){
					Intent intent = new Intent(MainActivity.this,Home_Activity.class);
					startActivity(intent);
				}
			}
		});
	}
	
	
	Dialog dialogDKTK;
	public void dialogDK(){
		dialogDKTK = new Dialog(MainActivity.this);
		dialogDKTK.setContentView(R.layout.activity_dangky);
		dialogDKTK.setTitle("Đăng Ký Tài Khoản");
		
		final EditText ten = (EditText)dialogDKTK.findViewById(R.id.editTextName);
		final EditText ngaySinh = (EditText)dialogDKTK.findViewById(R.id.editTextBirth);
		final EditText SDT = (EditText)dialogDKTK.findViewById(R.id.editTextSDT);
		final EditText taiKhoan = (EditText)dialogDKTK.findViewById(R.id.editTextUsername);
		final EditText matKhau = (EditText)dialogDKTK.findViewById(R.id.editTextPassword);
		final EditText nhapLaiMK = (EditText)dialogDKTK.findViewById(R.id.editTextRePass);
		final CheckBox agree = (CheckBox)dialogDKTK.findViewById(R.id.checkYes);
		Button dangKy = (Button)dialogDKTK.findViewById(R.id.btnDangKy);
		Button thoat = (Button)dialogDKTK.findViewById(R.id.btnThoatDK);
		final TextView thongbao = (TextView) dialogDKTK.findViewById(R.id.textViewNote);
		final UserDB  user= new UserDB();
//		final Boolean kiemtra = db.GetUserDB(taiKhoan.getText().toString());
		dangKy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ten.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tên");
					ten.requestFocus();
				}else if(ngaySinh.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập ngày sinh");
					ngaySinh.requestFocus();
				}else if(SDT.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập số điện thoại");
					SDT.requestFocus();
				}else if(taiKhoan.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tài khoản");
					taiKhoan.requestFocus();
				}else if(db.GetUserDB(taiKhoan.getText().toString()).equals(true)){
					thongbao.setText("Tài khoản đã tồn tại");
					taiKhoan.requestFocus();
				}else if(matKhau.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập mật khẩu");
					matKhau.requestFocus();
				}else if(nhapLaiMK.getText().toString().equals("")){
					thongbao.setText("Bạn chưa xác nhận lại mật khẩu");
					nhapLaiMK.requestFocus();
				}
				else if(!matKhau.getText().toString().equals(nhapLaiMK.getText().toString())){
					thongbao.setText("Mật khẩu không khớp");
					nhapLaiMK.requestFocus();
				}
				else if(agree.isChecked()){
					//chua So sanh du lieu voi CSDL
					try{
						user.setUser_Name(ten.getText().toString());
						user.setUser_Birth(ngaySinh.getText().toString());
						user.setUser_SDT(SDT.getText().toString());
						user.setUser(taiKhoan.getText().toString());
						user.setPassword(matKhau.getText().toString());
						user.setRePass(nhapLaiMK.getText().toString());
						db.addUser(user);
						dialogDKTK.dismiss();
					}catch(Exception e){
						thongbao.setText("Error:@"+e.getMessage());
					}
					Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
					
				}else{
					thongbao.setText("Bạn chưa đồng ý với điều khoản dịch vụ");
					agree.requestFocus();
				}
				
			}
		});
		
		thoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogDKTK.dismiss();
			}
		});
		
		dialogDKTK.show();
	}
	
	Dialog dialogQMK;
	public void dialogQMK(){
		dialogQMK = new Dialog(MainActivity.this);
		dialogQMK.setContentView(R.layout.activity_quenmk);
		dialogQMK.setTitle("Quên Mật Khẩu");
		
		final EditText SDT = (EditText)dialogQMK.findViewById(R.id.txtSdt);
		final EditText taiKhoan = (EditText)dialogQMK.findViewById(R.id.txtUsername);
		final EditText matKhau = (EditText)dialogQMK.findViewById(R.id.txtPassword);
		final EditText nhapLaiMK = (EditText)dialogQMK.findViewById(R.id.txtRePass);
		Button DMK = (Button)dialogQMK.findViewById(R.id.btnTDMK);
		Button thoat = (Button)dialogQMK.findViewById(R.id.btnThoatQMK);
		final TextView thongbao = (TextView) dialogQMK.findViewById(R.id.textViewNoteQMK);
		
		DMK.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(taiKhoan.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tài khoản");
					taiKhoan.requestFocus();
				}else if(SDT.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập số điện thoại");
					SDT.requestFocus();
				}else if(matKhau.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập mật khẩu");
					matKhau.requestFocus();
				}else if(nhapLaiMK.getText().toString().equals("")){
					thongbao.setText("Bạn chưa xác nhận lại mật khẩu");
					nhapLaiMK.requestFocus();
				}else{
					Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
					dialogQMK.dismiss();
				}
			}
		});
		
		thoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogQMK.dismiss();
			}
		});
		
		dialogQMK.show();
	}

}
