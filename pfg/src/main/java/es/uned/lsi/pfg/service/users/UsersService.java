/**
 * 
 */
package es.uned.lsi.pfg.service.users;

import java.util.List;

import es.uned.lsi.pfg.model.User;
import es.uned.lsi.pfg.model.UserSearch;

/**
 * Servicio de usuarios
 * @author Carlos Navalon Urrea
 */
public interface UsersService {
	/**
	 * Obtiene el nombre completo de usuario (nombre y apellidos) por id de usuario y perfil
	 * @param id id de usuario
	 * @param idRole perfil de usuario
	 * @return nombre completo de usuario
	 */
	public String getFullName(String id, String idRole);

	/**
	 * Obtiene un usuario por id
	 * @param idUser id de usuario
	 * @return el usuario
	 */
	public User getUser(String idUser);
	
	/**
	 * Comprueba si ya existe un usuario con ese id
	 * @param idUser id de usuario
	 * @return <code>true</code> si ya existe usuario, en otro caso <code>false</code>
	 */
	public boolean existsUser(String idUser);
	
	/**
	 * Guarda un usuario
	 * @param user el usuario
	 * @return <code>true</code> si se ha insertado correctamente, en otro caso <code>false</code>
	 */
	public boolean save(User user);

	/**
	 * Eliminia un usuario
	 * @param id ID del usuario
	 * @param idRole ID perfil 
	 * @return <code>true</code> si la operación se ha realizado con éxito, en caso contrario <code>false</code>
	 */
	public boolean delete(Integer id, String idRole);

	/**
	 * Busca los usuarios que coinciden con los datos de usuario pasados como parametro
	 * @param userSearch datos de usuario
	 * @return listado de los usuarios
	 */
	public List<UserSearch> search(UserSearch userSearch);




	
}
