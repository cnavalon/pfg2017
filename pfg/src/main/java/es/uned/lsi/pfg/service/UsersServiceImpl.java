/**
 * 
 */
package es.uned.lsi.pfg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.lsi.pfg.dao.UsersDAO;
import es.uned.lsi.pfg.model.User;

/**
 * @author Carlos Navalon Urrea
 * Implementacion del servicio de usuarios
 */
@Service
public class UsersServiceImpl implements UsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Override
	public String getFullName(String id) {
		logger.debug("getFullName: " + id);
		
		String fullName = "";
		User user = usersDAO.findUser(id);
		if(user != null){
			fullName = user.getName() + " " + user.getSurname1();
			if(user.getSurname2() != null)
				fullName += " " + user.getSurname2();
		}
		return fullName;
	}

	@Override
	public List<User> getAllUsers() {
		return usersDAO.findAllUsers();
	}

}
