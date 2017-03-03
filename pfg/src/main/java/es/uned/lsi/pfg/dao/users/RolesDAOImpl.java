/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Role;

/**
 * Implementacion del repositorio de perfiles
 * @author Carlos Navalon Urrea
 */
@Repository
public class RolesDAOImpl extends AbstractJpaDao implements RolesDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(RolesDAOImpl.class);
	
	@Override
	public List<Role> findAll() {
		logger.debug("findAll");
		try {
			return em.createNamedQuery(Role.Q_FIND_ALL, Role.class).getResultList();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage(),e);
			return null;
		}
	}

	@Override
	public List<Role> findByListIds(List<String> lstIds) {
		logger.debug("findAll");
		try {
			return em.createNamedQuery(Role.Q_FIND_BY_LIST_IDS, Role.class)
					.setParameter("listIds", lstIds)
					.getResultList();
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage(),e);
			return null;
		}
	}
	
	

}
