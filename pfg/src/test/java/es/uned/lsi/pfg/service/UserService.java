package es.uned.lsi.pfg.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.controller.UsersController;
import es.uned.lsi.pfg.dao.users.UsersDAO;
import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.service.users.UsersServiceImpl;

/**
 * Users Service Test
 * @author Carlos Navalon Urrea
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		UsersController.class,
        UsersServiceImpl.class,
        ServiceMockProvider .class
})
public class UserService {
	
	@Autowired
	UsersController usersController;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	UsersDAO usersDAOMock;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
//		List<User> lstUsers = new ArrayList<User>();
//		lstUsers.add(Mockito.mock(User.class));
//		when(usersDAOMock.findAllUsers()).thenReturn(lstUsers);
//		
//		ModelAndView model = usersController.getUsersList();
//		
//		verify(usersDAOMock).findAllUsers();
//		assertEquals(lstUsers, model.getModel().get("lstUsers"));
	}

}

@Configuration
class ServiceMockProvider  {
	
//	@Bean
//    public UsersDAO initUserDAOMock() {
//        return  Mockito.mock(UsersDAO.class);
//    }
}
