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
	
	document.addEventListener("keydown", function(event) {
		  if (event.keyCode === 13) {
		   $("#loginBtn").click();
		  }
		});
	
	
	$("#loginBtn").on('click', function() {
		if($("#userId").val() == ""){
			$("#loginChk").html("아이디를 입력해 주세요.");
		}
		else if($("#userPassword").val() == ""){
			$("#loginChk").html("비밀번호를 입력해 주세요.");
		}
		else{
			loginChk();
		}
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
				$("#loginChk").html("아이디 또는 비밀번호를 잘못입력했습니다.<br>입력하신 내용을 다시 확인해주세요");
				$("#loginChk").attr('color','red');
			}else if(data.Code == 1){
				location.href ="list";
			}else if (data.Code == 3){
				$("#loginChk").html("로그인 시도 초과");
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

  <title>Sign in</title>

</head>
<body>
  <div class="main">
    <p class="sign" align="center">Sign in</p>
    <div class="form1">
      <input class="un " id="userId" name="userId" type="text" align="center" placeholder="Username">
      <input class="pass" id="userPassword" name="userPassword" type="password" align="center" placeholder="Password">
      <input class="submit" id="loginBtn" type="button" value="Log in" style="background-color: #2186db;">  
      <div class="loginChk" id="loginChk">

 	</div>
    </div>    
     </div>
</body>
</html>
