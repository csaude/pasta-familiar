package org.openmrs.module.gaac;

import java.util.Date;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.Patient;
import org.openmrs.Relationship;

public class FamilyMember extends BaseOpenmrsData {

	private Integer familyMemberId;
	private Patient member;
	private Family family;
	private Date startDate;
	private Date endDate;
	private ReasonLeavingGaacType reasonLeaving;
	private Date restartDate;
	private Boolean leaving;
	private Boolean restart;
	private Relationship relacao;

	public Relationship getRelacao() {
		return relacao;
	}

	public void setRelacao(Relationship relacao) {
		this.relacao = relacao;
	}

	@Override
	public Integer getId() {

		return getFamilyMemberId();
	}

	@Override
	public void setId(Integer id) {

		setFamilyMemberId(id);
	}

	public Integer getFamilyMemberId() {
		return familyMemberId;
	}

	public void setFamilyMemberId(Integer familyMemberId) {
		this.familyMemberId = familyMemberId;
	}

	public Patient getMember() {
		return member;
	}

	public void setMember(Patient member) {
		this.member = member;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
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

	public ReasonLeavingGaacType getReasonLeaving() {
		return reasonLeaving;
	}

	public void setReasonLeaving(ReasonLeavingGaacType reasonLeaving) {
		this.reasonLeaving = reasonLeaving;
	}

	public Date getRestartDate() {
		return restartDate;
	}

	public void setRestartDate(Date restartDate) {
		this.restartDate = restartDate;
	}

	public Boolean getLeaving() {
		return leaving;
	}

	public void setLeaving(Boolean leaving) {
		this.leaving = leaving;
	}

	public Boolean getRestart() {
		return restart;
	}

	public void setRestart(Boolean restart) {
		this.restart = restart;
	}

}
