package es.uned.lsi.pfg.dao.users;

import java.util.List;

import es.uned.lsi.pfg.model.Person;
import es.uned.lsi.pfg.model.UserSearch;
/**
 * Repositorio de personas
 * @author Carlos Navalon Urrea
 */
public interface PersonDAO {
	
	/**
	 * Busca una persona por su id y en funcion de su perfil
	 * @param id id de la persona
	 * @param classPerson clase de la persona segun su perfil
	 * @return la persona de la clase buscada
	 */
	public <T extends Person> T find(Integer id, Class<T> classPerson);

	/**
	 * Obtiene un listado de todas las personas en base a su perfil
	 * @param classPerson clase de la persona segun su perfil
	 * @return un listado con todas las personas de la clase buscada
	 */
	public <T extends Person> List<T> findAll(Class<T> classPerson);
	
	/**
	 * Busca una persona por su id de usuario y en funcion de su perfil
	 * @param idUser id de usuario
	 * @param classPerson clase de la persona segun su perfil
	 * @return la persona de la clase buscada
	 */
	public <T extends Person> T findByIdUser(String idUser, Class<T> classPerson);

	
	/**
	 * Inserta o actualiza una persona
	 * @param person persona
	 * @throws Exception
	 */
	public <T extends Person> void upsert(T person) throws Exception;

	/**
	 * Busca personas que coincidan con los datos de busqueda para una clase determinada
	 * @param userSearch datos de busqueda
	 * @param classPerson clase de persona segun su perfil
	 * @return listado de todas las personas de la clase buscada que coincidan con los datos de busqueda
	 */
	public <T extends Person> List<T> searchUsers(UserSearch userSearch, Class<T> classPerson);

	/**
	 * Obtiene un listado de todas las personas en base a su perfil
	 * @param classPerson clase de la persona segun su perfil
	 * @return un listado con todas las personas de la clase buscada
	 */
	public <T extends Person> List<T> findAllHistoric(Class<T> classPerson); 
	
	
}
