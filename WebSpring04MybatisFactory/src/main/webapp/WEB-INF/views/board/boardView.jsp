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
	#replyList {background-color:#e9ecef;width:800px;}
</style>
<script>
	function boardDel(){
		if(confirm("삭제하시겠습니까?")){
			location.href="boardDel?no=${vo.no}";
		}
	}
	//ajax를 이용한 댓글처리 
	$(function(){
		function replyList(){
			var url="/home/replyList";
			var params="no=${vo.no}";
			
			$.ajax({
				url : url,
				data : params,
				success : function(result){
					var $result = $(result);
					var tag="<ul>";
					
					$result.each(function(idx,obj){
						tag+="<li style='border-bottom:1px solid gray'><div>"+obj.userid+"("+obj.replydate+") ";
						console.log("${logId}"+obj.userid);
						if(obj.userid=="${logId}"){//현재로그인한 사람과 댓글작성자가 동일할때 
							tag+="<input type='button' value='수정'/>";
							tag+="<input type='button' value='삭제' title='"+obj.num+"'/>";
						}
						tag+="<br/>"+obj.content+"</div>";
						
						if(obj.userid=="${logId}"){
							//수정 폼
							tag+="<div style='display:none;'> ";//style='display:none;'
							tag+="<form method='post'>";
							tag+="<textarea name='content' style='width:500px;height:80px;'>"+obj.content+"</textarea>";
							tag+="<input type='submit' value='수정'/>";
							tag+="<input type='hidden' name='num' value='"+obj.num+"'/>";
							tag+="</form></div>";
						}
						tag+="</li>"
					});	
					tag+="</ul>";
					$("#replyList").html(tag);
				},error : function(){
					console.log("댓글리스트 가져오기 에러,,,");
				}
			});
		}//댓글목록 출력 함수
		replyList();//목록출력
		//댓글 등록
		$("#replyWrite").click(function(){
			if($("#replycontent").val()!=''){
				var url = "/home/boardReplyFrm";
				var params = $("#replyFrm").serialize();//데이터 넣어주는 작업
				$.ajax({
					url : url,
					data : params,
					beforeSend : function(xmlHttpRequest){
						xmlHttpRequest.setRequestHeader("AJAX","true");
					},success : function(result){
						replyList();
						$("#replycontent").val("");
					},error:function(response){
						if(response.status==400){
							alert("로그인하세요");
							location.href="/home/loginForm";
						}else{
							alert("댓글등록실패,,,,하ㅜㅅㅜ");	
						}
					}
				});//ajax
			}//if
			else{//작성한 글이 없을때 
			 	alert("댓글내용을 작성하세요,,");	
			}
		});//click 이벤트
		//수정버튼 클릭하면 수정 폼 보여주기 
	      $(document).on('click','#replyList input[value=수정]',function(){
	    	  $("#replyList>ul>li>div:nth-child(1)").css("display","block");//글목록보여주기
	    	  $("#replyList>ul>li>div:nth-child(2)").css("display","none");//수정박스 숨기기
	    	  $(this).parent().css("display","none");
	    	  $(this).parent().next().css("display","block");
	      });//수정버튼 클릭시 수정 폼보여주기 
		//댓글수정버튼 클릭시 
		$(document).on('submit', '#replyList form', function(){
			var url = "/home/replyEdit"
			var params = $(this).serialize();//
			$.ajax({
				url : url,
				data : params,
				type : 'POST',
				success : function(result){
					replyList();
				},error : function(){
					console.log("댓글 수정 에러")
				}
			});
		});//댓글수정버튼 클릭시
		//댓글 삭제
		$(document).on('click', '#replyList input[value=삭제]',function(){
			if(confirm('삭제하시겠습니까?')){
				var url ="/home/replyDel";
				var params = "num="+$(this).attr("title");
				$.ajax({
					url : url,
					data : params,
					success : function(result){
						replyList();
					},error : function(){
						console.log("댓글삭제 실패,,");
					}
				});
			}
		})
		
	});
</script>
</head>
<body>
<div class="container">
	<h1>글내용보기</h1>
	번호 : ${vo.no }<br/>
	작성자 : ${vo.userid }<br/>
	작성일 : ${vo.writedate }, 조회수 : ${vo.hit }<br/>
	제목 : ${vo.subject }<br/>
	글내용 : ${vo.content }
	<c:if test="${logId==vo.userid }">
		 <a href="boardEdit?no=${vo.no }">수정</a>
		 <a href="javascript:boardDel()">삭제</a>
	</c:if>
	
	<!-- 댓글쓰기 부분 -->
	<div>
		<form method="post" id="replyFrm">
		<input type="hidden" name="no" value="${vo.no }"/>
			<textarea id="replycontent" name="content"></textarea>
			<input type="submit" value="댓글등록" id="replyWrite"/>
		</form>
	</div>
	<!-- 댓글리스트 -->
	<div id="replyList"></div>
</div>
</body>
</html>