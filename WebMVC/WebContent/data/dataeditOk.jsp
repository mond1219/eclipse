<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!-- 레코드 수정 성공시 -->
<c:if test="${result==1 }">
	<script>
		location.href="<%=request.getContextPath()%>/data/dataView.do?no=${no}"
	</script>
</c:if>
<!-- 레코드 수정 실패시 -->
<c:if test="${result==0 }">
	<script>
		location.href="<%=request.getContextPath()%>/data/dataEdit.do=?no=${no}";
	</script>
</c:if>