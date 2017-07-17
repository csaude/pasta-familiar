<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Affinity Types"
	otherwise="/login.htm" redirect="/module/gaac/affinityTypeList.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
	<openmrs:message code="gaac.affinityType.management" />
</h2>

<a href="affinityTypeForm.form"><openmrs:message
		code="gaac.affinityType.add" /></a>

<br />
<br />

<b class="boxHeader"><openmrs:message code="gaac.affinitys" /></b>
<form method="post" class="box">
	<table cellpadding="2" cellspacing="0">
		<tr>
			<th><openmrs:message code="general.name" /></th>
			<th><openmrs:message code="general.description" /></th>
		</tr>
		<c:forEach var="affinityType" items="${affinityTypeList}"
			varStatus="rowStatus">
			<tr class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'>
				<td valign="top"><a
					href="affinityTypeForm.form?affinityTypeId=${affinityType.affinityTypeId}">
						<c:choose>
							<c:when test="${affinityType.retired == true}">
								<del>${affinityType.name}</del>
							</c:when>
							<c:otherwise>
								${affinityType.name}
							</c:otherwise>
						</c:choose>
				</a></td>
				<td valign="top">${affinityType.description}</td>
			</tr>
		</c:forEach>
	</table>

</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>