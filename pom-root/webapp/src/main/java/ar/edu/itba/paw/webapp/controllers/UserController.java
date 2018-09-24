package ar.edu.itba.paw.webapp.controllers;

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

import java.io.IOException;
import java.util.List;


import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService us;
    @Autowired
    private PasswordEncoder pw;
    @Autowired
    private PropertyService ps;

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

    @RequestMapping(value = "/user/addFavourite", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public void postFav(@RequestParam("propertyId") long propertyId) {

        User myUser = us.getCurrentUser();
        if(myUser==null)
            return;
        ps.setFavourite(myUser.getId(),propertyId);

    }

        @RequestMapping(value = "/user/{id}/myproperties", method = RequestMethod.GET)
    public ModelAndView getPropertyDetailview(@PathVariable("id")final long id) {
        final ModelAndView mav = new ModelAndView("viewMyProperties");

        final List<Property> propertiesList = ps.getAllByUserId(id);
        /*final int propertiesCount = propertiesList.size();
        final int pagesCount = ps.getPageCount(propertiesList);*/

        mav.addObject("propertiesList", propertiesList);
        /*mav.addObject("propertiesCount", propertiesCount);
        mav.addObject("pagesCount", pagesCount);*/

        return mav;
    }

    @RequestMapping(value = "/user/{id}/myfavourites", method = RequestMethod.GET)
    public ModelAndView getFavourites(@PathVariable("id")final long id) {
        final ModelAndView mav = new ModelAndView("viewMyFavourites");

        final List<Property> propertiesList = ps.getFavourites(id);
        /*final int propertiesCount = propertiesList.size();
        final int pagesCount = ps.getPageCount(propertiesList);*/

        mav.addObject("propertiesList", propertiesList);
        /*mav.addObject("propertiesCount", propertiesCount);
        mav.addObject("pagesCount", pagesCount);*/

        return mav;
    }
}