package org.openmrs.module.gaac.validator;

import java.util.Date;

import javax.naming.Context;

import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.FamilyMember;
import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class FamilyValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Family.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Family family = (Family) target;
		if (family == null) {
			//Por trocar mensagens
			errors.rejectValue("family", "gaac.fm.error.general");
		} else {
		
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "familyIdentifier",
					"gaac.fm.error.identifier");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate",
					"gaac.fmmember.error.startDate");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "focalPatient",
					"gaac.fmmember.error.startDate");
			if(family.getLocation()==family.getNewLocation()){
				errors.rejectValue("newLocation", "gaac.error.location");	
			}
			
			
			
			if (family.getFamilyId() == null) {
				GaacService gs = GaacUtils.getService();
				
				Family familyIdentifierValidator = gs.getFamilyByIdentifier(family.getFamilyIdentifier());
				GaacMember gaacMemberValidator = gs.getGaacMemberByMember(family.getFocalPatient());
				Family familyHeaderValidator = gs.getFamilyMember(family.getFocalPatient());
				FamilyMember familyMemberValidator = gs.getFamilyMemberByMember(family.getFocalPatient());

				if (gaacMemberValidator != null) {

					String gaacIdentifier = gaacMemberValidator.getGaac().getGaacIdentifier();

					errors.rejectValue("focalPatient", "gaac.member.error.ingaac", new Object[] { gaacIdentifier }," ");
				
				}

				if (familyHeaderValidator != null || familyMemberValidator != null) {

		       	String familyIdentifier = familyHeaderValidator.getFamilyIdentifier();
					
		     	errors.rejectValue("focalPatient", "gaac.fmmember.error.infamily", new Object[] { familyIdentifier }," ");
				}
				
				if(familyIdentifierValidator != null){
					
					errors.rejectValue("familyIdentifier","gaac.fm.error.identifier.inuse");}	
				
				
			}else
			{
				
				if(family.getCrumbled()==true){
				
				if(family.getDateCrumbled()==null){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateCrumbled",
						"gaac.error.dateCrumbled"); 
				}else if(family.getDateCrumbled().after(new Date())){
					errors.rejectValue("dateCrumbled",
							"gaac.manage.error.DateCrumbled");
			}
				
			}
				
			
			}
			
			if(family.getLocation()==null){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location",
						"gaac.error.location"); 
			}
			
			
			
		}
	}

	}
