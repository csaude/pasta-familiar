package org.openmrs.module.gaac.propertyeditor;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.util.StringUtils;

public class DateEditor extends PropertiesEditor {

	private Log log = LogFactory.getLog(getClass());

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text))
			try {
				setValue(DateFormat.getInstance().parse(text));
			} catch (Exception ex) {
				this.log.error("Error setting text: " + text, ex);
				throw new IllegalArgumentException(
						"Date not parsed correctely: " + ex.getMessage());
			}
		else
			setValue(null);
	}

	public String getAsText() {
		Date t = (Date) getValue();
		if (t == null) {
			return "";
		}
		return t.toString();
	}

}
