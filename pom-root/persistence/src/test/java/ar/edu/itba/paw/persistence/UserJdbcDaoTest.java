package ar.edu.itba.paw.persistence;

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
public class UserJdbcDaoTest {

    private static final String PASSWORD = "Password";
    private static final String USERNAME = "Username";
    private static final String MAIL = "mail@mail.com";
    private static final String PHONE = "11-1111-1111";
    private static final String IMAGE = "IMAGE";

    @Autowired
    private DataSource ds;

    @Autowired
    private UserDaoImpl userDao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcTemplate.execute("truncate table users ");
    }

    @Test
    public void testCreate() {
        final Long id = userDao.createUser(USERNAME,PASSWORD,MAIL,PHONE,IMAGE);
        assertNotNull(id);
        assertTrue(id>=0);
    }

    @Test
    public void testFindById() {
        final long id = userDao.createUser(USERNAME,PASSWORD,MAIL,PHONE,IMAGE);
        Optional<User> u = userDao.findById(id);
        assertTrue(u.isPresent());
        assertEquals(id,u.get().getId());
    }

    @Test
    public void testFindByIdUserNotPresent() {
        Optional<User> u = userDao.findById(1);
        assertFalse(u.isPresent());
    }

    @Test
    public void testUserExist() {
        userDao.createUser(USERNAME,PASSWORD,MAIL,PHONE,IMAGE);
        assertTrue(userDao.userExist(MAIL));
    }

    @Test
    public void testUserNotExist() {
        assertFalse(userDao.userExist(MAIL));
    }

    @Test
    public void testFindByMail() {
        long id = userDao.createUser(USERNAME,PASSWORD,MAIL,PHONE,IMAGE);
        Optional<User> u = userDao.findByMail(MAIL);
        assertTrue(u.isPresent());
        assertEquals(id,u.get().getId());
    }

    @Test
    public void testFindByMailUserNotPresent() {
        Optional<User> u = userDao.findByMail(MAIL);
        assertFalse(u.isPresent());
    }

    @Test
    public void testGetAll() {
        for(int i=0;i <3; i++){
            userDao.createUser(USERNAME,PASSWORD,MAIL+String.valueOf(i),PHONE,IMAGE);
        }
        assertEquals(3,userDao.getAll().size());
    }
}