package org.openmrs.module.gaac;

import org.openmrs.BaseOpenmrsMetadata;

public class ReasonLeavingGaacType extends BaseOpenmrsMetadata {

	private Integer reasonLeavingTypeId;

	public ReasonLeavingGaacType() {
	}

	public ReasonLeavingGaacType(Integer reasonId) {
		setReasonLeavingTypeId(reasonId);
	}

	public ReasonLeavingGaacType(String name, String description) {
		setName(name);
		setDescription(description);
	}

	public Integer getId() {
		return getReasonLeavingTypeId();
	}

	public void setId(Integer reasonId) {
		setReasonLeavingTypeId(reasonId);
	}

	public Integer getReasonLeavingTypeId() {
		return this.reasonLeavingTypeId;
	}

	public void setReasonLeavingTypeId(Integer reasonLeavingTypeId) {
		this.reasonLeavingTypeId = reasonLeavingTypeId;
	}

	public int hashCode() {
		if (getReasonLeavingTypeId() == null)
			return super.hashCode();
		return getReasonLeavingTypeId().hashCode();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReasonLeavingGaacType other = (ReasonLeavingGaacType) obj;
		if (this.reasonLeavingTypeId == null) {
			if (other.reasonLeavingTypeId != null)
				return false;
		} else if (!this.reasonLeavingTypeId.equals(other.reasonLeavingTypeId))
			return false;
		return true;
	}

}
