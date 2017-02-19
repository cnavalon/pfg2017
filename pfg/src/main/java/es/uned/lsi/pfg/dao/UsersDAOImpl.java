/**
 * 
 */
package es.uned.lsi.pfg.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.model.User;

/**
 * @author Carlos Navalon Urrea
 * Implementacion del repositorio de usuarios
 */
@Repository
public class UsersDAOImpl extends AbstractJpaDao implements UsersDAO {

	private static final Logger logger = LoggerFactory.getLogger(UsersDAOImpl.class);
	
	@Override
	public User findUser(String id) {
		logger.debug("findUser:" + id);
		
		User user = null;
		try {
			user =  em.createNamedQuery(User.Q_FIND_BY_ID, User.class)
					.setParameter("idUser", id)
					.getSingleResult();
		} catch (Exception e) {
			logger.error("Error recuperando el usuario " + id, e);
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		logger.debug("findAllUsers");
		
		List<User> lstUsers = null;
		try {
			lstUsers =  em.createNamedQuery(User.Q_FIND_ALL, User.class)
					.getResultList();
		} catch (Exception e) {
			logger.error("Error recuperando todos los usuarios activos", e);
		}
		return lstUsers;
	}

	@Override
	public boolean upsert(User user) {
		try {
			em.merge(user);
			em.flush();
			return true;
		} catch (Exception e) {
			logger.error("Error insertando usuario en BBDD", e);
		}
		return false;
	}

	
}
