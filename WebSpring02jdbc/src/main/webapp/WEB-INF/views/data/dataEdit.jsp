<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	$(function(){
		$("#file b").click(function(){
			$(this).parent().css("display", "none");
			$(this).parent().next().attr("name", "delFile");//input hidden name속성의 값을 delfile로 변경
			$(this).parent().next().next().attr('type','file');//
		});
		
	});
</script>
<div class="container">
	<h1>자료실 글 수정하기</h1>
	<form method="post" action="dataEditOk" enctype="multipart/form-data">
		<input type="hidden" name="no" value="${vo.no }"/>
		<ul>
			<li>제목 : <input type="text" name="title" value="${vo.title }"/></li>
			<li> <textarea name="content">${vo.content }</textarea>
			<li id="file">첨부 파일
			<div>
				<div>${vo.filename1 }<b>X</b></div>
				<input type="hidden" name ="" value="${vo.filename1 }"/>
				<input type="hidden" name="filename"/>
			</div>
			<c:if test="${vo.filename2!=null && vo.filename2!='' }"><!-- 두번째 첨부파일이 있을때  -->
				<div>
					<div>${vo.filename2 }<b>X</b></div>
					<input type="hidden" name ="" value="${vo.filename2 }"/>
					<input type="hidden" name="filename"/>
				</div>
			</c:if>
			<c:if test="${vo.filename2=='' && vo.filename2.equals('') }"><!--두번째 첨부파일이 없을 때  -->
				<input type="file" name="filename"/>
			</c:if>
			<li><input type="submit" value="자료 수정하기"/></li>
		</ul>
	</form>

</div>
</body>
</html>