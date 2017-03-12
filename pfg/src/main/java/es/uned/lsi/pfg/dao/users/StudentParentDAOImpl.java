/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

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
	public void insert(StudentParent studentParent) {
		logger.debug("insert: " + studentParent);
		try {
			em.persist(studentParent);
		} catch (Exception e) {
			logger.error("ERROR insertando relacion alumno padre: " + studentParent, e);
		}
	}
	
	
}
