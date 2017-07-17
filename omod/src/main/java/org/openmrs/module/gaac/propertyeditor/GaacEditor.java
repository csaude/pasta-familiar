package org.openmrs.module.gaac.propertyeditor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.gaac.Gaac;
import org.openmrs.module.gaac.GaacUtils;
import org.openmrs.module.gaac.api.GaacService;
import org.springframework.util.StringUtils;

public class GaacEditor extends PropertyEditorSupport {

	private Log log = LogFactory.getLog(getClass());

	public void setAsText(String text) throws IllegalArgumentException {
		GaacService ps = GaacUtils.getService();
		if (StringUtils.hasText(text))
			try {
				setValue(ps.getGaac(Integer.valueOf(text)));
			} catch (Exception ex) {
				this.log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException("Gaac not found: "
						+ ex.getMessage());
			}
		else
			setValue(null);
	}

	public String getAsText() {
		Gaac t = (Gaac) getValue();
		if (t == null) {
			return "";
		}
		return t.getGaacId().toString();
	}

}
