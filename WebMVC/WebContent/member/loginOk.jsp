<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<c:if test="${userid !=null && username!=null }"><!--세션에 있는 데이터를 가지고 확인한다.-->
	<!-- 로그인성공 -->
	<script>
	location.href="<%=request.getContextPath()%>/index.do"<!--홈페이지로 이동-->
	</script>
</c:if>
<c:if test="${userid == null || username == null }">
	<!-- 로그인 실패 -->
	<script>
		history.back();
	</script>
</c:if>