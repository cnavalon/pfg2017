/**
 * 
 */
package es.uned.lsi.pfg.service.schoolCanteen;

import java.util.List;

import es.uned.lsi.pfg.model.DailyMenu;
import es.uned.lsi.pfg.model.Menu;
import es.uned.lsi.pfg.model.MenuType;

/**
 * Servicio de menus
 * @author Carlos Navalon Urrea
 *
 */
public interface MenuService {

	/**
	 * Obtiene un listado de los menus diarios de un mes
	 * @param month mes
	 * @return listado de los menus diarios de un mes
	 */
	public DailyMenu[] getMenuByMonth(Integer month);
	
	/**
	 * Obtiene un listado de todos los tipos de menu ordenados
	 * @return listado de todos los tipos de menu ordenados
	 */
	public List<MenuType> getAllMenuTypes();

	/**
	 * Inserta/actualiza/elimina un listado de menus
	 * @param lstMenus listado de menus
	 */
	public void save(List<Menu> lstMenus);

	/**
	 * Eliminia un menu diario
	 * @param month mes
	 * @param day dia
	 */
	public void deleteDailyMenu(Integer month, Integer day);

}
