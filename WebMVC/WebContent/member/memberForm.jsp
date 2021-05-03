<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<style>
/* 우편번호 건색 다운로드 : https://www.epost.go.kr/search/zipcode/areacdAddressDown.jsp */
	#memId>ul{overflow:auto;}
	#memId li{
		float:left; height:50px; padding:10px 0; border-bottom:1px solid #ddd;	
	}
	#memId li:nth-child(2n){/*홀수번째 */width:80%;}
	#memId li:nth-child(2n+1){/*짝수번째 */width:20%;}
	#memId li:last-of-type{width:100%;text-align:center;}
	#memId input{height:30px;line-height:20px;}
</style>
<script>
	$(function(){
		//아이디 중복검사
		$("#idCheck").click(function(){
			if($("#userid").val()!==""){
				window.open("<%=request.getContextPath()%>/member/idCheck.do?userid="+$("#userid").val(),"idchk","width=500px,height=400px");//파일명, 창이름,크기
			}else{
				alert("아이디를 입력후 중복검사하세요....");
			}
		});
		//아이디 중복검사 해제
		var userid;
		$("#userid").keyup(function(){
			$("#hiddenCheck").val("N");
			userid = $("#userid").val();
		});
		$("#userid").change(function(){
			if(userid!=$("#userid")){
				$("#hiddenCheck").val("N");
				//userid = $("#userid").val();
			}
		});
		//우편번호 검색 버튼 클릭
		$("#zipSearch").click(function(){
			window.open("<%=request.getContextPath()%>/member/zipSearch.do","zipcode","width=600, height=600");
		});
		//회원정도 유효성 검사
		$("#memId").submit(function(){
			if($("#userid").val()==""){
				alert("아이디를 입력하세요...");
				return false;
			}
			if($("#hiddenCheck").val() !=="Y"){//Y랑 같지 않으면
				alert("아이디중복검사를 하세요..");
				return false;
			}
			if($("#userpwd").val()==""){
				alert("비밀번호를 입력하세요...");
				return false;
			}
			if($("#userpwd").val() !== $("#userpwd2").val()){ //비밀번호 확인이랑 비밀번호가 같은지확인
				alert("비밀번호 다릅니다...");
				return false;
			}
			if($("#username").val()==""){
				alert("이름을 입력하세요");
				return false;
			}
			if($("#tel2").val()=="" || $("#tel3").val()==""){
				alert("전화번호를 입력하세요...");
				return false;
			}
			//관심분야 checked의 갯수를 구한다.
			var cnt = 0;
			$("input:checkbox[name=interest]").each(function(){
				if(this.checked){
					cnt++;
				}
			});
			if(cnt<2){
				alert("관심분야를 2개 이상 선택하세요...");
				return false;
			}
		});
		return true;
	});
</script>
<div class="container">
	<h1>회원가입하기</h1>
	<form method="post" id="memId" action="<%=request.getContextPath()%>/member/memberFormOk.do">
		<ul id="regForm">
			<li>아이디</li>
			<li><input type="text" name="userid" id="userid" maxlength="20"/>
				<input type="button" value="아이디중복검사" id="idCheck"/>
				<input type="hidden" name ="hiddenCheck" id="hiddenCheck" value="N"/><!--아이디 중복검사를 ㅏㅎ면 value를 Y로 바꿔준다.-->
			</li>
			<li>비밀번호</li>
			<li><input type="password" name="userpwd" id="userpwd" maxlength="20" value="1234"/></li>
			<li>비밀번호 확인</li>
			<li><input type="password" name="userpwd2" id="userpwd2" maxlength="20" value="1234"/></li>
			<li>이름</li>
			<li><input type="text" name="username" id="username" maxlength="20" value="이지수"/></li>
			<li>연락처</li>
			<li>
				<select name="tel1" id="tel1">
					<option value="010">010</option>
					<option value="02">02</option>
					<option value="031">031</option>
					<option value="033">033</option>
				</select>
				-<input type="text" name="tel2" id="tel2" maxlength="4" value="1234"/>-
				<input type="text" name="tel3" id="tel3" maxlength="4" value="1234"/>
			</li>
			<li>이메일</li>
			<li>
				<input type="text" name="emailid" id="emailid" maxlength="20" value="gggg"/>@
				<select name="emaildomain">
					<option value="nate.com">nate.com</option>
					<option value="naver.com">naver.com</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="gamil.com">gamil.com</option>
				</select>
			</li>
			<li>주소</li>
			<li><input type="text" name="zipcode" id="zipcode" size="5"/>
				<input type="button" value="우편번호검색" id="zipSearch"/><br/>
				<input type="text" name="addr" id="addr" size="40"/>
			</li>
			<li>상세주소</li>
			<li><input type="text" name="detailaddr" id="detailaddr"/></li>
			<li>관심분야</li>
			<li>
				<input type="checkbox" name="interest" value="컴퓨터"/>컴퓨터
				<input type="checkbox" name="interest" value="쇼핑"/>쇼핑
				<input type="checkbox" name="interest" value="등산"/>등산
				<input type="checkbox" name="interest" value="독서"/>독서
				<input type="checkbox" name="interest" value="인라인"/>인라인
				<input type="checkbox" name="interest" value="자전거"/>자전거
			</li>
			<li>
				<input type="submit" value="회원가입하기"/>
				<input type="reset" value="다시쓰기"/>
			</li>
		</ul>
	</form>
</div>



































