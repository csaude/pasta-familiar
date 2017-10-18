<spring:htmlEscape defaultHtmlEscape="true" />
<ul id="menu">
	<li class="first"><a
		href="${pageContext.request.contextPath}/admin"><spring:message
				code="admin.title.short" /></a></li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/gaacList") %>'>class="active"</c:if>
		<c:if test='<%= request.getRequestURI().contains("/addNewGaac") %>'>class="active"</c:if>
		<c:if test='<%= request.getRequestURI().contains("/gaacMemberForm") %>'>class="active"</c:if>>
		<a href="${pageContext.request.contextPath}/module/gaac/gaacList.form"><spring:message
				code="gaac.manage" /></a>
	</li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/affinityTypeList") %>'>class="active"</c:if>
		<c:if test='<%= request.getRequestURI().contains("/affinityTypeForm") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/gaac/affinityTypeList.form"><spring:message
				code="gaac.affinityType.manage" /></a>
	</li>

	<li
		<c:if test='<%= request.getRequestURI().contains("/reasonList") %>'>class="active"</c:if>
		<c:if test='<%= request.getRequestURI().contains("/reasonLeavingTypeForm") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/gaac/reasonList.form"><spring:message
				code="gaac.reasonLeaving.manage" /></a>
	</li>
	
	<li
		<c:if test='<%= request.getRequestURI().contains("/searchList") %>'>class="active"</c:if>>
		<a
		href="${pageContext.request.contextPath}/module/gaac/searchList.form"><spring:message
				code="gaac.search" /></a>
	</li>


	<li
		<c:if test='<%= request.getRequestURI().contains("/familyList") %>'>class="active"</c:if>
		<c:if test='<%= request.getRequestURI().contains("/addNewFamily") %>'>class="active"</c:if>
		<c:if test='<%= request.getRequestURI().contains("/familyMemberForm") %>'>class="active"</c:if>>
		<a href="${pageContext.request.contextPath}/module/gaac/familyList.form"><spring:message
				code="gaac.fmmanage" /></a>
	</li>

</ul>

