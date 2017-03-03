/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Teacher;

/**
 * Implementacion de repositorio de profesores
 * @author Carlos Navalon Urrea
 */
public class TeacherDAOImpl extends AbstractJpaDao implements TeacherDAO {
	private static final Logger logger = LoggerFactory.getLogger(TeacherDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.TeacherDAO#findTeacher(java.lang.String)
	 */
	@Override
	public Teacher findTeacher(String id) {
		logger.debug("findTeacher:" + id);
		
		Teacher teacher = null;
		try {
			teacher =  em.createNamedQuery(Teacher.Q_FIND_BY_ID, Teacher.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			logger.error("Error recuperando el profedor " + id, e);
		}
		return teacher;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.TeacherDAO#findAllTeachers()
	 */
	@Override
	public List<Teacher> findAllTeachers() {
		logger.debug("findAllTeachers");
		
		List<Teacher> lstTeachers = null;
		try {
			lstTeachers =  em.createNamedQuery(Teacher.Q_FIND_ALL, Teacher.class)
					.getResultList();
		} catch (Exception e) {
			logger.error("Error recuperando todos los profesores", e);
		}
		return lstTeachers;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.TeacherDAO#upsert(es.uned.lsi.pfg.model.Teacher)
	 */
	@Override
	public boolean upsert(Teacher teacher) {
		logger.debug("upsert: " + teacher);
		try {
			em.merge(teacher);
			em.flush();
			return true;
		} catch (Exception e) {
			logger.error("Error insertando profesor en BBDD: " + teacher, e);
		}
		return false;
	}

}
