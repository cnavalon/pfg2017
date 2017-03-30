/**
 * 
 */
package es.uned.lsi.pfg.dao.groups;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Course;

/**
 * Implementacion de repositorio de cursos
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class CoursesDAOImpl extends AbstractJpaDao implements CoursesDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(CoursesDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.courses.CoursesDAO#findAll()
	 */
	@Override
	public List<Course> findAll() {
		logger.debug("findAll");
		List<Course> lstCourses = new ArrayList<Course>();
		try {
			lstCourses = em.createNamedQuery(Course.Q_FIND_ALL, Course.class).getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando todos los cursos", e);
			return null;
		}
		return lstCourses;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.courses.CoursesDAO#findById(java.lang.String)
	 */
	@Override
	public Course findById(Integer idCourse) {
		logger.debug("findById: " + idCourse);
		try {
			return em.createNamedQuery(Course.Q_FIND_BY_ID, Course.class)
					.setParameter("id", idCourse)
					.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando curso: " + idCourse, e);
		}
		return null;
	}

}
