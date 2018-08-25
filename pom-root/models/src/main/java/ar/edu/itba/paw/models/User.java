package ar.edu.itba.paw.models;

import java.util.ArrayList;
import java.util.List;

public class User {

	private long id;
	private String name;
	private String surname;
	private String password;
	private String phone;
	private String mail;
	private String imageSrc;


	public User(long id,String name, String surname, String password, String phone, String mail) {
		this.id=id;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.phone = phone;
		this.mail = mail;
		this.imageSrc = imageSrc;
	}

	public long getId() {
		return id;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [uId=" + id + ", name=" + name + ", mail=" + mail + ", password=" + password;
	}


	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;

	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}