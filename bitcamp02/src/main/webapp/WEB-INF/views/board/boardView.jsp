<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
   function del(){
      if(confirm("삭제하시겠습니까?")){
         location.href="/jdbc/boardDel?no=${vo.no}";
      }
   }
   // ajax를 이용한 댓글처리
   $(function(){
	   //댓글 목록전체
	   function replyList(){
		   //서버에서 해당글의 댓글을 선택하여 
		   var url ="/jdbc/replyList";
		   var params="no=${vo.no}";
		   $.ajax({
			  url:url,
			  data:params,
			  success:function(result){
				  var $result = $(result);//vo1, vo2, 여러개 들어가있다. 
				  
				  var tag="<ul>";
				  $result.each(function(idx, obj){ //vo가 obj로 된다. idx는 반복하는횟수
					  tag+= "<li style='border-bottom:1px solid gray'><div>"+obj.userid+"("+obj.replydate+") ";
					  
					  
					  if(obj.userid=="${logId}"){//작성자랑 현재로그인 아이디한사람이 같으면 찍어준다.
						  tag+= "<input type='button' value='수정'/>";   //레코드번호
						  tag+= "<input type='button' value='삭제' title='"+obj.num+"'/>";  
					  }
					  
					  tag+="<br/> "+obj.content+"</div>";
					  if(obj.userid=="${logId}"){
						  //수정폼
						  tag+="<div style='display:none;'>";
						  tag+="<form method='post'>"
						  tag+="<textarea name='content' style='width:500px; height:80px;'>"+obj.content+"</textarea>";
						  tag+="<input type='submit' value='수정하기'/>";
						  tag+="<input type='hidden' name='num' value='"+obj.num+"'/>";
						  tag+="</form></div>";
					  }
					  tag+="</li>";
					  
				  });
				  tag+="<ul>";
				  $("#replyList").html(tag);
				  
			  },error:function(){
				  console.log("댓글리스트 가져오기 에러,,");
			  }
		   });
	   }
      // 댓글쓰기
      $("#replyWriteBtn").click(function(){
    	 	console.log("여기에 오류가 있니,,,");
    	  if($("#content").val()!=''){
	         var url = "/jdbc/replyWriteOk";
	         var params = $("#replyWriteFrm").serialize();// 데이터.. no=181&content=제곧내
	         $.ajax({
	            url : url,
	            data : params,
	            success:function(result){
	            	replyList();
	            	$("#content").val("");
	            },error:function(){
	               alert("컷이다 임마ㅎ");
	            }
	         });
    	  }else{
    		  alert("댓글을 입력후 클릭하세요,,,");
    	  }
      });
      //글 수정
      $(document).on('submit','#replyList form', function(){
    	  var url = "/jdbc/replyEditOk";
    	  var params = $(this).serialize(); //content=010&num=254
    	  $.ajax({
    		  url : url,
    		  data : params,
    		  type : "POST",
    		  success:function(result){
    			  replyList();
    		  },error:function(){
    			  console.log("댓글수정에러,,,");
    		  }
    	  });
    	  
    	  //댓글삭제
    	/*  $(document).on('click','#replyList input[value=삭제]',function(){
    		  if(confirm('삭제하시겠습니까?')){
    			  var url = "/jdbc/replyDel";
    			  var params = "num=" +$(this).attr("title");//타이틀에 있는 레코드 번호를 구해온다. 
    			  $.ajax({
    				 url : url,
    				 data : params,
    				 success : function(result){
    					 replyList();
    				 },error : function(){
    					 console.log("댓글삭제실패,,,,");
    				 }
    			  });
    		  }
    	  });  */
      });
      replyList();//글내용보기가 실행되면 댓글이 ajax 실행됨
      //수정버튼 클릭하면 수정 폼 보여주기 
      $(document).on('click','#replyList input[value=수정]',function(){
    	  $("#replyList>ul>li>div:ntn-child(1)").css("display","block");//글목록보여주기
    	  $("#replyList>ul>li>div:ntn-child(2)").css("display","none");//수정박스 숨기기
    	  $(this).parent().css("display","none");
    	  $(this).parent().next().css("display","block");
      });
      
      //댓글 삭제
	  $(document).on('click','#replyList input[value=삭제]',function(){
		  if(confirm('삭제하시겠습니까?')){
			  var url = "/jdbc/replyDel";
			  var params = "num=" +$(this).attr("title");//타이틀에 있는 레코드 번호를 구해온다. 
			  $.ajax({
				 url : url,
				 data : params,
				 success : function(result){
					 replyList();
				 },error : function(){
					 console.log("댓글삭제실패,,,,");
				 }
			  });
		  }
	  });
   });
   
</script>
<div class="container">
	${vo.userid }
	<h1>글내용보기</h1>
	<ul>
		<li>번호 : ${vo.no }</li>
		<li>작성자 : ${vo.userid }</li>
		<li>작성일 : ${vo.writedate }</li>
		<li>조회수 : ${vo.hit }</li>
		<li>제목 : ${vo.subject }</li>
		<li>내용 : ${vo.content }</li>
	</ul>
	<div>
		<c:if test="${logStatus=='Y' && logId ==vo.userid }">
			<a href="/jdbc/boardEdit?no=${vo.no }">수정</a>
			<a href="javascript:del()">삭제</a>
		</c:if>
		
	</div>
	<hr/>
	<!-- 댓글폼 -->
	<div>
		<c:if test="${logStatus=='Y' }"><!-- 로그인이 된경우만 댓글쓰기 가능 -->
			<form method="post" id="replyWriteFrm">
				<input type="hidden" name="no" value="${vo.no }"/>
				<textarea id="content" name="content" style="margin:0;width:500px;hieght:100px"></textarea>
				<input type="button" value="댓글등록"id="replyWriteBtn"/>
			</form>
		</c:if>
	</div>
	<!-- 댓글리스트 -->
	<div id="replyList">
		
	</div>
</div>

</body>
</html>