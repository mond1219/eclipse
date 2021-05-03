<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<link rel="stylesheet" href="/WebMVC/js_css/login_css.css"/>
<script>
	//아이디 비밀번호 입력이 됐는지확인
	$(function(){
		$("form").submit(function(){
			if($("#userid").val()==""){
				alert("아이디 입력하고 눌러라 ㅡㅅㅡ");
				return false;
			}
			if($("#userpwd").val()==""){
				alert("비밀번호 입력하고 눌러라 ㅡㅅㅡ");
				return false;
			}
		});
		
	});
</script>
<div id ="main">
	<!--로고 -->
	<div id="top_logo">
		<a href="https://www.daum.net"><img src="/WebMVC/img/daum.png"/></a>
	</div>
	<!-- 로그인폼, 배너 -->
	<div id ="login_form">
		<div id="login">
			<form method="post" action="<%=request.getContextPath()%>/member/loginOk.do">
				<input type="text" class="form_input form" name ="userid" id ="userid" placeholder="아이디입력"/>
				<input type="password" class="form_input form"name="userpwd" id="userpwd" placeholder="비밀번호 입력"/>
				<input type="submit" class="form_com form sub"value="로그인"/>
				<div id="state">
					<span>
						<input type="checkbox" name="Logstate" value="no"/>로그인상태 유지
					</span>
					<span style="float:right">
						<a href="#">보안</a>
						<a href="#">ON</a>
					</span>
				</div>
				<div id="chek">
					<a href="#">아이디 찾기</a>  |
					<a href="#">비밀번호 찾기</a>
				</div>
			</form>
		</div>
		<a href="#"><img src="/WebMVC/img/ad.png" id="banner"/></a>
	</div>

</div>