<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>MemberList | Othub</title>
   <link href="css/member/coupon/import.css" rel="stylesheet">
    <link href="css/member/coupon/common.css" rel="stylesheet">
	<script src="js/jquery-3.6.0.min.js"></script>
	<script>
	$(document).ready(function() {
		
		$("#deletebtn").click(function(ev){
			 if(!confirm("탈퇴 처리 하시겠습니까?")) {
				 ev.preventDefault();
			 }
		 });
		
		});//ready end
	</script>
</head>
<body>

	
	<section class="myorder">

		<div class="myorderBox">
			<h3 class="orderTitle">쿠폰함</h3>
				<div class="inner">
	<c:forEach items="${couponlist }" var="list">
            <div id="screen1">
               <div id = "period">유효기간 ${list.period }일</div>
               <div id="name">${list.cp_code} 쿠폰</div>
               <div id = "discount">${list.cp_discountValue } 원</div>
               <div id="date">${list.cp_createdAt } ~ ${list.cp_endAt }</div>
          </div>
            </c:forEach>
   </div>

   </div>
	</section>
	

</body>


</html>