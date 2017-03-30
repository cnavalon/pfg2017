/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Group;

/**
 * Implementacion de repositorio de clases
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class GroupsDAOImpl extends AbstractJpaDao implements GroupsDAO {

	private static final Logger logger = LoggerFactory.getLogger(GroupsDAOImpl.class);
	
	@Override
	public Group findById(Integer id) {
		logger.debug("findById: " + id);
		try {
			return em.createNamedQuery(Group.Q_FIND_BY_ID, Group.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando clase: " + id, e);
		}
		return null;
	}

	@Override
	public List<Group> findAll() {
		logger.debug("findAll");
		List<Group> lstGroups = new ArrayList<Group>();
		try {
			lstGroups = em.createNamedQuery(Group.Q_FIND_ALL, Group.class).getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando todas las clases", e);
			throw e;
		}
		return lstGroups;
	}

	@Override
	public void upsert(Group group) {
		logger.debug("upsert: " + group);
		try {
			em.merge(group);
			em.flush();
		} catch (Exception e) {
			logger.error("Error insertando/actualizando clase en BBDD: " + group + ". " + e.getMessage());
			throw e;
		}
	}

	@Override
	public void remove(Group group) {
		logger.debug("remove: " + group);
		try {
			em.remove(group);
			em.flush();
		} catch (Exception e) {
			logger.error("Error eliminando clase en BBDD: " + group + ". " + e.getMessage());
			throw e;
		}

	}

	@Override
	public Group findByCourseAndLetter(Integer course, String letter) {
		logger.debug("findByCourseAndLetter: " + course + ", " + letter);
		try {
			return em.createNamedQuery(Group.Q_FIND_BY_COURSE_LETTER, Group.class)
					.setParameter("course", course)
					.setParameter("letter", letter)
					.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando clase por curso y letra: " + course + ", " + letter, e);
		}
		return null;
	}

	@Override
	public List<Group> findByCourse(Integer course) {
		logger.debug("findByCourse: " + course );
		List<Group> lstGroups = new ArrayList<Group>();
		try {
			lstGroups = em.createNamedQuery(Group.Q_FIND_BY_COURSE, Group.class)
					.setParameter("course", course)
					.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando clases por curso: " + course, e);
			throw e;
		}
		return lstGroups;
	}

}
