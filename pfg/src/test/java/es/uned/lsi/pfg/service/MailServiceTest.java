package es.uned.lsi.pfg.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import es.uned.lsi.pfg.service.common.MailServiceImpl;
import static org.mockito.Mockito.*;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class MailServiceTest {
	
	@Mock
	private Environment env;
	
	@InjectMocks
	private MailServiceImpl mailServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(env.getProperty("email.user")).thenReturn("pfg.sender@gmail.com");
		when(env.getProperty("email.pass")).thenReturn("pfgsenderpass");
		mailServiceImpl.init();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sendMail() throws Exception {
		mailServiceImpl.sendMail("pruebas.pfg@gmail.com", "Prueba", "Mensaje de prueba");
	}
	
	@Test
	public void sendMailList() throws Exception {
		mailServiceImpl.sendListMail(Arrays.asList("pruebas.pfg@gmail.com","pfg.sender@gmail.com"), "Prueba", "Mensaje de prueba");
	}

}
