package es.uned.lsi.pfg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Attendee;
import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.Meeting;
import es.uned.lsi.pfg.model.MeetingFull;
import es.uned.lsi.pfg.model.Parent;
import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.ScheduleMeeting;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.model.TeacherMeeting;
import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.service.common.MailService;
import es.uned.lsi.pfg.service.groups.GroupsService;
import es.uned.lsi.pfg.service.meetings.MeetingsService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de tutorias
 * @author Carlos Navalon Urrea
 *
 */
@Controller
@RequestMapping("/meetings")
public class MeetingsController {

	private static final Logger logger = LoggerFactory.getLogger(MeetingsController.class);
	
	@Autowired
	private MeetingsService meetingsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MailService mailService;

	
	/**
	 * Obtiene la pagina de horario de tutorias
	 * @param session
	 * @return pagina de horario de tutorias
	 * @throws Exception
	 */
	@RequestMapping(value="/emp/schedules", method = RequestMethod.GET)
	public ModelAndView getMeetingsSchedule (HttpSession session) throws Exception{
		logger.debug("getMeetingsSchedule");
		ModelAndView model = new ModelAndView("meetingSchedule");
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		List<Teacher> lstTeachers = new ArrayList<Teacher>();
		if(role.getIdRole().equals(Constans.ROLE_TEACHER)){
			Integer id = (Integer)session.getAttribute(Constans.SESSION_USER_ID);
			lstTeachers.add(usersService.getById(id,Teacher.class));
		} else {
			lstTeachers = usersService.getByClass(Teacher.class);
		}
		model.addObject("lstTeachers", lstTeachers);
		return model;
	}
	
	/**
	 * Obtiene el listado de horarios de tutorias para un profesor
	 * @param user profesor
	 * @return listado de horarios de tutorias para un profesor
	 */
	@ResponseBody
	@RequestMapping(value="/emp/getTeacherScheduleMeeting", method = RequestMethod.POST)
	public List<ScheduleMeeting> getTeacherScheduleMeeting(@RequestParam Integer teacher){
		logger.debug("getTeacherScheduleMeeting: " + teacher);
		return meetingsService.getScheduleMeeting(teacher);
	}
	
	/**
	 * Inserta un horario de tutoria
	 * @param scheduleMeeting horario de tutoria
	 */
	@RequestMapping(value="/emp/saveScheduleMeeting", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void saveScheduleMeeting(@RequestBody ScheduleMeeting scheduleMeeting){
		logger.debug("saveScheduleMeeting: " + scheduleMeeting);
		meetingsService.saveScheduleMeeting(scheduleMeeting);
	}
	
	/**
	 * Elimina un horario de tutoria
	 * @param id id del horario de tutoria
	 */
	@RequestMapping(value="/emp/deleteScheduleMeeting/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteScheduleMeeting(@PathVariable("id") Integer id){
		logger.debug("deleteScheduleMeeting: " + id);
		meetingsService.deleteScheduleMeeting(id);
	}
	
	/**
	 * Obtiene la pagina de solicitud de tutoria
	 * @param session
	 * @param locale
	 * @return pagina de solicitud de tutoria
	 * @throws Exception
	 */
	@RequestMapping(value="/tut/requestMeetingPage", method = RequestMethod.GET)
	public ModelAndView getRequestMeetingPage (HttpSession session, Locale locale) throws Exception{
		logger.debug("getRequestMeetingPage");
		ModelAndView model = new ModelAndView("meetingRequest");
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		List<Student> lstStudent = new ArrayList<Student>();
		Integer id = (Integer)session.getAttribute(Constans.SESSION_USER_ID);
		Boolean isTeacher = false;
		if(role.getIdRole().equals(Constans.ROLE_TEACHER)){
			isTeacher = true;
			Group group = groupsService.getGroupByTutor(id);
			if(group != null){
				lstStudent = usersService.getStudensByGroup(group.getId());
			}
		} else {
			lstStudent = usersService.findStudentsList(id);
		}
		model.addObject("lstStudent", lstStudent);
		model.addObject("isTeacher", isTeacher);
		model.addObject("locale", locale.getLanguage());
		return model;
	}
	
	/**
	 * Encuentra el profesor para una tutoria en funcion del alumno
	 * @param student id del alumno
	 * @return el profesor para una tutoria 
	 */
	@ResponseBody
	@RequestMapping(value="tut/getMeetingTeacher/{student}", method = RequestMethod.GET)
	public TeacherMeeting getMeetingTeacher(@PathVariable("student") Integer student){
		logger.debug("getMeetingTeacher: " + student);
		Student std = usersService.getById(student, Student.class);
		if(std.getGroup() != null){
			return meetingsService.getTeacherMeeting(groupsService.getGroup(std.getGroup()).getTutor());
		}
		return null;
	}
	
	/**
	 * Encuentra los padres en funcion de un alumno
	 * @param student id alumno
	 * @param session
	 * @return listado de los padres del alumno
	 */
	@ResponseBody
	@RequestMapping(value="tut/getMeetingParents/{student}", method = RequestMethod.GET)
	public List<Parent> getMeetingParents(@PathVariable("student") Integer student, HttpSession session){
		logger.debug("getMeetingParents: " + student);
		try {
			List<Parent> lstParent = usersService.findParentsList(student);
			return lstParent;
		} catch (Exception e) {
			logger.error("Error recuperando listado de padres para tutoria: " + student, e);
		}
		return null;
	}
	
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="tut/requestMeeting", method = RequestMethod.POST)
	public String requestMeeting(@RequestBody MeetingFull meetingFull, Locale locale){
		logger.debug("requestMeeting: " + meetingFull);
		String response = null;
		Meeting meeting = null;
		try {
			meeting = meetingsService.saveMeeting(meetingFull);
			response = messageSource.getMessage("meeting.request.ok", null, locale);
			
		} catch (Exception e) {
			logger.error("Error al solicitar tutoría: " + meetingFull, e);
			return messageSource.getMessage("meeting.request.error", null, locale);
		}
		
		response += sendMeetingMail(meeting, meetingFull.getLstAttendee(), locale);
		return response;
	}
	
	private String sendMeetingMail(Meeting meeting, List<Attendee> lstAteendee, Locale locale){
		String error = "";
		String type = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			List<Person> lstPerson = new ArrayList<Person>();

			if(meeting.getType().equals(Constans.MEETING_TYPE_PRIVATE)){
				type = messageSource.getMessage("meeting.privateMeeting", null, locale);
			} else if (meeting.getType().equals(Constans.MEETING_TYPE_PARENT)) {
				type = messageSource.getMessage("meeting.parentsMeeting", null, locale);
			} else {
				type = messageSource.getMessage("meeting.default", null, locale);
			}
			String date = format.format(meeting.getDate()) + " " + meeting.getHour(); 
			String subject = messageSource.getMessage("meeting.email.subject", new String[]{type, meeting.getId().toString()}, locale);
			String text = messageSource.getMessage("meeting.email.requestText", new String[]{type, date}, locale);
			if(meeting.getComments() != null && !meeting.getComments().equals("")){
				text += "<br><br>" + meeting.getComments();
			}
			
			for(Attendee attendee : lstAteendee){
				User user = usersService.getUser(attendee.getUser());
				lstPerson.add(usersService.getByUser(user.getRole(), user.getIdUser()));
			}
			mailService.sendListMail(lstPerson, subject, text);
		} catch (Exception e) {
			logger.error("Error al enviar email", e);
			error += "<br><br>" + messageSource.getMessage("meeting.request.error.email", null, locale);
		}
		return error;
	}
	
	/**
	 * Obtiene la pagina de solicitud de tutoria
	 * @param session
	 * @param locale
	 * @return pagina de solicitud de tutoria
	 * @throws Exception
	 */
	@RequestMapping(value="/tch/requestMeetingParentsPage", method = RequestMethod.GET)
	public ModelAndView getRequestMeetingParentsPage (HttpSession session, Locale locale) throws Exception{
		logger.debug("getRequestMeetingParentsPage");
		ModelAndView model = new ModelAndView("meetingParentsRequest");
		Integer id = (Integer)session.getAttribute(Constans.SESSION_USER_ID);
		Group group = groupsService.getGroupByTutor(id);
		
		if(group != null){
			Map<Student, List<Parent>> mapStudentParent = new HashMap<Student, List<Parent>>();
			List<Student> lstStudents = usersService.getStudensByGroup(group.getId());
			for(Student student : lstStudents){
				mapStudentParent.put(student, usersService.findParentsList(student.getId()));
			}
			model.addObject("mapStudentParent", mapStudentParent);
		}
	
		model.addObject("group", group);
		model.addObject("locale", locale.getLanguage());
		return model;
	}
}
