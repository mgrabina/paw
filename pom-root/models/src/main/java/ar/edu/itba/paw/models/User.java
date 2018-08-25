package ar.edu.itba.paw.models;

public class User {

	private int id;
	private String name;
	private String surname;
	private String password;
	private String phone;
	private String mail;
	private String imageSrc;

	public User(int id, String name, String surname, String password, String phone, String mail, String imageSrc) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.phone = phone;
		this.mail = mail;
		this.imageSrc = imageSrc;
	}

	public User(String name, String surname, String password, String phone, String mail, String imageSrc) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.phone = phone;
		this.mail = mail;
		this.imageSrc = imageSrc;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPassword() {
		return password;
	}

	public String getPhone() {
		return phone;
	}

	public String getMail() {
		return mail;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
}
