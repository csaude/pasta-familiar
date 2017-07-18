package org.openmrs.module.gaac;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Location;
import org.openmrs.Patient;

public class Family extends BaseOpenmrsData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Integer familyId;
	private String familyIdentifier;
	private Date startDate;
	private Date endDate;
	private String description;
	private Patient focalPatient;
	private Set<FamilyMember> members;
	private Location location;
	private Boolean crumbled;
	private String reasonCrumbled;
	private Date dateCrumbled;
	

	//Nosso objecto transiente
	private Location newLocation;
	
	@Override
	public Integer getId() {
		
		return getFamilyId();
	}

	@Override
	public void setId(Integer id) {
		setFamilyId(id);
		
	}

	public Integer getFamilyId() {
		return familyId;
	}

	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}

	public String getFamilyIdentifier() {
		return familyIdentifier;
	}

	public void setFamilyIdentifier(String familyIdentifier) {
		this.familyIdentifier = familyIdentifier;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Patient getFocalPatient() {
		return focalPatient;
	}

	public void setFocalPatient(Patient focalPatient) {
		this.focalPatient = focalPatient;
	}


	public Set<FamilyMember> getMembers() {
		return members;
	}

	public void setMembers(Set<FamilyMember> members) {
		this.members = members;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Boolean getCrumbled() {
		return crumbled;
	}

	public void setCrumbled(Boolean crumbled) {
		this.crumbled = crumbled;
	}

	public String getReasonCrumbled() {
		return reasonCrumbled;
	}

	public void setReasonCrumbled(String reasonCrumbled) {
		this.reasonCrumbled = reasonCrumbled;
	}

	public Date getDateCrumbled() {
		return dateCrumbled;
	}

	public void setDateCrumbled(Date dateCrumbled) {
		this.dateCrumbled = dateCrumbled;
	}

	public Location getNewLocation() {
		return newLocation;
	}

	public void setNewLocation(Location newLocation) {
		this.newLocation = newLocation;
	}
	
	
	
	
	
	

}
