<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="container">
	<h1>글목록</h1>
	<c:if test="${logStatus=='Y' }">
		<a href="<%=request.getContextPath() %>/boardWrite"><div>글쓰기</div></a>
	</c:if>
	<ul>
		<c:forEach var="vo" items="${list }">
			<li>${vo.no }</li>
			<li><a href="<%=request.getContextPath() %>/boardView?no=${vo.no}">${vo.subject }</a></li>
			<li>글쓴이</li>
			<li>조회수</li>
			<li style="border-bottom:1px solid gray">등록일</li>
		</c:forEach>
	</ul>

</div>


</body>
</html>