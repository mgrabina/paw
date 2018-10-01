package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.PropertyDao;
import ar.edu.itba.paw.models.OperationType;
import ar.edu.itba.paw.models.Property;
import ar.edu.itba.paw.models.PropertyType;
import ar.edu.itba.paw.models.User;
import ar.edu.itba.paw.persistence.TestConfig;
import ar.edu.itba.paw.persistence.UserDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class PropertDaoTest {

    private static final String street = "street";
    private static final int floor = 1;
    private static final String apartment = "apartment";
    private static final OperationType optype = OperationType.rent;
    private static final PropertyType type = PropertyType.apartment;
    private static final long price = 8;
    private static final int covered_area= 10;
    private static final int totalArea = 1;
    private static final int rooms = 1;
    private static final int bath = 1;
    private static final boolean garage = false;
    private static final int tax_price = 1;


    private static final String PASSWORD = "Password";
    private static final String USERNAME = "Username";
    private static final String MAIL = "mail@mail.com";
    private static final String PHONE = "11-1111-1111";
    private static final String IMAGE = "IMAGE";

    @Autowired
    private DataSource ds;

    long uId;

    @Autowired
    private PropertyDaoImpl propertyDao;

    @Autowired
    private UserDaoImpl userDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("truncate table property");
        uId=userDao.createUser(USERNAME,PASSWORD,MAIL,PHONE,IMAGE);
    }

    @Test
    public void testCreate() {
        final Long id = propertyDao.createProperty(street,floor,apartment,type,uId,price,covered_area,totalArea,rooms,bath,garage,tax_price);
        assertNotNull(id);
        assertTrue(id>=0);
    }

    @Test
    public void testFindById() {
        final Long id = propertyDao.createProperty(street,floor,apartment,type,uId,price,covered_area,totalArea,rooms,bath,garage,tax_price);
        Property p = propertyDao.findById(id);
        assertNotNull(p);
        assertEquals(id,p.getId());
    }

    @Test
    public void testFindByIdUserNotPresent() {
        Property p = propertyDao.findById(1);
        assertNull(p);
    }
}
