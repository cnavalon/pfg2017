package es.uned.lsi.pfg.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Comment;
import es.uned.lsi.pfg.model.Group;
import es.uned.lsi.pfg.model.News;
import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.model.Subject;
import es.uned.lsi.pfg.service.groups.GroupsService;
import es.uned.lsi.pfg.service.news.NewsService;
import es.uned.lsi.pfg.service.users.UsersService;
import es.uned.lsi.pfg.utils.Constans;

/**
 * Controlador de noticias
 * @author Carlos Navalon Urrea
 */
@Controller
@RequestMapping("/news")
public class NewsController {
	private static Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsService;
	
	/**
	 * Obtiene la página de noticias
	 * @param session
	 * @return la página de noticias
	 * @throws Exception
	 */
	@RequestMapping(value="/newsPage", method = RequestMethod.GET)
	public ModelAndView getNewsPage (HttpSession session) throws Exception {
		logger.debug("getNewsPage");
		ModelAndView model = new ModelAndView("news");
		List<Group> lstGroups = new ArrayList<Group>();
		Integer id = (Integer) session.getAttribute(Constans.SESSION_ID);		
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		
		try {
			if(role.getIdRole().equals(Constans.ROLE_ADMIN)){
				lstGroups = groupsService.getAllGroups();
			} else if (role.getIdRole().equals(Constans.ROLE_TEACHER)){
				lstGroups = groupsService.getGroupsByTeacher(id);
			} else if (role.getIdRole().equals(Constans.ROLE_PARENT)){
				List<Student> lstStudens = usersService.findStudentsList(id);
				HashSet<Integer> hsGroupsIds = new HashSet<Integer>();
				for(Student student : lstStudens){
					if(student.getGroup() != null && !hsGroupsIds.contains(student.getGroup())){
						lstGroups.add(groupsService.getGroup(student.getGroup()));
						hsGroupsIds.add(student.getGroup());
					}
				}
			} else if (role.getIdRole().equals(Constans.ROLE_STUDENT)){
				Student student = usersService.getById(id, Student.class);
				if(student.getGroup() != null){
					lstGroups.add(groupsService.getGroup(student.getGroup()));
				}
			}
		} catch (Exception e) {
			logger.error("Error obteniendo listado de clases para usuario: " + id + ", " + role, e);
		}
		
		model.addObject("lstGroups", lstGroups);
		
		return model;
	}
	
	/**
	 * Recupera un listado de las asignaturas por clase. 
	 * En caso de que el usuario sea profersor recupera solo sus asignturas de dicha clase
	 * @param group id de clase
	 * @return listado de asignaturas
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/getSubjects/{group}", method = RequestMethod.GET)
	public List<Subject> getSubjects (@PathVariable("group") Integer group, HttpSession session) throws Exception {
		logger.debug("getSubjects: " + group);
		List<Subject> lstSubjects = null;
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		if(role.getIdRole().equals(Constans.ROLE_TEACHER)){
			Integer teacher = (Integer) session.getAttribute(Constans.SESSION_ID);
			lstSubjects = groupsService.getSubjectsByGroupAndTeacher(group, teacher);
		} else {
			lstSubjects =  groupsService.getSubjectsByGroup(group);
		}
		
		return lstSubjects;
	}
	
	/**
	 * Devuelve el numero de noticias para una clase y asignatura
	 * @param group id de clase
	 * @param subject id de asignatura
	 * @return el numero de noticias para una clase y asignatura
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/getNewsInfo/{group}/{subject}", method = RequestMethod.GET)
	public Long getNewsInfo (@PathVariable("group") Integer group, @PathVariable("subject") Integer subject) throws Exception {
		logger.debug("getNewsInfo: " + group + ", " + subject);
		return newsService.countNews(group, subject);
	}
	
	
	@RequestMapping(value="/getNews/{group}/{subject}/{page}", method = RequestMethod.GET)
	public ModelAndView getNews (@PathVariable("group") Integer group, @PathVariable("subject") Integer subject,
			@PathVariable("page") Integer page ) throws Exception {
		logger.debug("getNews: " + group + ", " + subject + ", " + page);
		ModelAndView model = new ModelAndView("newsList");
		List<News> lstNews = newsService.getNews(group, subject, page);
		for(News news : lstNews){
			news.setPerson(usersService.getByUser(news.getUser()));
			if(news.getComments() != null && !news.getComments().isEmpty()){
				for(Comment comment : news.getComments()){
					comment.setPerson(usersService.getByUser(comment.getUser()));
				}
			}
		}
		model.addObject("lstNews", lstNews);
		return model;
		
	}
	
	/**
	 * Devuelve el modal de añadir noticia
	 * @return modal de añadir noticia
	 * @throws Exception
	 */
	@RequestMapping(value="/getAddModal", method = RequestMethod.GET)
	public ModelAndView getAddModal(Locale locale) throws Exception {
		logger.debug("getAddModal");
		ModelAndView model = new ModelAndView("modalNews");
		model.addObject("title", messageSource.getMessage("news.title.add", null, locale));
		model.addObject("news", new News());
		return model;
	}
	
	/**
	 * Devuelve el modal de editar noticia
	 * @return modal de editar noticia
	 * @throws Exception
	 */
	@RequestMapping(value="/getUpdateModal/{idNews}", method = RequestMethod.GET)
	public ModelAndView getUpdateModal(@PathVariable("idNews") Integer idNew, Locale locale) throws Exception {
		logger.debug("getUpdateModal: " + idNew);
		ModelAndView model = new ModelAndView("modalNews");
		model.addObject("title", messageSource.getMessage("news.title.edit", null, locale));
		model.addObject("news", newsService.getNews(idNew));
		return model;
	}
	
	/**
	 * Inserta/actualiza una noticia
	 * @param news noticia
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/saveNew", method = RequestMethod.POST)
	public String saveNews(@RequestBody News news, Locale locale, HttpSession session) throws Exception {
		logger.debug("saveNews: " + news);
		String response = "";
		try {
			newsService.upsertNews(news);
			response = messageSource.getMessage("news.save.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando noticia " + news, e);
			response = messageSource.getMessage("news.save.error", null, locale);
		}
		return response;
	}
	
	/**
	 * Devuelve el modal de añadir comentario
	 * @return modal de añadir comentario
	 * @throws Exception
	 */
	@RequestMapping(value="/addCommentModal", method = RequestMethod.GET)
	public ModelAndView getAddCommentModal(Locale locale) throws Exception {
		logger.debug("getAddCommentModal");
		ModelAndView model = new ModelAndView("modalComment");
		model.addObject("title", messageSource.getMessage("news.comment.title.add", null, locale));
		model.addObject("comment", new Comment());
		return model;
	}
	
	/**
	 * Devuelve el modal de editar comentario
	 * @return modal de editar comentario
	 * @throws Exception
	 */
	@RequestMapping(value="/updateCommentModal/{idComment}", method = RequestMethod.GET)
	public ModelAndView getUpdateCommentModal(@PathVariable("idComment") Integer idComment, Locale locale) throws Exception {
		logger.debug("getUpdateCommentModal: " + idComment);
		ModelAndView model = new ModelAndView("modalComment");
		model.addObject("title", messageSource.getMessage("news.comment.title.edit", null, locale));
		model.addObject("comment", newsService.getComment(idComment));
		return model;
	}
	
	/**
	 * Inserta/actualiza un comentario
	 * @param comment comentario
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/saveComment", method = RequestMethod.POST)
	public String saveComment(@RequestBody Comment comment, Locale locale, HttpSession session) throws Exception {
		logger.debug("saveComment: " + comment);
		String response = "";
		try {
			newsService.upsertComment(comment);
			response = messageSource.getMessage("news.comment.save.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando comentario " + comment, e);
			response = messageSource.getMessage("news.comment.save.error", null, locale);
		}
		return response;
	}
	
	/**
	 * Elimina una noticia
	 * @param idNews id de noticia
	 * @param locale
	 * @return 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/deleteNews/{idNews}", method = RequestMethod.GET)
	public String deleteNews(@PathVariable("idNews") Integer idNews, Locale locale) throws Exception {
		logger.debug("deleteNews: " + idNews);
		String response = "";
		try {
			newsService.deleteNews(idNews);
			response = messageSource.getMessage("news.delete.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error eliminando noticia " + idNews, e);
			response = messageSource.getMessage("news.delete.error", null, locale);
		}
		return response;
	}
	
	/**
	 * Elimina un comentario
	 * @param idComment id de comentario
	 * @param locale
	 * @return 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/deleteComment/{idComment}", method = RequestMethod.GET)
	public String deleteComment(@PathVariable("idComment") Integer idComment, Locale locale) throws Exception {
		logger.debug("deleteComment: " + idComment);
		String response = "";
		try {
			newsService.deleteComment(idComment);
			response = messageSource.getMessage("news.comment.delete.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error eliminando comentario " + idComment, e);
			response = messageSource.getMessage("news.comment.delete.error", null, locale);
		}
		return response;
	}
}
