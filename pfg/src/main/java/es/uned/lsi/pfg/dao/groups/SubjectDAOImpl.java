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
import es.uned.lsi.pfg.model.Subject;

/**
 * Implementacion de repositorio de asignaturas
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class SubjectDAOImpl extends AbstractJpaDao implements SubjectDAO {

	private static final Logger logger = LoggerFactory.getLogger(SubjectDAOImpl.class);
	
	@Override
	public List<Subject> findAll() {
		logger.debug("findAll");
		List<Subject> lstSubjects = new ArrayList<Subject>();
		try {
			lstSubjects = em.createNamedQuery(Subject.Q_FIND_ALL, Subject.class)
				.getResultList();
		}catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando listado de todas las asignaturas", e);
			throw e;
		}
		return lstSubjects;
	}

	@Override
	public Subject findById(Integer id) {
		logger.debug("findById: " + id);
		try {
			return em.createNamedQuery(Subject.Q_FIND_BY_ID, Subject.class)
				.setParameter("id", id)
				.getSingleResult();
		}catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando asignatura por id: " + id, e);
			throw e;
		}
	}

	@Override
	public Subject findByCode(String code) {
		logger.debug("findByCode: " + code);
		try {
			return em.createNamedQuery(Subject.Q_FIND_BY_CODE, Subject.class)
				.setParameter("code", code)
				.getSingleResult();
		}catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando asignatura por codigo: " + code, e);
			throw e;
		}
	}

	@Override
	public Subject upsert(Subject subject) {
		logger.debug("upsert: " + subject);
		try {
			return (Subject)em.merge(subject);
		} catch (Exception e) {
			logger.error("Error insertando/actualizando asignatura: " + subject, e);
			throw e;
		}
	}

}
