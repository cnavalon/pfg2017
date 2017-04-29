/**
 * 
 */
package es.uned.lsi.pfg.dao.news;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Comment;

/**
 * Implementacion de repositorio de comentarios
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class CommentsDAOImpl extends AbstractJpaDao implements CommentsDAO {

	private static final Logger logger = LoggerFactory.getLogger(CommentsDAOImpl.class);
	
	@Override
	public List<Comment> findCommentByNews(Integer news) {
		logger.debug("findCommentByNews: " + news);
		List<Comment> lstComments = new ArrayList<Comment>();
		try {
			lstComments =  em.createNamedQuery(Comment.Q_FIND_BY_NEWS, Comment.class)
			.setParameter("news", news)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando comentarios por noticia: " + news, e);
			throw e;
		}
		return lstComments;
	}
	
	@Override
	public Comment findCommentById(Integer id) {
		logger.debug("findCommentById: " + id);
		try {
			return em.createNamedQuery(Comment.Q_FIND_BY_ID, Comment.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando comentario: " + id, e);
			throw e;
		}
	}

	@Override
	public Comment upsert(Comment comment) {
		logger.debug("upsert: " + comment);
		try {
			return em.merge(comment);
		} catch (Exception e) {
			logger.error("Error insertando/acutalizando comentario: " + comment, e);
			throw e;
		}
	}

}
