/**
 * 
 */
package es.uned.lsi.pfg.service.news;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.news.CommentsDAO;
import es.uned.lsi.pfg.dao.news.NewsDAO;
import es.uned.lsi.pfg.model.Comment;
import es.uned.lsi.pfg.model.News;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Implementacion de servicio de noticias
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class NewsServiceImpl implements NewsService {

	private static final Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);
	
	@Autowired
	private NewsDAO newsDAO;
	
	@Autowired
	private CommentsDAO commentsDAO;
	
	@Override
	public List<News> getNews(Integer group, Integer subject, Integer page) {
		logger.debug("getNews: " + group + ", " + subject + ", " + page);
		List<News> lstNews = newsDAO.findNewsByGroupSubject(group, subject, page*Constans.NEWS_ITEMS_PAGE, Constans.NEWS_ITEMS_PAGE);
		for(News news : lstNews){
			news.setComments(getComments(news.getId()));
		}
		return lstNews;
	}

	@Override
	@Transactional
	public void upsertNews(News news) {
		logger.debug("upsertNews: " + news);
		if(news.getId() != null){
			News oldNew = newsDAO.findNewsById(news.getId());
			news.setDate(oldNew.getDate());
		}
			
		news.setActive(true);
		newsDAO.upsert(news);
	}

	@Override
	@Transactional
	public void deleteNews(Integer idNews) {
		logger.debug("deleteNews: " + idNews);	
		News news = newsDAO.findNewsById(idNews);
		news.setActive(false);
		newsDAO.upsert(news);
		
		List<Comment> lstComments = getComments(idNews);
		for(Comment comment : lstComments){
			comment.setActive(false);
			commentsDAO.upsert(comment);
		}
	}

	@Override
	public List<Comment> getComments(Integer news) {
		logger.debug("getComments: " + news);
		return commentsDAO.findCommentByNews(news);
	}

	@Override
	@Transactional
	public void upsertComment(Comment comment) {
		logger.debug("upsertComment: " + comment);
		if(comment.getId() != null){
			Comment oldComment = commentsDAO.findCommentById(comment.getId());
			comment.setDate(oldComment.getDate());
		}
		comment.setActive(true);
		commentsDAO.upsert(comment);		
	}

	@Override
	@Transactional
	public void deleteComment(Integer idComment) {
		logger.debug("deleteComment: " + idComment);
		Comment comment = commentsDAO.findCommentById(idComment);
		comment.setActive(false);
		commentsDAO.upsert(comment);
	}

	@Override
	public Long countNews(Integer group, Integer subject) {
		logger.debug("countNews: " + group + ", " + subject);
		return newsDAO.countNews(group, subject);
	}

	@Override
	public News getNews(Integer idNew) {
		logger.debug("getNews: " + idNew);
		return newsDAO.findNewsById(idNew);
	}

	@Override
	public Comment getComment(Integer idComment) {
		logger.debug("getComment: " + idComment);
		return commentsDAO.findCommentById(idComment);
	}

}
