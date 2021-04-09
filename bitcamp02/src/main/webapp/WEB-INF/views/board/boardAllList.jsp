<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1>리스트</h1>
<c:if test="${logId!=null }">
	<a href="<%=request.getContextPath() %>/boardWrite"><div>글쓰기</div></a>
</c:if>
<ul>
	<c:forEach var="vo" items="${list }">
		<li>${vo.no }</li>
		<li>${vo.userid }</li>
		<li>${vo.writedate },조회수 : ${vo.hit }</li>
		<li style="border-bottom:1px solid gray">제목 : <a href="/jdbc/boardView?no=${vo.no}">${vo.subject }</a></li>
	</c:forEach>
</ul>
</body>
</html>