package org.openmrs.module.gaac;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.gaac.api.GaacService;

public class GaacUtils {

	protected final Log log = LogFactory.getLog(getClass());

	public static GaacService getService() {
		return (GaacService) Context.getService(GaacService.class);
	}

}
