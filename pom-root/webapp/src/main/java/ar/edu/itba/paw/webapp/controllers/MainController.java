package ar.edu.itba.paw.webapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

@Controller
public class MainController {
	
	@Autowired
	private UserService us;

	@RequestMapping("/")
	public ModelAndView index() {
		final ModelAndView mav = new ModelAndView("index");
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