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
package org.openmrs.module.gaac.api.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.gaac.AffinityType;
import org.openmrs.module.gaac.Family;
import org.openmrs.module.gaac.FamilyMember;
import org.openmrs.module.gaac.FamilyMemberType;
import org.openmrs.module.gaac.Gaac;
import org.openmrs.module.gaac.GaacMember;
import org.openmrs.module.gaac.ReasonLeavingGaacType;
import org.openmrs.module.gaac.api.GaacService;
import org.openmrs.module.gaac.api.db.GaacServiceDAO;

/**
 * It is a default implementation of {@link GaacService}.
 */
public class GaacServiceImpl extends BaseOpenmrsService implements
		GaacService {

	protected final Log log = LogFactory.getLog(this.getClass());

	private GaacServiceDAO dao;

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(GaacServiceDAO dao) {
		this.dao = dao;
	}

	/**
	 * @return the dao
	 */
	public GaacServiceDAO getDao() {
		return dao;
	}
	
	   public AffinityType saveAffinityType(AffinityType affinityType)
	   {
	     return this.dao.saveAffinityType(affinityType);
	   }
	 
	   public AffinityType getAffinityTypeById(Integer affinityId)
	     throws APIException
	   {
	    return this.dao.getAffinityType(affinityId);
	   }
	 
	   public AffinityType getAffinityTypeByUUID(String uuid)
	     throws APIException
	   {
	     return this.dao.getAffinityByUUID(uuid);
	   }
	 
	   public AffinityType getAffinityTypeByName(String name)
	     throws APIException
	   {
	   return this.dao.getAffinityType(name);
	   }
	 
	   public List<AffinityType> getAllAffinityType()
	     throws APIException
	   {
	     return this.dao.getAllAffinityTypes(Boolean.valueOf(false));
	   }
	 
	   public List<AffinityType> getAllAffinityType(Boolean includeRetired)
	     throws APIException
	   {
	    return this.dao.getAllAffinityTypes(includeRetired);
	   }
	 
	   public AffinityType retireAffinityType(AffinityType affinityType, String reason)
	     throws APIException
	   {
	     if (reason == null)
	       throw new IllegalArgumentException("the argument 'reason' is null");
	    affinityType.setRetired(Boolean.valueOf(true));
	     affinityType.setRetireReason(reason);
	     this.dao.saveAffinityType(affinityType);
	     return affinityType;
	   }
	 
	   public AffinityType unretireAffinityType(AffinityType affinityType)
	     throws APIException
	   {
	     affinityType.setRetired(Boolean.valueOf(false));
	     this.dao.saveAffinityType(affinityType);
	     return affinityType;
	   }
	 
	   public void purgeAffinityType(AffinityType affinityType)
	     throws APIException
	   {
	     this.dao.deleteAffinityType(affinityType);
	   }
	 
	   public ReasonLeavingGaacType saveReasonLeavingGaacType(ReasonLeavingGaacType reason)
	   {
	     return this.dao.saveReasonLeavingGaacType(reason);
	   }
	 
	   public ReasonLeavingGaacType getReasonLeavingGaacType(Integer reasonLeavingGaacTypeId)
	     throws APIException
	   {
	     return this.dao.getReasonLeavingGaacType(reasonLeavingGaacTypeId);
	   }
	 
	   public ReasonLeavingGaacType getReasonLeavingGaacTypeByUUID(String uuid)
	     throws APIException
	   {
	     return this.dao.getReasonLeavingGaacTypeByUUID(uuid);
	   }
	 
	   public ReasonLeavingGaacType getReasonLeavingGaacType(String name)
	     throws APIException
	   {
	     return this.dao.getReasonLeavingGaacType(name);
	   }
	 
	   public List<ReasonLeavingGaacType> getAllReasonLeavingGaacType()
	     throws APIException
	   {
	     return this.dao.getAllReasonLeavingGaacTypes(Boolean.valueOf(false));
	   }
	 
	   public List<ReasonLeavingGaacType> getAllReasonLeavingGaacType(Boolean includeRetired)
	     throws APIException
	   {
	    return this.dao.getAllReasonLeavingGaacTypes(includeRetired);
	   }
	 
	   public ReasonLeavingGaacType retireReasonLeavingGaacType(ReasonLeavingGaacType reasonLeavingGaacType, String reason)
	     throws APIException
	   {
	     if (reason == null)
	       throw new IllegalArgumentException("The Argument 'reason' is null");
	     reasonLeavingGaacType.setRetired(Boolean.TRUE);
	     reasonLeavingGaacType.setRetireReason(reason);
	     this.dao.saveReasonLeavingGaacType(reasonLeavingGaacType);
	     return reasonLeavingGaacType;
	   }
	 
	   public ReasonLeavingGaacType unretireReasonLeavingGaacType(ReasonLeavingGaacType reasonLeavingGaacType)
	     throws APIException
	   {
	     reasonLeavingGaacType.setRetired(Boolean.FALSE);
	     this.dao.saveReasonLeavingGaacType(reasonLeavingGaacType);
	     return reasonLeavingGaacType;
	   }
	 
	   public void purgeReasonLeavingGaacType(ReasonLeavingGaacType reasonLeavingGaacType)
	     throws APIException
	   {
	     this.dao.deleteReasonLeavingGaacType(reasonLeavingGaacType);
	   }
	 
	   public Gaac saveGaac(Gaac gaac)
	   {
	     return this.dao.saveGaac(gaac);
	   }
	 
	   public Gaac getGaac(Integer gaacId)
	     throws APIException
	   {
	     return this.dao.getGaac(gaacId);
	   }
	 
	   public Gaac getGaacByName(String name)
	     throws APIException
	   {
	    return this.dao.getGaacByName(name);
	   }
	 
	   public Gaac getGaacByUUID(String uuid)
	     throws APIException
	   {
	     return this.dao.getGaacByUUID(uuid);
	   }
	 
	   public Gaac getGaacByIdentifier(String identifier)
	     throws APIException
	   {
	     return this.dao.getGaacByIdentifier(identifier);
	   }
	 
	   public List<Gaac> getAllOfLocation(Location location)
	     throws APIException
	   {
	     return this.dao.getAllOfLocation(location);
	   }
	 
	   public List<Gaac> getAllOfAffinity(AffinityType affinity)
	     throws APIException
	   {
	     return this.dao.getAllOfAffinity(affinity);
	   }
	 
	   public List<Gaac> getAllGaac()
	     throws APIException
	   {
	     return this.dao.getAllGaac(Boolean.valueOf(false));
	   }
	 
	   public List<Gaac> getAllGaac(Boolean includeVoided)
	     throws APIException
	   {
	    return this.dao.getAllGaac(includeVoided);
	   }
	 
	   public List<Gaac> getAllGaacEnrolled(Date startDate, Date endDate, Location location, AffinityType affinity)
	     throws APIException
	   {
	     return this.dao.getAllGaacEnrolled(startDate, endDate, location, affinity);
	   }
	 
	   public Gaac retireGaac(Gaac gaac, String reason) throws APIException
	   {
	     if (reason == null)
	       throw new IllegalArgumentException(
	         "The Argument 'reason' shoul not be null");
	     gaac.setVoided(Boolean.valueOf(true));
	     gaac.setVoidReason(reason);
	     return this.dao.saveGaac(gaac);
	   }
	 
	   public Gaac unretireGaac(Gaac gaac) throws APIException
	   {
	     gaac.setVoided(Boolean.valueOf(false));
	     gaac.setVoidReason(null);
	     return this.dao.saveGaac(gaac);
	   }
	 
	   public void purgeGaac(Gaac gaac) throws APIException
	   {
	     this.dao.deleteGaac(gaac);
	   }
	 
	   public GaacMember saveGaacMember(GaacMember member)
	   {
	     return this.dao.saveGaacMember(member);
	   }
	 
	   public GaacMember getGaacMember(Integer gaacMemberId)
	     throws APIException
	   {
	     return this.dao.getGaacMember(gaacMemberId);
	   }
	 
	   public GaacMember getGaacMemberByMember(Patient member)
	     throws APIException
	   {
	     return this.dao.getGaacMemberByMember(member);
	   }
	   
	   //Damasceno
	   public List<GaacMember> getAllGaacMemberHistory(Patient member)
			     throws APIException
			   {
			    return this.dao.getAllGaacMemberHistory(member);
			   }
	   
	   public List<Gaac> getGaacByIdentifierAndLocation(String identifier,Location paramLocation)
			     throws APIException
			   {
			     return this.dao.getGaacByIdentifierAndLocation(identifier,paramLocation);
			   }

	@Override
	public Family getFamily(Integer paramInteger) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Family saveFamily(Family paramFamily) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purgeFamily(Family paramFamily) throws APIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Family unretireFamily(Family paramFamily) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Family> getAllFamilyEnrolled(Date paramDate1, Date paramDate2, Location paramLocation,
			FamilyMemberType paramFamilyType) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Family> getAllFamily(Boolean paramBoolean) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Family getFamilyByUUID(String paramString) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Family> getAllOfLocationFamily(Location paramLocation) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Family getFamilyByIdentifier(String paramString) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FamilyMember> getAllFamilyMemberHistory(Patient paramPatient) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FamilyMember> getAllOfFamilyType(FamilyMemberType paramFamilyType) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Family getFamilyMember(Patient paramPatient) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Family retireFamily(Family paramFamily, String paramString) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Family> getAllFamily() throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void purgeFamilyType(FamilyMemberType paramFamilyMemberType) throws APIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FamilyMemberType> getAllFamilyType() throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMemberType getFamilyTypeByName(String paramString) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMemberType getFamilyTypeById(Integer paramInteger) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMemberType saveFamilyType(FamilyMemberType paramFamilyMemberType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMemberType getFamilyTypeByUUID(String paramString) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FamilyMemberType> getAllFamilyType(Boolean paramBoolean) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMemberType retireFamilyType(FamilyMemberType paramFamilyMemberType, String paramString)
			throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMemberType unretireFamilyType(FamilyMemberType paramFamilyMemberType) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMember getFamilyMemberByMember(Patient paramPatient) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMember getFamilyMember(Integer paramInteger) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FamilyMember saveFamilyMember(FamilyMember paramFamilyMember) {
		// TODO Auto-generated method stub
		return null;
	}
}