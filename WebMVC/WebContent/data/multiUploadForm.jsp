<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<script src="//cdn.ckeditor.com/4.16.0/full/ckeditor.js"></script>
<!-- 다중 업로드
	http://commons.apache.org/ 에서
	FileUpload, IO를 다운로드
 -->
 <script>
 	$(function(){
 		CKEDITOR.replace("content");
 	});
 </script>
 <div class="container">
 	<h1>자료실 글올리기(다중업로드)</h1>																	<!--파일 업로드 일경우 반두시 enctype속성이 있어야한다.  -->
	<form method="post" action="<%=request.getContextPath()%>/data/multiUploadOk.do" id="dataFrm" enctype="multipart/form-data">
		<ul>
			<li>제목 : <input type="text" name="title" id="title" size="50" placeholder="글제목"/></li>
			<li>
				<textarea name="content" id="content"><</textarea>
			</li>
			<li>첨부파일 : <input type="file" name="filename" id="filename" multiple/></li>
			<li>
				<input type="submit" value="자료올리기(다중업로드)"/>
				<input type="reset" value="취소"/>	
			</li>
		</ul>
	</form>
 </div>