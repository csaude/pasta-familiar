<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

<form method="GET">

	Hello ${user.systemId}! Choose a Concept:
	<openmrs_tag:conceptField formFieldName="conceptId" />

	<input type="submit" value="View" />
</form>
<hr />
<table>
	<tr>
		<th>Concept ID</th>
		<td>${concept.conceptId }</td>
	</tr>
	<tr>
		<th>UUID</th>
		<td>${concept.uuid }</td>
	</tr>
	<tr>
		<th>Names</th>
		<td>
			<table border="1">
				<tr>
					<th>Locale</th>
					<th>Preferred</th>
					<th>Type</th>
					<th>Name</th>
					<th>Tags</th>
				</tr>
				<c:forEach var="cn" items="${concept.names }">
					<tr>
						<td>${cn.locale }</td>
						<td>${cn.localePreferred }</td>
						<td>${cn.conceptNameType }</td>
						<td><c:out value="${cn.name }" /></td>
						<td><c:forEach var="t" items="${cn.tags }" varStatus="status">
								<c:out value="${t.tag }" />
								<c:if test="${ not status.last }">.</c:if>
							</c:forEach></td>

					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>

<%@ include file="/WEB-INF/template/footer.jsp"%>