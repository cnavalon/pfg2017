/**
 * 
 */
package es.uned.lsi.pfg.service;

import java.util.List;

import es.uned.lsi.pfg.model.Role;

/**
 * @author Carlos Navalon Urrea
 * Servicio de perfiles
 */
public interface RolesService {

	/**
	 * Obtiene todos los perfiles
	 * @return todos los perfiles
	 */
	public List<Role> getAllRoles();
	
}
