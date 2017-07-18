package org.openmrs.module.gaac.validator;

import java.util.Date;
import java.util.List;

import org.openmrs.module.gaac.Gaac;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GaacValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Gaac.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		Gaac gaac = (Gaac) target;
		if (gaac == null) {
			errors.rejectValue("gaac", "gaac.error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gaacIdentifier",
					"gaac.error.identifier");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
					"error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDate",
					"gaac.member.error.startDate");
			
			if(gaac.getLocation()==gaac.getNewLocation()){
				errors.rejectValue("newLocation", "gaac.error.location");	
			}
			
			if (gaac.getGaacId() == null) {
				GaacService gs = GaacUtils.getService();
				Gaac temp = gs.getGaacByName(gaac.getName());
				if (temp != null) {
					errors.rejectValue("name", "gaac.error.name.inuse");
				}
				temp = gs.getGaacByIdentifier(gaac.getGaacIdentifier());
				if (temp != null){
					errors.rejectValue("gaacIdentifier",
							"gaac.error.identifier.inuse");}
			}else
			{
				
				if(gaac.getCrumbled()==true){
				
				if(gaac.getDateCrumbled()==null){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateCrumbled",
						"gaac.error.dateCrumbled"); 
				}else if(gaac.getDateCrumbled().after(new Date())){
					errors.rejectValue("dateCrumbled",
							"gaac.manage.error.DateCrumbled");
			}
				
			}
				
				GaacService gs = GaacUtils.getService();
				Gaac temp2 = gs.getGaac(gaac.getGaacId());
				if(temp2.getGaacIdentifier()!=gaac.getGaacIdentifier()){
					List<Gaac> temp3 = gs.getGaacByIdentifierAndLocation(gaac.getGaacIdentifier(),gaac.getLocation());
					if (temp3 != null){
						errors.rejectValue("gaacIdentifier",
								"gaac.error.identifier.inuse");}
				
				}
			
			}
			
			if(gaac.getLocation()==null){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location",
						"gaac.error.location"); 
			}
			if(gaac.getAffinity()==null){
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "affinity",
						"gaac.error.affinity"); 
			}
			
			
		}
	}

}
