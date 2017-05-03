/**
 * 
 */
package es.uned.lsi.pfg.dao.schoolCanteen;

import java.util.List;

import es.uned.lsi.pfg.model.MenuType;

/**
 * Repositorio de tipos de menu
 * @author Carlos Navalon Urrea
 *
 */
public interface MenuTypeDAO {
	
	/**
	 * Recupera un listado de todos los tipos de menu
	 * @return listado de todos los tipos de menu
	 */
	public List<MenuType> findAll();

}
