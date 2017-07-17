<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Reason Leaving Gaac Types"
	otherwise="/login.htm"
	redirect="/module/gaac/reasonLeavingTypeList.form" />

<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<script type="text/javascript">
	function confirmPurge() {
		if (confirm("Are you sure you want to purge this object? It will be permanently removed from the system.")) {
			return true;
		} else {
			return false;
		}
	}
</script>

<script type="text/javascript">
	function forceMaxLength(object, maxLength) {
		if (object.value.length >= maxLength) {
			object.value = object.value.substring(0, maxLength);
		}
	}
</script>

<h2>
	<openmrs:message code="gaac.reasonLeaving.manage" />
</h2>

<spring:hasBindErrors name="reasonLeavingType">
	<span class="error"> <openmrs:message code="gaac.error.general" />
	</span>
	<br />
</spring:hasBindErrors>

<form method="post">

	<table>
		<tr>
			<td><openmrs:message code="general.name" /></td>
			<td><spring:bind path="reasonLeavingType.name">
					<input type="text" name="name" value="${status.value}" size="35" />
					<c:if test="${status.errorMessage != ''}">
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</c:if>
				</spring:bind></td>
		</tr>
		<tr>
			<td valign="top"><openmrs:message code="general.description" /></td>
			<td valign="top"><spring:bind
					path="reasonLeavingType.description">
					<textarea name="description" rows="3" cols="40"
						onkeypress="return forceMaxLength(this, 1024);">${status.value}</textarea>
					<c:if test="${status.errorMessage != ''}">
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</c:if>
				</spring:bind></td>
		</tr>
		<c:if test="${!(reasonLeavingType.creator == null)}">
			<tr>
				<td><openmrs:message code="general.createdBy" /></td>
				<td>${reasonLeavingType.creator.personName} - <openmrs:formatDate
						date="${reasonLeavingType.dateCreated}" type="long" />
				</td>
			</tr>
		</c:if>
	</table>
	<br /> <input type="hidden" name="reasonLeavingTypeId:int"
		value="${reasonLeavingType.reasonLeavingTypeId}"> <input
		type="submit"
		value="<openmrs:message code="gaac.reasonLeaving.save"/>" name="save">


</form>

<br />

<c:if
	test="${not reasonLeavingType.retired && not empty reasonLeavingType.reasonLeavingTypeId}">
	<form method="post">
		<fieldset>
			<h4>
				<openmrs:message code="gaac.reasonLeaving.retire" />
			</h4>

			<b><openmrs:message code="general.reason" /></b> <input type="text"
				value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="reasonLeavingType">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}">
						<span class="error"><openmrs:message
								code="${error.defaultMessage}" text="${error.defaultMessage}" /></span>
					</c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br /> <input type="submit"
				value='<openmrs:message code="gaac.reasonLeaving.retire"/>'
				name="retire" />
		</fieldset>
	</form>
</c:if>

<br />

<c:if test="${not empty reasonLeavingType.reasonLeavingTypeId}">
	<openmrs:hasPrivilege privilege="Manage Reason Leaving Gaac Types">
		<form id="purge" method="post" onsubmit="return confirmPurge()">
			<fieldset>
				<h4>
					<openmrs:message code="gaac.reasonLeaving.purge" />
				</h4>
				<input type="submit"
					value='<openmrs:message code="gaac.reasonLeaving.purge"/>'
					name="purge" />
			</fieldset>
		</form>
	</openmrs:hasPrivilege>
</c:if>

<%@ include file="/WEB-INF/template/footer.jsp"%>