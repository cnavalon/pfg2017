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
import es.uned.lsi.pfg.model.Attendee;

/**
 * Implementacion de repositorio de asistentes a reunion
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class AttendeeDAOImpl extends AbstractJpaDao implements AttendeeDAO {

	private static final Logger logger = LoggerFactory.getLogger(AttendeeDAOImpl.class);
	
	@Override
	public Attendee upsert(Attendee attendee) {
		logger.debug("upsert: " + attendee);
		try {
			return em.merge(attendee);
		} catch (Exception e) {
			logger.error("Error insertando/acutalizando asistente a reunion: " + attendee, e);
			throw e;
		}
	}
	@Override
	public List<Attendee> findByMeeting(Integer meeting) {
		logger.debug("findByMeeting: " + meeting);
		List<Attendee> lstAttendees = new ArrayList<Attendee>();
		try {
			lstAttendees =  em.createNamedQuery(Attendee.Q_FIND_BY_MEETING, Attendee.class)
			.setParameter("meeting", meeting)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando asistentes por reunion: " + meeting, e);
			throw e;
		}
		return lstAttendees;
	}

	@Override
	public List<Attendee> findByUser(String user) {
		logger.debug("findByUser: " + user);
		List<Attendee> lstAttendees = new ArrayList<Attendee>();
		try {
			lstAttendees =  em.createNamedQuery(Attendee.Q_FIND_BY_USER, Attendee.class)
			.setParameter("user", user)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando asistentes por usuario: " + user, e);
			throw e;
		}
		return lstAttendees;
	}

}
