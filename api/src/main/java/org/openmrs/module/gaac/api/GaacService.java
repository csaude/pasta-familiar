/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.gaac.api;

import java.util.Date;
import java.util.List;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.gaac.AffinityType;
import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.FamilyMember;
import org.openmrs.module.gaac.Gaac;
import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.ReasonLeavingGaacType;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean
 * which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(GaacService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface GaacService extends OpenmrsService {

	@Authorized({ "Manage Affinity Types" })
	public AffinityType saveAffinityType(AffinityType paramAffinityType);

	@Transactional(readOnly = true)
	@Authorized({ "View Affinity Types" })
	public AffinityType getAffinityTypeById(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Affinity Types" })
	public AffinityType getAffinityTypeByUUID(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Affinity Types" })
	public AffinityType getAffinityTypeByName(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Affinity Types" })
	public List<AffinityType> getAllAffinityType() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Affinity Types" })
	public List<AffinityType> getAllAffinityType(Boolean paramBoolean) throws APIException;

	@Authorized({ "Manage Affinity Types" })
	public AffinityType retireAffinityType(AffinityType paramAffinityType, String paramString) throws APIException;

	@Authorized({ "Manage Affinity Types" })
	public AffinityType unretireAffinityType(AffinityType paramAffinityType) throws APIException;

	@Authorized({ "Manage Affinity Types" })
	public void purgeAffinityType(AffinityType paramAffinityType) throws APIException;

	@Authorized({ "Manage Reason Leaving Gaac Types" })
	public ReasonLeavingGaacType saveReasonLeavingGaacType(ReasonLeavingGaacType paramReasonLeavingGaacType);

	@Transactional(readOnly = true)
	@Authorized({ "View Reason Leaving Gaac Types" })
	public ReasonLeavingGaacType getReasonLeavingGaacType(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Reason Leaving Gaac Types" })
	public ReasonLeavingGaacType getReasonLeavingGaacTypeByUUID(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Reason Leaving Gaac Types" })
	public ReasonLeavingGaacType getReasonLeavingGaacType(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Reason Leaving Gaac Types" })
	public List<ReasonLeavingGaacType> getAllReasonLeavingGaacType() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Reason Leaving Gaac Types" })
	public List<ReasonLeavingGaacType> getAllReasonLeavingGaacType(Boolean paramBoolean) throws APIException;

	@Authorized({ "Manage Reason Leaving Gaac Types" })
	public ReasonLeavingGaacType retireReasonLeavingGaacType(ReasonLeavingGaacType paramReasonLeavingGaacType,
			String paramString) throws APIException;

	@Authorized({ "Manage Reason Leaving Gaac Types" })
	public ReasonLeavingGaacType unretireReasonLeavingGaacType(ReasonLeavingGaacType paramReasonLeavingGaacType)
			throws APIException;

	@Authorized({ "Manage Reason Leaving Gaac Types" })
	public void purgeReasonLeavingGaacType(ReasonLeavingGaacType paramReasonLeavingGaacType) throws APIException;

	@Authorized({ "Manage Gaac" })
	public Gaac saveGaac(Gaac paramGaac);

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public Gaac getGaac(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public Gaac getGaacByName(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public Gaac getGaacByUUID(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public Gaac getGaacByIdentifier(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public List<Gaac> getAllOfLocation(Location paramLocation) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public List<Gaac> getAllOfAffinity(AffinityType paramAffinityType) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public List<Gaac> getAllGaac() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public List<Gaac> getAllGaac(Boolean paramBoolean) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public List<Gaac> getAllGaacEnrolled(Date paramDate1, Date paramDate2, Location paramLocation,
			AffinityType paramAffinityType) throws APIException;

	@Authorized({ "Manage Gaac" })
	public Gaac retireGaac(Gaac paramGaac, String paramString) throws APIException;

	@Authorized({ "Manage Gaac" })
	public Gaac unretireGaac(Gaac paramGaac) throws APIException;

	@Authorized({ "Manage Gaac" })
	public void purgeGaac(Gaac paramGaac) throws APIException;

	@Authorized({ "Manage Gaac Member" })
	public GaacMember saveGaacMember(GaacMember paramGaacMember);

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac Member" })
	public GaacMember getGaacMember(Integer paramInteger) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac Member" })
	public GaacMember getGaacMemberByMember(Patient paramPatient) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public List<GaacMember> getAllGaacMemberHistory(Patient paramPatient) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Gaac" })
	public List<Gaac> getGaacByIdentifierAndLocation(String identifier, Location paramLocation) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family" })
	public Family getFamily(Integer paramInteger) throws APIException;

	@Authorized({ "Manage Family" })
	public Family saveFamily(Family paramFamily);

	@Authorized({ "Manage Family" })
	public void purgeFamily(Family paramFamily) throws APIException;

	@Authorized({ "Manage Family" })
	public Family unretireFamily(Family paramFamily) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family" })
	public List<Family> getAllFamily(Boolean paramBoolean) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family" })
	public Family getFamilyByUUID(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family" })
	public List<Family> getAllOfLocationFamily(Location paramLocation) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family" })
	public Family getFamilyByIdentifier(String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family" })
	public List<FamilyMember> getAllFamilyMemberHistory(Patient paramPatient) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family " })
	public Family getFamilyMember(Patient paramPatient) throws APIException;

	@Authorized({ "Manage Family" })
	public Family retireFamily(Family paramFamily, String paramString) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family" })
	public List<Family> getAllFamily() throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family Member" })
	public FamilyMember getFamilyMemberByMember(Patient paramPatient) throws APIException;

	@Transactional(readOnly = true)
	@Authorized({ "View Family Member" })
	public FamilyMember getFamilyMember(Integer paramInteger) throws APIException;

	@Authorized({ "Manage Family Member" })
	public FamilyMember saveFamilyMember(FamilyMember paramFamilyMember);

}
