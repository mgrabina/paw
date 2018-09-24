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

		
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value = "page", required = false) String pageNumberParam, @RequestParam Map<String, String> queryMap) {
		
		final ModelAndView mav = new ModelAndView("index");
		

		int pageNumber = formatPageNumber(pageNumberParam);

		Map<String,String> map= new HashMap<>();
		List<Property> propertiesList=ps.getFiltered(map);

		//Usar un getFiltered y pasarle el queryMap
		final int propertiesCount = propertiesList.size();
		final int pagesCount = ps.getPageCount(propertiesList);

		mav.addObject("propertiesList", ps.getPage(propertiesList, pageNumber));
		mav.addObject("propertiesCount", propertiesCount);
		mav.addObject("pagesCount", pagesCount);
		mav.addObject("myUser", us.getCurrentUser());
		mav.addObject("filters", ps.getPotentialFilters());

//		List<Property> busqueda = ps.getPropertiesByTagsSearch("palermo apartment");
//		List<Property> busqueda2 = ps.getPropertiesByTagsSearch("almagro");
//		List<Property> busqueda3 = ps.getPropertiesByTagsSearch("office boedo tagnodisponible otrotrucho");

		return mav;
	
	}

	@RequestMapping("/myfavourites")
    public ModelAndView myFavourites(@RequestParam(value = "page", required = false) String pageNumberParam) {
        return basicPaginatedListMAV("property_list", ps.getFavourites(us.getCurrentUser().getId()), pageNumberParam);
    }

    @RequestMapping("/myproperties")
    public ModelAndView myProperties(@RequestParam(value = "page", required = false) String pageNumberParam) {
        return basicPaginatedListMAV("property_list", ps.getAllByUserId(us.getCurrentUser().getId()), pageNumberParam);
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(value = "page", required = false) String pageNumberParam, @RequestParam(value = "query", required = false) String query) {
        return basicPaginatedListMAV("property_list", ps.getPropertiesByTagsSearch(query), pageNumberParam);
    }

    private ModelAndView basicPaginatedListMAV(String name, List<Property> list, String pageNumberParam ){
        final ModelAndView mav = new ModelAndView(name);
        final User user = us.getCurrentUser();
        List<Property> propertiesList;
        if(list != null)
            propertiesList = list;
        else
            propertiesList = new LinkedList<Property>();
        final int propertiesCount = propertiesList.size();
        final int pagesCount = ps.getPageCount(propertiesList);
        mav.addObject("propertiesList", ps.getPage(propertiesList, formatPageNumber(pageNumberParam)));
        mav.addObject("propertiesCount", propertiesCount);
        mav.addObject("pagesCount", pagesCount);
        mav.addObject("userId", user.getId());
        return mav;
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
    private int formatPageNumber(String pageNumberParam){
	    int pageNumber = 1;
        if ( pageNumberParam != null) {
            try {
                int auxPageNumber = Integer.parseInt(pageNumberParam);
                if (auxPageNumber > 0)
                    pageNumber = auxPageNumber;

            }catch (Exception e){
                // Couldnt parse param, using default.
            }
        }
        return pageNumber;
    }
}