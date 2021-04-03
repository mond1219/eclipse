<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<script>
	$(function(){
		$('#slider').bxSlider({
			mode:'horizontal',
			auto:true,
			captions : true,
			/* autoControls:true, */
			/* pager:true, */
			hideControlOnEnd : true
		});
	});
</script>
<style>
	#med{
		width:800px; overflow:auto;margin:0 auto; padding:0;
	}
	#slider img{
		width:100%;heigh:100%
	}
</style>
<div id="med">
	<ul id="slider">
		<li><img src="<%=request.getContextPath() %>/img/banner1.jpg" title="Seoul Music Festival"/></li>
		<li><img src="<%=request.getContextPath() %>/img/banner2.jpg" title="SIBAC 2019"/></li>
		<li><img src="<%=request.getContextPath() %>/img/banner3.jpg" title="2019 서울전환도시 국제컨퍼런스"/></li>
		<li><img src="<%=request.getContextPath() %>/img/banner4.jpg" title="2019 다다다 발표대회"/></li>
		<li><img src="<%=request.getContextPath() %>/img/banner5.jpg" title="2019 서울인공지능챗본론"/></li>
		<li><img src="<%=request.getContextPath() %>/img/banner6.jpg" title="서울 차없는 날"/></li>
		<li><img src="<%=request.getContextPath() %>/img/banner7.jpg" title="Zero 제로페이 미국 캐나다 이벤트"/></li>
	</ul>	
</div>
<style>
	#freelist{width:800px;overflow:auto;margin:0 auto; padding:0;}
	#freelist>h2{color:gray;text-align:center}
	.title{margin:0 auto; padding:5px;}
	.title>li{width:10%;height:30px;float:left; list-style-type:none;margin:0 auto;
	border-bottom:1px solid gray;}
	.title>li:nth-child(1){width:5%;height:30px;}
	.title>li:nth-child(2){width:5%;}
	.title>li:nth-child(3){width:55%;}
	.title>li:nth-child(5){width:15%}
	#page ul{margin:0 auto;width:280px; padding:20px 0 40px 0;}
	#page li{ padding:0;list-style-type:none;float:left; font-weight:1.5em;
	width:40px;border:1px solid gray;text-align:center;
	}
</style>
<div id="freelist">
	<h2>자유게시판</h2>
	<input type="checkbox" name="all" value="전체선택"/>전체선택<br/>
	<ul class="title">
		<li>  </li>
		<li>번호</li>
		<li>제목</li>
		<li>작성자</li>
		<li>작성일</li>
		<li>조회수</li>
	</ul>
	<c:forEach var="vo" items="${list }">
		<ul class="title">
			<li><input type="checkbox" name="no"/></li>
			<li>${vo.no }</li>
			<li class="wordCut">${vo.subject }</li>
			<li>${vo.userid }</li>
			<li>${vo.writedate }</li>
			<li>${vo.hit }</li>
		</ul>
	</c:forEach>
	<div id="page" style="clear:left;">
      <ul>
        		 <li><a href="<%=request.getContextPath()%>/list.jsp?num=1">prev</a></li>
         
         <!-- 페이지 번호              1부터                            5까지   -->
         <c:forEach var="p" begin="${pVO.startPageNum}" end="${pVO.startPageNum+pVO.onePageNum-1}">
            <c:if test="${p<=pVO.totalPage}">
                  <!-- 현재페이지 일때 -->
                  <c:if test="${p==pVO.pageNum}">
                     <li><a href="<%=request.getContextPath()%>/list.jsp?num=1">${p}</a></li>
                  </c:if>
                  <!-- 현재페이지가 아닐때 -->
                  <c:if test="${p!=pVO.pageNum}">
                     <li><a href="<%=request.getContextPath()%>/list.jsp?num=1">${p}</a></li>
                  </c:if>
            </c:if>
         </c:forEach>
         
         <!-- 다음 페이지 -->
         <c:if test="${pVO.pageNum < pVO.totalPage}">
            <li><a href="<%=request.getContextPath()%>/list.jsp?num=1">next</a></li>
         </c:if>
      </ul>
   </div>
</div>





























