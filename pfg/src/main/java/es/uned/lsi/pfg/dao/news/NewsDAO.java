/**
 * 
 */
package es.uned.lsi.pfg.dao.news;

import java.util.List;

import es.uned.lsi.pfg.model.News;

/**
 * Repositorio de noticias
 * @author Carlos Navalon Urrea
 *
 */
public interface NewsDAO {
	
	/**
	 * Recupera un listado de noticias por clase y asignatura
	 * descartando y limitando el resultado
	 * @param group id de clase
	 * @param subject id de asignatura
	 * @param offset numeros de resigistros iniciales descartados
	 * @param limit numeros de registros a devolver
	 * @return listado de noticias ordenada por fecha descendiente
	 */
	public List<News> findNewsByGroupSubject(Integer group, Integer subject, Integer offset, Integer limit);
	
	/**
	 * Recupera una noticia por id
	 * @param id id de noticia
	 * @return noticia
	 */
	public News findNewsById(Integer id);
	
	/**
	 * Inserta/actualiza una noticia
	 * @param news noticia
	 * @return noticia actualizada
	 */
	public News upsert(News news);

	/**
	 * Devuelve el numero de noticias de una clase y asignatura
	 * @param group id de clase
	 * @param subject id de asingatura
	 * @return el numero de noticias de la clase y asignatura
	 */
	public Long countNews(Integer group, Integer subject);

}
