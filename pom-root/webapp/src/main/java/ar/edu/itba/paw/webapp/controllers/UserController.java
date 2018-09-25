package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.EmailService;
import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import ar.edu.itba.paw.webapp.forms.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;
import sun.misc.Resource;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;


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
                                    @ModelAttribute("propertyId") final String propertyId) throws IOException {
        try{
            String destMail = ps.getPropertyById(Integer.parseInt(propertyId)).get().getPublisherUser().getMail();
            es.sendMessage(destMail, DEFAULT_CONTACT_SUBJECT, message);
            ModelAndView mav = new ModelAndView("alert");
            mav.addObject("title", "Message Sent"); //TODO: Language support
            return mav;
        }catch (Exception e){
            // Couldnt send mail
            return new ModelAndView("error");
        }
    }
        @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ModelAndView postRegister(@Valid @ModelAttribute("registerForm") final RegisterForm form, final BindingResult result) throws IOException {

        if(result.hasErrors()){
            return getRegister(form);
        }

//        if(us.userExist(form.getMail())){
//            ModelAndView m =new ModelAndView("register");
//            m.addObject("errorMessage", "Existing user");
//            return m;
//        }

        us.createUser(form.getUsername(), pw.encode(form.getPassword()), form.getMail(), form.getPhone(), "implementImage");

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