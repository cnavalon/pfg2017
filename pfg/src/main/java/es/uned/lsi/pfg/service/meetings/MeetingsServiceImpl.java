/**
 * 
 */
package es.uned.lsi.pfg.service.meetings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.meetings.AttendeeDAO;
import es.uned.lsi.pfg.dao.meetings.MeetingDAO;
import es.uned.lsi.pfg.dao.meetings.ScheduleMeetingsDAO;
import es.uned.lsi.pfg.model.Attendee;
import es.uned.lsi.pfg.model.Meeting;
import es.uned.lsi.pfg.model.MeetingFull;
import es.uned.lsi.pfg.model.ScheduleMeeting;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.model.TeacherMeeting;

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
	
	@Autowired
	private MeetingDAO meetingDAO;
	
	@Autowired
	private AttendeeDAO attendeeDAO;
	
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

	@Override
	public TeacherMeeting getTeacherMeeting(Teacher teacher) {
		logger.debug("getTeacherMeeting: " + teacher);
		List<ScheduleMeeting> lstScheduleMeeting = getScheduleMeeting(teacher.getId());
		Map<Integer, List<String>> mapDayHour = new HashMap<Integer, List<String>>();
		for(ScheduleMeeting scheduleMeeting : lstScheduleMeeting){
			Integer day = scheduleMeeting.getDay();
			if(!mapDayHour.containsKey(day)){
				mapDayHour.put(day, new ArrayList<String>());
			}
			mapDayHour.get(day).add(scheduleMeeting.getInitHour() + " - " + scheduleMeeting.getEndHour());
		}
		return new TeacherMeeting(teacher, mapDayHour);
	}

	@Override
	@Transactional
	public Meeting saveMeeting(MeetingFull meetingFull) {
		logger.debug("requestMeeting: " + meetingFull);
		Meeting meeting = meetingDAO.upsert(new Meeting(meetingFull));
		for(Attendee attendee : meetingFull.getLstAttendee()){
			attendee.setMeeting(meeting.getId());
			attendeeDAO.upsert(attendee);
		}
		return meeting;
	}

}
