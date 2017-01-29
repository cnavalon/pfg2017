/**
 * 
 */
package es.uned.lsi.pfg.service;

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

	@Autowired
	private UsersDAO usersDAO;
	
	@Override
	public String getFullName(String id) {
		String fullName = "";
		User user = usersDAO.findUser(id);
		if(user != null){
			fullName = user.getName() + " " + user.getSurname1();
			if(user.getSurname2() != null)
				fullName += " " + user.getSurname2();
		}
		return fullName;
	}

}
