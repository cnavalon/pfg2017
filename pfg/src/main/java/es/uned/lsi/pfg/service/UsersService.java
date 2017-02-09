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
}
