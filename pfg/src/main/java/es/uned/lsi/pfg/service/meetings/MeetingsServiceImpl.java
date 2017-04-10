/**
 * 
 */
package es.uned.lsi.pfg.service.meetings;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.meetings.ScheduleMeetingsDAO;
import es.uned.lsi.pfg.model.ScheduleMeeting;

/**
 * Implementacion del servicio de tutorias
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class MeetingsServiceImpl implements MeetingsService {

	private static final Logger logger = LoggerFactory.getLogger(MeetingsServiceImpl.class);
	
	@Autowired
	private ScheduleMeetingsDAO scheduleMeetingsDAO;
	
	@Override
	public List<ScheduleMeeting> getScheduleMeeting(Integer idTeacher) {
		logger.debug("getScheduleMeeting: " + idTeacher);
		return scheduleMeetingsDAO.findByTeacher(idTeacher);
	}

	@Transactional
	@Override
	public void saveScheduleMeeting(ScheduleMeeting scheduleMeeting) {
		logger.debug("saveScheduleMeeting: " + scheduleMeeting);
		scheduleMeetingsDAO.save(scheduleMeeting);
		
	}

	@Transactional
	@Override
	public void deleteScheduleMeeting(Integer id) {
		logger.debug("deleteScheduleMeeting: " + id);
		ScheduleMeeting scheduleMeeting = scheduleMeetingsDAO.findById(id);
		scheduleMeetingsDAO.delete(scheduleMeeting);
	}

}
