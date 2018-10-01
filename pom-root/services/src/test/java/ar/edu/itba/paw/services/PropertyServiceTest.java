package ar.edu.itba.paw.services;

import ar.edu.itba.paw.interfaces.PropertyDao;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.security.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PropertyServiceTest {

    @Mock
    private PropertyDao propertyDao;

    @InjectMocks
    private PropertyServiceImpl service=new PropertyServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    private static final String street = "street";
    private static final int floor = 1;
    private static final String apartment = "apartment";
    private static final String neighborhood= "neighborhood";
    private static final OperationType optype = OperationType.rent;
    private static final PropertyType type = PropertyType.apartment;
    private static final long price = 8;
    private static final int covered_area= 10;
    private static final int totalArea = 1;
    private static final int rooms = 1;
    private static final int bath = 1;
    private static final boolean garage = false;
    private static final int tax_price = 1;
    private static final String ad_message= "ad_message";
    private static final String ad_description= "ad_description";
    private static final boolean inmediate_delivery = false;


    @Test
    public void testGetFilteredWithCorrectFilters() {
        //only test that correct filters return a list with a property

        when(propertyDao.getFiltered(any(String.class), any(ArrayList.class), any(String.class))).thenAnswer(new Answer<List<Property>>() {

            @Override
            public List<Property> answer(InvocationOnMock invocation) throws Throwable {
                List<Property> p = new LinkedList<>();
                User u = User.CreateUser((long) 1, "juan", "asdasd", "132213", "test@test", "image");
                p.add(new Property((long) 1,street,floor,apartment,type,optype,u,price,covered_area,totalArea,rooms,bath,garage,tax_price));
                return p;
            }
        });
        Map<String, String> filter = new HashMap<>();
        filter.put("type","apartment");
        filter.put("maxArea","10");
        filter.put("minPrice","10");
        filter.put("garage","true");

        List<Property> p=service.getFiltered(filter);
        assertFalse(p.isEmpty());
    }

    @Test
    public void testGetFilteredWithIncorrectFilters() {
        Map<String, String> filter = new HashMap<>();
        filter.put("ssss","sadas");
        List<Property> p=service.getFiltered(filter);
        assertTrue(p.isEmpty());
    }


}