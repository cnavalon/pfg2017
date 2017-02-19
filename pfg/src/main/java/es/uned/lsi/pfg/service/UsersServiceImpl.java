/**
 * 
 */
package es.uned.lsi.pfg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
	private ShaPasswordEncoder shaPasswordEncoder;
	
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
		logger.debug("getAllUsers");
		return usersDAO.findAllUsers();
	}

	@Override
	public User getUser(String id) {
		logger.debug("getUser: " + id);
		return usersDAO.findUser(id);
	}

	@Override
	public boolean existsUser(String id) {
		logger.debug("existsUser: " + id);
		if(usersDAO.findUser(id) == null){
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean save(User user) {
		logger.debug("user: " + user.getId());
		user.setPassword(shaPasswordEncoder.encodePassword(user.getPassword(), null));
		user.setEnabled(true);
		return usersDAO.upsert(user);
	}

	@Override
	@Transactional
	public boolean delete(String id) {
		User user = usersDAO.findUser(id);
		if(user != null){
			user.setEnabled(false);
			return usersDAO.upsert(user);
		}
		return false;
	}

}
