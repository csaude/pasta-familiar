package org.openmrs.module.gaac.validator;

import java.util.Date;

import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.FamilyMember;
import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FamilyMemberValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return FamilyMember.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		FamilyMember member = (FamilyMember) target;
		if (member == null) {
			errors.rejectValue("familyMember", "gaac.member.error.general");
		} else {

			if (member.getMember() == null) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member", "gaac.member.error.general");
			} else {
				if (member.getFamilyMemberId() == null) {
					GaacService gs = GaacUtils.getService();
					GaacMember gaacMemberValidator = gs.getGaacMemberByMember(member.getMember());
					Family familyHeaderValidator = gs.getFamilyMember(member.getMember());
					FamilyMember familyMemberValidator = gs.getFamilyMemberByMember(member.getMember());

					if (gaacMemberValidator != null) {

						String gaacIdentifier = gaacMemberValidator.getGaac().getGaacIdentifier();

						errors.rejectValue("member", "gaac.member.error.ingaac", new Object[] { gaacIdentifier },
								" ");

					}

					if (familyHeaderValidator != null ) {

						String familyIdentifier = familyHeaderValidator.getFamilyIdentifier();

						errors.rejectValue("member", "gaac.fmmember.error.infamily",
								new Object[] { familyIdentifier }, " ");
					}
					
					if (familyMemberValidator != null) {

						String familyIdentifier = familyMemberValidator.getFamily().getFamilyIdentifier();

						errors.rejectValue("member", "gaac.fmmember.error.infamily",
								new Object[] { familyIdentifier }, " ");
					}
				}

			}

			if (member.getStartDate() == null) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate", "gaac.member.error.startDate");
			} else {
				if ((member.getFamily() != null) && (member.getStartDate().before(member.getFamily().getStartDate()))) {
					errors.rejectValue("startDate", "gaac.member.error.startDateAfterGaac");
				}
			}

			if (member.getFamilyMemberId() != null) {
				if (member.getLeaving() == true) {

					if (member.getEndDate() == null) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate", "gaac.member.error.endDate");
					} else if (member.getEndDate().after(new Date())) {
						errors.rejectValue("endDate", "gaac.member.error.startDateAfterGaac");
					}

					if (member.getReasonLeaving() == null) {
						ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reasonLeaving",
								"gaac.member.error.reasonLeaving");
					}
				}

			}

		}
	}

}
