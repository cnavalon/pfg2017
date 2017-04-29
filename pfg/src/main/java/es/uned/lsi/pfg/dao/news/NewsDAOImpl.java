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
import es.uned.lsi.pfg.model.News;

/**
 * Implementacion de repositorio de noticias
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class NewsDAOImpl extends AbstractJpaDao implements NewsDAO {
	private static final Logger logger = LoggerFactory.getLogger(NewsDAOImpl.class);

	@Override
	public List<News> findNewsByGroupSubject(Integer group, Integer subject, Integer offset, Integer limit) {
		logger.debug("findNewsByGroupSubject: " + group + ", " + subject + ", " + offset + ", " + limit);
		List<News> lstNews = new ArrayList<News>();
		try {
			lstNews =  em.createNamedQuery(News.Q_FIND_BY_GROUP_SUBJECT, News.class)
			.setParameter("group", group)
			.setParameter("subject", subject)
			.setFirstResult(offset)
			.setMaxResults(limit)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando noticias por clase y asignatura: " + group + ", " + subject, e);
			throw e;
		}
		return lstNews;
	}

	@Override
	public News findNewsById(Integer id) {
		logger.debug("findNewsById: " + id);
		try {
			return em.createNamedQuery(News.Q_FIND_BY_ID, News.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando noticia: " + id, e);
			throw e;
		}
	}
	
	@Override
	public News upsert(News news) {
		logger.debug("upsert: " + news);
		try {
			return em.merge(news);
		} catch (Exception e) {
			logger.error("Error insertando/acutalizando noticia: " + news, e);
			throw e;
		}
	}

	@Override
	public Long countNews(Integer group, Integer subject) {
		logger.debug("countNews: " + group + ", " + subject);
		try {
			return em.createNamedQuery(News.Q_COUNT_BY_GROUP_SUBJECT, Long.class)
			.setParameter("group", group)
			.setParameter("subject", subject)
			.getSingleResult();
		} catch (Exception e) {
			logger.error("Error contando noticia: " + group + ", " + subject, e);
			throw e;
		}
	}

	

}
