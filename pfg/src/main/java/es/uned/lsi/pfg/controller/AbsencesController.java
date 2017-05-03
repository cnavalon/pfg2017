package es.uned.lsi.pfg.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Absence;
import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.Parent;
import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.model.Subject;
import es.uned.lsi.pfg.model.Teacher;
import es.uned.lsi.pfg.service.absences.AbsencesService;
import es.uned.lsi.pfg.service.common.MailService;
import es.uned.lsi.pfg.service.groups.GroupsService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de faltas de asistencia
 * @author Carlos Navalon Urrea
 */
@Controller
@RequestMapping("/absences")
public class AbsencesController {
	private static Logger logger = LoggerFactory.getLogger(AbsencesController.class);
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private AbsencesService absencesService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsService;
	
	@Autowired
	private MailService mailService;
	
	/**
	 * Devuelve la pagina de ver faltas de asistencia
	 * @param session
	 * @return pagina de ver faltas de asistencia
	 * @throws Exception
	 */
	@RequestMapping(value="/viewAbsences", method = RequestMethod.GET)
	public ModelAndView viewAbsences (HttpSession session) throws Exception {
		logger.debug("viewAbsences");
		ModelAndView model = new ModelAndView("viewAbsences");
		List<Student> lstStudents = new ArrayList<Student>();
		Integer id = (Integer) session.getAttribute(Constans.SESSION_ID);		
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		try {
			if(role.getIdRole().equals(Constans.ROLE_ADMIN)){
				lstStudents = usersService.getByClass(Student.class);
			} else if (role.getIdRole().equals(Constans.ROLE_TEACHER)){
				Group group = groupsService.getGroupByTutor(id);
				if(group != null){
					lstStudents = usersService.getStudensByGroup(group.getId());
				}
			} else if (role.getIdRole().equals(Constans.ROLE_STUDENT)){
				lstStudents.add(usersService.getById(id, Student.class));
			}  else if (role.getIdRole().equals(Constans.ROLE_PARENT)){
				lstStudents = usersService.findStudentsList(id);
			}
		} catch (Exception e) {
			logger.error("Error obteniendo listado de alumnos para usuario: " + id + ", " + role, e);
		}
		model.addObject("lstStudents", lstStudents);
		return model;
	}
	
	
	/**
	 * Devuelve la pagina de insertar faltas de asistencia
	 * @param session
	 * @return pagina de insertar faltas de asistencia
	 * @throws Exception
	 */
	@RequestMapping(value="/emp/absencesSchedule", method = RequestMethod.GET)
	public ModelAndView addAbsences (HttpSession session) throws Exception {
		logger.debug("absencesSchedule");
		ModelAndView model = new ModelAndView("absencesSchedule");
		List<Teacher> lstTeacher = new ArrayList<Teacher>();
		Integer id = (Integer) session.getAttribute(Constans.SESSION_ID);		
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		
		try {
			if(role.getIdRole().equals(Constans.ROLE_ADMIN)){
				lstTeacher = usersService.getByClass(Teacher.class);
			} else if (role.getIdRole().equals(Constans.ROLE_TEACHER)){
				lstTeacher.add(usersService.getById(id, Teacher.class));
			}
		} catch (Exception e) {
			logger.error("Error obteniendo listado de profesores para usuario: " + id + ", " + role, e);
		}
		model.addObject("lstTeacher", lstTeacher);
		return model;
	}
	
	/**
	 * Obtiene la tabla interactiva de horarios de un profesor 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/getTeacherSchedule/{id}", method = RequestMethod.GET)
	public ModelAndView getTeacherSchedule(@PathVariable("id") Integer id){
		logger.debug("getTeacherSchedule: " + id);
		ModelAndView model = new ModelAndView("scheduleTeacher");
		Teacher teacher = usersService.getById(id, Teacher.class);
		model.addObject("schedule", groupsService.getScheduleByTeacher(teacher));
		model.addObject("interactive", true);
		
		return model;
	}
	
	/**
	 * Obtiene el modal para insertar faltas de asistencia de una clase
	 * @param group id clase
	 * @param schedule id horario
	 * @param day fecha
	 * @return
	 */
	@RequestMapping(value="/emp/modalAbsences/{group}/{schedule}/{date}", method = RequestMethod.GET)
	public ModelAndView getModalAbsences(@PathVariable("group") Integer group, 
			@PathVariable("schedule") Integer schedule, @PathVariable("date") Long date){
		logger.debug("getModalAbsences: " + group + ", " + schedule + ", " + date);
		Date day = new Date(date);
		ModelAndView model = new ModelAndView("modalAbsences");
		model.addObject("lstStudents", usersService.getStudensByGroup(group));
		model.addObject("lstAbsencesStudens", absencesService.getStudentsAbsencesByScheduleDay(schedule, day));
		return model;
	}
	
	
	
	/**
	 * Inserta/actualiza faltas de asistencia
	 * @param lstAbsences listado de faltas de asistencia
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/emp/saveAbsences", method = RequestMethod.POST)
	public String saveAbsences(@RequestBody List<Absence> lstAbsences, Locale locale) throws Exception {
		logger.debug("saveAbsences: " + lstAbsences);
		String response = "";
		List<Absence> lstSaved = null;
		try {
			lstSaved = absencesService.upsert(lstAbsences);
			response = messageSource.getMessage("absences.save.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando faltas de asistencia " + lstAbsences, e);
			response = messageSource.getMessage("absences.save.error", null, locale);
		}
		if(lstSaved != null && !lstSaved.isEmpty()){
			sendAbscenceMail(lstSaved, locale);
		}
		return response;
	}
	
	private void sendAbscenceMail(List<Absence> lstAbsences, Locale locale){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for(Absence absence : lstAbsences){
			try {
				List<Parent> lstParent = usersService.findParentsList(absence.getStudent().getId());
				List<Person> lstPerson = new ArrayList<Person>(lstParent);
				String subject = "";
				String text = "";
				
				String[] subjectArgs = new String[]{absence.getStudent().getFullName()};
				String[] textArgs =new String[]{
						absence.getStudent().getFullName(),
						format.format(absence.getDay()),
						absence.getSchedule().getInitHour() + " - " + absence.getSchedule().getEndHour()};
				
				if(absence.isActive()){
					subject = messageSource.getMessage("absences.email.subject", subjectArgs, locale);
					text = messageSource.getMessage("absences.email.text", textArgs, locale);
				} else {
					subject = messageSource.getMessage("absences.email.subject.remove", subjectArgs, locale);
					text = messageSource.getMessage("absences.email.text.remove", textArgs, locale);
				}
				mailService.sendListMail(lstPerson, subject, text);
			} catch (Exception e) {
				logger.error("Error al enviar email de falta de asistencia: " + absence, e);
			}
		}
	}
	
	
	/**
	 * Recupera un listado de faltas de asistencia por alumno
	 * @param studentId id del alumno
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/getAbsencesStudent/{studentId}", method = RequestMethod.GET)
	public List<Absence> getAbsencesStudent (@PathVariable("studentId") Integer studentId) throws Exception {
		logger.debug("getAbsencesStudent: " + studentId );
		List<Absence> lstAbscences = new ArrayList<Absence>();
		try {
			lstAbscences = absencesService.getAbsencesByStudent(studentId);
			for(Absence absence : lstAbscences){
				Subject subject = groupsService.getSubjectByCode(absence.getSchedule().getSubject());
				absence.setSubject(subject);
			}
		} catch (Exception e) {
			logger.error("Error recuperando faltas de asistencia por alumno: " + studentId,e);
		}
		return lstAbscences;
	}
	
	
	
	
}
