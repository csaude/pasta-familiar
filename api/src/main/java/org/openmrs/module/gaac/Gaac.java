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
package org.openmrs.module.gaac;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.BaseOpenmrsMetadata;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Location;
import org.openmrs.Patient;

/**
 * It is a model class. It should extend either {@link BaseOpenmrsObject} or
 * {@link BaseOpenmrsMetadata}.
 */
public class Gaac extends BaseOpenmrsData implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer gaacId;
	private String gaacIdentifier;
	private Date startDate;
	private Date endDate;
	private String name;
	private String description;
	private Patient focalPatient;
	private AffinityType affinity;
	private Set<GaacMember> members;
	private Location location;
	private Boolean crumbled;
	private String reasonCrumbled;
	private Date dateCrumbled;
	
	//Nosso objecto transiente
	private Location newLocation;

	public Integer getGaacId() {
		return this.gaacId;
	}

	public void setGaacId(Integer gaacId) {
		this.gaacId = gaacId;
	}

	public Integer getId() {
		return getGaacId();
	}

	public void setId(Integer id) {
		setGaacId(id);
	}

	public String getGaacIdentifier() {
		return this.gaacIdentifier;
	}

	public void setGaacIdentifier(String gaacIdentifier) {
		this.gaacIdentifier = gaacIdentifier;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Patient getFocalPatient() {
		return this.focalPatient;
	}

	public void setFocalPatient(Patient focalPatient) {
		this.focalPatient = focalPatient;
	}

	public AffinityType getAffinity() {
		return this.affinity;
	}

	public void setAffinity(AffinityType affinity) {
		this.affinity = affinity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<GaacMember> getMembers() {
		return this.members;
	}

	public void setMembers(Set<GaacMember> members) {
		this.members = members;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Boolean getCrumbled() {
		return this.crumbled;
	}

	public void setCrumbled(Boolean crumbled) {
		this.crumbled = crumbled;
	}

	public String getReasonCrumbled() {
		return this.reasonCrumbled;
	}

	public void setReasonCrumbled(String reasonCrumbled) {
		this.reasonCrumbled = reasonCrumbled;
	}

	public Date getDateCrumbled() {
		return this.dateCrumbled;
	}

	public void setDateCrumbled(Date dateCrumbled) {
		this.dateCrumbled = dateCrumbled;
	}
	
	public Location getNewLocation() {
		return this.newLocation;
	}

	public void setNewLocation(Location newLocation) {
		this.newLocation = newLocation;
	}

}