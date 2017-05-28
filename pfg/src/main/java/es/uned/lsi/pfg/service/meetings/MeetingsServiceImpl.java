/**
 * 
 */
package es.uned.lsi.pfg.service.meetings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import es.uned.lsi.pfg.model.Constans;
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

	@Override
	public List<Meeting> getActiveMeetingsByUser(String idUser) {
		logger.debug("getMeetingsByUser: " + idUser);
		Date date = new Date();

		//Reunioes que ha creado el usuario, en estado pendiente o aceptadas y posteriores a la fecha actual
		List<String> lstState = Arrays.asList(Constans.MEETING_STATUS_ACCEPTED, Constans.MEETING_STATUS_PENDING);
		List<Meeting> lstMeeting = meetingDAO.findByUserStateDate(idUser, lstState, date);
		
		//Reuniones en las que esta como invitado, aceptadas y posteriores a la fecha actual
		List<Attendee> lstAttendee = attendeeDAO.findByUserAndState(idUser, Constans.MEETING_STATUS_ACCEPTED);
		for(Attendee attendee : lstAttendee){
			Meeting meeting = meetingDAO.findByIdDate(attendee.getMeeting(), date);
			if(meeting != null){
				lstMeeting.add(meeting);
			}
		}
		
		return lstMeeting;
	}

	@Override
	public List<Meeting> getActiveRequest(String idUser) {
		logger.debug("getActiveRequest: " + idUser);
		List<Meeting> lstMeeting = new ArrayList<Meeting>();
		Date date = new Date();

		List<Attendee> lstAttendee = attendeeDAO.findByUserAndState(idUser, Constans.MEETING_STATUS_PENDING);
		for(Attendee attendee : lstAttendee){
			Meeting meeting = meetingDAO.findByIdDate(attendee.getMeeting(), date);
			if(meeting != null){
				meeting.setStatus(attendee.getStatus());
				lstMeeting.add(meeting);
			}
		}
		
		return lstMeeting;
	}

	@Transactional
	@Override
	public Attendee saveResponse(String idUser, Integer idMeeting, String state, String comment) {
		logger.debug("saveResponse: " + idUser + ", " + idMeeting + ", " + state + ", " + comment);
		Attendee attendee = attendeeDAO.findByMeetingUser(idMeeting, idUser);
		attendee.setStatus(state);
		attendee.setComments(comment);
		attendee = attendeeDAO.upsert(attendee);
		
		//Actualiza el estado de la reunion
		if(state.equals(Constans.MEETING_STATUS_ACCEPTED)){
			changeStateMeeting(idMeeting, Constans.MEETING_STATUS_ACCEPTED);
		} else if(state.equals(Constans.MEETING_STATUS_REJECTED)){
			List<Attendee> lstAttendee = attendeeDAO.findByMeeting(idMeeting);
			boolean allReject = true;
			for(Attendee item : lstAttendee){
				if(!item.getStatus().equals(Constans.MEETING_STATUS_REJECTED)){
					allReject = false;
					break;
				}
			}
			if(allReject){
				changeStateMeeting(idMeeting, Constans.MEETING_STATUS_REJECTED);
			}
		}
		return attendee;
	}
	
	private void changeStateMeeting(Integer idMeeting, String state){
		Meeting meeting = meetingDAO.findById(idMeeting);
		meeting.setStatus(state);
		meetingDAO.upsert(meeting);
	}

	@Override
	public Meeting getMeeting(Integer meeting) {
		logger.debug("getMeeting: " + meeting);
		return meetingDAO.findById(meeting);
	}

	@Override
	public List<Attendee> getAttendeeByMeeting(Integer meeting) {
		logger.debug("getAttendeeByMeeting: " + meeting);
		return attendeeDAO.findByMeeting(meeting);
	}

	@Transactional
	@Override
	public Meeting cancelMeeting(Integer idMeeting) {
		logger.debug("cancelMeeting: " + idMeeting);
		Meeting meeting = meetingDAO.findById(idMeeting);
		meeting.setStatus(Constans.MEETING_STATUS_CANCELED);
		meeting = meetingDAO.upsert(meeting);
		
		List<Attendee> lstAttendee = attendeeDAO.findByMeeting(idMeeting);
		for(Attendee attendee : lstAttendee){
			if(attendee.getStatus().equals(Constans.MEETING_STATUS_ACCEPTED) || attendee.getStatus().equals(Constans.MEETING_STATUS_PENDING)){
				attendee.setStatus(Constans.MEETING_STATUS_CANCELED);
				attendeeDAO.upsert(attendee);
			}
		}
		
		return meeting;
	}

	@Override
	public List<Attendee> getAttendeeByMeetingState(Integer meeting, String state) {
		logger.debug("getAttendeeByMeetingState: " + meeting + ", " + state);
		return attendeeDAO.findByMeetingState(meeting, state);
	}

	@Override
	public List<Meeting> getMeetingsByUser(String idUser) {
		logger.debug("getMeetingsByUser: " + idUser);
		
		//Reunioes que ha creado el usuario
		List<Meeting> lstMeeting = meetingDAO.findByUser(idUser);
		
		//Reuniones en las que esta como invitado y no pendientes
		List<Attendee> lstAttendee = attendeeDAO.findByUserAndNotState(idUser, Constans.MEETING_STATUS_PENDING);
		for(Attendee attendee : lstAttendee){
			Meeting meeting = meetingDAO.findById(attendee.getMeeting());
			if(meeting != null){
				lstMeeting.add(meeting);
			}
		}
		
		return lstMeeting;
	}

	@Override
	public List<Meeting> getRequest(String idUser) {
		logger.debug("getRequest: " + idUser);
		List<Meeting> lstMeeting = new ArrayList<Meeting>();

		List<Attendee> lstAttendee = attendeeDAO.findByUserAndState(idUser, Constans.MEETING_STATUS_PENDING);
		for(Attendee attendee : lstAttendee){
			Meeting meeting = meetingDAO.findById(attendee.getMeeting());
			if(meeting != null){
				meeting.setStatus(attendee.getStatus());
				lstMeeting.add(meeting);
			}
		}
		
		return lstMeeting;
	}

}
