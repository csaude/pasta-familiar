<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Reason Leaving Gaac Types"
	otherwise="/login.htm" redirect="/module/gaac/reasonList.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<h2>
	<openmrs:message code="gaac.reasonLeaving.title" />
</h2>

<a href="reasonLeavingTypeForm.form"><openmrs:message
		code="gaac.reasonLeaving.add" /></a>

<br />
<br />


<b class="boxHeader"><openmrs:message code="gaac.leavings" /></b>
<form method="post" class="box">
	<table cellpadding="2" cellspacing="0">
		<tr>
			<th><openmrs:message code="general.name" /></th>
			<th><openmrs:message code="general.description" /></th>
		</tr>
		<c:forEach var="reasonLeavingType" items="${reasonLeavingTypeList}"
			varStatus="rowStatus">
			<tr class='${rowStatus.index % 2 == 0 ? "evenRow" : "oddRow" }'>
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
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>