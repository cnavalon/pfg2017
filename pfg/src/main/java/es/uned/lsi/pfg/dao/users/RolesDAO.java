/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import es.uned.lsi.pfg.model.Role;

/**
 * Repositorio de perfiles.
 * @author Carlos Navalon Urrea
 */
public interface RolesDAO {

	/**
	 * Devuelve todos los perfiles
	 * @return todos los perfiles
	 */
	public List<Role> findAll();

	/**
	 * Obtiene los perfiles por ids
	 * @param lstIds lista de ids de perfiles
	 * @return los perfiles 
	 */
	public List<Role> findByListIds(List<String> lstIds);
}
