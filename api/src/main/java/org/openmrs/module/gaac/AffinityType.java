package org.openmrs.module.gaac;

import org.openmrs.BaseOpenmrsMetadata;

public class AffinityType extends BaseOpenmrsMetadata {

	private Integer affinityTypeId;

	public Integer getId() {
		return getAffinityTypeId();
	}

	public void setId(Integer affinity) {
		setAffinityTypeId(affinity);
	}

	public Integer getAffinityTypeId() {
		return this.affinityTypeId;
	}

	public void setAffinityTypeId(Integer affinityTypeId) {
		this.affinityTypeId = affinityTypeId;
	}

	public int hashCode() {
		if (getAffinityTypeId() == null)
			return super.hashCode();
		return getAffinityTypeId().hashCode();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AffinityType other = (AffinityType) obj;
		if (this.affinityTypeId == null) {
			if (other.affinityTypeId != null)
				return false;
		} else if (!this.affinityTypeId.equals(other.affinityTypeId))
			return false;
		return true;
	}

}
