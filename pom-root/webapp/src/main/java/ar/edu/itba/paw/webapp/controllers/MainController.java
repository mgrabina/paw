package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.models.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

import java.util.List;

@Controller
public class MainController {
		
	@Autowired
	private UserService us;
	
	@Autowired
	private PropertyService ps;

	@RequestMapping("/")
	public ModelAndView index() {
		
		final ModelAndView mav = new ModelAndView("index");
		
		//Aca vamos a usar un getWithFilters()
		final List<Property> propertiesList = ps.getAll();
		final int propertiesCount = propertiesList.size();
		final int pagesCount = ps.getPageCount(propertiesList);
		
		mav.addObject("propertiesList", ps.getPage(propertiesList));
		mav.addObject("propertiesCount", propertiesCount);
		mav.addObject("pagesCount", pagesCount);
		
		//Add applied filters to ease client exp
//		final User user = us.GetUser();
//		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping("/not-found")
	public ModelAndView error() {
		final ModelAndView mav = new ModelAndView("404");
		return mav;
	}

}