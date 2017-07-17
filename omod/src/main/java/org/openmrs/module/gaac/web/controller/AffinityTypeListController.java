package org.openmrs.module.gaac.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AffinityTypeListController {

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = { "/module/gaac/affinityTypeList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ModelAndView getAffinityTypeList(
			@RequestParam(required = false, value = "openmrs_msg") String openmrs_msg) {
		ModelAndView modelAndView = new ModelAndView();

		GaacService service = GaacUtils.getService();
		List types = service.getAllAffinityType();

		modelAndView.addObject("affinityTypeList", types);
		modelAndView.addObject("openmrs_msg", openmrs_msg);

		return modelAndView;
	}

}
