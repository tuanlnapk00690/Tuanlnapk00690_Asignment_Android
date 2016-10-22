package com.model;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;

public class Connect_DB extends SQLiteOpenHelper{

	private static final int  Data_Version=1;
    private static final String Data_Name="QuanLyLop";
    private static final String Table_Class="ClassPoly";
    private static final String Class_ID ="ID";
    private static final String Class_MaLop ="MaLop";
    private static final String Class_TenLop ="TenLop";
    
    private static final String Table_Student="QuanLySV";
    private static final String Student_ID_SV="ID_SV";
    private static final String Student_MaSV="MaSV";
    private static final String Student_TenSV="TenSV";
    private static final String Student_NganhHoc="NganhHoc";
    private static final String Student_GioiTinh="GioiTinh";
    private static final String Student_ID_Lop="ID_Lop";
    private static final String Student_TenLop="TenLop";
    private static final String Student_NgaySinh="NgaySinh";
    
    private static final String Table_User="QuanLyUser";
    private static final String User_ID="ID_User";
    private static final String User_Name="User_Name";
    private static final String User_Birth="User_Birth";
    private static final String User_SDT="User_SDT";
    private static final String User="User";
    private static final String Password="Password";
    private static final String RePass="RePass";
    
    public Connect_DB(Context context) {
        super(context, Data_Name, null, Data_Version);
    }
	
	@Override
	public void onCreate(SQLiteDatabase arg0) {
		String Create_Table_Class = "CREATE TABLE " + Table_Class + "("
                + Class_ID + " INTEGER PRIMARY KEY ," + Class_MaLop + " TEXT,"
                + Class_TenLop + " TEXT" + ")";
        arg0.execSQL(Create_Table_Class);	
       
        String Create_Table_Student = "CREATE TABLE "+ Table_Student +"(" + Student_ID_SV
        		+" INTEGER PRIMARY KEY , "+ Student_MaSV +" TEXT, "+ Student_TenSV +" TEXT, "
        		+ Student_NganhHoc +" TEXT, "+ Student_TenLop +" TEXT, "
        		+ Student_ID_Lop +" INTEGER, "+ Student_NgaySinh +" TEXT, "+ Student_GioiTinh +" TEXT )";
        arg0.execSQL(Create_Table_Student);
        
        String Create_Table_User = "CREATE TABLE "+ Table_User +"(" + User_ID
        		+" INTEGER PRIMARY KEY , "+ User_Name +" TEXT, "+ User_Birth +" TEXT, "
        		+ User_SDT +" TEXT, "+ User +" TEXT, "
        		+ Password +" INTEGER, "+ RePass +" TEXT )";
        arg0.execSQL(Create_Table_User);
	}
	
	public ArrayList<UserDB> GetAllUserDB(){
		ArrayList<UserDB> dsUser = new ArrayList<UserDB>();
		String cautruyvan = "SELECT * FROM " + Table_User;
		SQLiteDatabase userdb = this.getReadableDatabase();
		
		Cursor cursor = userdb.rawQuery(cautruyvan, null);
		if(cursor.moveToFirst()){
			do{
				UserDB user = new UserDB();
				user.setID_User(cursor.getInt(cursor.getColumnIndex(User_ID)));
				user.setUser_Name(cursor.getString(cursor.getColumnIndex(User_Name)));
				user.setUser_Birth(cursor.getString(cursor.getColumnIndex(User_Birth)));
				user.setUser_SDT(cursor.getString(cursor.getColumnIndex(User_SDT)));
				user.setUser(cursor.getString(cursor.getColumnIndex(User)));
				user.setPassword(cursor.getString(cursor.getColumnIndex(Password)));
				user.setRePass(cursor.getString(cursor.getColumnIndex(RePass)));
				dsUser.add(user);
			}while(cursor.moveToNext());
		}
		return dsUser;
	}
	
	public Boolean GetUserDB(String user){
		UserDB dsUser = new UserDB();
		String cautruyvan = "SELECT * FROM " + Table_User+" WHERE "+User+" = '"+user+"'";
		SQLiteDatabase userdb = this.getReadableDatabase();
		Cursor cursor = userdb.rawQuery(cautruyvan, null);
		if(cursor.moveToFirst()){
			do{
				return true;
			}while(cursor.moveToNext());
		}
		return false;
	}
	
	public Boolean GetUserDB(String user,String pass){
		UserDB dsUser = new UserDB();
		String cautruyvan = "SELECT * FROM " + Table_User+" WHERE "+User+" = '"+user+"' AND "+Password+" = '"+pass+"'";
		SQLiteDatabase userdb = this.getReadableDatabase();
		Cursor cursor = userdb.rawQuery(cautruyvan, null);
		if(cursor.moveToFirst()){
			do{
				return true;
			}while(cursor.moveToNext());
		}
		return false;
	}
	
	public void addUser(UserDB userdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues conValues = new ContentValues();
		conValues.put(User_Name, userdb.getUser_Name());
		conValues.put(User_Birth, userdb.getUser_Birth());
		conValues.put(User_SDT, userdb.getUser_SDT());
		conValues.put(User, userdb.getUser());
		conValues.put(Password, userdb.getPassword());
		conValues.put(RePass, userdb.getRePass());
		sqLiteDatabase.insert(Table_User, null, conValues);
		sqLiteDatabase.close();
	}
	
	public void deleteUser(UserDB userdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		sqLiteDatabase.delete(Table_User, User_ID + " = ? ",new String []{String.valueOf(userdb.getID_User())});
		sqLiteDatabase.close();
	}
	
	public void updateUser(UserDB userdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(User_Name,userdb.getUser_Name());
		values.put(User_Birth,userdb.getUser_Birth());
		values.put(User_SDT,userdb.getUser_SDT());
		values.put(User,userdb.getUser());
		values.put(Password,userdb.getPassword());
		values.put(RePass,userdb.getRePass());
		String cautruyvan= User_ID +" = ? ";
		sqLiteDatabase.update(Table_User,values, cautruyvan, new String []{String.valueOf(userdb.getID_User())});
		sqLiteDatabase.close();
	}
	
	public void addClass(ClassDB classdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Class_MaLop,classdb.getMaLop());
		values.put(Class_TenLop,classdb.getTenLop());
		sqLiteDatabase.insert(Table_Class, null, values);
		sqLiteDatabase.close();
	}
	public void updateClass(ClassDB classdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Class_MaLop,classdb.getMaLop());
		values.put(Class_TenLop,classdb.getTenLop());
		String cautruyvan= Class_ID +" = ? ";
		sqLiteDatabase.update(Table_Class,values, cautruyvan, new String []{String.valueOf(classdb.getID())});
		sqLiteDatabase.close();
	}
	public void deleteClass(ClassDB classdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		sqLiteDatabase.delete(Table_Class, Class_ID + " = ? ",new String []{String.valueOf(classdb.getID())});
		sqLiteDatabase.close();
	}
	
	public ArrayList<ClassDB> GetAllClassDB(){
		
		ArrayList<ClassDB> dsLop = new ArrayList<ClassDB>();
		String cautruyvan = "SELECT * FROM "+Table_Class;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(cautruyvan, null);
		
		if(cursor.moveToFirst()){
			do{
				ClassDB lop = new ClassDB();
				lop.setID(cursor.getInt(0));
				lop.setMaLop(cursor.getString(1));
				lop.setTenLop(cursor.getString(2)); 
				dsLop.add(lop);
			}while(cursor.moveToNext());
		}
		
		return dsLop;	
	}
	
	public ClassDB GetClassDB(int id){	
		ClassDB dsLop = new ClassDB();
		String cautruyvan = "SELECT * FROM "+Table_Class+" WHERE ID = "+id;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(cautruyvan, null);
		if(cursor.moveToFirst()){
			do{
				dsLop.setID(cursor.getInt(0));
				dsLop.setMaLop(cursor.getString(1));
				dsLop.setTenLop(cursor.getString(2)); 
			}while(cursor.moveToNext());
		}
		return dsLop;	
	}
	
	public StudentDB GetStudentDB(int id){
		StudentDB dsSinhVien = new StudentDB();
		String cautruyvan = "SELECT * FROM " + Table_Student+" WHERE "+Student_ID_SV+" = "+String.valueOf(id);
		SQLiteDatabase svdb = this.getReadableDatabase();
		Cursor cursor = svdb.rawQuery(cautruyvan, null);
		if(cursor.moveToFirst()){
			do{
				dsSinhVien.setID_SV(cursor.getInt(cursor.getColumnIndex(Student_ID_SV)));
				dsSinhVien.setMaSV(cursor.getString(cursor.getColumnIndex(Student_MaSV)));
				dsSinhVien.setTenSV(cursor.getString(cursor.getColumnIndex(Student_TenSV)));
				dsSinhVien.setTenLop(cursor.getString(cursor.getColumnIndex(Student_TenLop)));
				dsSinhVien.setNganhHoc(cursor.getString(cursor.getColumnIndex(Student_NganhHoc)));
				dsSinhVien.setNgaySinhString(cursor.getString(cursor.getColumnIndex(Student_NgaySinh)));
				dsSinhVien.setGioiTinh(cursor.getString(cursor.getColumnIndex(Student_GioiTinh)));
			}while(cursor.moveToNext());
		}
		return dsSinhVien;
	}
	
	public ArrayList<StudentDB> GetAllStudentDB(){
		ArrayList<StudentDB> dsSinhVien = new ArrayList<StudentDB>();
		String cautruyvan = "SELECT * FROM " + Table_Student;
		SQLiteDatabase svdb = this.getReadableDatabase();
		
		Cursor cursor = svdb.rawQuery(cautruyvan, null);
		if(cursor.moveToFirst()){
			do{
				StudentDB sv = new StudentDB();
				sv.setID_SV(cursor.getInt(cursor.getColumnIndex(Student_ID_SV)));
				sv.setID_Lop(cursor.getInt(cursor.getColumnIndex(Student_ID_Lop)));
				sv.setMaSV(cursor.getString(cursor.getColumnIndex(Student_MaSV)));
				sv.setTenSV(cursor.getString(cursor.getColumnIndex(Student_TenSV)));
				sv.setTenLop(cursor.getString(cursor.getColumnIndex(Student_TenLop)));
				sv.setNganhHoc(cursor.getString(cursor.getColumnIndex(Student_NganhHoc)));
				sv.setNgaySinhString(cursor.getString(cursor.getColumnIndex(Student_NgaySinh)));
				sv.setGioiTinh(cursor.getString(cursor.getColumnIndex(Student_GioiTinh)));
				dsSinhVien.add(sv);
			}while(cursor.moveToNext());
		}
		return dsSinhVien;
	}
	
	public void addStudent(StudentDB studentdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues conValues = new ContentValues();
		conValues.put(Student_MaSV,studentdb.getMaSV());
		conValues.put(Student_TenSV,studentdb.getTenSV());
		conValues.put(Student_NganhHoc,studentdb.getNganhHoc());
		conValues.put(Student_TenLop,studentdb.getTenLop());
		conValues.put(Student_ID_Lop,studentdb.getID_Lop());
		conValues.put(Student_NgaySinh,studentdb.getNgaySinh());
		conValues.put(Student_GioiTinh,studentdb.getGioiTinh());
		sqLiteDatabase.insert(Table_Student, null, conValues);
		sqLiteDatabase.close();
	}
	public void updateStudent(StudentDB studentdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		ContentValues conValues = new ContentValues();
		conValues.put(Student_MaSV,studentdb.getMaSV());
		conValues.put(Student_TenSV,studentdb.getTenSV());
		conValues.put(Student_NganhHoc,studentdb.getNganhHoc());
		conValues.put(Student_TenLop,studentdb.getTenLop());
		conValues.put(Student_ID_Lop,studentdb.getID_Lop());
		conValues.put(Student_NgaySinh,studentdb.getNgaySinh());
		conValues.put(Student_GioiTinh,studentdb.getGioiTinh());
		String cautruyvan= Student_ID_SV +" = ? ";
		sqLiteDatabase.update(Table_Student,conValues, cautruyvan, new String []{String.valueOf(studentdb.getID_SV())});
		sqLiteDatabase.close();
	}
	public void deleteStudent(StudentDB studentdb){
		SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
		sqLiteDatabase.delete(Table_Student, Student_ID_SV + " = ?",new String []{String.valueOf(studentdb.getID_SV())});
		sqLiteDatabase.close();
	}
	
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	
		db.execSQL("DROP IF EXISTS "+ Table_Class);
		db.execSQL("DROP IF EXISTS "+ Table_Student);
		db.execSQL("DROP IF EXISTS "+ Table_User);
	}
	
	
}
