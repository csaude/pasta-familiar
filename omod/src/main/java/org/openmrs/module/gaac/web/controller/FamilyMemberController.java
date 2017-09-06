package org.openmrs.module.gaac.web.controller;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Person;
import org.openmrs.Relationship;
import org.openmrs.RelationshipType;
import org.openmrs.api.PersonService;
import org.openmrs.api.context.Context;
import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.FamilyMember;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.ReasonLeavingGaacType;
import org.openmrs.module.gaac.api.GaacService;
import org.openmrs.module.gaac.propertyeditor.FamilyEditor;
import org.openmrs.module.gaac.propertyeditor.ReasonLeavingTypeEditor;
import org.openmrs.module.gaac.validator.FamilyMemberValidator;
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
public class FamilyMemberController {

	private Log log = LogFactory.getLog(getClass());
	private FamilyMemberValidator familyMemberValidator;

	@Autowired
	public FamilyMemberController(FamilyMemberValidator validator) {
		this.familyMemberValidator = validator;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(ReasonLeavingGaacType.class, new ReasonLeavingTypeEditor());

		binder.registerCustomEditor(Family.class, new FamilyEditor());

		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(SimpleDateFormat.getDateInstance(3, Context.getLocale()), true));

		binder.registerCustomEditor(Relationship.class, new PropertyEditorSupport() {

			public void setAsText(String text) throws IllegalArgumentException {
				PersonService ps = Context.getPersonService();
				Relationship rs = new Relationship();
				rs.setRelationshipType(ps.getRelationshipType(Integer.parseInt(text)));
				setValue(rs);

			}

			public String getAsText() {
				RelationshipType t = (RelationshipType) getValue();

				if (t == null) {
					return "";
				}

				return t.getRelationshipTypeId().toString();
			}

		});

	}

	@RequestMapping(value = { "/module/gaac/familyMemberForm" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public void showGaacForm(Model model, @ModelAttribute("familyMember") FamilyMember familyMember,
			@RequestParam(required = false, value = "familyId") Family family) {
		GaacService gs = GaacUtils.getService();
		PersonService ps = Context.getPersonService();

		model.addAttribute("reasonLeavingTypes", gs.getAllReasonLeavingGaacType());

		int voided = 0, not_voided = 0;

		Family family2 = GaacUtils.getService().getFamily(family.getFamilyId());
		if (family2.getMembers() != null) {
			for (FamilyMember member : family2.getMembers()) {
				if (member.getVoided() == true) {
					voided = voided + 1;
				} else {
					not_voided = not_voided + 1;
				}
			}
		}
		model.addAttribute("voided", voided);
		model.addAttribute("not_voided", not_voided);
		model.addAttribute("relacao", ps.getAllRelationshipTypes());

	}

	@RequestMapping(value = { "/module/gaac/familyMemberForm" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ModelAndView processForm(@ModelAttribute("familyMember") FamilyMember familyMember, BindingResult result,
			SessionStatus status, @RequestParam(required = false, value = "familyId") Family family,
			@RequestParam(required = false, value = "relacaotype") String relacaoPessoa, HttpServletRequest request) {
		GaacService service = GaacUtils.getService();
		PersonService ps = Context.getPersonService();
		ModelAndView model = new ModelAndView();

		if (familyMember.getFamily() == null) {
			familyMember.setFamily(service.getFamily(Integer.valueOf(request.getParameter("familyId"))));

		}
		this.familyMemberValidator.validate(familyMember, result);
		if (result.hasErrors()) {
			model.addObject("reasonLeavingTypes", service.getAllReasonLeavingGaacType());
			model.addObject("relacao", ps.getAllRelationshipTypes());
			return model;
		} else {

			if ((familyMember.getRestart() != null) && (familyMember.getRestart().booleanValue())) {
				familyMember.setLeaving(Boolean.valueOf(false));
				familyMember.setReasonLeaving(null);
				familyMember.setEndDate(null);
				log.debug("RESTART IS TRUE AND RESTART DATE IS" + familyMember.getRestartDate());
			}

			Person member = familyMember.getMember();
			Person header = family.getFocalPatient();

			Relationship newR = new Relationship();

			newR.setRelationshipType(ps.getRelationshipType(Integer.valueOf(relacaoPessoa)));
			newR.setPersonA(header);
			newR.setPersonB(member);
			newR.setDateCreated(new Date());

			if (familyMember.getFamilyMemberId() == null) {

				ps.saveRelationship(newR);
				familyMember.setRelacao(newR);

			} else {

				Relationship rs = familyMember.getRelacao();
				rs.setRelationshipType(ps.getRelationshipType(Integer.valueOf(relacaoPessoa)));

				ps.saveRelationship(rs);

			}

			if (familyMember.getVoided()) {

				Relationship rs = familyMember.getRelacao();

				rs.setVoided(true);
				rs.setVoidedBy(familyMember.getVoidedBy());
				rs.setVoidReason(familyMember.getVoidReason());
				rs.setDateVoided(familyMember.getDateVoided());


			}

			service.saveFamilyMember(familyMember);

			HttpSession httpSession = request.getSession();
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "gaac.fmmember.saved");

			return new ModelAndView(new RedirectView(request.getContextPath()
					+ "/module/gaac/addNewFamily.form?familyId=" + familyMember.getFamily().getFamilyId()));
		}
	}

	@ModelAttribute("familyMember")
	FamilyMember formBackingObject(@RequestParam(value = "familyMemberId", required = false) Integer familyMemberId,
			HttpServletRequest request) {
		if (familyMemberId != null) {
			FamilyMember member = GaacUtils.getService().getFamilyMember(familyMemberId);

			int valorComparar = member.getRelacao().getRelationshipType().getRelationshipTypeId();

			request.setAttribute("compara", valorComparar);

			return member;
		}
		FamilyMember member = new FamilyMember();

		return member;
	}

}
