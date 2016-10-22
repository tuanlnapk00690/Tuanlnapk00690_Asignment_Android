package com.model;

public class UserDB {

	private int ID_User ;
	private String User_Name;
	private String User_Birth;
	private String User_SDT;
	private String User;
	private String Password;
	private String RePass;
	
	public UserDB() {
	}
	
	public UserDB( String user_Name, String user_Birth, String user_SDT, String user, String password,
			String rePass) {
		super();
		User_Name = user_Name;
		User_Birth = user_Birth;
		User_SDT = user_SDT;
		User = user;
		Password = password;
		RePass = rePass;
	}
	
	public UserDB(int iD_User, String user_Name, String user_Birth, String user_SDT, String user, String password,
			String rePass) {
		super();
		ID_User = iD_User;
		User_Name = user_Name;
		User_Birth = user_Birth;
		User_SDT = user_SDT;
		User = user;
		Password = password;
		RePass = rePass;
	}

	public int getID_User() {
		return ID_User;
	}

	public void setID_User(int iD_User) {
		ID_User = iD_User;
	}

	public String getUser_Name() {
		return User_Name;
	}

	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}

	public String getUser_Birth() {
		return User_Birth;
	}

	public void setUser_Birth(String user_Birth) {
		User_Birth = user_Birth;
	}

	public String getUser_SDT() {
		return User_SDT;
	}

	public void setUser_SDT(String user_SDT) {
		User_SDT = user_SDT;
	}

	public String getUser() {
		return User;
	}

	public void setUser(String user) {
		User = user;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRePass() {
		return RePass;
	}

	public void setRePass(String rePass) {
		RePass = rePass;
	}
	
	
}
