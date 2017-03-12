/**
 * 
 */
package es.uned.lsi.pfg.service.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.lsi.pfg.dao.users.RolesDAO;
import es.uned.lsi.pfg.model.Role;

/**
 * Implementacion del servicio de perfiles
 * @author Carlos Navalon Urrea
 */
@Service
public class RolesServiceImpl implements RolesService {

	private static final Logger logger = LoggerFactory.getLogger(RolesServiceImpl.class);
	
	@Autowired
	private RolesDAO rolesDAO;
	
	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.service.RolesService#getAllRoles()
	 */
	@Override
	public List<Role> getAllRoles() {
		logger.debug("getAllRoles");
		return rolesDAO.findAll();
	}

	@Override
	public Role getRole(String idRole) {
		logger.debug("getRole:" + idRole);
		return rolesDAO.findById(idRole);
	}

}
