<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- js -->
 <script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<title>Insert title here</title>
<script>
$(document).ready(function(){
    selectOneMember(); // 예시로 1을 넘김
});

var query = window.location.search;
var param = new URLSearchParams(query);
var userId = param.get('userId');

function selectOneMember() {
    var listurl = "getMember?userId="+userId;
    let list = [];

    $.ajax({
        url : listurl,
        type : 'get',
        dataType : 'json',
        success : function(res){
        	var list =""
        	console.log("통신성공");
			console.log(res);
			list += '<form action="deleteUser" method="post">'
        	list += '<input type="text" name="userId" value="'+res.userId+'" >';
        	list += '<button type="submit" >삭제</button>';
        	list += '</form>';
			
        	$("#userInfo").append(list);
        },
        error:function(){
            console.log("통신에러");
        }
    });//ajax
};
</script>

</head>
<body>
<div id="userInfo">
	<span id="user"></span>

</div>
</body>
</html>