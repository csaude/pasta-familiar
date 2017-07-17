package org.openmrs.module.gaac.validator;

import org.openmrs.module.gaac.AffinityType;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class AffinityTypeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return AffinityType.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		AffinityType affinityType = (AffinityType) target;
		if (affinityType == null) {
			errors.rejectValue("affinityType",
					"gaac.affinityType.error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
					"error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
					"error.description");
			if (affinityType.getAffinityTypeId() == null) {
				GaacService gs = GaacUtils.getService();
				AffinityType tempType = gs.getAffinityTypeByName(affinityType
						.getName());
				if (tempType != null)
					errors.rejectValue("name",
							"gaac.affinityType.error.nameinuse");
			}
		}
	}

}
