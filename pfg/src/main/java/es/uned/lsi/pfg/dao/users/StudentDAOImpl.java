/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Student;

/**
 * Implementacion de repositorio de estudiantes
 * @author Carlos Navalon Urrea
 */
@Repository
public class StudentDAOImpl extends AbstractJpaDao implements StudentDAO {

	private static final Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.StudentDAO#findStudent(java.lang.String)
	 */
	@Override
	public Student findStudent(String id) {
		logger.debug("findStudent:" + id);
		
		Student student = null;
		try {
			student =  em.createNamedQuery(Student.Q_FIND_BY_ID, Student.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando el estudiante " + id, e);
		}
		return student;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.StudentDAO#findAllStudents()
	 */
	@Override
	public List<Student> findAllStudents() {
		logger.debug("findAllStudents");
		
		List<Student> lstStudents = null;
		try {
			lstStudents =  em.createNamedQuery(Student.Q_FIND_ALL, Student.class)
					.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando todos los estudiantes", e);
		}
		return lstStudents;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.StudentDAO#upsert(es.uned.lsi.pfg.model.Student)
	 */
	@Override
	public boolean upsert(Student student) {
		logger.debug("upsert: " + student);
		try {
			em.merge(student);
			em.flush();
			return true;
		} catch (Exception e) {
			logger.error("Error insertando estudiante en BBDD: " + student, e);
		}
		return false;
	}

	@Override
	public List<Student> findStudentsByCourse(Integer course) {
		logger.debug("findStudentsByCourse: " + course);
		
		List<Student> lstStudents = new ArrayList<Student>();
		try {
			lstStudents =  em.createNamedQuery(Student.Q_FIND_BY_COURSE, Student.class)
					.setParameter("course", course)
					.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando listado de alumnos por curso: " + course, e);
			return null;
		}
		return lstStudents;
	}
	
	@Override
	public List<Student> findStudentsByCourseWithoutGroup(Integer course) {
		logger.debug("findStudentsByCourseWithoutGroup: " + course);
		
		List<Student> lstStudents = new ArrayList<Student>();
		try {
			lstStudents =  em.createNamedQuery(Student.Q_FIND_BY_COURSE_WITHOUT_GROUP, Student.class)
					.setParameter("course", course)
					.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando listado de alumnos por curso sin clase: " + course, e);
			return null;
		}
		return lstStudents;
	}

	@Override
	public Long countStudents(Integer group) {
		logger.debug("countStudents: " + group);
		try {
			return (Long) em.createNamedQuery(Student.Q_COUNT_BY_GROUP)
					.setParameter("group", group)
					.getSingleResult();
		} catch (Exception e) {
			logger.error("ERROR " + e.getMessage(),e);
		}
		return null;
	}

	@Override
	public List<Student> findStundentsByGroup(Integer group) {
		logger.debug("findStundentsByGroup: " + group);
		List<Student> lstStudents = new ArrayList<Student>();
		try {
			lstStudents = em.createNamedQuery(Student.Q_FIND_BY_GROUP, Student.class)
					.setParameter("group", group)
					.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando listado de alumnos por clase :" + group + ". " + e.getMessage());
			throw e;
		}
		return lstStudents;
	}
}
