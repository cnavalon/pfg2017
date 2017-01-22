/**
 * 
 */
package es.uned.lsi.pfg.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.model.Role;

/**
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class RolesDAOImpl extends AbstractJpaDao implements RolesDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RolesDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.RolesDAO#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Role> findAll() {
		logger.debug("findAll");
		try {
			return em.createQuery("SELECT r FROM Role r").getResultList();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage(),e);
			return null;
		}
	}
	
	

}
