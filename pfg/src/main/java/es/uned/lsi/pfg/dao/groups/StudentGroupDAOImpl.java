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

/**
 * Implementacion de repositorio alumno-clase
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class StudentGroupDAOImpl extends AbstractJpaDao implements StudentGroupDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(StudentGroupDAOImpl.class);

	@Override
	public Long countStudents(Integer group) {
		logger.debug("countStudents: " + group);
		try {
			return (Long) em.createQuery("SELECT COUNT(x)FROM StudentGroup x WHERE x.group = " + group).getSingleResult();
		} catch (Exception e) {
			logger.error("ERROR " + e.getMessage(),e);
		}
		return null;
	}

	@Override
	public List<Integer> findStundentsByGroup(Integer group) {
		logger.debug("findStundentsByGroup: " + group);
		List<Integer> lstStudents = new ArrayList<Integer>();
		try {
			lstStudents = em.createQuery("SELECT x.student FROM StudentGroup x WHERE x.group = " + group, Integer.class).getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error obteniendo alumnos de clase :" + group + ". " + e.getMessage());
			throw e;
		}
		return lstStudents;
	}

}
