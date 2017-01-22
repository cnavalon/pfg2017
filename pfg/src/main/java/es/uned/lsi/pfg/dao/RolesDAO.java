/**
 * 
 */
package es.uned.lsi.pfg.dao;

import java.util.List;

import es.uned.lsi.pfg.model.Role;

/**
 * @author Carlos Navalon Urrea
 *
 */
public interface RolesDAO {

	List<Role> findAll();
}
