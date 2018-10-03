package ar.edu.itba.paw.webapp.forms;

import ar.edu.itba.paw.webapp.forms.customValidators.Image;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.imageio.ImageIO;
import javax.validation.Valid;


public class RegisterForm {

    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String username;

    @Size(min = 5, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String surname;

    @Size(min = 5, max = 80)
    private String mail;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String password;

    @Size(min = 1, max = 25)
    private String phone;

    @Image
    private MultipartFile image;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }


}
