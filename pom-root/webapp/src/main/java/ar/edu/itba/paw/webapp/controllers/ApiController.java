package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApiController {

    @Autowired
    private UserService us;

    @Autowired
    private PropertyService ps;

    @RequestMapping(value = "/api/setFavourite", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public void postSetFavourite(@RequestParam("toPropertyId") long toProperty) {
        User myUser = us.getCurrentUser();
        ps.setFavourite(myUser.getId(), toProperty);
    }

    @RequestMapping(value = "/api/deleteFavourite", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public void postDiscardFavourite(@RequestParam("toPropertyId") long toProperty) {
        User myUser = us.getCurrentUser();
        ps.deleteFavourite(myUser.getId(), toProperty);
    }

}