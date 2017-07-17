package org.openmrs.module.gaac;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;

public class GaacMember extends BaseOpenmrsData {

	private Integer gaacMemberId;
	private Patient member;
	private Gaac gaac;
	private Date startDate;
	private Date endDate;
	private ReasonLeavingGaacType reasonLeaving;
	private String description;
	private Date restartDate;
	private Boolean leaving;
	private Boolean restart;

	public Integer getId() {
		return getGaacMemberId();
	}

	public void setId(Integer arg0) {
		setGaacMemberId(arg0);
	}

	public Integer getGaacMemberId() {
		return this.gaacMemberId;
	}

	public void setGaacMemberId(Integer gaacMemberId) {
		this.gaacMemberId = gaacMemberId;
	}

	public Patient getMember() {
		return this.member;
	}

	public void setMember(Patient member) {
		this.member = member;
	}

	public Gaac getGaac() {
		return this.gaac;
	}

	public void setGaac(Gaac gaac) {
		this.gaac = gaac;
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

	public ReasonLeavingGaacType getReasonLeaving() {
		return this.reasonLeaving;
	}

	public void setReasonLeaving(ReasonLeavingGaacType reasonLeaving) {
		this.reasonLeaving = reasonLeaving;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRestartDate() {
		return this.restartDate;
	}

	public void setRestartDate(Date restartDate) {
		this.restartDate = restartDate;
	}

	public Boolean getLeaving() {
		return this.leaving;
	}

	public void setLeaving(Boolean leaving) {
		this.leaving = leaving;
	}

	public Boolean getRestart() {
		return this.restart;
	}

	public void setRestart(Boolean restart) {
		this.restart = restart;
	}

}
