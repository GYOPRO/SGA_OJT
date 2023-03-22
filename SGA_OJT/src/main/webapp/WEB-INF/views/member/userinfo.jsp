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

let query = window.location.search;
let param = new URLSearchParams(query);
let user_id = param.get('user_id');

console.log(user_id); 

function selectOneMember() {
    var listurl = "getMember?user_id="+user_id;
    let list = [];

    $.ajax({
        url : listurl,
        type : 'get',
        dataType : 'json',
        success : function(res){
            console.log("통신성공");

        },
        error:function(){
            console.log("통신에러");
        }
    });//ajax
};
</script>

</head>
<body>
<div class="userInfo">

</div>
</body>
</html>