package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.webapp.forms.LoginForm;
import ar.edu.itba.paw.webapp.forms.NewPropertyForm;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService ps;

    @RequestMapping(value = "/property/register", method = RequestMethod.GET)
    public ModelAndView getRegister(@ModelAttribute("newPropertyForm") final NewPropertyForm form) {
        return new ModelAndView("registerproperty");
    }

    @RequestMapping(value = "/property/register", method = RequestMethod.POST)
    public ModelAndView postRegister(@Valid @ModelAttribute("newPropertyForm") final NewPropertyForm form, final BindingResult result) throws IOException {

//        if(result.hasErrors()){
//            return  new ModelAndView("singup");
//        }

//        if(us.userExist(form.getMail())){
//            ModelAndView m =new ModelAndView("register");
//            m.addObject("errorMessage", "Existing user");
//            return m;
//        }


        ps.createProperty(
                form.getStreet(),
                Integer.valueOf(form.getNumber()),
                Integer.valueOf(form.getFloor()),
                form.getApartment(),
                Property.Type.valueOf(form.getType().toString()),
                1, //TODO: REAL USER
                Long.valueOf(form.getPrice()),
                Integer.valueOf(form.getCoveredArea()),
                Integer.valueOf(form.getTotalArea()),
                Integer.valueOf(form.getRooms()),
                Integer.valueOf(form.getBaths()),
                Boolean.valueOf(form.getGarage()),
                Integer.valueOf(form.getTaxPrice())
        );
        return new ModelAndView("index");   //TODO: Redirect to Property Detail View
    }
}