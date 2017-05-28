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
import es.uned.lsi.pfg.model.Constans;
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
import es.uned.lsi.pfg.service.common.MailService;
import es.uned.lsi.pfg.service.groups.GroupsService;
import es.uned.lsi.pfg.service.meetings.MeetingsService;
import es.uned.lsi.pfg.service.users.UsersService;

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
			Integer id = (Integer)session.getAttribute(Constans.SESSION_ID);
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
	public List<ScheduleMeeting> getTeacherScheduleMeeting(@RequestParam Integer teacher) throws Exception{
		logger.debug("getTeacherScheduleMeeting: " + teacher);
		return meetingsService.getScheduleMeeting(teacher);
	}
	
	/**
	 * Inserta un horario de tutoria
	 * @param scheduleMeeting horario de tutoria
	 */
	@RequestMapping(value="/emp/saveScheduleMeeting", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void saveScheduleMeeting(@RequestBody ScheduleMeeting scheduleMeeting) throws Exception{
		logger.debug("saveScheduleMeeting: " + scheduleMeeting);
		meetingsService.saveScheduleMeeting(scheduleMeeting);
	}
	
	/**
	 * Elimina un horario de tutoria
	 * @param id id del horario de tutoria
	 */
	@RequestMapping(value="/emp/deleteScheduleMeeting/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteScheduleMeeting(@PathVariable("id") Integer id) throws Exception{
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
		Integer id = (Integer)session.getAttribute(Constans.SESSION_ID);
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
	public TeacherMeeting getMeetingTeacher(@PathVariable("student") Integer student) throws Exception{
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
	public List<Parent> getMeetingParents(@PathVariable("student") Integer student, HttpSession session) throws Exception{
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
	public String requestMeeting(@RequestBody MeetingFull meetingFull, Locale locale) throws Exception{
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
	
	private String sendMeetingMail(Meeting meeting, List<Attendee> lstAttendee, Locale locale){
		String error = "";
		String type = "";
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			List<Person> lstPerson = new ArrayList<Person>();

			if(meeting.getType().equals(Constans.MEETING_TYPE_PRIVATE)){
				type = messageSource.getMessage("meeting.type.T", null, locale);
			} else if (meeting.getType().equals(Constans.MEETING_TYPE_PARENT)) {
				type = messageSource.getMessage("meeting.type.P", null, locale);
			} 
			String date = format.format(meeting.getDate()) + " " + meeting.getHour(); 
			String subject = messageSource.getMessage("meeting.email.subject", new String[]{type, meeting.getId().toString()}, locale);
			String text = messageSource.getMessage("meeting.email.requestText", new String[]{type, date}, locale);
			if(meeting.getComments() != null && !meeting.getComments().equals("")){
				text += "<br><br>" + meeting.getComments();
			}
			
			for(Attendee attendee : lstAttendee){
				lstPerson.add(usersService.getByUser(attendee.getUser()));
			}
			mailService.sendListMail(lstPerson, subject, text);
		} catch (Exception e) {
			logger.error("Error al enviar email", e);
			error += "<br><br>" + messageSource.getMessage("meeting.request.error.email", null, locale);
		}
		return error;
	}
	
	/**
	 * Obtiene la pagina de solicitud de reunion de padres
	 * @param session
	 * @param locale
	 * @return pagina de solicitud de reunion de padres
	 * @throws Exception
	 */
	@RequestMapping(value="/tch/requestMeetingParentsPage", method = RequestMethod.GET)
	public ModelAndView getRequestMeetingParentsPage (HttpSession session, Locale locale) throws Exception{
		logger.debug("getRequestMeetingParentsPage");
		ModelAndView model = new ModelAndView("meetingParentsRequest");
		Integer id = (Integer)session.getAttribute(Constans.SESSION_ID);
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
	
	/**
	 * Obtiene la pagina de agenda de reuniones
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/atp/diary", method = RequestMethod.GET)
	public ModelAndView getDiaryPage (HttpSession session) throws Exception{
		logger.debug("getDiaryPage");
		ModelAndView model = new ModelAndView("meetingDiary");
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		List<Teacher> lstTeacher = new ArrayList<Teacher>();
		List<Parent> lstParent = new ArrayList<Parent>();
		
		if(role.getIdRole().equals(Constans.ROLE_ADMIN)){
			lstTeacher = usersService.getByClass(Teacher.class);
			lstParent = usersService.getByClass(Parent.class);
		} else if(role.getIdRole().equals(Constans.ROLE_TEACHER)){
			Integer id = (Integer)session.getAttribute(Constans.SESSION_ID);
			lstTeacher.add(usersService.getById(id, Teacher.class));
		} else if(role.getIdRole().equals(Constans.ROLE_PARENT)){
			Integer id = (Integer)session.getAttribute(Constans.SESSION_ID);
			lstParent.add(usersService.getById(id, Parent.class));
		}
	
		model.addObject("lstTeacher", lstTeacher);
		model.addObject("lstParent", lstParent);
		return model;
	}
	
	/**
	 * Recupera un listado de las reuniones activas del usuario
	 * @param idUser id de usuario
	 * @return listado de reuniones
	 */
	@RequestMapping(value="/atp/getActiveMeeting/{idUser}", method = RequestMethod.GET)
	@ResponseBody
	public List<Meeting> getActiveMeetings(@PathVariable("idUser") String idUser) throws Exception{
		logger.debug("getActiveMeetings: " + idUser);
		List<Meeting> lstMeetings = new ArrayList<Meeting>();
		try {
			lstMeetings = meetingsService.getActiveMeetingsByUser(idUser);
			for(Meeting meeting : lstMeetings){
				meeting.setPerson(usersService.getByUser(meeting.getUser()));
			}
		} catch (Exception e) {
			logger.error("Error recuperando reuniones activas por usuario: " + idUser, e);
		}
		
		return lstMeetings;
	}
	
	/**
	 * Recupera un listado de las reuniones del usuario
	 * @param idUser id de usuario
	 * @return listado de reuniones
	 */
	@RequestMapping(value="/atp/getMeeting/{idUser}", method = RequestMethod.GET)
	@ResponseBody
	public List<Meeting> getMeetings(@PathVariable("idUser") String idUser) throws Exception{
		logger.debug("getMeetings: " + idUser);
		List<Meeting> lstMeetings = new ArrayList<Meeting>();
		try {
			lstMeetings = meetingsService.getMeetingsByUser(idUser);
			for(Meeting meeting : lstMeetings){
				meeting.setPerson(usersService.getByUser(meeting.getUser()));
			}
		} catch (Exception e) {
			logger.error("Error recuperando reuniones por usuario: " + idUser, e);
		}
		
		return lstMeetings;
	}
	
	/**
	 * Obtiene un listado de las solicitudes de reuniones activas pendientes 
	 * @param idUser id de usuario
	 * @return listado de reuniones
	 */
	@RequestMapping(value="/atp/getActiveRequest/{idUser}", method = RequestMethod.GET)
	@ResponseBody
	public List<Meeting> getActiveRequest(@PathVariable("idUser") String idUser) throws Exception{
		logger.debug("getActiveRequest: " + idUser);
		List<Meeting> lstMeetings = new ArrayList<Meeting>();
		try {
			lstMeetings = meetingsService.getActiveRequest(idUser);
			if(lstMeetings != null && !lstMeetings.isEmpty()){
				for(Meeting meeting : lstMeetings){
					meeting.setPerson(usersService.getByUser(meeting.getUser()));
				}
			}
		} catch (Exception e) {
			logger.error("Error recuperando peticiones de reunión activas por usuario: " + idUser, e);
		}
		
		return lstMeetings;
	}
	
	/**
	 * Obtiene un listado de las solicitudes de reunioes pendientes 
	 * @param idUser id de usuario
	 * @return listado de reuniones
	 */
	@RequestMapping(value="/atp/getRequest/{idUser}", method = RequestMethod.GET)
	@ResponseBody
	public List<Meeting> getRequest(@PathVariable("idUser") String idUser) throws Exception{
		logger.debug("getRequest: " + idUser);
		List<Meeting> lstMeetings = new ArrayList<Meeting>();
		try {
			lstMeetings = meetingsService.getRequest(idUser);
			if(lstMeetings != null && !lstMeetings.isEmpty()){
				for(Meeting meeting : lstMeetings){
					meeting.setPerson(usersService.getByUser(meeting.getUser()));
				}
			}
		} catch (Exception e) {
			logger.error("Error recuperando peticiones de reunión por usuario: " + idUser, e);
		}
		
		return lstMeetings;
	}
	
	
	
	/**
	 * Obtiene el modal de respuesta a solicitud de reunion
	 * @param state estado de respuesta
	 * @param locale
	 * @return modal de respuesta a solicitud de reunion
	 */
	@RequestMapping(value="/tut/responseModal/{state}", method = RequestMethod.GET)
	public ModelAndView getResponseModal(@PathVariable("state") String state, Locale locale) throws Exception{
		logger.debug("getResponseModal: " + state);
		ModelAndView model = new ModelAndView("modalResponse");
		String title = "";
		if(state.equals("A")){
			title = messageSource.getMessage("meeting.accept", null, locale);
		} else if(state.equals("R")){
			title = messageSource.getMessage("meeting.reject", null, locale);
		}
		model.addObject("title", title);
		return model;
	}
	
	/**
	 * Guarda un repuesta a peticion de reunion y actualiza estado de reunion si procede
	 * @param meeting id reuion
	 * @param state estado respuesta
	 * @param comment comentario respuesta
	 * @param session
	 * @param locale
	 * @return mensaje de resultado de operacion
	 */
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/tut/responseMeeting", method = RequestMethod.POST)
	public String responseMeeting(@RequestParam Integer meeting, @RequestParam String state, 
			@RequestParam String comment, HttpSession session, Locale locale){
		logger.debug("responseMeeting: " + meeting + ", " + state + ", " + comment);
		String response = "";
		Attendee attendee = null;
		try {
			String idUser = (String) session.getAttribute(Constans.SESSION_USER);
			attendee = meetingsService.saveResponse(idUser, meeting, state, comment);
			response = messageSource.getMessage("meeting.response.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando respuesta de solicitud de reunion: " + meeting + ", " + state + ", " + comment, e);
			return  messageSource.getMessage("meeting.response.error", null, locale);
		}
		if(response != null)
			response += sendResponseMail(attendee, locale);
		return response;
	}
	
	private String sendResponseMail(Attendee attendee, Locale locale){
		String error = "";
		try {
			Meeting meeting = meetingsService.getMeeting(attendee.getMeeting());
			String type = "";
			if(meeting.getType().equals(Constans.MEETING_TYPE_PRIVATE)){
				type = messageSource.getMessage("meeting.type.T", null, locale);
			} else if (meeting.getType().equals(Constans.MEETING_TYPE_PARENT)) {
				type = messageSource.getMessage("meeting.type.P", null, locale);
			}
			
			String state =  messageSource.getMessage("meeting.status." + attendee.getStatus(), null, locale);
			String idMeeting = attendee.getMeeting().toString();
			Person personAttendee = usersService.getByUser(attendee.getUser());
			String subject = messageSource.getMessage("meeting.email.subject", new String[]{type, idMeeting}, locale);
			String text = messageSource.getMessage("meeting.email.responseText", new String[]{type, state, personAttendee.getFullName()}, locale);
			if(attendee.getComments() != null && !attendee.getComments().equals("")){
				text += "<br><br>" + attendee.getComments();
			}
			Person personMeeting = usersService.getByUser(meeting.getUser());
			mailService.sendMail(personMeeting, subject, text);
		} catch (Exception e) {
			logger.error("Error al enviar email", e);
			error += "<br><br>" + messageSource.getMessage("meeting.request.error.email", null, locale);
		}
		return error;
	}
	
	/**
	 * Obtiene modal de asistentes a reunion
	 * @param meeting id de reuinon
	 * @return modal de asistentes a reunion
	 * @throws Exception
	 */
	@RequestMapping(value="/atp/modalAttendee/{meeting}", method = RequestMethod.GET)
	public ModelAndView getModalAttendee(@PathVariable("meeting") Integer meeting) throws Exception{
		logger.debug("getModalAttendee: " + meeting);
		ModelAndView model = new ModelAndView("modalAttendee");
		List<Attendee> lstAttendee = new ArrayList<Attendee>();
		try {
			lstAttendee = meetingsService.getAttendeeByMeeting(meeting);
			if(lstAttendee != null && !lstAttendee.isEmpty()){
				for(Attendee attendee : lstAttendee){
					attendee.setPerson(usersService.getByUser(attendee.getUser()));
				}
			}
		} catch (Exception e) {
			logger.error("Error obteniendo modal de asistentes: " + meeting, e);
		}
		model.addObject("lstAttendee", lstAttendee);
		return model;
	}
	
	/**
	 * Cancela una reunion
	 * @param idMeeting id reunion
	 * @param locale
	 * @return mensaje con resultado de la operacion
	 * @throws Exception
	 */
	@ResponseBody
	@ResponseStatus(value=HttpStatus.OK)
	@RequestMapping(value="/atp/cancelMeeting/{idMeeting}", method = RequestMethod.GET)
	public String cancelMeeting(@PathVariable("idMeeting") Integer idMeeting, Locale locale) throws Exception{
		logger.debug("cancelMeeting: " + idMeeting);
		String response = "";
		Meeting meeting = null;
		try {
			meeting = meetingsService.cancelMeeting(idMeeting);
			response = messageSource.getMessage("meeting.cancel.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error cancelando reunion: " + idMeeting);
			return messageSource.getMessage("meeting.cancel.error", null, locale);
		}
		if(meeting != null)
			response += sendCancelMail(meeting, locale);
		
		return response;
	}
	
	private String sendCancelMail(Meeting meeting, Locale locale){
		String error = "";
		try {
			String type = "";
			if(meeting.getType().equals(Constans.MEETING_TYPE_PRIVATE)){
				type = messageSource.getMessage("meeting.type.T", null, locale);
			} else if (meeting.getType().equals(Constans.MEETING_TYPE_PARENT)) {
				type = messageSource.getMessage("meeting.type.P", null, locale);
			}
			String idMeeting = meeting.getId().toString();
			String subject = messageSource.getMessage("meeting.email.subject", new String[]{type, idMeeting}, locale);
			String text = messageSource.getMessage("meeting.email.cancelText", new String[]{type}, locale);
			
			List<Person> lstPerson = new ArrayList<Person>();
			List<Attendee> lstAttendee = meetingsService.getAttendeeByMeetingState(meeting.getId(), Constans.MEETING_STATUS_CANCELED);
			for(Attendee attendee : lstAttendee){
				lstPerson.add(usersService.getByUser(attendee.getUser()));
			}
			mailService.sendListMail(lstPerson, subject, text);
		} catch (Exception e) {
			logger.error("Error al enviar email", e);
			error += "<br><br>" + messageSource.getMessage("meeting.request.error.email", null, locale);
		}
		return error;
	}
	
}


