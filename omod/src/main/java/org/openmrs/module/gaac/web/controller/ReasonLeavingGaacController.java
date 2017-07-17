package org.openmrs.module.gaac.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.context.Context;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.ReasonLeavingGaacType;
import org.openmrs.module.gaac.api.GaacService;
import org.openmrs.module.gaac.validator.ReasonLeavingGaacTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

//Damasceno
import javax.servlet.http.HttpSession;
import org.openmrs.web.WebConstants;

@Controller
public class ReasonLeavingGaacController {

	protected final Log log = LogFactory.getLog(getClass());
	private ReasonLeavingGaacTypeValidator reasonLeavingvalidator;

	@Autowired
	public ReasonLeavingGaacController(
			ReasonLeavingGaacTypeValidator reasonLeavingvalidator) {
		this.reasonLeavingvalidator = reasonLeavingvalidator;
	}

	@ModelAttribute("reasonLeavingType")
	ReasonLeavingGaacType formBackingObject(
			@RequestParam(value = "reasonLeavingTypeId", required = false) Integer reasonLeavingTypeId) {
		if (reasonLeavingTypeId != null) {
			return GaacUtils.getService().getReasonLeavingGaacType(
					reasonLeavingTypeId);
		}
		return new ReasonLeavingGaacType();
	}

	@RequestMapping(value = { "/module/gaac/reasonLeavingTypeForm" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void showReasonLeavingTypeForm(
			Model model,
			@ModelAttribute("reasonLeavingType") ReasonLeavingGaacType reasonLeavingType) {
	}

	@RequestMapping(value = { "/module/gaac/reasonLeavingTypeForm" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView processForm(
			@ModelAttribute("reasonLeavingType") ReasonLeavingGaacType reasonLeavingType,
			BindingResult result, SessionStatus status,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		HttpSession httpSession = request.getSession();
		this.reasonLeavingvalidator.validate(reasonLeavingType, result);
		if (result.hasErrors()) {
			return model;
		}
		if (Context.isAuthenticated()) {
			GaacService service = GaacUtils.getService();

			if (request.getParameter("save") != null) {
				service.saveReasonLeavingGaacType(reasonLeavingType);

				RedirectView rdV = new RedirectView(request.getContextPath()
						+ "/module/gaac/reasonList.form");

				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
						"gaac.reasonLeaving.saved");

				return new ModelAndView(rdV);
			}
			if (request.getParameter("retire") != null) {
				String retireReason = request.getParameter("retireReason");
				if ((reasonLeavingType.getReasonLeavingTypeId() != null)
						&& (!StringUtils.hasText(retireReason))) {
					result.rejectValue("retireReason", "retireReason",
							"gaac.affinityType.error.emptyretirereason");
					return model;
				}
				service.retireReasonLeavingGaacType(reasonLeavingType,
						retireReason);

				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
						"gaac.reasonLeaving.retired");

				return new ModelAndView(new RedirectView(
						request.getContextPath()
								+ "/module/gaac/reasonList.form"));
			}
			if (request.getParameter("purge") != null) {
				try {
					service.purgeReasonLeavingGaacType(reasonLeavingType);

					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
							"gaac.reasonLeaving.purged");

					return new ModelAndView(new RedirectView(
							request.getContextPath()
									+ "/module/gaac/reasonList.form"));
				} catch (DataIntegrityViolationException e) {
					model.addObject("openmrs_error",
							"error.object.inuse.cannot.purge");
				} catch (APIException e) {
					model.addObject("openmrs_error",
							"error.general: " + e.getLocalizedMessage());
				}

			}

		}

		return model;
	}

}
