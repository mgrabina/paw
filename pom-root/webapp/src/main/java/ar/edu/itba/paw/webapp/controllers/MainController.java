package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.webapp.forms.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {
		
	@Autowired
	private UserService us;
	
	@Autowired
	private PropertyService ps;

		
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value = "page", required = false) String pageNumberParam, @RequestParam Map<String, String> queryMap) {
		
		final ModelAndView mav = new ModelAndView("index");
		
		int pageNumber = 1;
		
		if ( pageNumberParam != null) {
			try {
				int auxPageNumber = Integer.parseInt(pageNumberParam);
				pageNumber = auxPageNumber;
				
				if (pageNumber <= 0) return new ModelAndView("redirect:/");
			} catch (NumberFormatException e) {
				return new ModelAndView("redirect:/");
			}
		}
		Map<String,String> map= new HashMap<>();
		map.put("type","apartment");
		map.put("minPrice","99999");
		map.put("garage","true");
		List<Property> aux=ps.getFiltered(map);

		//Usar un getFiltered y pasarle el queryMap
		final List<Property> propertiesList = ps.getAll();
		final int propertiesCount = propertiesList.size();
		final int pagesCount = ps.getPageCount(propertiesList);
		String userId = "1"; //esta hardcodeado, despues ver como meterlo o si no esta logueado no deberia estar esa var
		
		mav.addObject("propertiesList", ps.getPage(propertiesList, pageNumber));
		mav.addObject("propertiesCount", propertiesCount);
		mav.addObject("pagesCount", pagesCount);
		mav.addObject("userId", userId);

//		List<Property> busqueda = ps.getPropertysByTagsSearch("palermo apartment");
//		List<Property> busqueda2 = ps.getPropertysByTagsSearch("almagro");
//		List<Property> busqueda3 = ps.getPropertysByTagsSearch("office boedo tagnodisponible otrotrucho");

		return mav;
	
	}

	@RequestMapping("/not-found")
	public ModelAndView error() {
		final ModelAndView mav = new ModelAndView("404");
		return mav;
	}

}