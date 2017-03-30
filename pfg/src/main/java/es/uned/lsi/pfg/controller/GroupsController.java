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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Group;
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
	@RequestMapping(value="/adm/groups", method = RequestMethod.GET)
	public ModelAndView getGroups(Locale locale) throws Exception {
		logger.debug("getGroups");
		
		ModelAndView model = new ModelAndView("groups");
		model.addObject("title", messageSource.getMessage("group.title.new", null, locale));
		
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
	 * Obtiene la página de nueva clase
	 * @return la página de nueva clase
	 * @throws Exception
	 */
	@RequestMapping(value="/adm/newGroup", method = RequestMethod.GET)
	public ModelAndView newGroup() throws Exception {
		logger.debug("newGroup");
		
		ModelAndView model = new ModelAndView("groupForm");
		model.addObject("group", new Group());
		model.addObject("lstCourses", groupsService.getAllCourses());
		model.addObject("lstTeachers", usersService.search(new UserSearch(null, Constans.ROLE_TEACHER, null, null, null, null)));
		
		
		return model;
	}
	
	/**
	 * Obtiene un listado de alumnos por curso y por clase o sin clase asignada
	 * @param course id de curso
	 * @param letter letra de clase
	 * @return listado de alumnos por curso y por clase o sin clase
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/searchStudents", method = RequestMethod.POST)
	public List<UserSearch> searchStudents (@RequestParam Integer course, @RequestParam String letter) throws Exception {
		logger.debug("searchStudents: " + course + ", " + letter );
		try {
			Group group = groupsService.getGroupByCourseAndLetter(course, letter);
			Integer groupId = group == null? null : group.getId();
			List<UserSearch> lstStudents = usersService.getStudensToAddGroup(course, groupId);
			return lstStudents;
		} catch (Exception e) {
			logger.error("ERROR recuperando listado de alumnos por clase: " + course + "," + letter, e);
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/adm/save", method = RequestMethod.POST)
	public String saveGroup(@RequestParam("course") String course, @RequestParam("letter") String letter,@RequestParam("tutor") String tutor) throws Exception {
		logger.debug("saveGroup");
		
		System.out.println(course + letter + tutor);
		
		
		return null;
	}

}
