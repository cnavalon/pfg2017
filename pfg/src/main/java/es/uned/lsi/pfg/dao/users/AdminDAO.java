package es.uned.lsi.pfg.dao.users;

import java.util.List;

import es.uned.lsi.pfg.model.Admin;
/**
 * Repositorio de administradores
 * @author Carlos Navalon Urrea
 */
public interface AdminDAO {
	
	/**
	 * Obtiene un adminstrador por su id
	 * @param id id del adminstrador
	 * @return el adminstrador
	 */
	public Admin findAdmin(String id);

	/**
	 * Obtiene todos los adminsitradores
	 * @return lista todos los adminsitradores
	 */
	public List<Admin> findAllAdmins();
	
	/**
	 * Obtiene un adminstrador por el id de usuario
	 * @param id id de usuario
	 * @return el adminstrador
	 */
	public Admin findAdminByIdUser(String idUser);

	/**
	 * Inserta o actualiza un administrador
	 * @param admin administrador
	 * @return <code>true</code> si la operacion se ha realizado correctamente, <code>false</code> en caso contrario
	 */
	public boolean upsert(Admin admin); 
	
	
}
