<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:require privilege="View Gaac" otherwise="/login.htm"
	redirect="/module/gaac/gaacList.form" />
<openmrs:message var="pageTitle" code="gaac.manage.titlebar"
	scope="page" />


<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/localHeader.jsp"%>

<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/css/dataTables_jui.css" />
<openmrs:htmlInclude
	file="/scripts/jquery/dataTables/js/jquery.dataTables.min.js" />

<script type="text/javascript">
	$j(document).ready(function() {
		$j('#myTable').dataTable({
			"iDisplayLength" : 50
		});
	})
</script>

<h2>
	<openmrs:message code="gaac.list" />
</h2>

<a href="addNewGaac.form"><openmrs:message code="gaac.addNew" /></a>
<br />
<br />

<b class="boxHeader" ><openmrs:message code="gaac.list.title" /></b>
<form method="post" class="box" style="margin-bottom:15px;">
	<table id="myTable" class="display" width="100%" cellpadding="2" cellspacing="0"
		style="font-size: 13px;">
		<thead>
			<tr>
				<th><openmrs:message code="general.name" /></th>
				<th><openmrs:message code="gaac.manage.identifier" /></th>
				<th><openmrs:message code="gaac.manage.startDate" /></th>
				<th><openmrs:message code="gaac.manage.location" /></th>
				<th><openmrs:message code="gaac.manage.affinity" /></th>
				<th><openmrs:message code="gaac.manage.focal" /></th>
				<th><openmrs:message code="gaac.manage.DateCrumbled" /></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="gaac" items="${gaacList}">
				<tr>
					<td><a href="addNewGaac.form?gaacId=${gaac.gaacId}"> <c:choose>
								<c:when test="${gaac.crumbled == true}">
									<del>${gaac.name}</del>
								</c:when>
								<c:otherwise>
								${gaac.name}
							</c:otherwise>
							</c:choose>
					</a></td>
					<td>${gaac.gaacIdentifier}</td>
					<td><openmrs:formatDate date="${gaac.startDate}" type="medium" /></td>
					<td>${gaac.location.name}</td>
					<td>${gaac.affinity.name}</td>
					<td>${gaac.focalPatient.personName}</td>
					<td><openmrs:formatDate date="${gaac.dateCrumbled}" type="medium" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>