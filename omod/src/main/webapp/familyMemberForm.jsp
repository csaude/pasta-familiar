<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Family Member" otherwise="/login.htm"
	redirect="/module/gaac/familyMemberForm.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<openmrs:htmlInclude file="/scripts/dojoConfig.js" />
<openmrs:htmlInclude file="/scripts/dojo/dojo.js" />

<script type="text/javascript">
	dojo.addOnLoad(function() {
		voidedClicked(document.getElementById("voided"));
		leavingClicked(document.getElementById("leaving"));
		restartClicked(document.getElementById("restart"));
	})
</script>

<script type="text/javascript">
	function confirmPurge() {
		if (confirm("Tem certeza que pretende apagar este membro? Será permanentemente eliminado")) {
			return true;
		} else {
			return false;
		}
	}

	function voidedClicked(input) {
		var reason = document.getElementById("voidReason");
		var voidedBy = document.getElementById("voidedBy");
		if (input) {
			if (input.checked) {
				reason.style.display = "";
				if (voidedBy)
					voidedBy.style.display = "";
			} else {
				reason.style.display = "none";
				reason.value = "";
				if (voidedBy)
					voidedBy.style.display = "none";
			}
		}
	}

	function leavingClicked(input) {
		var memberEndDate = document.getElementById("memberEndDate");
		var memberReasonLeaving = document
				.getElementById("memberReasonLeaving");

		if (input) {
			if (input.checked) {
				memberEndDate.style.display = "";
				memberReasonLeaving.style.display = "";
			} else {
				memberEndDate.style.display = "none";
				memberEndDate.value = "";
				memberReasonLeaving.style.display = "none";
				memberReasonLeaving.value = "";
			}
		}
	}

	function restartClicked(input) {
		var restartD = document.getElementById("restartDate1");
		if (input) {
			if (input.checked) {
				restartD.style.display = "";
			} else {
				restartD.style.display = "none";
				restartD.value = "";
			}
		}
	}
	
	function validateForm() {
		var x = document.forms["myForm"]["members_not_voided"].value;
		var y = document.forms["myForm"]["familyMemberId:int"].value;
		if (x > 5&&(y==null||y=="")) {
			
			if (confirm("Este GAAC ja tem 6 pacientes! Deseja adicionar mais?")) {
				return true;
			} else {
				return false;
			}

		} else {
			
		}
	}
</script>

<h2>
	<openmrs:message code="gaac.fmmember.title" />
</h2>


<form method="post"  name="myForm"  onsubmit="return validateForm()">

	<table>
		<tr>
			<td align="right"><openmrs:message code="gaac.member.member" /></td>
			<td><spring:bind path="familyMember.member">
					<openmrs_tag:patientField formFieldName="member"
						searchLabelCode="Patient.find" initialValue="${status.value}"
						linkUrl="" callback="" allowSearch="true" />
					<c:if test="${status.errorMessage != ''}">
						<span class="error">${status.errorMessage}</span>
					</c:if>
				</spring:bind></td>
		</tr>
		
		<tr>
			<td align="right"><openmrs:message code="gaac.fmmember.type" /></td>
			<td>
			<select name="relacaotype">
						<option value=""></option>
						<c:forEach items="${relacao}" var="type">
							<option value="${type.relationshipTypeId}"
								<c:if test="${type.relationshipTypeId == compara}">selected</c:if>>${type.bIsToA}</option> 
						</c:forEach>
					</select>	
			</td>
		</tr>
		<tr>
			<td align="right"><openmrs:message code="gaac.fmmanage.startDate" /></td>
			<td><spring:bind path="familyMember.startDate">
					<input type="text" name="startDate" size="10"
						value="${status.value}" onClick="showCalendar(this)"
						id="startDate" />
					<c:if test="${status.errorMessage != ''}">
						<span class="error">${status.errorMessage}</span>
					</c:if>
				</spring:bind></td>
		</tr>
	
		
		<tr id="familyMembers">
					<td colspan="2">
					<input type="hidden" name="members_voided" value="${voided}" readonly="readonly"/>
					<input type="hidden" name="members_not_voided" readonly="readonly" value="${not_voided}" />
					</td>
		</tr>

		<c:if test="${familyMember.familyMemberId != null}">
			<tr>
				<td align="right"><openmrs:message code="general.createdBy" /></td>
				<td>${familyMember.creator.personName}- <openmrs:formatDate
						date="${familyMember.dateCreated}" type="long" />
				</td>
			</tr>


			<tr>
				<td align="right"><openmrs:message code="gaac.member.leaving" /></td>
				<td><spring:bind path="familyMember.leaving">
						<input type="hidden" name="_${status.expression}" />
						<input type="checkbox" name="${status.expression}" id="leaving"
							onClick="leavingClicked(this)"
							<c:if test="${familyMember.leaving}">checked</c:if> />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>

			<tr id="memberEndDate">
				<td align="right"><openmrs:message code="gaac.member.endDate" /></td>
				<td><spring:bind path="familyMember.endDate">
						<input type="text" name="endDate" size="10"
							value="${status.value}" onClick="showCalendar(this)" />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>

			<tr id="memberReasonLeaving">
				<td align="right"><openmrs:message
						code="gaac.member.reasonLeaving" /></td>
				<td><spring:bind path="familyMember.reasonLeaving">
						<select name="${status.expression}">
							<option value=""></option>
							<c:forEach items="${reasonLeavingTypes}" var="type">
								<option value="${type.reasonLeavingTypeId}"
									<c:if test="${type.reasonLeavingTypeId == status.value}">selected</c:if>>${type.name}</option>
							</c:forEach>
						</select>
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>


			<c:if test="${familyMember.leaving==true}">

				<tr>
					<td align="right"><openmrs:message code="gaac.member.restart" /></td>
					<td><spring:bind path="familyMember.restart">
							<input type="hidden" name="_${status.expression}" />
							<input type="checkbox" name="${status.expression}" id="restart"
								onClick="restartClicked(this)"
								<c:if test="${familyMember.restart}">checked</c:if> />
							<c:if test="${status.errorMessage != ''}">
								<span class="error">${status.errorMessage}</span>
							</c:if>
						</spring:bind></td>
				</tr>
				<tr id="restartDate1">
					<td align="right"><openmrs:message
							code="gaac.member.restartDate" /></td>
					<td><spring:bind path="familyMember.restartDate">
							<input type="text" name="restartDate" size="10"
								value="${status.value}" onClick="showCalendar(this)" />
							<c:if test="${status.errorMessage != ''}">
								<span class="error">${status.errorMessage}</span>
							</c:if>
						</spring:bind></td>
				</tr>
			</c:if>

			<tr>
				<td align="right"><openmrs:message code="general.voided" /></td>
				<td><spring:bind path="familyMember.voided">
						<input type="hidden" name="_${status.expression}" />
						<input type="checkbox" name="${status.expression}" id="voided"
							onClick="voidedClicked(this)"
							<c:if test="${familyMember.voided}">checked</c:if> />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>


			<tr id="voidReason">
				<td align="right"><openmrs:message code="general.voidReason" /></td>
				<td><spring:bind path="familyMember.voidReason">
						<input type="text" value="${status.value}"
							name="${status.expression}" size="40" />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>
			<c:if test="${family.voidedBy != null}">
				<tr id="voidedBy">
					<td align="right"><openmrs:message code="general.voidedBy" /></td>
					<td>${family.voidedBy.personName}- <openmrs:formatDate
							date="${family.dateVoided}" type="medium" />
					</td>
				</tr>
			</c:if>
			
		</c:if>
		
	</table>
	<input type="hidden" name="familyMemberId:int"
		value="${familyMember.familyMemberId}"> <br /> <input
		type="submit" value="<openmrs:message code="gaac.member.save"/>"
		name="save" />
</form>
<br />
<%@ include file="/WEB-INF/template/footer.jsp"%>