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
import es.uned.lsi.pfg.model.MenuType;

/**
 * Implementacion de repositorio de tipos de menu
 * @author Carlos Navalon Urrea
 *
 */
@Repository
public class MenuTypeDAOImpl extends AbstractJpaDao implements MenuTypeDAO {

	private static final Logger logger = LoggerFactory.getLogger(MenuTypeDAOImpl.class);
	
	@Override
	public List<MenuType> findAll() {
		logger.debug("findAll");
		List<MenuType> lstMenuTypes = new ArrayList<MenuType>();
		try {
			lstMenuTypes =  em.createNamedQuery(MenuType.Q_FIND_ALL, MenuType.class)
			.getResultList();
		} catch (NoResultException e) {
			logger.debug("Empty results");
		} catch (Exception e) {
			logger.error("Error recuperando tipos de menu", e);
			throw e;
		}
		return lstMenuTypes;
	}

}
