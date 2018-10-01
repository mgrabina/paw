package ar.edu.itba.paw.webapp.controllers;

import ar.edu.itba.paw.interfaces.PropertyService;
import ar.edu.itba.paw.interfaces.UserService;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Paginate {

    private static final int PAGE_SIZE = 10;
    private static final int FIRST_PAGE = 1;

    protected static ModelAndView basicPaginatedListMAV(String name, List<Property> list, String pageNumberParam,User user){
        final ModelAndView mav = new ModelAndView(name);
        List<Property> propertiesList;
        if(list != null)
            propertiesList = list;
        else
            propertiesList = new LinkedList<Property>();
        final int propertiesCount = propertiesList.size();
        final int pagesCount = getPageCount(propertiesList);
        mav.addObject("propertiesList", getPage(propertiesList, formatPageNumber(pageNumberParam)));
        mav.addObject("propertiesCount", propertiesCount);
        mav.addObject("pagesCount", pagesCount);
        mav.addObject("myUser", user);
        if(user!=null)
            mav.addObject("userId", user.getId());
        return mav;
    }

    protected static int formatPageNumber(String pageNumberParam){
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

    public static List<Property> getPage(List<Property> propertiesList, int pageNumber) throws IllegalArgumentException{

        if(pageNumber <= 0) {
            throw new IllegalArgumentException("Invalid page number: " + pageNumber);
        }

        int from = (pageNumber - 1) * PAGE_SIZE;

        if(propertiesList == null || propertiesList.size() < from){
            return Collections.emptyList();
        }

        // toIndex exclusive
        return propertiesList.subList(from, Math.min(from + PAGE_SIZE, propertiesList.size()));

    }

    public static List<Property> getPage(final List<Property> propertiesList){
        return getPage(propertiesList, FIRST_PAGE);
    }


    public static int getPageCount(List<Property> propertiesList) {

        if(propertiesList == null)
            return 0;
        
        return (int) Math.ceil(propertiesList.size() / (float) PAGE_SIZE);
    }
}
