package org.openmrs.module.gaac.validator;

import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.ReasonLeavingGaacType;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ReasonLeavingGaacTypeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return ReasonLeavingGaacType.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ReasonLeavingGaacType reasonLeavingType = (ReasonLeavingGaacType) target;
		if (reasonLeavingType == null) {
			errors.rejectValue("reasonLeavingType",
					"gaac.reasonLeaving.error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
					"error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
					"error.description");

			if (reasonLeavingType.getReasonLeavingTypeId() == null) {
				GaacService gs = GaacUtils.getService();
				ReasonLeavingGaacType tempType = gs
						.getReasonLeavingGaacType(reasonLeavingType.getName());
				if (tempType != null)
					errors.rejectValue("name",
							"gaac.reasonLeaving.error.nameinuse");
			}
		}
	}

}
