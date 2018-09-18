package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.webapp.forms.LoginForm;
import ar.edu.itba.paw.webapp.forms.NewPropertyForm;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService ps;
    @Autowired
    private UserService us;

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
                form.getNumber(),
                form.getFloor(),
                form.getApartment(),
                form.getNeighborhood(),
                form.getOperationType(),
                form.getType(),
                us.getCurrentUser(),
                form.getPrice(),
                form.getCoveredArea(),
                form.getTotalArea(),
                form.getRooms(),
                form.getBaths(),
                Boolean.valueOf(form.getGarage()),
                form.getTaxPrice(),
                form.getAdMessage(),
                form.getAdDescription(),
                form.getInmediateDelivery()
        );
                
        return new ModelAndView("index");   //TODO: Redirect to Property Detail View
    }

    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    public ModelAndView getPropertyDetailview(@PathVariable("id") int id) {
        final ModelAndView mav = new ModelAndView("detailview");

        //final Property property = ps.findById(id);

        mav.addObject("name", "My building");
        mav.addObject("rating", "3,5");
        mav.addObject("description", "Lorem ipsum ...");
        mav.addObject("street", "Street info");
        mav.addObject("number", "Number info");
        mav.addObject("floor", "Floor info");
        mav.addObject("type", "Type info");
        mav.addObject("price", "Price info");
        mav.addObject("area", "Area info");

        /*mav.addObject("name", property.getAdDescription());
        mav.addObject("rating", "3,5");
        mav.addObject("description", property.getAdMessage());
        mav.addObject("street", property.getStreet());
        mav.addObject("number", property.getNumber());
        mav.addObject("floor", property.getFloor());
        mav.addObject("type", property.getType());
        mav.addObject("price", property.getPrice());
        mav.addObject("area", property.getTotalArea());*/

        return mav;
    }
}