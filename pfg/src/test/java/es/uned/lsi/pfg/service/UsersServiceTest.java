/**
 * 
 */
package es.uned.lsi.pfg.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import es.uned.lsi.pfg.dao.UsersDAO;
import es.uned.lsi.pfg.model.User;

/**
 * @author Carlos Navalon Urrea
 * Test clase UserService
 */
@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {

	private static final String USER_ID = "UserIdTest";
	private static final String USER_NAME = "UserNameTest";
	private static final String USER_SURNAME_1 = "UserSN1Test";
	private static final String USER_SURNAME_2 = "UserSN2Test";
	
	@InjectMocks
	private UsersServiceImpl usersService;
	
	@Mock
	private UsersDAO usersDAO;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getFullNameWithSecondSurmane() {
		String fullName = USER_NAME + " " + USER_SURNAME_1 + " " + USER_SURNAME_2;
		User user = new User();
		user.setName(USER_NAME);
		user.setSurname1(USER_SURNAME_1);
		user.setSurname2(USER_SURNAME_2);
		
		checkFullName(fullName, user);
	}
	
	@Test
	public void getFullNameWithoutSecondSurmane() {
		String fullName = USER_NAME + " " + USER_SURNAME_1;
		User user = new User();
		user.setName(USER_NAME);
		user.setSurname1(USER_SURNAME_1);
		
		checkFullName(fullName, user);
	}

	private void checkFullName(String fullName, User user) {
		when(usersDAO.findUser(USER_ID)).thenReturn(user);
		
		assertEquals(fullName, usersService.getFullName(USER_ID));
	}

}
