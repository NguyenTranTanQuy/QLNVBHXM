<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<link href="<c:url value='/resources/assets/css/rules.css' />"
	rel="stylesheet" type="text/css" />

<div class="rules-container">
	<div class="rules-content">
		<h1 class="rules-heading">Bảng quy định phòng ban</h1>
		<ol id="rules-list">
			<c:forEach var="qd" items="${qds}">
				<li class="rules-item">
					<p class="rules-describe">
						<b>${qd.tenqd}</b> ${qd.mota}
					</p>
				</li>
			</c:forEach>
		</ol>
	</div>
</div>