<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<script src="//cdn.ckeditor.com/4.16.0/full/ckeditor.js"></script>
<style>
	#subject{width:80%;}
</style>
<script>
	
	$(function(){
		CKEDITOR.replace("content");
		
		//글쓰기 유효성 검사
		$("#boardFrm").submit(function(){
			if($("#subject").val()==""){
				alert("제목를 입력하세요...");
				return false;
			}
			if(CKEDITOR.instances.content.getData()==""){
				alert("내용를 입력하세요...");
				return false;
			}
		});
	});
	
	
</script>
<div class="container">
	<h1>게시판 글쓰기</h1>										<!--  글제목 내용, 작성자, -->
	<form method="post" id="boardFrm" action="<%=request.getContextPath()%>/board/writeOk.do">
		<div>제목 : <input type="text" name="subject" id="subject"/></div>
		<textarea name="content" id="content"></textarea>
		<input type="submit" value="글등록"/>
		<input type="reset" value="다시쓰기"/>
	</form>
</div>