package org.openmrs.module.gaac.web.controller;

import java.util.List;

import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReasonLeavingGaacListController {

	@RequestMapping(value = { "/module/gaac/reasonList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView getReasonList(
			@RequestParam(required = false, value = "openmrs_msg") String openmrs_msg) {
		ModelAndView modelAndView = new ModelAndView();

		GaacService service = GaacUtils.getService();
		List types = service.getAllReasonLeavingGaacType();

		modelAndView.addObject("reasonLeavingTypeList", types);
		modelAndView.addObject("openmrs_msg", openmrs_msg);
		return modelAndView;
	}

}
