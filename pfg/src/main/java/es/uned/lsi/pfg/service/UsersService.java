/**
 * 
 */
package es.uned.lsi.pfg.service;

import java.util.List;

import es.uned.lsi.pfg.model.User;

/**
 * @author Carlos Navalon Urrea
 * Servicio de usuarios
 */
public interface UsersService {
	/**
	 * Obtiene el nombre completo de usuario (nombre y apellidos)
	 * @param id id del usuario
	 * @return el nombre completo
	 */
	public String getFullName(String id);

	/**
	 * Obtiene todos los usuarios activos
	 * @return lista de todos los usuarios activos
	 */
	public List<User> getAllUsers();
	
	/**
	 * Obtiene un usuario por id
	 * @param id id de usuario
	 * @return el usuario
	 */
	public User getUser(String id);
	
	/**
	 * Comprueba si ya existe un usuario con ese id
	 * @param id id de usuario
	 * @return <code>true</code> si ya existe usuario, en otro caso <code>false</code>
	 */
	public boolean existsUser(String id);
	
	/**
	 * Guarda un usuario
	 * @param user el usuario
	 * @return <code>true</code> si se ha insertado correctamente, en otro caso <code>false</code>
	 */
	public boolean save(User user);

	/**
	 * Eliminia un usuario
	 * @param id id del usuario
	 * @return <code>true</code> si la operación se ha realizado con éxito, en caso contrario <code>false</code>
	 */
	public boolean delete(String id);
}
