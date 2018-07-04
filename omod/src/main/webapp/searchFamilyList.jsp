<%@ include file="/WEB-INF/template/include.jsp"%>


<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
	<spring:message code="gaac.fmsearch.manage" />
</h2>

<br />


<form method="get">

	<table>
		<tr>
			<td><openmrs:message code="gaac.search.patient" /></td>
			<td><openmrs_tag:patientField formFieldName="patient"
					searchLabelCode="Patient.find" initialValue="${status.value}"
					linkUrl="" callback="" allowSearch="true" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" name="action"
				value="<openmrs:message code="general.search"/>" /></td>
		</tr>
	</table>
</form>

<br />

<c:if test="${not empty members}">
<c:if test="${members== 0}">
	<span style="color:red;"><openmrs:message code="gaac.search.nomatch"/></span>
</c:if>
</c:if>

<c:if test="${gaacMember!=null}">
	<openmrs:portlet url="patientHeader" id="patientDashboardHeader"
		patientId="${gaacMember.member.patientId}" />
</c:if>



<c:if test="${fn:length(gaacMemberHistoryList) > 0}">
	<b class="boxHeader" ><openmrs:message code="gaac.fmsearch.history" /></b>
	<div class="box">
		<table class="openmrsSearchTable" style="width: 100%;" cellpadding="2"
			cellspacing="0">
			<thead>
				<tr style="" dojoattachpoint="headerRow">
					<th><openmrs:message code="gaac.fmmanage.identifier" javaScriptEscape="true" /></th>

					<th><openmrs:message code="gaac.manage.location" /></th>
					<th><openmrs:message code="gaac.member.startDate"
							javaScriptEscape="true" /></th>
					<th><openmrs:message code="gaac.member.endDate"
							javaScriptEscape="true" /></th>
					<th><openmrs:message code="gaac.member.reasonLeaving"
							javaScriptEscape="true" /></th>
					<th><openmrs:message code="general.voided" /></th>
					<th><openmrs:message code="general.voidedBy" /></th>
					<th><openmrs:message code="general.voidReason" /></th>
				</tr>
			</thead>
			<c:forEach var="member" items="${gaacMemberHistoryList}"
				varStatus="rowStatus">
				<tr
					class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" } ${user.retired ? "retired" : "" }'>
					<td><c:if test="${member.family.voided==false}"><a href="addNewFamily.form?familyId=${member.family.familyId}"><c:out value="${member.family.familyIdentifier}" /></a></c:if>
					<c:if test="${member.family.voided==true}"><c:out value="${member.family.familyIdentifier}" /></c:if>
					</td>
					<td><c:out value="${member.family.location.name}" /></td>
					<td><openmrs:formatDate date="${member.startDate}"
							type="medium" /></td>
					<td><c:if test="${member.endDate != null}">
							<openmrs:formatDate date="${member.endDate}" type="medium" />
						</c:if></td>
					<td><c:if test="${member.reasonLeaving != null}">
							<c:out value="${member.reasonLeaving.name}" />
						</c:if></td>
					<td><c:if test="${member.voided==true}">
							<openmrs:message code="general.yes" />
						</c:if> <c:if test="${member.voided==false}">
							<openmrs:message code="general.no" />
						</c:if></td>
					<td><c:out value="${member.voidedBy.personName}" /> - <openmrs:formatDate
							date="${member.dateVoided}" type="medium" /></td>
					<td><c:out value="${member.voidReason}" /></td>

				</tr>
			</c:forEach>

		</table>
	</div>
</c:if>


<%@ include file="/WEB-INF/template/footer.jsp"%>