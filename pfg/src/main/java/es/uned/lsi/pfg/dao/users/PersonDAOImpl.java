/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.lang.reflect.Constructor;
import java.util.List;

import javax.persistence.NoResultException;

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
		} catch (NoResultException e) {
			logger.debug("Empty results");
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
		} catch (NoResultException e) {
			logger.debug("Empty results");
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
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando por id de usuario: " + idUser + ", " + classPerson, e);
		}
		return null;
	}

	@Override
	public <T extends Person> void upsert(T person) throws Exception {
		logger.debug("upsert: " + person);
		try {
			em.merge(person);
			em.flush();
		} catch (Exception e) {
			logger.error("Error insertando persona en BBDD: " + person + ". " + e.getMessage());
			throw e;
		}
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
			if(userSearch.getId() != null)
				query += " AND x.id = " + userSearch.getId();
			if(userSearch.getIdUser() != null && !userSearch.getIdUser().equals(""))
				query += " AND x.idUser LIKE '%" + userSearch.getIdUser() + "%'";
			
			
			return em.createQuery(query, classPerson).getResultList(); 
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error buscando personas: " + userSearch + "," + classPerson, e);
		}
		return null;
	}

	@Override
	public <T extends Person> List<T> findAllHistoric(Class<T> classPerson) {
		logger.debug("findAllHistoric: " + classPerson);
		List<T> lstPersons = null;
		try {
			lstPersons =  em.createQuery("SELECT x FROM " + classPerson.getName() + " x", classPerson)
					.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando todos: " + classPerson, e);
		}
		return lstPersons;
	}
}
