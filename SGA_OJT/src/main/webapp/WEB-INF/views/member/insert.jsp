<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="expire" content="-1" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="imagetoolbar" content="no" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
    <meta name="robots" content="index,follow" />
    <!-- css, javascript -->
    <link href="resources/css/common.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    
<title>registe</title>

<script>
$(document).ready(function(){


});//ready



</script>


</head>

</head>
<body>

<div id="container">
    <div class="inner">
        <div class="form_content signup_wrap">
            <h1>User registration</h1>
            <form action="insertMember" method="post">
                <label>아이디</label>
                <div class="check">
	                <input type="text" name="user_id" placeholder="ID" id="user_id">
	                <button type="button" onclick="checkId()" id="idcheck">중복 확인</button>
                </div>
              	<span><font id="checkId"></font></span>
              	
                <label>비밀번호</label>
                <div class=checkpw>
                <input type="password" name="user_password" placeholder="비밀번호 입력(문자,숫자,특수문자 포함 9자 이상)" id="user_password" class="pwcheck1">
                </div>
                <span class="pass"><font id="checkPw1"></font></span>
                <label>비밀번호 확인</label>
                <div class=checkpw>
	                <input type="password"  placeholder="비밀번호 재입력" id="check_password" class="pwcheck2">
                </div>
                <span class="pass"><font id="checkPw2"></font></span>
                <button id="signbtn" class="button2 button-md button-primary button-winona wow fadeInRight" type="submit" formmethod="post" disabled="disabled">가입하기</button>

            </form>
        </div>
    </div>
</div>

</body>

<script>

let idChecked = false; //중복 확인을 거쳤는지 확인


function checkId(){
	var checkurl = 'checkId';
	var user_id = $('#user_id').val();
	
	$.ajax({
		url : checkurl,
		type : 'post',
		data : {user_id:user_id},
		dataType : 'json',
		success : function(cnt){
			console.log("통신 성공");
			console.log(user_id);
			if(cnt != 1  ){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                $("#checkId").html("사용 가능한 아이디입니다.")
                $("#checkId").attr('color','green');
                idChecked = true;
            } else  { // cnt가 1일 경우 -> 이미 존재하는 아이디
            	$("#checkId").html("이미 사용중인 아이디입니다.")
                $("#checkId").attr('color','red');
                idChecked = false;
                
			}
		},
		error:function(){
			console.log("통신 실패");
			console.log(user_id);
			
		}
			
	});
}//checkid end
</script>
<script>
let pwChecked1 = false;

$(".pwcheck1").on('focusout',function(e){
	checkPw1($(this).val());
})


function checkPw1(pw){
	var id = $("#user_id").val();
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/;
	var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	
	if(pw==""){
		$("#checkPw1").text("");
		return;
	}
	
	if(false === reg.test(pw) || /(\w)\1\1\1/.test(pw) || pw.search(id) > -1){
		$("#checkPw1").html("9~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
		pwChecked1 = false;
	}else{
		$("#checkPw1").html("");
		pwChecked1 = true;
	}
	setAble();
}
</script>
<script>
let pwChecked2 = false;

//비밀번화 재확인
$(".pwcheck2").on('focusout',function(e) { // 비밀번호 칸 나갈 때 마다 중복검사 실행
    checkPw2($(this).val());
})


function checkPw2(pw){
	
		if(pw==""){
			$("#checkPw2").text("");
			return;
		}
		
		if( $("#user_password").val() != $("#check_password").val() ){//비밀번호가 일치하지 않는다면
			$("#checkPw2").html('비밀번호가 일치하지 않습니다'); //문구 출력
			$("#checkPw2").attr('color','red');
			$("#check_password").focuson();
			pwChecked2 = false;
		}
		else {
			$("#checkPw2").html('비밀번호가 일치합니다.');
			$("#checkPw2").attr('color','green');
			pwChecked2 = true;
		}
		setAble();
	}
</script>
<script>
	function setAble(){
		console.log("idChecked : " + idChecked + " pwChecked : " + pwChecked1);
		if(idChecked && pwChecked1 && pwChecked2){
			//아이디와 비밀번호 둘다 유효성 검사를 마쳤다면
			$("button[type=submit]").removeAttr("disabled");
		}
		else{
			$("button[type=submit]").attr("disabled","")
			
		}
	}
</script>


</html>
