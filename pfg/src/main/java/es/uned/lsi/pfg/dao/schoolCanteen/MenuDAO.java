/**
 * 
 */
package es.uned.lsi.pfg.dao.schoolCanteen;

import java.util.List;

import es.uned.lsi.pfg.model.Menu;

/**
 * Repositorio de menus
 * @author Carlos Navalon Urrea
 *
 */
public interface MenuDAO {

	/**
	 * Recupera un menu por id
	 * @param id id de menu
	 * @return menu
	 */
	public Menu findById(Integer id);
	
	/**
	 * Recupera un listado de menus por mes
	 * @param month mes
	 * @return listado de menus
	 */
	public List<Menu> findByMonth(Integer month);
	
	/**
	 * Recupera un listado de menus por mes y dia
	 * @param month mes
	 * @param day dia
	 * @return listado de menus
	 */
	public List<Menu> findByMonthDay(Integer month, Integer day);

	/**
	 * Recupera un menu por mes, dia, tipo y orden
	 * @param month mes
	 * @param day dia
	 * @param type tipo
	 * @param order orden
	 * @return listado de menus
	 */
	public Menu findByMonthDayTypeOrder(Integer month, Integer day, String type, Integer order);

	/**
	 * Inserta / actualiza un menu
	 * @param menu menu
	 * @return menu actualizado
	 */
	public Menu upsert(Menu menu);
	
	/**
	 * Elimina un menu
	 * @param menu menu
	 */
	public void delete(Menu menu);
	
	/**
	 * Elimina un grupo de menus por mes y dia
	 * @param month mes
	 * @param day dia
	 */
	public void deleteByMonthDay(Integer month, Integer day);
	
}
