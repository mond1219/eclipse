<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
<style>
	ul, li{margin:0; padding:0; list-style-type:none;}
	#boardList li{float:left; width:10%; height:40px; line-height:40px; border-bottom:1px solid lightblue; }
	#boardList li:nth-child(6n+3){width:50%;}
	.wordcut{white-sapce:nowrap; overflow:hidden; text-overflow:ellips;}
</style>
<script>
	/* $(()=>{
			$("#serchFrm").submit((=>)){
				if($("#searchWord").val()==""){
					alert("검색어 입력하세요");
					return false;
				}
				return true;
			}
	}); */
	
	$(document).ready(function(){
	    //최상단 체크박스 클릭
	    $("#checkall").click(function(){
	        //클릭되었으면
	        if($("#checkall").prop("checked")){
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
	            $("input[name=checkNo]").prop("checked",true);
	            //클릭이 안되있으면
	        }else{
	            //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
	            $("input[name=checkNo]").prop("checked",false);
	        }
	    })
	    //선택삭제가 클릭되면 
	    $("#delSelect").click(function(){
	    	$("#delList").submit();
	    })
	})
</script>
</head>
<body>
<div class="container">
	<h1>Oracle Mybaits noXML 리스트</h1>
	<c:if test="${logStatus!=null }">
		<a href="boardWrite">글쓰기</a>
	</c:if>
	<div id="search">
		<form method="get" action="list">
			<select name="searchKey">
				<option value="subject">제목</option>
				<option value="userid">작성자</option>
				<option value="content">글내용</option>
			</select>
			<input type="text" name="searchWord"/>
			<input type="submit" value="검색"/>
		</form>
	</div>
	<form id="delList"action="multiDel">
		<p>
			<input type="checkbox" id="checkall" value="전체선택"/>전체선택  
			<input type="button" id="delSelect" value="선택삭제"/>
		</p>
		<ul id="boardList">
			<li>선택</li>
			<li>번호</li>
			<li>제목</li>
			<li>작성자</li>
			<li>등록일</li>
			<li>조회수</li>
			<c:forEach var="vo" items="${list}">
				<li><input type="checkbox"name="checkNo" value="${vo.no }"/></li>
				<li>${vo.no }</li>
				<li class="wordcut"><a href="boardView?no=${vo.no}">${vo.subject}</a></li>
				<li>${vo.userid}</li>
				<li>${vo.writedate }</li>
				<li>${vo.hit }</li>
			</c:forEach>
		</ul>
	</form>
</div>

</body>
</html>