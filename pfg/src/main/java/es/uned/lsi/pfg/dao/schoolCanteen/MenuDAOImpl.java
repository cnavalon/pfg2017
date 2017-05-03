/**
 * 
 */
package es.uned.lsi.pfg.dao.schoolCanteen;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import es.uned.lsi.pfg.dao.AbstractJpaDao;
import es.uned.lsi.pfg.model.Menu;

/**
 * Implementacion de repositorio de menus
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class MenuDAOImpl extends AbstractJpaDao implements MenuDAO {

	private static final Logger logger = LoggerFactory.getLogger(MenuDAOImpl.class);
	
	@Override
	public Menu findById(Integer id) {
		logger.debug("findById: " + id);
		try {
			return em.createNamedQuery(Menu.Q_FIND_BY_ID, Menu.class)
			.setParameter("id", id)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando menu: " + id, e);
			throw e;
		}
	}
	
	@Override
	public List<Menu> findByMonth(Integer month) {
		logger.debug("findByMonth: " + month);
		List<Menu> lstMenus = new ArrayList<Menu>();
		try {
			lstMenus =  em.createNamedQuery(Menu.Q_FIND_BY_MONTH, Menu.class)
			.setParameter("month", month)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando menus por mes: " + month, e);
			throw e;
		}
		return lstMenus;
	}

	@Override
	public List<Menu> findByMonthDay(Integer month, Integer day) {
		logger.debug("findByMonthDay: " + month + ", " + day);
		List<Menu> lstMenus = new ArrayList<Menu>();
		try {
			lstMenus =  em.createNamedQuery(Menu.Q_FIND_BY_MONTH_DAY, Menu.class)
			.setParameter("month", month)
			.setParameter("day", day)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando menus por mes y dia: " + month + ", " + day, e);
			throw e;
		}
		return lstMenus;
	}

	@Override
	public Menu findByMonthDayTypeOrder(Integer month, Integer day, String type, Integer order) {
		logger.debug("findByMonthDayType: " + month + ", " + day + ", " + type + ", " + order);
		try {
			return em.createNamedQuery(Menu.Q_FIND_BY_MONTH_DAY_TYPE_ORDER, Menu.class)
			.setParameter("month", month)
			.setParameter("day", day)
			.setParameter("type", type)
			.setParameter("order", order)
			.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("Empty results");
			return null;
		} catch (Exception e) {
			logger.error("Error recuperando menu por mes, dia y tipo: " + month + ", " + day + ", " + type + ", " + order, e);
			throw e;
		}
	}
	
	@Override
	public Menu upsert(Menu menu) {
		logger.debug("upsert: " + menu);
		try {
			return em.merge(menu);
		} catch (Exception e) {
			logger.error("Error insertando/acutalizando menu: " + menu, e);
			throw e;
		}
	}

	@Override
	public void delete(Menu menu) {
		logger.debug("delete: " + menu);
		try {
			em.remove(menu);
		} catch (Exception e) {
			logger.error("Error eliminando menu: " + menu, e);
			throw e;
		}

	}

	@Override
	public void deleteByMonthDay(Integer month, Integer day) {
		logger.debug("deleteByMonthDay: " + month + ", " + day);
		try {
			em.createNamedQuery(Menu.Q_REMOVE_BY_MONTH_DAY)
			.setParameter("month", month)
			.setParameter("day", day)
			.executeUpdate();
		} catch (Exception e) {
			logger.error("Error eliminando menus por mes y dia: " + month + ", " + day, e);
			throw e;
		}
	}

}
