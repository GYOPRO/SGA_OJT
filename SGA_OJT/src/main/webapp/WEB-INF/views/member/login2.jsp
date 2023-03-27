<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<script>
$(document).ready(function () {
	$("#loginBtn").on('click', function() {
		loginChk();
	})
});

function loginChk(){
	$.ajax({
		url : "/sol/loginChk",
		type : "POST",
		data : {
			userId : $("#userId").val(),
			userPassword : $("#userPassword").val()
		},
		success : function(data){
			if(data.Code == 0){
				alert("아이디 비밀번호 재확인");
			}else if(data.Code == 1){
				location.href ="list";
			}else if (data.Code == 3){
				alert("로그인 시도 초과");
			}
			console.log("통신 성공")
		
			
		},
		error : function(){
			console.log("통신 실패")
			alert("err");
		}
	})
}
</script>
<title>Insert title here</title>
<link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="resources/css/common3.css" />

</head>
  <body>
<div id="container">
    <div class="inner">
      	 <div class="form_content form_content_login">
            <h1>LOG IN</h1>
                <fieldset>회원가입 폼</fieldset>
                <input id="userId" type="text" name="userId" placeholder="Id">
                <input id="userPassword" type="password" name="userPassword" placeholder="Password">
                <a href="signin">Sign in</a>
                <input id="loginBtn" type="submit" value="Log in" style="background-color: #2186db;">

        </div>
    </div>
</div>

  </body>
</html>
