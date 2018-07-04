<%@ include file="/WEB-INF/template/include.jsp"%>
<!-- 
<openmrs:require privilege="View Family" otherwise="/login.htm"
	redirect="/module/gaac/familyList.form" />
	
	 -->
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
	<openmrs:message code="gaac.fmlist" />
</h2>

<a href="addNewFamily.form"><openmrs:message code="gaac.fmaddNew" /></a>
<br />
<br />

<b class="boxHeader" ><openmrs:message code="gaac.fmlist.title" /></b>
<form method="post" class="box" style="margin-bottom:15px;">
	<table id="myTable" class="display" width="100%" cellpadding="2" cellspacing="0"
		style="font-size: 13px;">
		<thead>
			<tr>
		
				
				<th><openmrs:message code="gaac.fmmanage.identifier" /></th>
				<th><openmrs:message code="gaac.fmmanage.startDate" /></th>
				<th><openmrs:message code="gaac.fmmanage.location" /></th>
				<th><openmrs:message code="gaac.fmmanage.focal" /></th>
				<th><openmrs:message code="gaac.fmmanage.cell" /></th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="family" items="${familyList}" varStatus="status">
				<tr>
			
					<td><a href="addNewFamily.form?familyId=${family.familyId}"> <c:choose>
								<c:when test="${family.crumbled == true}">
									<del>${family.familyIdentifier}</del>
								</c:when>
								<c:otherwise>
								${family.familyIdentifier}
							</c:otherwise>
							</c:choose>
					</a></td>
				 
					<td><openmrs:formatDate date="${family.startDate}" type="medium" /></td>
					<td>${family.location.name}</td>
					<td>${family.focalPatient.personName}</td>
					<td>${familyContato[status.index]}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
</form>
<%@ include file="/WEB-INF/template/footer.jsp"%>