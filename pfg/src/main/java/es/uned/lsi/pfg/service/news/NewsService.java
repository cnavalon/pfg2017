/**
 * 
 */
package es.uned.lsi.pfg.service.news;

import java.util.List;

import es.uned.lsi.pfg.model.Comment;
import es.uned.lsi.pfg.model.News;

/**
 * Servicio de noticias
 * @author Carlos Navalon Urrea
 *
 */
public interface NewsService {
	
	/**
	 * Recupera un listado de noticias por clase y asignatura paginado
	 * @param group id de clase
	 * @param subject id de asignatura
	 * @param page numero de pagina
	 * @return listado de noticias
	 */
	public List<News> getNews(Integer group, Integer subject, Integer page);
	
	/**
	 * Inserta/actualiza una noticia
	 * @param news noticia
	 */
	public void upsertNews(News news);
	
	/**
	 * Elimina una noticia
	 * @param idNews id de noticia
	 */
	public void deleteNews(Integer idNews);

	/**
	 * Recupera un listado de comentarios por noticia
	 * @param news id de noticia
	 * @return listado de comentarios
	 */
	public List<Comment> getComments(Integer news);
	
	/**
	 * Inserta/actualiza un comentario
	 * @param comment comentario
	 */
	public void upsertComment(Comment comment);
	
	/**
	 * Eliminar un comentario
	 * @param idComment id del comentario
	 */
	public void deleteComment(Integer idComment);
	
	/**
	 * Devuelve el numero de noticias de una clase y asignatura
	 * @param group id de clase
	 * @param subject id de asingatura
	 * @return el numero de noticias de la clase y asignatura
	 */
	public Long countNews(Integer group, Integer subject);

	/**
	 * Devuelve una noticia por id
	 * @param idNew id de noticia
	 * @return noticia
	 */
	public News getNews(Integer idNew);
	
	/**
	 * Devuelve un comentario por id
	 * @param idComment id de comentario
	 * @return idComment
	 */
	public Comment getComment(Integer idComment);
}
