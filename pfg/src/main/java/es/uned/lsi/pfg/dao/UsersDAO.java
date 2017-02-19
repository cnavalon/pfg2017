package es.uned.lsi.pfg.dao;

import java.util.List;

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

	/**
	 * Obtiene todos los usuarios activos
	 * @return lista de usuarios activos
	 */
	public List<User> findAllUsers();
	
	/**
	 * Inserta o actualiza un usuario en BBDD
	 * @param user el usuario
	 * @return <code>true</code> si la operacion se ha realizado correctamente, <code>false</code> en caso contrario
	 */
	public boolean upsert(User user); 
	
	
}
