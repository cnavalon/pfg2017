/**
 * 
 */
package es.uned.lsi.pfg.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.model.User;

/**
 * @author Carlos Navalon Urrea
 * 
 */
@Repository
public class UsersDAOImpl extends AbstractJpaDao implements UsersDAO {

	private static final Logger logger = LoggerFactory.getLogger(UsersDAOImpl.class);
	
	@Override
	public User findUser(String id) {
		logger.debug("findUser:" + id);
		User user = null;
		try {
			user =  em.createQuery("SELECT u FROM User u WHERE id = :idUser AND enabled = 1", User.class)
					.setParameter("idUser", id)
					.getSingleResult();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage(),e);
		}
		return user;
	}

	
}
