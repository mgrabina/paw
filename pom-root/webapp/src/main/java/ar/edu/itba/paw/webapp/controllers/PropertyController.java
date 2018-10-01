package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.ImageUploaderService;
import ar.edu.itba.paw.interfaces.PropertyService;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.forms.LoginForm;
import ar.edu.itba.paw.webapp.forms.NewPropertyForm;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService ps;
    
    @Autowired
    private UserService us;
    
    @Autowired
    private ImageUploaderService ius;
    

    @RequestMapping(value = "/property/register", method = RequestMethod.GET)
    public ModelAndView getRegister(@ModelAttribute("newPropertyForm") final NewPropertyForm form) {
        
    	final ModelAndView mav = new ModelAndView("register_property");
        mav.addObject("myUser", us.getCurrentUser());

    	
        return mav;
    }

    @RequestMapping(value = "/property/register", method = RequestMethod.POST)
    public ModelAndView postRegister(@Valid @ModelAttribute("newPropertyForm") final NewPropertyForm form, final BindingResult result) throws IOException {

//        if(result.hasErrors()){
//            return  new ModelAndView("singup");
//        }
       	
        final Long pId = ps.createProperty(
                form.getStreet(),
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
        
        for (MultipartFile image : form.getImages()) {
    		BufferedImage bImage = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
        	String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        	long size = image.getSize();
        	
        	String imageSrc = ius.uploadImage(bImage, extension, image.getOriginalFilename(), size);
        	ps.linkImage(imageSrc, pId);
    	}
                
        return new ModelAndView("redirect:/property/"+pId);
    }

    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    public ModelAndView getPropertyDetailview(@PathVariable("id") int id) {
        final ModelAndView mav = new ModelAndView("detailview");
        Optional<Property> property = ps.getPropertyById(id);
        if(property.isPresent()){
            try{
                mav.addObject("property", property.get());
            }catch (Exception e){
                return new ModelAndView("error");
            }
        }else {
            return new ModelAndView("404");
        }

        return mav;
    }

    @RequestMapping(value = "/property/delete", method = RequestMethod.POST)
    public ModelAndView postRegister(@ModelAttribute("id") final String idProperty) throws IOException {

        if (!checkMyProperty(idProperty))
            return new ModelAndView("404");
        long longId;
        try {
            longId = Long.getLong(idProperty);
        } catch (Exception e){
            return new ModelAndView("404");
        }
        if(ps.deleteProperty(longId))
           return new ModelAndView("redirect:/myproperties"); //agregar que vaya al detail
        return new ModelAndView("404");
    }


    @RequestMapping(value = "/property/update", method = RequestMethod.POST)
    public ModelAndView postRegister(@ModelAttribute("id") final String idProperty, @ModelAttribute("desc") final String description,
                                     @ModelAttribute("message") final String message,
                                     @ModelAttribute("price") final String price) throws IOException {
        if (!checkMyProperty(idProperty))
            return new ModelAndView("404");
        long longPrice;
        long longId;
        try {
            longPrice=Long.getLong(price);
            longId = Long.getLong(idProperty);
        } catch (Exception e){
            return new ModelAndView("404");
        }
        if( ps.updateProperty(longId,description,longPrice,message)){
            return new ModelAndView("redirect:/property/"+idProperty);
        }
        return new ModelAndView("404");
    }

    private boolean checkMyProperty(final String idProperty){
        Optional<Property> myP;
        try {
            myP = ps.getPropertyById(Long.getLong(idProperty));
        }catch (Exception e){
            return false;
        }
        if(!myP.isPresent())
            return false;
        User me = us.getCurrentUser();
        if(myP.get().getPublisherUser().getId()!=me.getId())
            return false;
        return true;
    }

}