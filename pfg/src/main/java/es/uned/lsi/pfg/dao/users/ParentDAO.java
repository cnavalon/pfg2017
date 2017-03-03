/**
 * 
 */
package es.uned.lsi.pfg.dao.users;

import java.util.List;

import es.uned.lsi.pfg.model.Parent;

/**
 * Repositorio de padres
 * @author Carlos Navalon Urrea
 */
public interface ParentDAO {
	/**
	 * Obtiene un padre por su id
	 * @param id id del padre
	 * @return el padre
	 */
	public Parent findParent(String id);

	/**
	 * Obtiene todos los padres
	 * @return lista todos los padres
	 */
	public List<Parent> findAllParents();

	/**
	 * Inserta o actualiza un padre
	 * @param parent padre
	 * @return <code>true</code> si la operacion se ha realizado correctamente, <code>false</code> en caso contrario
	 */
	public boolean upsert(Parent parent); 
}
