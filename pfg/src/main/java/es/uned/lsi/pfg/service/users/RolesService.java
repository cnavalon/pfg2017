/**
 * 
 */
package es.uned.lsi.pfg.service.users;

import java.util.List;

import es.uned.lsi.pfg.model.Role;

/**
 * Servicio de perfiles
 * @author Carlos Navalon Urrea
 */
public interface RolesService {

	/**
	 * Obtiene todos los perfiles
	 * @return todos los perfiles
	 */
	public List<Role> getAllRoles();
	
	/**
	 * Obtiene un perfil por id 
	 * @param idRole id 
	 * @return el perfil
	 */
	public Role getRole(String idRole);
}
