/**
 * 
 */
package es.uned.lsi.pfg.dao.news;

import java.util.List;

import es.uned.lsi.pfg.model.Comment;

/**
 * Repositorio de comentarios
 * @author Carlos Navalon Urrea
 *
 */
public interface CommentsDAO {
	
	/**
	 * Recupera un listado de comentarios por noticia
	 * @param news id de noticia
	 * @return listado de comentarios ordenado por fecha ascendente
	 */
	public List<Comment> findCommentByNews(Integer news);
	
	/**
	 * Recupera un comentario por id
	 * @param id id de comentario
	 * @return comentario
	 */
	public Comment findCommentById(Integer id);
	
	/**
	 * Inserta/actualiza un comentario
	 * @param comment comentario
	 * @return comentario actualizado
	 */
	public Comment upsert(Comment comment);

}
