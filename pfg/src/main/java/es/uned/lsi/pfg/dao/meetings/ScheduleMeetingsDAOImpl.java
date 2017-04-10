/**
 * 
 */
package es.uned.lsi.pfg.dao.meetings;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.ScheduleMeeting;

/**
 * Implementacion de repositorio de horario de tutorias
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class ScheduleMeetingsDAOImpl extends AbstractJpaDao implements ScheduleMeetingsDAO {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleMeetingsDAOImpl.class);
	
	@Override
	public List<ScheduleMeeting> findByTeacher(Integer teacher) {
		logger.debug("findByTeacher: " + teacher);
		List<ScheduleMeeting> lstScheduleMeeting = new ArrayList<ScheduleMeeting>();
		try {
			lstScheduleMeeting =  em.createNamedQuery(ScheduleMeeting.Q_FIND_BY_TEACHER, ScheduleMeeting.class)
			.setParameter("teacher", teacher)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando horarios de turoria por profesor: " + teacher, e);
			throw e;
		}
		return lstScheduleMeeting;
	}

	@Override
	public void save(ScheduleMeeting scheduleMeeting) {
		logger.debug("save: " + scheduleMeeting);
		try {
			em.persist(scheduleMeeting);
		} catch (Exception e) {
			logger.error("ERROR insertando horario de tutoria: " + scheduleMeeting, e);
			throw e;
		}
	}

	@Override
	public void delete(ScheduleMeeting scheduleMeeting) {
		logger.debug("delete: " + scheduleMeeting);
		try {
			em.remove(scheduleMeeting);
		} catch (Exception e) {
			logger.error("ERROR eliminando horario de tutoria: " + scheduleMeeting, e);
			throw e;
		}

	}

	@Override
	public ScheduleMeeting findById(Integer id) {
		logger.debug("findById: " + id);
		try {
			return em.createNamedQuery(ScheduleMeeting.Q_FIND_BY_ID, ScheduleMeeting.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando horarios de turoria por id: " + id, e);
			throw e;
		}
	}

}
