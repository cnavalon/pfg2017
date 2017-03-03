/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.lang.reflect.Constructor;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.UserSearch;

/**
 * Implementación del repositorio de personas
 * @author Carlos Navalon Urrea
 */
@Repository
public class PersonDAOImpl extends AbstractJpaDao implements PersonDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonDAOImpl.class);

	@Override
	public <T extends Person> T find(Integer id, Class<T> classPerson) {
		logger.debug("find:" + id + ", " + classPerson);

		try {
			Constructor<T> declaredConstructor = classPerson.getDeclaredConstructor();
			T person = declaredConstructor.newInstance();
			person =  em.createNamedQuery(person.getQueryFindById(), classPerson)
					.setParameter("id", id)
					.getSingleResult();
			return person;
		} catch (Exception e) {
			logger.error("Error recuperando " + id + ", " + classPerson, e);
		}
		return null;
	}

	@Override
	public <T extends Person> List<T> findAll(Class<T> classPerson) {
		logger.debug("findAllParents: " + classPerson);
		
		List<T> lstPersons = null;
		try {
			Constructor<T> declaredConstructor = classPerson.getDeclaredConstructor();
			T person = declaredConstructor.newInstance();
			lstPersons =  em.createNamedQuery(person.getQueryFindAll(), classPerson)
					.getResultList();
		} catch (Exception e) {
			logger.error("Error recuperando todos: " + classPerson, e);
		}
		return lstPersons;
	}

	@Override
	public <T extends Person> T findByIdUser(String idUser, Class<T> classPerson) {
		logger.debug("findByIdUser:" + idUser + ", " + classPerson);
		
		try {
			Constructor<T> declaredConstructor = classPerson.getDeclaredConstructor();
			T person = declaredConstructor.newInstance();
			person =  em.createNamedQuery(person.getQueryFindByIdUser(), classPerson)
					.setParameter("idUser", idUser)
					.getSingleResult();
			return person;
		} catch (Exception e) {
			logger.error("Error recuperando por id de usuario: " + idUser + ", " + classPerson, e);
		}
		return null;
	}

	@Override
	public <T extends Person> boolean upsert(T person) {
		logger.debug("upsert: " + person);
		try {
			em.merge(person);
			em.flush();
			return true;
		} catch (Exception e) {
			logger.error("Error insertando en BBDD: " + person, e);
		}
		return false;
	}
	
	@Override
	public <T extends Person> List<T> searchUsers(UserSearch userSearch, Class<T> classPerson) {
		logger.debug("searchUsers: " + userSearch + "," + classPerson);
		try {
			String query = "SELECT x FROM " + classPerson.getSimpleName() + " x WHERE x.enabled = 1";
			if(userSearch.getName() != null && !userSearch.getName().equals(""))
				query += " AND x.name LIKE '%" + userSearch.getName() + "%'";
			if(userSearch.getSurname1() != null && !userSearch.getSurname1().equals(""))
				query += " AND x.surname1 LIKE '%" + userSearch.getSurname1() + "%'";
			if(userSearch.getSurname2() != null && !userSearch.getSurname2().equals(""))
				query += " AND x.surname2 LIKE '%" + userSearch.getSurname2() + "%'";
			
			return em.createQuery(query).getResultList(); 
		} catch (Exception e) {
			logger.error("Error buscando personas: " + userSearch + "," + classPerson, e);
		}
		return null;
	}
}
