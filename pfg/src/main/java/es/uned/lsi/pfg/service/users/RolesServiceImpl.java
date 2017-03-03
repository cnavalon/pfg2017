/**
 * 
 */
package es.uned.lsi.pfg.service.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.lsi.pfg.dao.users.RolesDAO;
import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Implementacion del servicio de perfiles
 * @author Carlos Navalon Urrea
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

	@Override
	public List<Role> getEmployeeRoles() {
		List<String> lstIds = new ArrayList<String>();
		lstIds.add(Constans.ROLE_ADMIN);
		lstIds.add(Constans.ROLE_TEACHER);
		return rolesDAO.findByListIds(lstIds);
	}

}
