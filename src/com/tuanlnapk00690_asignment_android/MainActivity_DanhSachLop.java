package com.tuanlnapk00690_asignment_android;

import java.util.ArrayList;

import com.Adapter.ClassAdapter;
import com.model.ClassDB;
import com.model.Connect_DB;
import com.model.UserDB;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_DanhSachLop extends Activity {

	ListView listViewClass;
	ArrayList<ClassDB> danhsachlop=null;
	ClassAdapter classadpter;
	Connect_DB db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.danh_sach_lop_activity);
		
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
	        db=new Connect_DB(this);
			listViewClass = (ListView) findViewById(R.id.listViewClass);
				final ClassDB lop = new ClassDB();
				Bundle bundle = getIntent().getExtras();
				if(bundle!=null){
					String malop = bundle.getString("maLop");
					String tenlop = bundle.getString("tenLop");
					lop.setMaLop(malop);
					lop.setTenLop(tenlop);
					db.addClass(lop);
				}
				danhsachlop=db.GetAllClassDB();
				classadpter = new ClassAdapter(getApplicationContext(), R.layout.item, danhsachlop); 
				listViewClass.setAdapter(classadpter);
				
				ImageButton themlop = (ImageButton) findViewById(R.id.imgbtnAddClass);
				themlop.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						addClass();
					}
				});
				
//				listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//					@Override
//					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//						deleteDialog(danhsachlop.get(arg2).getID());
//					}
//				});
			
			listViewClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
//					alertDialog(danhsachlop.get(arg2).getID());
					
					PopupMenu popup = new PopupMenu(MainActivity_DanhSachLop.this, arg1);
					popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
					popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem arg0) {
							if(arg0.getItemId()==R.id.delete){
								deleteDialog(danhsachlop.get(arg2).getID());
							}
							else if(arg0.getItemId()==R.id.update){
								alertDialog(danhsachlop.get(arg2).getID());
							}
							return false;
						}
					});
					popup.show();
					return false;
				}
			});
	}
	
	public void deleteDialog(int id){
		AlertDialog.Builder del = new AlertDialog.Builder(MainActivity_DanhSachLop.this);
		del.setTitle("Thông báo");
		del.setMessage("Bạn có muốn xóa thật không?");
		lop.setID(id);
		del.setPositiveButton("Có", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				deleteClass(arg1);
				arg0.cancel();
			}
		});
		del.setNegativeButton("Không", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		del.show();
	}
	
	ClassDB lop=new ClassDB();
	Dialog dialogSua;
	public void alertDialog(final int id){
		dialogSua = new Dialog(MainActivity_DanhSachLop.this);
		dialogSua.setContentView(R.layout.dialog_sua);
		dialogSua.setTitle("Sửa lớp");
		lop.setID(id);
		final	EditText txtSuaMaLop = (EditText) dialogSua.findViewById(R.id.txtSuaMaSV);
		final	EditText txtSuaTenLop = (EditText) dialogSua.findViewById(R.id.txtSuaTenSV);
		final TextView thongbao = (TextView)dialogSua.findViewById(R.id.txtMes);
		Button btnOKSua = (Button) dialogSua.findViewById(R.id.btnOKSuaSV);
		Button btnThoatSua = (Button) dialogSua.findViewById(R.id.btnThoatSuaSV);
		
		ClassDB cls=db.GetClassDB(id);
		txtSuaMaLop.setText(cls.getMaLop());
		txtSuaTenLop.setText(cls.getTenLop());
		
		btnOKSua.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(txtSuaMaLop.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập mã lớp");
					txtSuaMaLop.requestFocus();
				}else if(txtSuaMaLop.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tên lớp");
					txtSuaMaLop.requestFocus();
				}
				else{
				lop.setMaLop(txtSuaMaLop.getText().toString());
				lop.setTenLop(txtSuaTenLop.getText().toString());
				db.updateClass(lop);
				danhsachlop=db.GetAllClassDB();
				classadpter.reloadlist(danhsachlop);
				Toast.makeText(MainActivity_DanhSachLop.this, "Sửa Thành Công", Toast.LENGTH_LONG).show();
				dialogSua.dismiss();
				}
			}
		});
		btnThoatSua.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dialogSua.dismiss();
			}
		});
		
		dialogSua.show();
	}
	
	public void deleteClass(int id){
		db.deleteClass(lop);
		danhsachlop=db.GetAllClassDB();
		classadpter = new ClassAdapter(getApplicationContext(), R.layout.item, danhsachlop);
		listViewClass.setAdapter(classadpter);
		Toast.makeText(MainActivity_DanhSachLop.this, "Đã xóa", Toast.LENGTH_LONG).show();
	}
	
	public void addClass(){
		final Dialog dialog = new Dialog(MainActivity_DanhSachLop.this);
		dialog.setContentView(R.layout.dialog_themlop);
		dialog.setTitle("Thêm Lớp");
		
		final EditText txtMaLop = (EditText) dialog.findViewById(R.id.txtMaLop);
		final EditText txtTenLop = (EditText) dialog.findViewById(R.id.txtTenLop);
		final TextView thongbao = (TextView)dialog.findViewById(R.id.txtAlert);
		Button btnAddClass = (Button) dialog.findViewById(R.id.btnAddClass);
		Button btnThoat = (Button) dialog.findViewById(R.id.btnThoat);
		
		btnAddClass.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(txtMaLop.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập mã lớp");
					txtMaLop.requestFocus();
				}else if(txtTenLop.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tên lớp");
					txtTenLop.requestFocus();
				}
				else{
					lop.setMaLop(txtMaLop.getText().toString());
					lop.setTenLop(txtTenLop.getText().toString());
					db.addClass(lop);
					danhsachlop=db.GetAllClassDB();
					classadpter = new ClassAdapter(getApplicationContext(), R.layout.item, danhsachlop); 
					listViewClass.setAdapter(classadpter);
					Toast.makeText(MainActivity_DanhSachLop.this,"Thêm thành công", Toast.LENGTH_LONG).show();
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		 {
		 case R.id.item1:
			 addClass();
		 break;

		 }
		return super.onOptionsItemSelected(item);
	} 
	
}
