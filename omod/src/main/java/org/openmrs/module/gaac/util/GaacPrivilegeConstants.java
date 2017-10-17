package org.openmrs.module.gaac.util;

import org.openmrs.annotation.AddOnStartup;
import org.openmrs.util.PrivilegeConstants;

public class GaacPrivilegeConstants extends PrivilegeConstants{
	
	@AddOnStartup(description="Able to add/edit/retire affinity types")
	  public static final String MANAGE_AFFINITY_TYPES = "Manage Affinity Types";

	  @AddOnStartup(description="Able to view affinity types")
	  public static final String VIEW_AFFINITY_TYPES = "View Affinity Types";

	  @AddOnStartup(description="Able to add/edit/retire reason leaving gaac types")
	  public static final String MANAGE_REASON_LEAVING_GAAC_TYPES = "Manage Reason Leaving Gaac Types";

	  @AddOnStartup(description="Able to view reason leaving gaac types")
	  public static final String VIEW_REASON_LEAVING_GAAC_TYPES = "View Reason Leaving Gaac Types";

	  @AddOnStartup(description="Able to add/edit/retire gaac")
	  public static final String MANAGE_GAAC = "Manage Gaac";

	  @AddOnStartup(description="Able to view gaac")
	  public static final String VIEW_GAAC = "View Gaac";

	  @AddOnStartup(description="Able to add/edit/retire gaac member")
	  public static final String MANAGE_GAAC_MEMBER = "Manage Gaac Member";

	  @AddOnStartup(description="Able to view gaac member")
	  public static final String VIEW_GAAC_MEMBER = "View Gaac Member";
	  
	  @AddOnStartup(description="Able to add/edit/retire family")
	  public static final String MANAGE_FAMILY = "Manage Family";

	  @AddOnStartup(description="Able to view family")
	  public static final String VIEW_FAMILY = "View Family";
	  
	  
	  @AddOnStartup(description="Able to add/edit/retire gaac member")
	  public static final String MANAGE_FAMILY_MEMBER = "Manage Family Member";

	  @AddOnStartup(description="Able to view gaac member")
	  public static final String VIEW_FAMILY_MEMBER = "View Family Member";

}
