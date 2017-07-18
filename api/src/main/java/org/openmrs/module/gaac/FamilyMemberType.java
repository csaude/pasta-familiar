package org.openmrs.module.gaac;

import org.openmrs.BaseOpenmrsMetadata;

public class FamilyMemberType extends BaseOpenmrsMetadata{

	private Integer familyTypeMemberId;
	
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return getFamilyTypeMemberId();
	}

	@Override
	public void setId(Integer familyTypeId) {
		setFamilyTypeMemberId(familyTypeId);
		
	}

	public Integer getFamilyTypeMemberId() {
		return this.familyTypeMemberId;
	}

	public void setFamilyTypeMemberId(Integer familyTypeId) {
		this.familyTypeMemberId = familyTypeId;
	}


	@Override
	public int hashCode() {
		if (getFamilyTypeMemberId() == null)
			return super.hashCode();
		return getFamilyTypeMemberId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FamilyMemberType other = (FamilyMemberType) obj;
		if (familyTypeMemberId == null) {
			if (other.familyTypeMemberId != null)
				return false;
		} else if (!familyTypeMemberId.equals(other.familyTypeMemberId))
			return false;
		return true;
	}


}
