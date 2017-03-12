/**
 * 
 */
package es.uned.lsi.pfg.service.courses;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.lsi.pfg.dao.courses.CoursesDAO;
import es.uned.lsi.pfg.model.Course;

/**
 * Implementacion del servicio de cursos
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class CoursesServiceImpl implements CoursesService {

	private static final Logger logger = LoggerFactory.getLogger(CoursesServiceImpl.class);
	
	@Autowired
	private CoursesDAO coursesDAO;
	
	@Override
	public List<Course> getAllCourses() {
		logger.debug("getAllCourses");
		return coursesDAO.findAll();
	}

	@Override
	public Course getCourse(String idCourse) {
		logger.debug("getCourse: " + idCourse);
		return coursesDAO.findById(idCourse);
	}

}
