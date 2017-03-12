package es.uned.lsi.pfg.dao.users;

import es.uned.lsi.pfg.model.User;
/**
 * Repositorio de usuarios
 * @author Carlos Navalon Urrea
 */
public interface UsersDAO {
	
	/**
	 * Obtiene un usuario por su id
	 * @param id id del usuario
	 * @return el usuario
	 */
	public User findUser(String id);

	/**
	 * Obtiene todos los usuarios 
	 * @return lista de usuarios 
	 */
//	public List<User> findAllUsers();
	
	/**
	 * Inserta o actualiza un usuario en BBDD
	 * @param user el usuario
	 * @return <code>true</code> si la operacion se ha realizado correctamente, <code>false</code> en caso contrario
	 */
	public boolean upsert(User user); 
	
	
}
