<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--                                                                      12355 제주특별자치도 서귀포시 __ 신서로79길 15-2, (강정동, 인터넷 카페)   -->
<hr id="z"/>
<c:forEach var="vo" items="${list }">   <!-- 이전페이지에서 req에 list라는 변수명으로 넣어서 보내서 사용 가능함 --> <!-- ZipcodeVO에 있는 변수명 -->
   <li><span class="zCode">${vo.zipcode}</span> <span class="addr">${vo.sido}${vo.sigungu}<c:if test="${vo.um!=null}"> ${vo.um }</c:if> ${vo.doro} ${vo.gibun1 }<c:if test="${vo.gibun2!=0}">-${vo.gibun2 }</c:if>, (${vo.dong} <c:if test="${vo.sibuild!=null }">,${vo.sibuild }</c:if>)</span></li>
</c:forEach>
<hr id="z"/>