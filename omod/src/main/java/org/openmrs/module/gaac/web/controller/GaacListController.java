package org.openmrs.module.gaac.web.controller;

import java.util.List;

import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GaacListController {

	@RequestMapping(value = { "/module/gaac/gaacList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView getGaacList() {
		ModelAndView modelAndView = new ModelAndView();

		GaacService service = GaacUtils.getService();
		List gaacs = service.getAllGaac();

		modelAndView.addObject("gaacList", gaacs);
		return modelAndView;
	}

}
