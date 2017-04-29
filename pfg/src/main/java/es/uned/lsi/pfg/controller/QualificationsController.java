package es.uned.lsi.pfg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.Qualification;
import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.model.Subject;
import es.uned.lsi.pfg.model.SubjectQualification;
import es.uned.lsi.pfg.service.groups.GroupsService;
import es.uned.lsi.pfg.service.qualification.QualificationService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de calificaciones
 * @author Carlos Navalon Urrea
 */
@Controller
@RequestMapping("/qualifications")
public class QualificationsController {
	private static Logger logger = LoggerFactory.getLogger(QualificationsController.class);
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private QualificationService qualificationService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsService;
	
	/**
	 * Devuelve la pagina de ver calificaciones
	 * @param session
	 * @return pagina de ver calificaciones
	 * @throws Exception
	 */
	@RequestMapping(value="/viewQualifications", method = RequestMethod.GET)
	public ModelAndView viewQualifications (HttpSession session) throws Exception {
		logger.debug("viewQualifications");
		ModelAndView model = new ModelAndView("viewQualifications");
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
			logger.error("Error obteniendo listado de clases para usuario: " + id + ", " + role, e);
		}
		model.addObject("lstStudents", lstStudents);
		return model;
	}
	
	@RequestMapping(value="/emp/addQualifications", method = RequestMethod.GET)
	public ModelAndView addQualifications (HttpSession session) throws Exception {
		logger.debug("addQualifications");
		ModelAndView model = new ModelAndView("addQualifications");
		List<Group> lstGroups = new ArrayList<Group>();
		Integer id = (Integer) session.getAttribute(Constans.SESSION_ID);		
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		
		try {
			if(role.getIdRole().equals(Constans.ROLE_ADMIN)){
				lstGroups = groupsService.getAllGroups();
			} else if (role.getIdRole().equals(Constans.ROLE_TEACHER)){
				lstGroups = groupsService.getGroupsByTeacher(id);
			}
		} catch (Exception e) {
			logger.error("Error obteniendo listado de clases para usuario: " + id + ", " + role, e);
		}
		
		model.addObject("lstGroups", lstGroups);
		return model;
	}
	
	/**
	 * Recupera un listado de las asignaturas por clase. 
	 * En caso de que el usuario sea profersor recupera solo sus asignturas de dicha clase
	 * @param group id de clase
	 * @return listado de asignaturas
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/emp/getSubjects/{group}", method = RequestMethod.GET)
	public List<Subject> getSubjects (@PathVariable("group") Integer group, HttpSession session) throws Exception {
		logger.debug("getSubjects: " + group);
		List<Subject> lstSubjects = null;
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		if(role.getIdRole().equals(Constans.ROLE_TEACHER)){
			Integer teacher = (Integer) session.getAttribute(Constans.SESSION_ID);
			lstSubjects = groupsService.getSubjectsByGroupAndTeacher(group, teacher);
		} else {
			lstSubjects =  groupsService.getSubjectsByGroup(group);
		}
		
		return lstSubjects;
	}
	
	/**
	 * Devuelve un listado de los alumnos por clase
	 * @param group clase
	 * @return listado de alumnos
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/emp/getStudents/{group}", method = RequestMethod.GET)
	public List<Student> getStudents (@PathVariable("group") Integer group) throws Exception {
		logger.debug("getStudents: " + group);
		return usersService.getStudensByGroup(group);
	}
	
	/**
	 * Obtiene un listado de califiaciones por clase y asignatura
	 * @param group id de clase
	 * @param subject id de asignatura
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/emp/getQualificationsGroup/{group}/{subject}", method = RequestMethod.GET)
	public List<Qualification> getQualificationsGroup (@PathVariable("group") Integer group, @PathVariable("subject") Integer subject) throws Exception {
		logger.debug("getQualificationsGroup: " + group + ", " + subject);
		return qualificationService.getQualificationsByGroupSubject(group, subject);
	}
	
	/**
	 * Inserta/actualiza calificaciones
	 * @param lstQualification calificaciones
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/emp/saveQualification", method = RequestMethod.POST)
	public String saveQualification(@RequestBody List<Qualification> lstQualification, Locale locale) throws Exception {
		logger.debug("saveQualification: " + lstQualification);
		String response = "";
		try {
			qualificationService.upsert(lstQualification);
			response = messageSource.getMessage("qualifications.save.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando calificaciones " + lstQualification, e);
			response = messageSource.getMessage("qualifications.save.error", null, locale);
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/getQualificationsStudent/{studentId}", method = RequestMethod.GET)
	public List<SubjectQualification> getQualificationsStudent (@PathVariable("studentId") Integer studentId) throws Exception {
		logger.debug("getQualificationsStudent: " + studentId );
		List<SubjectQualification> lstSubjectQualification = new ArrayList<SubjectQualification>();
		Student student = usersService.getById(studentId, Student.class);
		if(student.getGroup() != null){
			Map<Integer, Qualification> mapQualifications = qualificationService.getQualificationsByStudent(studentId);
			List<Subject> lstSubjects = groupsService.getSubjectsByGroup(student.getGroup());
			for(Subject subject : lstSubjects){
				SubjectQualification subjectQ = null;
				if(mapQualifications.containsKey(subject.getId())){
					subjectQ =  new SubjectQualification(subject, mapQualifications.get(subject.getId()));
				} else {
					subjectQ =  new SubjectQualification(subject, new Qualification());
				}
				lstSubjectQualification.add(subjectQ);
			}
		}
		return lstSubjectQualification;
	}
	
}
