<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>


<c:forEach var="nv" items="${lnv}">
	<div class="employee-working">
		<input class="employee-working-checkbox" type="checkbox" value="${nv.manv}" id="${nv.manv}"
			${map.get(nv.manv)?'checked':''} ${maps.get(nv.manv)?'':'disabled'}/> <label for="${nv.manv}"
			class="employee-working-name"> ${nv.manv} - ${nv.ho}
			${nv.ten} </label>
	</div>
</c:forEach>

