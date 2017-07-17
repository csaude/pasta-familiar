<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Reason Leaving Gaac Types"
	otherwise="/login.htm"
	redirect="/module/gaac/reasonLeavingTypeList.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
	<openmrs:message code="gaac.reasonLeaving.title" />
</h2>

<a href="reasonLeavingTypeForm.form"><openmrs:message
		code="gaac.reasonLeaving.add" /></a>

<openmrs:extensionPoint
	pointId="org.openmrs.admin.encounters.encounterTypeList.afterAdd"
	type="html" />

<br />
<br />

<b class="boxHeader"><openmrs:message
		code="gaac.reasonLeaving.manage" /></b>
<form method="post" class="box">
	<table>
		<tr>
			<th><openmrs:message code="general.name" /></th>
			<th><openmrs:message code="general.description" /></th>
		</tr>
		<c:forEach var="reasonLeavingType" items="${reasonLeavingTypeList}">
			<tr>
				<td valign="top"><a
					href="reasonLeavingTypeForm.form?reasonLeavingTypeId=${reasonLeavingType.reasonLeavingTypeId}">
						<c:choose>
							<c:when test="${reasonLeavingType.retired == true}">
								<del>${reasonLeavingType.name}</del>
							</c:when>
							<c:otherwise>
								${reasonLeavingType.name}
							</c:otherwise>
						</c:choose>
				</a></td>
				<td valign="top">${reasonLeavingType.description}</td>
			</tr>
		</c:forEach>
	</table>
	<openmrs:extensionPoint
		pointId="org.openmrs.admin.encounters.encounterTypeList.inForm"
		type="html" />

</form>

<openmrs:extensionPoint
	pointId="org.openmrs.admin.encounters.encounterTypeList.footer"
	type="html" />

<%@ include file="/WEB-INF/template/footer.jsp"%>