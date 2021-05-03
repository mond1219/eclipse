<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<script>
	$(function(){
		$("#setId").click(function(){
			//opener : 현재 윈도우를 열어준 객체
			opener.document.getElementById("userid").value=$("#checkId").text();
			opener.document.getElementById("hiddenCheck").value="Y";
			self.close();//window.close();도 된다.
		});
		$("#frm").submit(function(){
			if($("#userid").val()==""){
				alert("아이디를 입력후 중복검사하세요...");
				return false;
			}
		});
	});
</script>
<style>
	#menu, footer,top{dispaly:none;}
</style>
<div>
	<c:if test="${ !checkResult }"><!--  false값이라 !하여 true로 바꿔 if문을 실행한다.-->
		<span style="color:red" id="checkId">${ userid }</span>은 사용가능한 아이디 입니다.
		<input type="button" value="아이디 사용하기" id="setId"/>
	</c:if>
	<c:if test="${ checkResult }"><!-- -->
		<span>${ userid }</span>은 사용불가능한 아이디 입니다.
	</c:if>
	<hr/>
	<h3>아이디를 입력후 중복검사버튼 누르세요.</h3>
	<form method="post" id ="frm" action="<%=request.getContextPath() %>/member/idCheck.do">
		아이디 : <input type="text" name="userid" id="userid"/>
		<input type="submit" value="아이디중복검사"/>
	</form>
</div>