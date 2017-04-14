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
import es.uned.lsi.pfg.model.Meeting;

/**
 * Implementacion del repositorio de reuniones
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class MeetingDAOImpl extends AbstractJpaDao implements MeetingDAO {

	private static final Logger logger = LoggerFactory.getLogger(MeetingDAOImpl.class); 	
	
	@Override
	public Meeting upsert(Meeting meeting) {
		logger.debug("upsert: " + meeting);
		try {
			return em.merge(meeting);
		} catch (Exception e) {
			logger.error("Error insertando/acutalizando reunion: " + meeting, e);
			throw e;
		}
	}

	@Override
	public Meeting findMeeting(Integer id) {
		logger.debug("findMeeting: " + id);
		try {
			return em.createNamedQuery(Meeting.Q_FIND_BY_ID, Meeting.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando reunion: " + id, e);
			throw e;
		}
	}

	@Override
	public List<Meeting> findMeetingByUser(String user) {
		logger.debug("findMeetingByUser: " + user);
		List<Meeting> lstMeeting = new ArrayList<Meeting>();
		try {
			lstMeeting =  em.createNamedQuery(Meeting.Q_FIND_BY_USER, Meeting.class)
			.setParameter("user", user)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando reuniones por usuario: " + user, e);
			throw e;
		}
		return lstMeeting;
	}

}
