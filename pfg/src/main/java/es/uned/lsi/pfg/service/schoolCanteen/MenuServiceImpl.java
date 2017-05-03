/**
 * 
 */
package es.uned.lsi.pfg.service.schoolCanteen;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.uned.lsi.pfg.dao.schoolCanteen.MenuDAO;
import es.uned.lsi.pfg.dao.schoolCanteen.MenuTypeDAO;
import es.uned.lsi.pfg.model.DailyMenu;
import es.uned.lsi.pfg.model.Menu;
import es.uned.lsi.pfg.model.MenuType;

/**
 * Implementacion del servicio de menus
 * @author Carlos Navalon Urrea
 *
 */
@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuDAO menuDAO;
	
	@Autowired
	private MenuTypeDAO menuTypeDAO;
	
	@Override
	public DailyMenu[] getMenuByMonth(Integer month) {
		logger.debug("getMenuByMonth: " + month);
		DailyMenu[] monthlyMenu = new DailyMenu[31];
		List<Menu> lstMenus = menuDAO.findByMonth(month);
		if(lstMenus != null && !lstMenus.isEmpty()){
			for(Menu menu : lstMenus){
				if(monthlyMenu[menu.getDay()] == null){
					monthlyMenu[menu.getDay()] = new DailyMenu();
				}
				DailyMenu dailyMenu = monthlyMenu[menu.getDay()];
				dailyMenu.getData().get(menu.getOrder()).put(menu.getType(), menu);
			}
		}
		return monthlyMenu;
	}

	@Override
	public List<MenuType> getAllMenuTypes() {
		logger.debug("getAllMenuTypes");
		return menuTypeDAO.findAll();
	}
	
	@Transactional
	@Override
	public void save(List<Menu> lstMenus) {
		logger.debug("save: " + lstMenus);
		for(Menu menu : lstMenus){
			Menu oldMenu = menuDAO.findByMonthDayTypeOrder(menu.getMonth(), menu.getDay(), menu.getType(), menu.getOrder());
			if(oldMenu != null){
				menu.setId(oldMenu.getId());
				if(menu.getDescription() == null || menu.getDescription().equals("")){
					menuDAO.delete(oldMenu);
					continue;
				}
			}
			menuDAO.upsert(menu);
		}
	}

	@Transactional
	@Override
	public void deleteDailyMenu(Integer month, Integer day) {
		logger.debug("deleteDailyMenu: " + month + ", " + day);
		menuDAO.deleteByMonthDay(month, day);
	}
	
}
