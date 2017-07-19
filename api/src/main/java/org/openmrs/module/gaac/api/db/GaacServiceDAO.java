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
package org.openmrs.module.gaac.api.db;

import java.util.Date;
import java.util.List;

import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.gaac.AffinityType;
import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.FamilyMember;
import org.openmrs.module.gaac.FamilyMemberType;
import org.openmrs.module.gaac.Gaac;
import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.ReasonLeavingGaacType;
import org.openmrs.module.gaac.api.GaacService;

/**
 * Database methods for {@link GaacService}.
 */
public interface GaacServiceDAO {

	public AffinityType saveAffinityType(AffinityType paramAffinityType);

	public void deleteAffinityType(AffinityType paramAffinityType)
			throws DAOException;

	public AffinityType getAffinityType(Integer paramInteger)
			throws DAOException;

	public AffinityType getAffinityType(String paramString) throws DAOException;

	public AffinityType getAffinityByUUID(String paramString)
			throws DAOException;

	public List<AffinityType> getAllAffinityTypes(Boolean paramBoolean)
			throws DAOException;

	public ReasonLeavingGaacType saveReasonLeavingGaacType(
			ReasonLeavingGaacType paramReasonLeavingGaacType);

	public void deleteReasonLeavingGaacType(
			ReasonLeavingGaacType paramReasonLeavingGaacType)
			throws DAOException;

	public ReasonLeavingGaacType getReasonLeavingGaacType(Integer paramInteger)
			throws DAOException;

	public ReasonLeavingGaacType getReasonLeavingGaacType(String paramString)
			throws DAOException;

	public ReasonLeavingGaacType getReasonLeavingGaacTypeByUUID(
			String paramString) throws DAOException;

	public List<ReasonLeavingGaacType> getAllReasonLeavingGaacTypes(
			Boolean paramBoolean) throws DAOException;

	public Gaac saveGaac(Gaac paramGaac);

	public Gaac getGaac(Integer paramInteger) throws DAOException;

	public Gaac getGaacByName(String paramString) throws DAOException;

	public Gaac getGaacByUUID(String paramString) throws DAOException;

	public Gaac getGaacByIdentifier(String paramString) throws DAOException;

	public List<Gaac> getAllOfLocation(Location paramLocation)
			throws DAOException;

	public List<Gaac> getAllOfAffinity(AffinityType paramAffinityType)
			throws DAOException;

	public List<Gaac> getAllGaac(Boolean paramBoolean) throws DAOException;

	public List<Gaac> getAllGaacEnrolled(Date paramDate1, Date paramDate2,
			Location paramLocation, AffinityType paramAffinityType)
			throws DAOException;

	public void deleteGaac(Gaac paramGaac) throws DAOException;

	public GaacMember saveGaacMember(GaacMember paramGaacMember);

	public GaacMember getGaacMember(Integer paramInteger) throws DAOException;

	public GaacMember getGaacMemberByMember(Patient paramPatient)
			throws DAOException;
	
	//Damasceno
	public List<GaacMember> getAllGaacMemberHistory(Patient paramPatient) throws DAOException;
	public List<Gaac> getGaacByIdentifierAndLocation(String paramString,Location paramLocation) throws DAOException;
	
	
	//Sacur Pasta Familiar
	public FamilyMemberType saveFamilyType(FamilyMemberType paramFamilyType);

	public void deleteFamilyType(FamilyMemberType paramFamilyType) throws DAOException;

	public FamilyMemberType getFamilyType(Integer paramInteger) throws DAOException;

	public FamilyMemberType getFamilyType(String paramString) throws DAOException;

	public FamilyMemberType getFamilyTypeByUUID(String paramString) throws DAOException;

	public List<FamilyMemberType> getAllFamilyTypes(Boolean paramBoolean) throws DAOException;	
	public Family saveFamily(Family paramFamily);

	public Family getFamily(Integer paramInteger) throws DAOException;

	public Family getFamilyByUUID(String paramString) throws DAOException;

	public Family getFamilyByIdentifier(String paramString) throws DAOException;
	
	public Family getFamilyMember(Patient paramPatient) throws DAOException;

	public List<Family> getAllOfLocationFamily(Location paramLocation) throws DAOException;

	public List<Family> getAllFamily(Boolean paramBoolean) throws DAOException;

	public List<Family> getAllFamilyEnrolled(Date paramDate1, Date paramDate2, Location paramLocation,
			FamilyMemberType paramFamilyType) throws DAOException;

	public void deleteFamily(Family paramFamily) throws DAOException;

	public FamilyMember saveFamilyMember(FamilyMember paramFamilyMember);

	public FamilyMember getFamilyMember(Integer paramInteger) throws DAOException;

	public FamilyMember getFamilyMemberByMember(Patient paramPatient) throws DAOException;

	public List<FamilyMember> getAllFamilyMemberHistory(Patient paramPatient) throws DAOException;

	public List<FamilyMember> getAllOfFamilyType( FamilyMemberType paramFamilyType) throws DAOException;
	
}