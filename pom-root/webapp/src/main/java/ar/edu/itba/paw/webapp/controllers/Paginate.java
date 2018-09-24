package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

public class Paginate {


    @Autowired
    private UserService us;

    @Autowired
    private PropertyService ps;

    protected ModelAndView basicPaginatedListMAV(String name, List<Property> list, String pageNumberParam ){
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

    protected int formatPageNumber(String pageNumberParam){
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
