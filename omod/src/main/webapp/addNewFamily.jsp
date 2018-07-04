<%@ include file="/WEB-INF/template/include.jsp"%>

<!-- 
<openmrs:require privilege="Manage Family" otherwise="/login.htm"
	redirect="/module/gaac/addNewFamily.form" />
 -->
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>



<openmrs:htmlInclude file="/scripts/calendar/calendar.js" />
<openmrs:htmlInclude file="/scripts/dojoConfig.js" />
<openmrs:htmlInclude file="/scripts/dojo/dojo.js" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />

<script type="text/javascript">
	dojo.addOnLoad(function() {
		voidedClicked(document.getElementById("voided"));
		crumbledClicked(document.getElementById("crumbled"));
	})
</script>

<script type="text/javascript">
	function confirmPurge() {
		if (confirm("Are you sure you want to purge this object? It will be permanently removed from the system.")) {
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
				if (voidedBy)
					voidedBy.style.display = "none";
			}
		}
	}

	function crumbledClicked(input) {
		var reasonCrub = document.getElementById("reasonCrumbled");
		var dateCrub = document.getElementById("dateCrumbled1");
		if (input) {
			if (input.checked) {
				reasonCrub.style.display = "";
				dateCrub.style.display = "";

			} else {
				reasonCrub.style.display = "none";
				dateCrub.style.display = "none";
			}
		}
	}

	function validateForm() {
		var x = document.forms["myForm"]["newLocation"].value;
		if (x == null || x == "") {

		} else {
			if (confirm("Tem a certeza que deseja transferir esta Familia?")) {
				return true;
			} else {
				return false;
			}
		}
	}
</script>

<h2>
	<openmrs:message code="gaac.fmmanage" />
</h2>


<form method="post" name="myForm" onsubmit="return validateForm()">
	<table>


		<tr>
			<td align="right"><openmrs:message code="gaac.fmmanage.identifier" /></td>
			<td><spring:bind path="family.familyIdentifier">
					<input type="text" name="familyIdentifier" value="${status.value}"
						size="35" />
					<c:if test="${status.errorMessage != ''}">
						<span class="error">${status.errorMessage}</span>
					</c:if>
				</spring:bind></td>
		</tr>

		<tr>
			<td align="right"><openmrs:message code="gaac.manage.startDate" /></td>
			<td><spring:bind path="family.startDate">
					<input type="text" name="startDate" size="10"
						value="${status.value}" onClick="showCalendar(this)" />
					<c:if test="${status.errorMessage != ''}">
						<span class="error">${status.errorMessage}</span>
					</c:if>
				</spring:bind></td>
		</tr>

		<tr>
			<td align="right"><openmrs:message code="gaac.manage.location" /></td>
			<td><spring:bind path="family.location">
					<openmrs_tag:locationField formFieldName="location"
						initialValue="${status.value}" />
					<c:if test="${status.errorMessage != ''}">
						<span class="error">${status.errorMessage}</span>
					</c:if>
				</spring:bind></td>
		</tr>
		
		


		<tr>
			<td align="right"><openmrs:message code="gaac.fmmanage.focal" /></td>
			<td><spring:bind path="family.focalPatient">
					<openmrs_tag:patientField formFieldName="focalPatient"
						searchLabelCode="Patient.find" initialValue="${status.value}"
						linkUrl="" callback="" allowSearch="true" />
					<c:if test="${status.errorMessage != ''}">
						<span class="error">${status.errorMessage}</span>
					</c:if>
				</spring:bind></td>
		</tr>

		<c:if test="${family.familyId != null}">
			<tr>
				<td align="right"><openmrs:message code="general.createdBy" /></td>
				<td>${family.creator.personName}-<openmrs:formatDate
						date="${family.dateCreated}" type="long" />
				</td>
			</tr>



			<tr>
				<td align="right"><openmrs:message code="gaac.manage.crumbled" /></td>
				<td><spring:bind path="family.crumbled">
						<input type="hidden" name="_${status.expression}" />
						<input type="checkbox" name="${status.expression}" id="crumbled"
							onClick="crumbledClicked(this)"
							<c:if test="${family.crumbled}">checked</c:if> />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>

			<tr id="dateCrumbled1">
				<td align="right"><openmrs:message
						code="gaac.manage.DateCrumbled" /></td>
				<td><spring:bind path="family.dateCrumbled">
						<input type="text" name="dateCrumbled" size="10"
							value="${status.value}" onClick="showCalendar(this)" />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>

			<tr id="reasonCrumbled">
				<td align="right"><openmrs:message
						code="gaac.manage.crumbledReason" /></td>
				<td><spring:bind path="family.reasonCrumbled">
						<input type="text" value="${status.value}"
							name="${status.expression}" size="40" />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>


			<tr>
				<td align="right"><openmrs:message code="general.voided" /></td>
				<td><spring:bind path="family.voided">
						<input type="hidden" name="_${status.expression}" />
						<input type="checkbox" name="${status.expression}" id="voided"
							onClick="voidedClicked(this)"
							<c:if test="${family.voided}">checked</c:if> />
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</spring:bind></td>
			</tr>

			<tr id="voidReason">
				<td align="right"><openmrs:message code="general.voidReason" /></td>
				<td><spring:bind path="family.voidReason">
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
					<td>${family.voidedBy.personName}-<openmrs:formatDate
							date="${family.dateVoided}" type="medium" />
					</td>
				</tr>
			</c:if>

		</c:if>
	</table>
	<input type="hidden" name="familyId:int" value="${family.familyId}">
	<br /> <input type="submit"
		value="<openmrs:message code="gaac.fmmanage.save"/>" name="save" />

</form>

<br />

<c:if test="${family.familyId != null}">
	<b class="boxHeader"><openmrs:message code="gaac.fmmember.list" /></b>
	<form method="post" class="box" style="margin-bottom:15px;">

		<table id="myTable" class="display" width="100%" cellpadding="2" cellspacing="0"
		style="font-size: 13px;">
				<thead>
			<tr>
				<th><openmrs:message code="Patient.identifier" /></th>
				<th><openmrs:message code="general.name" /></th>
				<th><openmrs:message code="gaac.fmmember.type" /></th>
				<th><openmrs:message code="gaac.fmmember.startDate" /></th>
				<th><openmrs:message code="gaac.member.endDate" /></th>
				<th><openmrs:message code="gaac.member.reasonLeaving" /></th>


			</tr>
			</thead>
				<tbody>
					<c:forEach var="gaacmember" items="${family.members}"
				varStatus="rowStatus">
				<c:if test="${gaacmember.voided == false }">
					<tr class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'>

						<td><a
							href="familyMemberForm.form?familyMemberId=${gaacmember.familyMemberId}&familyId=${family.familyId}">
								<c:choose>
									<c:when test="${gaacmember.leaving == true}">
										<del>${gaacmember.member.patientIdentifier}</del>
									</c:when>
									<c:otherwise>
								${gaacmember.member.patientIdentifier}
							</c:otherwise>
								</c:choose>
						</a></td>
						<td>${gaacmember.member.personName}</td>
						<td>${gaacmember.relacao.getRelationshipType().getbIsToA()}</td>
						<td><openmrs:formatDate date="${gaacmember.startDate}"
								type="medium" /></td>
						<td><c:if test="${gaacmember.endDate != null}">
								<openmrs:formatDate date="${gaacmember.endDate}" type="medium" />
							</c:if></td>
						<td><c:if test="${gaacmember.reasonLeaving != null}">
						${gaacmember.reasonLeaving.name}
					</c:if></td>
					</tr>
				</c:if>
			</c:forEach>
	</tbody>
		<a href="familyMemberForm.form?familyId=${family.familyId}"><openmrs:message
				code="gaac.member.addNew" /></a>

</table>
	</form>
</c:if>


