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

import es.uned.lsi.pfg.model.Parent;
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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void sendMail() throws Exception {
		Parent person = new Parent();
		person.setEmail("pruebas.pfg@gmail.com");
		mailServiceImpl.sendMail(person, "tutoría 5", "Tiene una nueva solicitud de tutoría. Fecha: 13/04/2017 10:00 - 11:00.<br>Un saludo");
	}
	
//	@Test
//	public void sendMailList() throws Exception {
//		mailServiceImpl.sendListMail(Arrays.asList("pruebas.pfg@gmail.com","pfg.sender@gmail.com"), "Prueba", "Mensaje de prueba");
//	}

}
