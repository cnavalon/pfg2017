package es.uned.lsi.pfg.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test/")
public class TestController {

	@RequestMapping(value="/adm/lang", method = RequestMethod.GET)
	public ModelAndView testAmdLang(Locale locale){
		ModelAndView model = new ModelAndView();
		model.setViewName("testLang");

		return model;
	}
	
	@RequestMapping(value="/all/lang", method = RequestMethod.GET)
	public ModelAndView tesAllLang(Locale locale){
		ModelAndView model = new ModelAndView();
		model.setViewName("testLang");

		return model;
	}
	
	@RequestMapping(value="/tch/lang", method = RequestMethod.GET)
	public ModelAndView tesTchLang(Locale locale){
		ModelAndView model = new ModelAndView();
		model.setViewName("testLang");

		return model;
	}
			
}
