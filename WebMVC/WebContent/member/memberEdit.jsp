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
		//우편번호 검색 버튼 클릭
		$("#zipSearch").click(function(){
			window.open("<%=request.getContextPath()%>/member/zipSearch.do","zipcode","width=600, height=600");
		});
		//회원정보 유효성 검사
		$("#memId").submit(function(){
			if($("#userpwd").val()==""){
				alert("비밀번호를 입력하세요...");
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
	<h1 style="padding:30pz;text-align:center;">회원정보수정</h1>
	<form method="post" id="memId" action="<%=request.getContextPath()%>/member/memberEditOk.do">
		<ul id="regForm">
			<li>아이디</li>
			<li>${userid}</li>
			<li>비밀번호</li>
			<li><input type="password" name="userpwd" id="userpwd" maxlength="20"/></li>
			<li>이름</li>
			<li><input type="text" name="username" id="username" maxlength="20" value="${vo.username }" readonly/></li>
			<li>연락처</li>
			<li>
				<select name="tel1" id="tel1">
					<option value="010" <c:if test="${vo.tel1 =='010' }">selected</c:if>>010</option>
					<option value="02" <c:if test="${vo.tel1 =='02' }">selected</c:if>>02</option>
					<option value="031" <c:if test="${vo.tel1 =='031' }">selected</c:if>>031</option>
					<option value="033" <c:if test="${vo.tel1 =='033' }">selected</c:if>>033</option>
				</select>
				-<input type="text" name="tel2" id="tel2" maxlength="4" value="${vo.tel2 }"/>-
				<input type="text" name="tel3" id="tel3" maxlength="4" value="${vo.tel3 }"/>
			</li>
			<li>이메일</li>
			<li>
				<input type="text" name="emailid" id="emailid" maxlength="20" value="${vo.emailid }"/>@
				<select name="emaildomain">
					<option value="nate.com">nate.com</option>
					<option value="naver.com">naver.com</option>
					<option value="hanmail.net">hanmail.net</option>
					<option value="gamil.com">gamil.com</option>
				</select>
			</li>
			<li>주소</li>
			<li><input type="text" name="zipcode" id="zipcode" size="5" value="${vo.zipcode }"/>
				<input type="button" value="우편번호검색" id="zipSearch" ><br/>
				<input type="text" name="addr" id="addr" size="40" value="${vo.addr }"/>
			</li>
			<li>상세주소</li>
			<li><input type="text" name="detailaddr" id="detailaddr" value="${vo.detailaddr }"/></li>
			<li>관심분야</li>
			<li>
				<input type="checkbox" name="interest" value="컴퓨터"<c:forEach var="i" items="${vo.interest}"><c:if test="${i=='컴퓨터'}">checked</c:if></c:forEach>>컴퓨터
				<input type="checkbox" name="interest" value="쇼핑"<c:forTokens var ="i" items="${vo.interestStr }" delims="/"><c:if test="${i=='쇼핑' }">checked</c:if></c:forTokens>>쇼핑
				<input type="checkbox" name="interest" value="등산"<c:forEach var="i" items="${vo.interest}"><c:if test="${i=='등산'}">checked</c:if></c:forEach>>등산
				<input type="checkbox" name="interest" value="독서"<c:forEach var="i" items="${vo.interest}"><c:if test="${i=='독서'}">checked</c:if></c:forEach>>독서
				<input type="checkbox" name="interest" value="인라인"<c:forEach var="i" items="${vo.interest}"><c:if test="${i=='인라인'}">checked</c:if></c:forEach>>인라인
				<input type="checkbox" name="interest" value="자전거"<c:forEach var="i" items="${vo.interest}"><c:if test="${i=='자전거'}">checked</c:if></c:forEach>>자전거
			</li>
			<li>
				<input type="submit" value="회원수정하기"/>
				<input type="reset" value="다시쓰기"/>
			</li>
		</ul>
	</form>
</div>



































