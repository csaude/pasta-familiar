package org.openmrs.module.gaac.validator;

import java.util.Date;

import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GaacMemberValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return GaacMember.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		GaacMember member = (GaacMember) target;
		if (member == null) {
			errors.rejectValue("gaacMember", "gaac.member.error.general");
		} else {
			
		if(member.getMember()==null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "member",
					"gaac.member.error.general");
		}else{
			if (member.getGaacMemberId() == null) {
				GaacService gs = GaacUtils.getService();
				GaacMember temp = gs.getGaacMemberByMember(member.getMember());
				if (temp != null) {
					errors.rejectValue("member", "gaac.member.error.ingaac");
				}
			}
			
			if(member.getMember().getAge()<15){
				errors.rejectValue("member",
						"gaac.member.error.age");
			}
			
		}
		
			
		if(member.getStartDate()==null){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate",
					"gaac.member.error.startDate");
		}else{
			if ((member.getGaac() != null) && (member.getStartDate().before(member.getGaac().getStartDate()))) {
				errors.rejectValue("startDate",
						"gaac.member.error.startDateAfterGaac");
			}	
		}
		
		if(member.getGaacMemberId() != null){
			if(member.getLeaving()==true){
				
				if(member.getEndDate()==null){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endDate",
							"gaac.member.error.endDate");
				}else if(member.getEndDate().after(new Date())){
					errors.rejectValue("endDate",
							"gaac.member.error.startDateAfterGaac");
				}
				
				if(member.getReasonLeaving()==null){
					ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reasonLeaving",
							"gaac.member.error.reasonLeaving");
				}
			}
			
			
		}
		
			
		

			
		}
	}

}
