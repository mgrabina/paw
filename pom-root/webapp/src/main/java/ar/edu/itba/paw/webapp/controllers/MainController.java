package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
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

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.logging.Logger;

@Controller
public class MainController {
		
	@Autowired
	private UserService us;
	
	@Autowired
	private PropertyService ps;
	
	private final String PAGE_QUERY_KEY = "page";


	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value = PAGE_QUERY_KEY, required = false) String pageNumberParam, @RequestParam Map<String, String> queryMap) {
		
		final ModelAndView mav = new ModelAndView("index");
		
		int pageNumber = Paginate.formatPageNumber(pageNumberParam);


		List<Property> propertiesList=ps.getFiltered(ps.getFiltrableFields(queryMap));
		Map<Integer, Map<String, Long>> potFilters = ps.getPotentialFilters(propertiesList);

		final int propertiesCount = propertiesList.size();
		final int pagesCount = Paginate.getPageCount(propertiesList);
		User u = us.getCurrentUser();
		mav.addObject("propertiesList", Paginate.getPage(propertiesList, pageNumber));
		mav.addObject("propertiesCount", propertiesCount);
		mav.addObject("pagesCount", pagesCount);
		mav.addObject("myUser", u );
		mav.addObject("filters", potFilters);
		mav.addObject("timeFilter", ps.getPropertiesDateBreakdown(propertiesList));
		mav.addObject("filterNames", ps.getShowableFilters(queryMap));
		mav.addObject("orderBy", queryMap.get("order_by"));
		mav.addObject("favList", us.getFavouritesMap(us.getCurrentUser()));

		return mav;
	
	}

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(value = PAGE_QUERY_KEY, required = false) String pageNumberParam, @RequestParam(value = "query", required = false) String query) {
        return Paginate.basicPaginatedListMAV("property_list", ps.getPropertiesByTagsSearch(query), pageNumberParam,us.getCurrentUser());
    }

	@RequestMapping("/not-found")
	public ModelAndView notFound() {
		final ModelAndView mav = new ModelAndView("404");
		return mav;
	}

    @RequestMapping(value = "/errors")
    public static ModelAndView renderErrorPage(HttpServletRequest request, Locale locale, Integer error) {

        ModelAndView errorPage = new ModelAndView("errorPage");

        if(request != null) {
            Integer statusCode = (Integer) request
                    .getAttribute("javax.servlet.error.status_code");
            errorPage.addObject("statusCode", statusCode);
        }else{
            errorPage.addObject("statusCode", error.intValue());
        }

        return errorPage;
    }

}