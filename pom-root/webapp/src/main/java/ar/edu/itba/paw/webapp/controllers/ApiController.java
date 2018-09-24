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


    @RequestMapping(value = "/api/deleteFavourite", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public Boolean postDiscardFavourite(@RequestParam("PropertyId") long propertyId) {
        User myUser = us.getCurrentUser();
        if(myUser==null || !ps.propertyExists(propertyId))
            return false;
        us.deleteFavourite(myUser.getId(),propertyId);
        return true;
    }

    @RequestMapping(value = "/api/addFavourite", method = RequestMethod.POST, produces="application/json")
    @ResponseBody
    public Boolean postFav(@RequestParam("propertyId") long propertyId) {

        User myUser = us.getCurrentUser();
        if(myUser==null || !ps.propertyExists(propertyId))
            return false;
        us.setFavourite(myUser.getId(),propertyId);
        return true;
    }

}