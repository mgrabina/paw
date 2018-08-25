package ar.edu.itba.paw.models;

import java.util.ArrayList;
import java.util.List;

public class User {

	private long uId;
	private String name;
	private String mail;
	private String password;


	private User(long uid, String username, String usermail, String password) {

		this.uId = uid;
		this.name = username;
		this.mail = usermail;
		this.password = password;
	}

	public static User create(long uid, String username, String usermail, String password) {
		return new User(uid, username, usermail, password);
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	public String getUsername() {
		return name;
	}

	public void setUsername(String username) {
		this.name = username;
	}

	public String getUsermail() {
		return mail;
	}

	public void setUsermail(String usermail) {
		this.mail = usermail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [uId=" + uId + ", name=" + name + ", mail=" + mail + ", password=" + password + ", profileImgs="
				+ "]";
	}

	public Boolean isValid() {
		return this.uId > 0;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

}
