package es.uned.lsi.pfg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.uned.lsi.pfg.model.Constans;
import es.uned.lsi.pfg.model.DailyMenu;
import es.uned.lsi.pfg.model.Menu;
import es.uned.lsi.pfg.model.Parent;
import es.uned.lsi.pfg.model.Payment;
import es.uned.lsi.pfg.model.Role;
import es.uned.lsi.pfg.model.Student;
import es.uned.lsi.pfg.service.common.PDFService;
import es.uned.lsi.pfg.service.schoolCanteen.MenuService;
import es.uned.lsi.pfg.service.schoolCanteen.PaymentsService;
import es.uned.lsi.pfg.service.users.UsersService;

/**
 * Controlador de comedor escolar
 * @author Carlos Navalon Urrea
 */
@Controller
@RequestMapping("/schoolCanteen")
public class SchoolCanteenController {
	
	private static final Logger logger = LoggerFactory.getLogger(SchoolCanteenController.class);
	
	@Autowired
	private MenuService menusService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PaymentsService paymentsService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PDFService pdfService;
	
	/**
	 * Obtiene la pagina de menu
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menu", method = RequestMethod.GET)
	public ModelAndView menuPage (Locale locale) throws Exception {
		logger.debug("menuPage");
		ModelAndView model = new ModelAndView("menus");
		model.addObject("lstMenuTypes", menusService.getAllMenuTypes());
		model.addObject("locale", locale.getLanguage());
		return model;
	}
	
	/**
	 * Obtiene un menu mensual
	 * @param month mes
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/getMenuMonth/{month}", method = RequestMethod.GET)
	public DailyMenu[] getMenuMonth (@PathVariable("month") Integer month) throws Exception {
		logger.debug("getMenuMonth: " + month);
		return menusService.getMenuByMonth(month);
	}
	
	/**
	 * Obtiene el formulario de un menu
	 * @param month mes
	 * @param day dia
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/adm/modalMenu/{month}/{day}", method = RequestMethod.GET)
	public ModelAndView modalMenu(@PathVariable("month") Integer month, @PathVariable("day") Integer day) throws Exception {
		logger.debug("modalMenu: "  + month + ", " + day);
		ModelAndView model = new ModelAndView("modalMenu");
		model.addObject("lstMenuTypes", menusService.getAllMenuTypes());
		return model;
	}
	
	/**
	 * Inserta / actualiza / elimina un listado de menus
	 * @param lstMenus listado de menus
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/saveMenu", method = RequestMethod.POST)
	public String saveMenus(@RequestBody List<Menu> lstMenus, Locale locale) throws Exception {
		logger.debug("saveMenus: " + lstMenus);
		String response = "";
		try {
			menusService.save(lstMenus);
			response = messageSource.getMessage("menu.save.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando menus " + lstMenus, e);
			response = messageSource.getMessage("menu.save.error", null, locale);
		}
		return response;
	}

	/**
	 * Elimina un menu diario
	 * @param month mes 
	 * @param day dia
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/adm/deleteMenuDay/{month}/{day}", method = RequestMethod.GET)
	public String deleteMenuDay(@PathVariable("month") Integer month, @PathVariable("day") Integer day, Locale locale) throws Exception {
		logger.debug("deleteMenuDay: "  + month + ", " + day);
		String response = "";
		try {
			menusService.deleteDailyMenu(month, day);
			response = messageSource.getMessage("menu.delete.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error eliminando menu diario"  + month + ", " + day, e);
			response = messageSource.getMessage("menu.delete.error", null, locale);
		}
		return response;
	}
	
	@RequestMapping(value="/ap/payments", method = RequestMethod.GET)
	public ModelAndView viewPayments (HttpSession session, Locale locale) throws Exception {
		logger.debug("viewPayments");
		ModelAndView model = new ModelAndView("payments");
		List<Student> lstStudents = new ArrayList<Student>();
		Integer id = (Integer) session.getAttribute(Constans.SESSION_ID);		
		Role role = (Role)session.getAttribute(Constans.SESSION_ROLE);
		try {
			if(role.getIdRole().equals(Constans.ROLE_ADMIN)){
				lstStudents = usersService.getByClass(Student.class);
			}  else if (role.getIdRole().equals(Constans.ROLE_PARENT)){
				lstStudents = usersService.findStudentsList(id);
			}
		} catch (Exception e) {
			logger.error("Error obteniendo listado de alumnos para usuario: " + id + ", " + role, e);
		}
		model.addObject("lstStudents", lstStudents);
		model.addObject("locale", locale.getLanguage());
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/ap/paymentData/{student}", method = RequestMethod.GET)
	public List<Payment> getPayments (@PathVariable("student") Integer student) throws Exception {
		logger.debug("getPayments: " + student);
		return paymentsService.getPaymentsByStudent(student);
	}
	
	@RequestMapping(value="/par/payModal/{month}/{amount}", method = RequestMethod.GET)
	public ModelAndView payModal (@PathVariable("month") Integer month, @PathVariable("amount") Integer amount, 
			HttpSession session, Locale locale) throws Exception {
		logger.debug("payModal: " + month + ", " + amount);
		ModelAndView model = new ModelAndView("modalPay");
		Integer id = (Integer) session.getAttribute(Constans.SESSION_ID);	
		model.addObject("month", month);
		model.addObject("amount", amount);
		model.addObject("parent", usersService.getById(id, Parent.class));
		model.addObject("locale", locale.getLanguage());
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/par/pay", method = RequestMethod.POST)
	public String pay(@RequestBody Payment payment, Locale locale) throws Exception {
		logger.debug("pay: " + payment);
		String response = "";
		try {
			paymentsService.insert(payment);
			response = messageSource.getMessage("payments.pay.ok", null, locale);
		} catch (Exception e) {
			logger.error("Error guardando el pago " + payment, e);
			response = messageSource.getMessage("payments.pay.ok", null, locale);
		}
		return response;
	}
	
	@RequestMapping(value="par/receipt", method = RequestMethod.POST)
	public void generateReceipt(@RequestParam("id") Integer id, HttpServletResponse response, Locale locale) throws Exception {
		logger.debug("generateReceipt: " + id);
		try {
			Payment payment = paymentsService.getPayment(id);
			Student student = usersService.getById(payment.getStudent(), Student.class);
			byte[] data = pdfService.generateReceipt(payment, student);
			
			String title = messageSource.getMessage("payments.receipt", null, locale) + "_" + id + ".pdf";
			response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "attachment; filename=" + title);
	        response.setContentLength(data.length);

	        response.getOutputStream().write(data);
	        response.getOutputStream().flush();
		} catch (Exception e) {
			logger.error("Error generando recibo pago : " + id, e);
		}
	}
	
}
