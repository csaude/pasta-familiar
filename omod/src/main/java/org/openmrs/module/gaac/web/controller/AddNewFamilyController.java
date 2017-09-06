package org.openmrs.module.gaac.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Relationship;
import org.openmrs.api.context.Context;
import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.FamilyMember;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.openmrs.module.gaac.validator.FamilyValidator;
import org.openmrs.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AddNewFamilyController {

	private Log log = LogFactory.getLog(getClass());
	private FamilyValidator familyValidator;

	@Autowired
	public AddNewFamilyController(FamilyValidator validator) {
		this.familyValidator = validator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(SimpleDateFormat.getDateInstance(3, Context.getLocale()), true));
	}

	@RequestMapping(value = { "/module/gaac/addNewFamily" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	// showAffinityTypeForm
	public void showshowFamilyForm(Model model, @ModelAttribute("family") Family family) {
	}

	@ModelAttribute("family")
	Family formBackingObject(@RequestParam(value = "familyId", required = false) Integer familyId,
			HttpServletRequest request) {

		if (familyId != null) {
			Family family = GaacUtils.getService().getFamily(familyId);

			return family;
		}

		Family family = new Family();

		return family;
	}

	@RequestMapping(value = { "/module/gaac/addNewFamily" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView processForm(@ModelAttribute("family") Family family, BindingResult result, SessionStatus status,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();

		GaacService service = GaacUtils.getService();

		this.familyValidator.validate(family, result);

		if (result.hasErrors()) {

			return model;
		} else {

			Relationship rs;

			// Anular
			if ((family.getVoided() != null) && (family.getVoided().booleanValue())) {
				for (FamilyMember member : family.getMembers()) {
					member.setVoided(true);
					member.setVoidedBy(family.getVoidedBy());
					member.setVoidReason(family.getVoidReason());
					member.setDateVoided(family.getDateVoided());

					rs = member.getRelacao();
					rs.setVoided(true);
					rs.setVoidedBy(family.getVoidedBy());
					rs.setVoidReason(family.getVoidReason());
					rs.setDateVoided(family.getDateVoided());

				}
			}

			// Desintegrar

			if ((family.getCrumbled() != null) && (family.getCrumbled().booleanValue())) {

				for (FamilyMember member : family.getMembers()) {
					member.setLeaving(Boolean.valueOf(true));
					member.setReasonLeaving(service.getReasonLeavingGaacType(Integer.valueOf(5)));
					member.setEndDate(family.getDateCrumbled());
				}
				family.setEndDate(family.getDateCrumbled());

			}

			// Reintengrar

			if (family.getCrumbled() != null && (!family.getCrumbled().booleanValue()) && family.getEndDate() !=null) {
				family.setEndDate(null);
				family.setReasonCrumbled(null);
				family.setDateCrumbled(null);
				for (FamilyMember member : family.getMembers()) {
					if (member.getReasonLeaving().getId() == 5) {
						member.setLeaving(false);
						member.setReasonLeaving(null);
						member.setEndDate(null);
					}
				}
			}

			service.saveFamily(family);

			HttpSession httpSession = request.getSession();
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "gaac.fmsaved");

			return new ModelAndView(new RedirectView(request.getContextPath() + "/module/gaac/familyList.form"));

		}
	}
}
