/**
 * 
 */
package es.uned.lsi.pfg.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.GroupForm;
import es.uned.lsi.pfg.model.Option;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.model.UserSearch;
import es.uned.lsi.pfg.service.groups.GroupsService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de clases
 * @author Carlos Navalon Urrea
 *
 */
@Controller
@RequestMapping("/groups")
public class GroupsController {
	
	private static final Logger logger = LoggerFactory.getLogger(GroupsController.class);
	
	@Autowired
	private GroupsService groupsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
    private MessageSource messageSource;
	
	/**
	 * Obtiene la pagina de listado de clases
	 * @return la pagina de listado de clases
	 * @throws Exception
	 */
	@RequestMapping(value="/emp/groups", method = RequestMethod.GET)
	public ModelAndView getGroups() throws Exception {
		logger.debug("getGroups");
		
		ModelAndView model = new ModelAndView("groups");
		
		List<Group> lstGroups = groupsService.getAllGroups();
		model.addObject("lstGroups",  lstGroups);
		
		List<Integer> lstGroupIds = new ArrayList<Integer>();
		for(Group group : lstGroups){
			lstGroupIds.add(group.getId());
		}
		model.addObject("mapGroupCount",usersService.getMapGroupCount(lstGroupIds));
		
		return model;
	}
	
	/**
	 * Obtiene la pagina de nueva clase
	 * @return la pagina de nueva clase
	 * @throws Exception
	 */
	@RequestMapping(value="/adm/newGroup", method = RequestMethod.GET)
	public ModelAndView newGroup(Locale locale) throws Exception {
		logger.debug("newGroup");
		return getGroupForm(locale, "group.title.new", new GroupForm(), true);
	}
	
	/**
	 * Obtiene la pagina de editar clase
	 * @param idGroup clase
	 * @param locale
	 * @return la pagina de editar clase
	 * @throws Exception
	 */
	@RequestMapping(value="/adm/editGroup/{idGroup}", method = RequestMethod.GET)
	public ModelAndView editGroup(@PathVariable("idGroup") Integer idGroup, Locale locale) throws Exception {
		logger.debug("editGroup: " + idGroup);
		Group group = groupsService.getGroup(idGroup);
		if(group.getLetter() != null && group.getLetter().equals(Constans.NO_LETTER))
			group.setLetter("");
		return getGroupForm(locale, "group.title.edit", new GroupForm(group), false);
	}

	/**
	 * Obtiene el formulario de clase
	 * @param locale
	 * @param title titulo
	 * @param group clase
	 * @return el formulario de clase
	 */
	private ModelAndView getGroupForm(Locale locale, String title, GroupForm group, boolean isNew) {
		ModelAndView model = new ModelAndView("groupForm");
		model.addObject("title", messageSource.getMessage(title, null, locale));
		model.addObject("group", group);
		model.addObject("isNew", isNew);
		model.addObject("lstCourses", groupsService.getAllCourses());
		model.addObject("lstTeachers", usersService.search(new UserSearch(null, Constans.ROLE_TEACHER, null, null, null, null)));
		
		
		return model;
	}
	
	/**
	 * Elimna una clase
	 * @param idGroup clase
	 * @param locale
	 * @return mensaje de resultado de la operacion
	 * @throws Exception
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value="/adm/deleteGroup/{idGroup}", method = RequestMethod.GET)
	public String deleteGroup(@PathVariable("idGroup") Integer idGroup, Locale locale) throws Exception {
		logger.debug("deleteGroup: " + idGroup);
		String error = "";
		try {
			usersService.deleteStudentsGroup(idGroup);
			//TODO borrar horario
			groupsService.deleteGroup(idGroup);
			error = messageSource.getMessage("group.deleted", null, locale);
		} catch (Exception e) {
			logger.error("Error deleting group: " + idGroup, e);
			error = messageSource.getMessage("group.delete.error", null, locale);
		}
		return error;
	}
	
	/**
	 * Obtiene un modal de vista de clase
	 * @param idGroup id de clase
	 * @param locale
	 * @return modal de vista de clase
	 * @throws Exception
	 */
	@RequestMapping(value="/emp/viewGroup/{idGroup}", method = RequestMethod.GET)
	public ModelAndView viewGroup(@PathVariable("idGroup") Integer idGroup, Locale locale) throws Exception {
		logger.debug("viewGroup: " + idGroup);
		
		ModelAndView model = new ModelAndView("viewGroup");
		model.addObject("group", groupsService.getGroup(idGroup));
		model.addObject("lstStudents", usersService.getStudensByGroup(idGroup));
		
		return model;
	}
	
//	/**
//	 * Obtiene un listado de alumnos por curso y por clase o sin clase asignada
//	 * @param course id de curso
//	 * @param letter letra de clase
//	 * @return listado de alumnos por curso y por clase o sin clase
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping(value="/adm/searchStudents", method = RequestMethod.POST)
//	public List<UserSearch> searchStudents (@RequestParam Integer course, @RequestParam String letter) throws Exception {
//		logger.debug("searchStudents: " + course + ", " + letter );
//		try {
//			Group group = groupsService.getGroupByCourseAndLetter(course, letter);
//			Integer groupId = group == null? null : group.getId();
//			return usersService.getStudensToAddGroup(course, groupId);
//		} catch (Exception e) {
//			logger.error("ERROR recuperando listado de alumnos por clase: " + course + "," + letter, e);
//		}
//		return null;
//	}
	/**
	 * Obtiene un listado de alumnos sin clase asignada para un curso
	 * @param course curso 
	 * @return istado de alumnos sin clase asignada
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/searchFreeStudents/{course}", method = RequestMethod.GET)
	public List<Option> searchFreeStudents (@PathVariable("course") Integer course) throws Exception {
		logger.debug("searchFreeStudents: " + course );
		List<Option> lstOptions = new ArrayList<Option>();
		List<Student> lstStudents = usersService.getFreeStudensByCourse(course);
		for(Student student : lstStudents){
			lstOptions.add(new Option(Integer.toString(student.getId()), student.getSurname1() + " " + student.getSurname2() + ", " + student.getName() + " (" + student.getId() + ")"));
		}
		return lstOptions;
		 
	}
	
	/**
	 * Obtiene un listado de los alumnos por curso y letra de clase
	 * @param course curso
	 * @param letter letra de clase
	 * @return listado de alumnos
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/searchSelectedStudents", method = RequestMethod.POST)
	public List<Option> searchSelectedStudents (@RequestParam Integer course, @RequestParam String letter) throws Exception {
		logger.debug("searchSelectedStudents: " + course + ", " + letter);
		List<Option> lstOptions = new ArrayList<Option>();
		Group group = groupsService.getGroupByCourseAndLetter(course, letter);
		if(group != null){
			List<Student> lstStudents = usersService.getStudensByGroup(group.getId());
			for(Student student : lstStudents){
				lstOptions.add(new Option(Integer.toString(student.getId()), student.getSurname1() + " " + student.getSurname2() + ", " + student.getName() + " (" + student.getId() + ")"));
			}
		}
		return lstOptions;
		 
	}
	/**
	 * Valida la combinación curso y letra para una clase
	 * @param course curso
	 * @param letter letra
	 * @param locale
	 * @return mensaje de error si existe, en caso contrario cadena vacia
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/validLetter", method = RequestMethod.POST)
	public String validLetter (@RequestParam Integer course, @RequestParam String letter, Locale locale) throws Exception {
		logger.debug("validLetter: " + course + ", " + letter );
		String error = "";
		if(groupsService.getGroupByCourseAndLetter(course, letter) != null){
			error = messageSource.getMessage("group.exist", null, locale);
		}
		return error;
	}
	
	/**
	 * Inserta/actualiza una clase
	 * @param groupForm formulario de clase
	 * @param locale
	 * @return mensaje de error si existe, en caso contrario cadena vacia
	 * @throws Exception
	 */
	@Transactional
	@ResponseBody
	@RequestMapping(value="/adm/save", method = RequestMethod.POST)
	public String saveGroup(@ModelAttribute GroupForm groupForm, Locale locale) throws Exception {
		logger.debug("saveGroup: " + groupForm);
		String error = "";
		try {
			Integer idGroup = groupsService.saveGroup(new Group(groupForm));
			if(!groupForm.isSkipStudents()){
				usersService.saveStudentsGroup(idGroup, groupForm.getLstStudents());
			}
			if(!groupForm.isSkipSchedule()){
//				groupsService.saveSchedule(idGroup, groupForm.getFile());
			}
		} catch (Exception e) {
			logger.error("Error saving group: " + groupForm, e);
			error = messageSource.getMessage("group.save.error", null, locale);
			error += "<br><br>" + e.getMessage();
		}
		return error;
	}
}
