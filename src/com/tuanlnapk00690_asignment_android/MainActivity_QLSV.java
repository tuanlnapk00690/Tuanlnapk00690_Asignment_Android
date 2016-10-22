package com.tuanlnapk00690_asignment_android;

import java.util.ArrayList;

import com.Adapter.StudentAdapter;
import com.model.Connect_DB;
import com.model.StudentDB;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_QLSV extends Activity {

	ListView listViewStudent;
	ArrayList<StudentDB> danhsachsv = null;
	StudentAdapter studentadapter;
	Connect_DB dbsv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qlsv_activity);
		
		ActionBar actionbar = getActionBar();
		actionbar.setDisplayHomeAsUpEnabled(true);
		
			dbsv=new Connect_DB(this);
			listViewStudent = (ListView) findViewById(R.id.listViewSV);	
//			dbsv.addStudent(new StudentDB("maSV", "tenSV", "nganhHoc", "tenLop", "ngaySinh", 1, "Nam"));
			danhsachsv = dbsv.GetAllStudentDB();
			studentadapter = new StudentAdapter(getApplicationContext(),R.layout.row_student,danhsachsv);
			listViewStudent.setAdapter(studentadapter);
		
			ImageButton btnThemSV = (ImageButton) findViewById(R.id.imgbtnThemSV);
			
			btnThemSV.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					dialogThemSV();
				}
			});
			
//			listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//					dialogXoaSV(danhsachsv.get(arg2).getID_SV());
//				}
//			});
			
			listViewStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
//					dialogSuaSV(danhsachsv.get(arg2).getID_SV());
					
					PopupMenu popup = new PopupMenu(MainActivity_QLSV.this, arg1);
					popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
					popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						@Override
						public boolean onMenuItemClick(MenuItem arg0) {
							if(arg0.getItemId()==R.id.delete){
								dialogXoaSV(danhsachsv.get(arg2).getID_SV());
							}
							else if(arg0.getItemId()==R.id.update){
								dialogSuaSV(danhsachsv.get(arg2).getID_SV());
							}
							return false;
						}
					});
					popup.show();
					return false;
				}
			});
	}
	
	StudentDB sv = new StudentDB(); 
	Dialog dialogSuaSV;
	public void dialogSuaSV(final int id){
		dialogSuaSV = new Dialog(MainActivity_QLSV.this);
		dialogSuaSV.setTitle("Sửa thông tin sinh viên");
		dialogSuaSV.setContentView(R.layout.dialog_suasv);
		sv.setID_SV(id);
		final EditText txtSuaMaSV = (EditText) dialogSuaSV.findViewById(R.id.txtSuaMaSV);
		final EditText txtSuaTenSV = (EditText) dialogSuaSV.findViewById(R.id.txtSuaTenSV);
		final EditText txtSuaNganhHoc = (EditText) dialogSuaSV.findViewById(R.id.txtSuaNganhHoc);
		final EditText txtSuaTenLopSV = (EditText) dialogSuaSV.findViewById(R.id.txtSuaLopHoc);
		final EditText txtSuaNgaySinh = (EditText) dialogSuaSV.findViewById(R.id.txtSuaNgaySinh);
		final EditText txtSuaGioiTinh = (EditText) dialogSuaSV.findViewById(R.id.txtSuaGioiTinh);
		final TextView thongbao = (TextView)dialogSuaSV.findViewById(R.id.txtThongBao);
		
		Button btnOKSuaSV = (Button)dialogSuaSV.findViewById(R.id.btnOKSuaSV);
		Button btnThoatSuaSV = (Button)dialogSuaSV.findViewById(R.id.btnThoatSuaSV);
		
		StudentDB student = dbsv.GetStudentDB(id);
		txtSuaMaSV.setText(student.getMaSV());
		txtSuaTenSV.setText(student.getTenSV());
		txtSuaNganhHoc.setText(student.getNganhHoc());
		txtSuaTenLopSV.setText(student.getTenLop());
		txtSuaNgaySinh.setText(student.getStringNgaySinh());
		txtSuaGioiTinh.setText(student.getGioiTinh());
		
		btnOKSuaSV.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {	
				
				if(txtSuaMaSV.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập mã sinh viên");
					txtSuaMaSV.requestFocus();
				}else if(txtSuaTenSV.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tên sinh viên");
					txtSuaTenSV.requestFocus();
				}else if(txtSuaNganhHoc.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập ngành học sinh viên");
					txtSuaNganhHoc.requestFocus();
				}else if(txtSuaTenLopSV.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tên lớp sinh viên");
					txtSuaTenLopSV.requestFocus();
				}else if(txtSuaNgaySinh.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập ngày sinh sinh viên");
					txtSuaNgaySinh.requestFocus();
				}else if(txtSuaGioiTinh.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập giới tính sinh viên");
					txtSuaGioiTinh.requestFocus();
				}else{
				sv.setMaSV(txtSuaMaSV.getText().toString());
				sv.setTenSV(txtSuaTenSV.getText().toString());
				sv.setNganhHoc(txtSuaNganhHoc.getText().toString());
				sv.setTenLop(txtSuaTenLopSV.getText().toString());
				sv.setNgaySinh(txtSuaNgaySinh.getText().toString());
				sv.setGioiTinh(txtSuaGioiTinh.getText().toString());
				dbsv.updateStudent(sv);
				danhsachsv=dbsv.GetAllStudentDB();
				studentadapter.reloadlistSV(danhsachsv);
				listViewStudent.setAdapter(studentadapter);
				Toast.makeText(MainActivity_QLSV.this,"Sửa Thành Công", Toast.LENGTH_LONG).show();
				dialogSuaSV.dismiss();
				}
			}
		});
		
		btnThoatSuaSV.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dialogSuaSV.dismiss();
			}
		});
		dialogSuaSV.show();
	}
	
	public void dialogXoaSV(final int id){
		AlertDialog.Builder delSV = new AlertDialog.Builder(MainActivity_QLSV.this);
		delSV.setTitle("Thông báo");
		delSV.setMessage("Bạn có muốn xóa không?");
		sv.setID_SV(id);
		delSV.setPositiveButton("Có", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				deleteSV(which);
				dialog.cancel();
			}
		});
		delSV.setNegativeButton("Không", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				dialog.cancel();
			}
		});
		delSV.show();
	}
	
	public void dialogThemSV(){
		dialogSuaSV = new Dialog(MainActivity_QLSV.this);
		dialogSuaSV.setTitle("Thêm sinh viên");
		dialogSuaSV.setContentView(R.layout.dialog_suasv);
		final EditText txtSuaMaSV = (EditText) dialogSuaSV.findViewById(R.id.txtSuaMaSV);
		final EditText txtSuaTenSV = (EditText) dialogSuaSV.findViewById(R.id.txtSuaTenSV);
		final EditText txtSuaNganhHoc = (EditText) dialogSuaSV.findViewById(R.id.txtSuaNganhHoc);
		final EditText txtSuaTenLopSV = (EditText) dialogSuaSV.findViewById(R.id.txtSuaLopHoc);
		final EditText txtSuaNgaySinh = (EditText) dialogSuaSV.findViewById(R.id.txtSuaNgaySinh);
		final EditText txtSuaGioiTinh = (EditText) dialogSuaSV.findViewById(R.id.txtSuaGioiTinh);
		final TextView thongbao = (TextView)dialogSuaSV.findViewById(R.id.txtThongBao);
		
		Button btnOKSuaSV = (Button)dialogSuaSV.findViewById(R.id.btnOKSuaSV);
		Button btnThoatSuaSV = (Button)dialogSuaSV.findViewById(R.id.btnThoatSuaSV);
		
		btnOKSuaSV.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(txtSuaMaSV.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập mã sinh viên");
					txtSuaMaSV.requestFocus();
				}else if(txtSuaTenSV.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tên sinh viên");
					txtSuaTenSV.requestFocus();
				}else if(txtSuaNganhHoc.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập ngành học sinh viên");
					txtSuaNganhHoc.requestFocus();
				}else if(txtSuaTenLopSV.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập tên lớp sinh viên");
					txtSuaTenLopSV.requestFocus();
				}else if(txtSuaNgaySinh.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập ngày sinh sinh viên");
					txtSuaNgaySinh.requestFocus();
				}else if(txtSuaGioiTinh.getText().toString().equals("")){
					thongbao.setText("Bạn chưa nhập giới tính sinh viên");
					txtSuaGioiTinh.requestFocus();
				}else{
					sv.setMaSV(txtSuaMaSV.getText().toString());
					sv.setTenSV(txtSuaTenSV.getText().toString());
					sv.setNganhHoc(txtSuaNganhHoc.getText().toString());
					sv.setTenLop(txtSuaTenLopSV.getText().toString());
					sv.setNgaySinh(txtSuaNgaySinh.getText().toString());
					sv.setGioiTinh(txtSuaGioiTinh.getText().toString());
					dbsv.addStudent(sv);
					danhsachsv=dbsv.GetAllStudentDB();
					studentadapter = new StudentAdapter(getApplicationContext(),R.layout.row_student,danhsachsv);
					listViewStudent.setAdapter(studentadapter);
					Toast.makeText(MainActivity_QLSV.this,"Thêm Thành Công", Toast.LENGTH_LONG).show();
					dialogSuaSV.dismiss();
				}
			}
		});
		
		btnThoatSuaSV.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dialogSuaSV.dismiss();
			}
		});
		dialogSuaSV.show();
	}
	
	public void deleteSV(int id){
		dbsv.deleteStudent(sv);
		danhsachsv=dbsv.GetAllStudentDB();
		studentadapter = new StudentAdapter(getApplicationContext(),R.layout.row_student,danhsachsv);
		listViewStudent.setAdapter(studentadapter);
		Toast.makeText(MainActivity_QLSV.this,"Đã xóa", Toast.LENGTH_LONG).show();
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
			 dialogThemSV();
		 break;

		 }
		return super.onOptionsItemSelected(item);
	} 

}
