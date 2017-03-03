/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Student;

/**
 * Implementacion de repositorio de estudiantes
 * @author Carlos Navalon Urrea
 */
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

}
