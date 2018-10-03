package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.EmailService;
import ar.edu.itba.paw.interfaces.ImageUploaderService;
import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.webapp.config.WebConfig;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import ar.edu.itba.paw.webapp.forms.LoginForm;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.MessageSourceResourceBundle;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;
import sun.misc.Resource;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
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

    private final String DEFAULT_CONTACT_SUBJECT = "Contact from Chozapp";
    private final String DEFAULT_IMAGE_VALIDATION_ERROR = "Invalid Image";
    private final String DEFAULT_EXISTING_USER = "Existing user";
    private final String PAGE_QUERY_KEY = "page";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

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
            return mav;
        }catch (Exception e){
            // Couldnt send mail
            LOGGER.debug("Could not send mail to for id {} with message {}", propertyId, message);
            return new ModelAndView("error");
        }
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
//    @Transactional
    public ModelAndView postRegister(@Valid @ModelAttribute("registerForm") final RegisterForm form, final BindingResult result, HttpServletRequest request) throws IOException {

        if(result.hasErrors()){
            List<String> errors = new LinkedList<>();
            errors.addAll(result.getFieldErrors().stream().map(objectError -> objectError.getField() + " | " + objectError.getDefaultMessage()).collect(Collectors.toList()));
            ModelAndView mav = new ModelAndView("register");
            mav.addObject("errors", errors);
            return mav;
        }

        if(us.userExist(form.getMail())){
            ModelAndView m =new ModelAndView("register");
            List<String> errors = new LinkedList<>();
            errors.add(DEFAULT_EXISTING_USER);
            m.addObject("errors", errors);
            return m;
        }
        String imageSrc;
        try{
            // Image upload
            MultipartFile image = form.getImage();
            BufferedImage bImage = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
            String extension = FilenameUtils.getExtension(image.getOriginalFilename());
            long size = image.getSize();
            imageSrc = ius.uploadImage(bImage, extension, image.getOriginalFilename(), size);
        }catch (Exception e){
            LOGGER.debug("Could not upload photo");
            ModelAndView m =new ModelAndView("register");
            List<String> errors = new LinkedList<>();
            errors.add(DEFAULT_IMAGE_VALIDATION_ERROR);
            m.addObject("errors", errors);
            return m;
        }


        us.createUser(form.getUsername(), pw.encode(form.getPassword()), form.getMail(), form.getPhone(), imageSrc);

        try{
            request.login(form.getMail(), form.getPassword());
        }catch (Exception e){
            LOGGER.debug("Could not login on register." + e.getMessage());
        }
        return new ModelAndView("redirect:/");
    }


    @RequestMapping("/myfavourites")
    public ModelAndView myFavourites(@RequestParam(value = "page", required = false) String pageNumberParam) {
        return Paginate.basicPaginatedListMAV("property_list", us.getFavourites(us.getCurrentUser().getId()), pageNumberParam,us.getCurrentUser(), Paginate.pageType.myFavourites.ordinal());
    }

    @RequestMapping("/myproperties")
    public ModelAndView myProperties(@RequestParam(value = "page", required = false) String pageNumberParam) {
        return Paginate.basicPaginatedListMAV("property_list", ps.getAllByUserId(us.getCurrentUser().getId()), pageNumberParam,us.getCurrentUser(), Paginate.pageType.myProperties.ordinal());
    }
    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(value = PAGE_QUERY_KEY, required = false) String pageNumberParam, @RequestParam(value = "query", required = false) String query) {
        return Paginate.basicPaginatedListMAV("property_list", ps.getPropertiesByTagsSearch(query), pageNumberParam,us.getCurrentUser(), Paginate.pageType.search.ordinal());
    }

}