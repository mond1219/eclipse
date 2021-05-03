<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<c:if test="${result>0 }">
	<!-- 글등록완료시 -->
	<script>
		location.href = "<%=request.getContextPath()%>/board/list.do"
	</script>
</c:if>
<c:if test="${result ==0}">
	<!-- 등록실패시 -->
	<script>
		alert("글등록 실패하였습니다.");
		history.back();
	</script>
</c:if>