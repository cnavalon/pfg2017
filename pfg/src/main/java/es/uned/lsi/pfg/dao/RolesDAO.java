/**
 * 
 */
package es.uned.lsi.pfg.dao;

import java.util.List;

import es.uned.lsi.pfg.model.Role;

/**
 * @author Carlos Navalon Urrea
 * Repositorio de perfiles.
 */
public interface RolesDAO {

	/**
	 * Devuelve todos los perfiles
	 * @return todos los perfiles
	 */
	List<Role> findAll();
}
