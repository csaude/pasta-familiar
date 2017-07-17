<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="Manage Affinity Types"
	otherwise="/login.htm" redirect="/module/gaac/affinityTypeList.form" />

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
	<openmrs:message code="gaac.affinityType.manage" />
</h2>

<spring:hasBindErrors name="affinityType">
	<span class="error"> <openmrs:message code="gaac.error.general" />
	</span>
	<br />
</spring:hasBindErrors>
<form method="post">

	<table>
		<tr>
			<td><openmrs:message code="general.name" /></td>
			<td><spring:bind path="affinityType.name">
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
			<td valign="top"><spring:bind path="affinityType.description">
					<textarea name="description" rows="3" cols="40"
						onkeypress="return forceMaxLength(this, 1024);">${status.value}</textarea>
					<c:if test="${status.errorMessage != ''}">
						<c:if test="${status.errorMessage != ''}">
							<span class="error">${status.errorMessage}</span>
						</c:if>
					</c:if>
				</spring:bind></td>
		</tr>
		<c:if test="${!(affinityType.creator == null)}">
			<tr>
				<td><openmrs:message code="general.createdBy" /></td>
				<td>${affinityType.creator.personName} - <openmrs:formatDate
						date="${affinityType.dateCreated}" type="long" />
				</td>
			</tr>
		</c:if>
	</table>
	<br /> <input type="hidden" name="affinityTypeId:int"
		value="${affinityType.affinityTypeId}"> <input type="submit"
		value="<openmrs:message code="gaac.affinityType.save"/>" name="save">


</form>

<br />

<c:if
	test="${not affinityType.retired && not empty affinityType.affinityTypeId}">
	<form method="post">
		<fieldset>
			<h4>
				<openmrs:message code="gaac.affinityType.retire" />
			</h4>

			<b><openmrs:message code="general.reason" /></b> <input type="text"
				value="" size="40" name="retireReason" />
			<spring:hasBindErrors name="affinityType">
				<c:forEach items="${errors.allErrors}" var="error">
					<c:if test="${error.code == 'retireReason'}">
						<span class="error"> <openmrs:message
								code="${error.defaultMessage}" text="${error.defaultMessage}" />
						</span>
					</c:if>
				</c:forEach>
			</spring:hasBindErrors>
			<br /> <input type="submit"
				value='<openmrs:message code="gaac.affinityType.retire"/>'
				name="retire" />
	</form>
</c:if>

<br />

<c:if test="${not empty affinityType.affinityTypeId}">
	<openmrs:hasPrivilege privilege="Manage Affinity Types">
		<form id="purge" method="post" onsubmit="return confirmPurge()">
			<fieldset>
				<h4>
					<openmrs:message code="gaac.affinityType.purge" />
				</h4>
				<input type="submit"
					value='<openmrs:message code="gaac.affinityType.purge"/>'
					name="purge" />
			</fieldset>
		</form>
	</openmrs:hasPrivilege>
</c:if>

<%@ include file="/WEB-INF/template/footer.jsp"%>