/**
 * 
 */
package es.uned.lsi.pfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.lsi.pfg.dao.RolesDAO;
import es.uned.lsi.pfg.model.Role;

/**
 * @author Carlos Navalon Urrea
 * Implementacion del servicio de perfiles
 */
@Service
public class RolesServiceImpl implements RolesService {

	@Autowired
	private RolesDAO rolesDAO;
	
	/* (non-Javadoc)
	 * @see es.uned.lsi.pfg.service.RolesService#getAllRoles()
	 */
	@Override
	public List<Role> getAllRoles() {
		return rolesDAO.findAll();
	}

}
