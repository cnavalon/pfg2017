/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Admin;

/**
 * Implementación del repositorio de administradores
 * @author Carlos Navalon Urrea
 */
public class AdminDAOImpl extends AbstractJpaDao implements AdminDAO {

	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.AdminDAO#findAdmin(java.lang.String)
	 */
	@Override
	public Admin findAdmin(String id) {
		logger.debug("findAdmin:" + id);
		
		Admin admin = null;
		try {
			admin =  em.createNamedQuery(Admin.Q_FIND_BY_ID, Admin.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			logger.error("Error recuperando el adminsitrador " + id, e);
		}
		return admin;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.AdminDAO#findAllAdmins()
	 */
	@Override
	public List<Admin> findAllAdmins() {
		logger.debug("findAllAdmins");
		
		List<Admin> lstAdmins = null;
		try {
			lstAdmins =  em.createNamedQuery(Admin.Q_FIND_ALL, Admin.class)
					.getResultList();
		} catch (Exception e) {
			logger.error("Error recuperando todos los administradores", e);
		}
		return lstAdmins;
	}

	@Override
	public Admin findAdminByIdUser(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.dao.AdminDAO#upsert(es.uned.lsi.pfg.model.Admin)
	 */
	@Override
	public boolean upsert(Admin admin) {
		logger.debug("upsert: " + admin);
		try {
			em.merge(admin);
			em.flush();
			return true;
		} catch (Exception e) {
			logger.error("Error insertando administrador en BBDD: " + admin, e);
		}
		return false;
	}


}
