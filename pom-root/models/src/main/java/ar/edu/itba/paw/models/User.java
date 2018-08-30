package ar.edu.itba.paw.models;

public class User {

	private long id;
	private String name;
	private String password;
	private String phone;
	private String mail;
	private String imageSrc;

	public static User CreateUser(long id, String name, String password, String phone, String mail, String imageSrc) {
		return new User(id, name, password, phone, mail, imageSrc);
	}

	private User(long id, String name, String password, String phone, String mail, String imageSrc) {
		this.id = id;
		this.name = name;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;

	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + ", mail=" + mail
				+ ", imageSrc=" + imageSrc + "]";
	}
	
	
	
}