/**
 * 
 */
package es.uned.lsi.pfg.service;


/**
 * @author Carlos Navalon Urrea
 * Servicio de usuarios
 */
public interface UsersService {
	/**
	 * Obtiene el nombre completo de usuario (nombre y apellidos)
	 * @param id: id del usuario
	 * @return el nombre completo
	 */
	public String getFullName(String id);
}
