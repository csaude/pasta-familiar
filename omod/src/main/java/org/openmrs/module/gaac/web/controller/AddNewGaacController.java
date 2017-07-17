package org.openmrs.module.gaac.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.gaac.AffinityType;
import org.openmrs.module.gaac.Gaac;
import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.openmrs.module.gaac.propertyeditor.AffinityTypeEditor;
import org.openmrs.module.gaac.validator.GaacValidator;
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

//Damasceno
import javax.servlet.http.HttpSession;
import org.openmrs.web.WebConstants;

@Controller
public class AddNewGaacController {

	private Log log = LogFactory.getLog(getClass());
	private GaacValidator gaacValidator;

	@Autowired
	public AddNewGaacController(GaacValidator validator) {
		this.gaacValidator = validator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(AffinityType.class,
				new AffinityTypeEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				SimpleDateFormat.getDateInstance(3, Context.getLocale()), true));
	}

	@RequestMapping(value = { "/module/gaac/addNewGaac" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public void showAffinityTypeForm(Model model,
			@ModelAttribute("gaac") Gaac gaac) {
		model.addAttribute("affinityTypes", GaacUtils.getService()
				.getAllAffinityType());
	}

	@ModelAttribute("gaac")
	Gaac formBackingObject(@RequestParam(value = "gaacId", required = false) Integer gaacId) {
		if (gaacId != null) {
			Gaac gaac = GaacUtils.getService().getGaac(gaacId);

			return gaac;
		}
		Gaac gaac = new Gaac();

		return gaac;
	}

	@RequestMapping(value = { "/module/gaac/addNewGaac" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView processForm(@ModelAttribute("gaac") Gaac gaac,
			BindingResult result, SessionStatus status,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();

		this.gaacValidator.validate(gaac, result);
		if (result.hasErrors()) {
			// DL - Recarregar a Lista de afinidades caso der erro de validacaoo
			model.addObject("affinityTypes", GaacUtils.getService()
					.getAllAffinityType());
			return model;
		} else {

			GaacService service = GaacUtils.getService();

			if (gaac.getNewLocation() != null) {

				this.gaacValidator.validate(gaac, result);
				if (result.hasErrors()) {
					// DL - Recarregar a Lista de afinidades caso der erro de
					// validacaoo
					model.addObject("affinityTypes", GaacUtils.getService()
							.getAllAffinityType());
					return model;
				} else {

					// Insert New
					Gaac gaac2 = new Gaac();
					gaac2.setName(gaac.getName());
					gaac2.setGaacIdentifier(gaac.getGaacIdentifier() + "T");
					gaac2.setLocation(gaac.getNewLocation());
					gaac2.setAffinity(gaac.getAffinity());
					if (gaac.getFocalPatient() != null) {
						gaac2.setFocalPatient(gaac.getFocalPatient());
					} else {
						gaac2.setFocalPatient(null);
					}
					gaac2.setStartDate(gaac.getStartDate());

					if (gaac.getDescription() == null
							|| gaac.getDescription().equals("")
							|| gaac.getDescription().equals(" ")) {
						gaac2.setDescription(gaac.getDescription()
								+ "GAAC Transferido de: "
								+ gaac.getLocation().getName());
					} else {
						gaac2.setDescription(gaac.getDescription()
								+ ", GAAC Transferido de: "
								+ gaac.getLocation().getName());
					}

					gaac2.setCrumbled(gaac.getCrumbled());

					if ((gaac2.getGaacId() == null)) {
						GaacMember member = new GaacMember();
						member.setGaac(gaac2);
						member.setStartDate(gaac2.getStartDate());
						member.setDescription(gaac2.getDescription());

						if (gaac.getMembers() != null) {
							gaac2.setMembers(new HashSet<GaacMember>());
							for (GaacMember memberOld : gaac.getMembers()) {

								GaacMember member2 = new GaacMember();

								member2.setGaac(gaac2);
								member2.setStartDate(memberOld.getStartDate());
								member2.setEndDate(memberOld.getEndDate());
								member2.setReasonLeaving(memberOld
										.getReasonLeaving());
								member2.setLeaving(memberOld.getLeaving());
								member2.setVoided(memberOld.getVoided());
								member2.setVoidedBy(memberOld.getVoidedBy());
								member2.setVoidReason(memberOld.getVoidReason());
								member2.setDateVoided(memberOld.getDateVoided());
								member2.setDescription(memberOld
										.getDescription());
								member2.setMember(memberOld.getMember());

								gaac2.getMembers().add(member2);
							}

						}

						// Update
						gaac.setVoided(true);
						gaac.setVoidReason("GAAC Transferido para US: "
								+ gaac.getNewLocation().getName());
						for (GaacMember member3 : gaac.getMembers()) {
							member3.setVoided(true);
							member3.setVoidedBy(gaac.getVoidedBy());
							member3.setVoidReason(gaac.getVoidReason());
							member3.setDateVoided(gaac.getDateVoided());

						}

						service.saveGaac(gaac);

					}

					// Desintegrar
					// DL - Como nao eh possivel desintegrar e reintegrar ao
					// mesmo tempo foi trocado a estrategia condicional para
					// if-elseif
					if ((gaac2.getCrumbled() != null)
							&& (gaac2.getCrumbled() == true)) {
						for (GaacMember member : gaac2.getMembers()) {
							member.setLeaving(Boolean.valueOf(true));
							member.setReasonLeaving(service
									.getReasonLeavingGaacType(Integer
											.valueOf(5)));
							member.setEndDate(gaac2.getDateCrumbled());
						}
						gaac2.setEndDate(gaac2.getDateCrumbled());

					}

					// Reintegar

					else if (gaac2.getCrumbled() != null
							&& (!gaac2.getCrumbled() == false)) {
						gaac2.setEndDate(null);
						gaac2.setReasonCrumbled(null);
						gaac2.setDateCrumbled(null);
						for (GaacMember member : gaac2.getMembers()) {
							if (member.getReasonLeaving().getId() == 5) {
								member.setLeaving(false);
								member.setReasonLeaving(null);
								member.setEndDate(null);
							}
						}
					}

					service.saveGaac(gaac2);

					HttpSession httpSession = request.getSession();
					httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
							"gaac.transfered");

					return new ModelAndView(new RedirectView(
							request.getContextPath()
									+ "/module/gaac/gaacList.form"));

				}

			} else {

				if ((gaac.getGaacId() == null)
						&& (gaac.getFocalPatient() != null)) {
					GaacMember member = new GaacMember();
					member.setGaac(gaac);
					member.setStartDate(gaac.getStartDate());
					member.setMember(gaac.getFocalPatient());
					member.setDescription(gaac.getDescription());
					if (gaac.getMembers() == null)
						gaac.setMembers(new HashSet<GaacMember>());
					gaac.getMembers().add(member);
				}

				// Desintegrar
				// DL - Como nao eh possivel desintegrar e reintegrar ao mesmo
				// tempo foi trocado a estrategia condicional para if-elseif
				if ((gaac.getCrumbled() != null)
						&& (gaac.getCrumbled() == true)) {
					for (GaacMember member : gaac.getMembers()) {
						member.setLeaving(Boolean.valueOf(true));
						member.setReasonLeaving(service
								.getReasonLeavingGaacType(Integer.valueOf(5)));
						member.setEndDate(gaac.getDateCrumbled());
					}
					gaac.setEndDate(gaac.getDateCrumbled());

				}

				// Reintegar

				else if (gaac.getCrumbled() != null
						&& (!gaac.getCrumbled() == false)) {
					gaac.setEndDate(null);
					gaac.setReasonCrumbled(null);
					gaac.setDateCrumbled(null);
					for (GaacMember member : gaac.getMembers()) {
						if (member.getReasonLeaving().getId() == 5) {
							member.setLeaving(false);
							member.setReasonLeaving(null);
							member.setEndDate(null);
						}
					}
				}

				// Anular
				// DL - Como nao eh possivel anular e desanular ao mesmo tempo
				// foi trocado a estrategia condicional para if-elseif
				if ((gaac.getVoided() != null) && (gaac.getVoided() == true)) {
					for (GaacMember member : gaac.getMembers()) {
						member.setVoided(true);
						member.setVoidedBy(gaac.getVoidedBy());
						member.setVoidReason(gaac.getVoidReason());
						member.setDateVoided(gaac.getDateVoided());
					}
				}

				// Desanular
				else if ((gaac.getVoided() != null)
						&& (!gaac.getVoided() == false)) {
					for (GaacMember member : gaac.getMembers()) {
						member.setVoided(false);
						member.setVoidedBy(null);
						member.setVoidReason(null);
						member.setDateVoided(null);
					}
				}

				service.saveGaac(gaac);

				HttpSession httpSession = request.getSession();
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR,
						"gaac.saved");

				return new ModelAndView(
						new RedirectView(request.getContextPath()
								+ "/module/gaac/gaacList.form"));
			}
		}
	}

}
