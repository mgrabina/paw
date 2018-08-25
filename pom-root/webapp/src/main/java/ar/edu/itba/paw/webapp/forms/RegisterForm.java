package ar.edu.itba.paw.webapp.forms;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;


public class RegisterForm {

    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String username;

    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String surname;

    private String mail;

    @Size(min = 6, max = 100)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String password;

    @Size(min = 5, max = 100)
    private String phone;

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
