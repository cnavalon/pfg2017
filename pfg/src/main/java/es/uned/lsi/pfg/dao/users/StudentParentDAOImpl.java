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
import es.uned.lsi.pfg.model.StudentParent;

/**
 * Implementacion del repositorio de la tabla relacional student_parent
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class StudentParentDAOImpl extends AbstractJpaDao implements StudentParentDAO {
	private static final Logger logger = LoggerFactory.getLogger(StudentParentDAOImpl.class);

	@Override
	public void insert(StudentParent studentParent) throws Exception {
		logger.debug("insert: " + studentParent);
		try {
			em.persist(studentParent);
		} catch (Exception e) {
			logger.error("ERROR insertando relacion alumno padre: " + studentParent+ ". " + e.getMessage());
			throw e;
		}
	}

	@Override
	public List<StudentParent> findByStudent(Integer student) {
		logger.debug("findByStudent: " + student);
		List<StudentParent> lstStudentParent = new ArrayList<StudentParent>();
		try {
			lstStudentParent = em.createQuery("SELECT x FROM StudentParent x WHERE x.student = " + student).getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error obteniendo listado de padres para " + student, e);
			return null;
		}
		return lstStudentParent;
	}
	
	@Override
	public List<StudentParent> findByParent(Integer parent) {
		logger.debug("findByParent: " + parent);
		List<StudentParent> lstStudentParent = new ArrayList<StudentParent>();
		try {
			lstStudentParent = em.createQuery("SELECT x FROM StudentParent x WHERE x.parent = " + parent).getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error obteniendo listado de padres para " + parent, e);
			return null;
		}
		return lstStudentParent;
	}

	@Override
	public void remove(StudentParent studentParent) throws Exception {
		logger.debug("remove: " + studentParent);
		try {
			em.remove(studentParent);
		} catch (Exception e) {
			logger.error("ERROR eliminado relacion " + studentParent + ": " + e.getMessage());
			throw e;
		}
	}
	
	
}
