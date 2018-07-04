package org.openmrs.module.gaac.web.controller;

import java.util.List;

import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.openmrs.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchFamilyListController {

	@RequestMapping(value = "/module/gaac/searchFamilyList")
	public ModelAndView getGaacs(
			@RequestParam(value = "action", required = false) String action,
			@RequestParam(value = "patient", required = false) Patient patient)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView();

		if (patient == null) {
			return modelAndView;
		} else {

			GaacService service = GaacUtils.getService();
			List gaacmembers = service.getAllFamilyMemberHistory(patient);

			if (gaacmembers.size() > 0) {
				modelAndView.addObject("gaacMemberHistoryList", gaacmembers);
				modelAndView.addObject("gaacMember", gaacmembers.get(0));
			}
			else {modelAndView.addObject("members", 0);}
			return modelAndView;

		}

	}
}
