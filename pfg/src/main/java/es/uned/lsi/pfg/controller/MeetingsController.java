package es.uned.lsi.pfg.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.ScheduleMeeting;
import es.uned.lsi.pfg.model.Teacher;
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
	
	@RequestMapping(value="/emp/saveScheduleMeeting", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void saveScheduleMeeting(@RequestBody ScheduleMeeting scheduleMeeting){
		logger.debug("saveScheduleMeeting: " + scheduleMeeting);
		meetingsService.saveScheduleMeeting(scheduleMeeting);
	}
	
	@RequestMapping(value="/emp/deleteScheduleMeeting/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteScheduleMeeting(@PathVariable("id") Integer id){
		logger.debug("deleteScheduleMeeting: " + id);
		meetingsService.deleteScheduleMeeting(id);
	}
}
