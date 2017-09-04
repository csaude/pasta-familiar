package org.openmrs.module.gaac.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.util.StringUtils;

public class FamilyEditor extends PropertyEditorSupport {

	private Log log = LogFactory.getLog(getClass());

	public void setAsText(String text) throws IllegalArgumentException {
		GaacService ps = GaacUtils.getService();
		if (StringUtils.hasText(text))
			try {
				setValue(ps.getFamily(Integer.valueOf(text)));
			} catch (Exception ex) {
				this.log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Family not found: "
						+ ex.getMessage());
			}
		else
			setValue(null);
	}

	public String getAsText() {
		Family t = (Family) getValue();
		if (t == null) {
			return "";
		}
		return t.getFamilyId().toString();
	}

}
