/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Parent;

/**
 * Implementacion de repositorio de padres
 * @author Carlos Navalon Urrea
 */
public class ParentDAOImpl extends AbstractJpaDao implements ParentDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ParentDAOImpl.class);

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.users.ParentDAO#findParent(java.lang.String)
	 */
	@Override
	public Parent findParent(String id) {
		logger.debug("findParent:" + id);
		
		Parent parent = null;
		try {
			parent =  em.createNamedQuery(Parent.Q_FIND_BY_ID, Parent.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			logger.error("Error recuperando el padre " + id, e);
		}
		return parent;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.users.ParentDAO#findAllAdmins()
	 */
	@Override
	public List<Parent> findAllParents() {
		logger.debug("findAllParents");
		
		List<Parent> lstParents = null;
		try {
			lstParents =  em.createNamedQuery(Parent.Q_FIND_ALL, Parent.class)
					.getResultList();
		} catch (Exception e) {
			logger.error("Error recuperando todos los padres", e);
		}
		return lstParents;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.users.ParentDAO#upsert(es.uned.lsi.pfg.model.Parent)
	 */
	@Override
	public boolean upsert(Parent parent) {
		logger.debug("upsert: " + parent);
		try {
			em.merge(parent);
			em.flush();
			return true;
		} catch (Exception e) {
			logger.error("Error insertando padre en BBDD: " + parent, e);
		}
		return false;
	}

}
