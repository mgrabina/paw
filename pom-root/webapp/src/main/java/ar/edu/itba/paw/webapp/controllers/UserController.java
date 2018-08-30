package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.webapp.forms.RegisterForm;
import ar.edu.itba.paw.webapp.forms.LoginForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;

import java.io.IOException;


import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService us;
    @Autowired
    private PasswordEncoder pw;

    @RequestMapping(value = "/user/login")
    public ModelAndView getLogin(@ModelAttribute("loginForm") final LoginForm form) {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView getRegister(@ModelAttribute("registerForm") final RegisterForm form) {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ModelAndView postRegister(@Valid @ModelAttribute("registerForm") final RegisterForm form, final BindingResult result) throws IOException {

//        if(result.hasErrors()){
//            return  new ModelAndView("singup");
//        }

//        if(us.userExist(form.getMail())){
//            ModelAndView m =new ModelAndView("register");
//            m.addObject("errorMessage", "Existing user");
//            return m;
//        }

        us.createUser(form.getUsername(), pw.encode(form.getPassword()), form.getMail(), form.getPhone(), "implementImage");
        
        return new ModelAndView("login");
    }
}