/**
 * 
 */
package es.uned.lsi.pfg.dao.qualifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Qualification;

/**
 * Implementacion de repositorio de calificaciones
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class QualificationsDAOImpl extends AbstractJpaDao implements QualificationsDAO {

	private static final Logger logger = LoggerFactory.getLogger(QualificationsDAOImpl.class);
	
	@Override
	public Qualification findById(Integer id) {
		logger.debug("findById: " + id);
		try {
			return em.createNamedQuery(Qualification.Q_FIND_BY_ID, Qualification.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando calificacion: " + id, e);
			throw e;
		}
	}

	@Override
	public List<Qualification> findByStudentGroup(Integer student, Integer group) {
		logger.debug("findByStudent: " + student);
		List<Qualification> lstQualifications = new ArrayList<Qualification>();
		try {
			lstQualifications =  em.createNamedQuery(Qualification.Q_FIND_BY_STUDENT_GROUP, Qualification.class)
			.setParameter("student", student)
			.setParameter("group", group)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando calificacion por estudiante: " + student, e);
			throw e;
		}
		return lstQualifications;
	}

	@Override
	public List<Qualification> findByGroupSubject(Integer group, Integer subject) {
		logger.debug("findByGroupSubject: " + group + ", " + subject);
		List<Qualification> lstQualifications = new ArrayList<Qualification>();
		try {
			lstQualifications =  em.createNamedQuery(Qualification.Q_FIND_BY_GROUP_SUBJECT, Qualification.class)
			.setParameter("group", group)
			.setParameter("subject", subject)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando calificacion por clase y asignatura: " + group + ", " + subject, e);
			throw e;
		}
		return lstQualifications;
	}

	@Override
	public Qualification upsert(Qualification qualification) {
		logger.debug("upsert: " + qualification);
		try {
			return em.merge(qualification);
		} catch (Exception e) {
			logger.error("Error insertando/acutalizando calificacion: " + qualification, e);
			throw e;
		}
	}

}
