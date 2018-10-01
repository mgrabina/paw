package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.EmailService;
import ar.edu.itba.paw.interfaces.ImageUploaderService;
import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import ar.edu.itba.paw.webapp.forms.LoginForm;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


import javax.imageio.ImageIO;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService us;
    @Autowired
    private PasswordEncoder pw;
    @Autowired
    private PropertyService ps;
    @Autowired
    private EmailService es;

    @Autowired
    private ImageUploaderService ius;

    private final String DEFAULT_CONTACT_SUBJECT = "Contact from Chozapp";  //TODO: Set HTML Content by language


    @RequestMapping(value = "/user/login")
    public ModelAndView getLogin(@ModelAttribute("loginForm") final LoginForm form) {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView getRegister(@ModelAttribute("registerForm") final RegisterForm form) {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public ModelAndView postContact(@ModelAttribute("message") final String message,
                                    @ModelAttribute("propertyId") final String propertyId,
                                    @ModelAttribute("mail") final String dest) throws IOException {
        try{
            String destMail = ps.getPropertyById(Integer.parseInt(propertyId)).get().getPublisherUser().getMail();
            es.sendMessage(destMail, DEFAULT_CONTACT_SUBJECT + " - " + ((dest != null)?dest:""), message);
            ModelAndView mav = new ModelAndView("alert");
            mav.addObject("title", "Message Sent"); //TODO: Language support
            return mav;
        }catch (Exception e){
            // Couldnt send mail
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
//    @Transactional
    public ModelAndView postRegister(@Valid @ModelAttribute("registerForm") final RegisterForm form, final BindingResult result) throws IOException {

        if(result.hasErrors()){
            List<String> errors = new LinkedList<>();
            errors.addAll(result.getFieldErrors().stream().map(objectError -> objectError.getField() + " | " + objectError.getDefaultMessage()).collect(Collectors.toList()));
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("errors", errors);
            return mav;
        }

        if(us.userExist(form.getMail())){
            ModelAndView m =new ModelAndView("register");
            m.addObject("errorMessage", "Existing user");
            return m;
        }

        // Image upload
        MultipartFile image = form.getImage();
        BufferedImage bImage = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        long size = image.getSize();
        String imageSrc = ius.uploadImage(bImage, extension, image.getOriginalFilename(), size);

        Long id = us.createUser(form.getUsername(), pw.encode(form.getPassword()), form.getMail(), form.getPhone(), imageSrc);

        return new ModelAndView("redirect:/");
    }


    @RequestMapping("/myfavourites")
    public ModelAndView myFavourites(@RequestParam(value = "page", required = false) String pageNumberParam) {
        return Paginate.basicPaginatedListMAV("property_list", us.getFavourites(us.getCurrentUser().getId()), pageNumberParam,us.getCurrentUser());
    }

    @RequestMapping("/myproperties")
    public ModelAndView myProperties(@RequestParam(value = "page", required = false) String pageNumberParam) {
        return Paginate.basicPaginatedListMAV("property_list", ps.getAllByUserId(us.getCurrentUser().getId()), pageNumberParam,us.getCurrentUser());
    }

}