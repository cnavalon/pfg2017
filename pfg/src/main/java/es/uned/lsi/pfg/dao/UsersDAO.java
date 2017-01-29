package es.uned.lsi.pfg.dao;

import es.uned.lsi.pfg.model.User;
/**
 * @author Carlos Navalon Urrea
 * Repositorio de usuarios
 */
public interface UsersDAO {
	
	/**
	 * Obtiene un usuario por su id
	 * @param id id del usuario
	 * @return el usuario
	 */
	public User findUser(String id); 
	
}
