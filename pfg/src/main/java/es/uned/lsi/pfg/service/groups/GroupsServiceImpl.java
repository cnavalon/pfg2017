/**
 * 
 */
package es.uned.lsi.pfg.service.groups;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.lsi.pfg.dao.groups.CoursesDAO;
import es.uned.lsi.pfg.dao.groups.GroupsDAO;
import es.uned.lsi.pfg.model.Course;
import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Implementacion del servicio de clases
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class GroupsServiceImpl implements GroupsService {

	private static final Logger logger = LoggerFactory.getLogger(GroupsServiceImpl.class);
	
	@Autowired
	private CoursesDAO coursesDAO;
	
	@Autowired
	private GroupsDAO groupsDAO;
	
	@Override
	public List<Course> getAllCourses() {
		logger.debug("getAllCourses");
		return coursesDAO.findAll();
	}

	@Override
	public Course getCourse(Integer idCourse) {
		logger.debug("getCourse: " + idCourse);
		return coursesDAO.findById(idCourse);
	}

	@Override
	public List<Group> getAllGroups() {
		logger.debug("getAllGroups");
		return groupsDAO.findAll();
	}

	@Override
	public Group getGroup(Integer group) {
		logger.debug("getGroup: " + group);
		return groupsDAO.findById(group);
	}
	
	@Override
	public Group getGroupByCourseAndLetter(Integer course, String letter) {
		logger.debug("getGroupByCourseAndLetter: " + course + ", " + letter);
		if(letter == null || letter.equals(""))
			letter = Constans.NO_LETTER;
		return groupsDAO.findByCourseAndLetter(course,letter);
	}

	@Override
	public List<Group> getGroupsByCourse(Integer course) {
		logger.debug("getGroupsByCourse: " + course);
		return groupsDAO.findByCourse(course);
	}

	@Override
	public Integer saveGroup(Group group) {
		logger.debug("saveGroup: " + group);
		Group newGroup = groupsDAO.upsert(group);
		return newGroup.getId();
	}

	@Override
	public void deleteGroup(Integer idGroup) {
		logger.debug("deleteGroup: " + idGroup);
		Group group = groupsDAO.findById(idGroup);
		groupsDAO.remove(group);
	}

	

}
