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
    <link href="resources/css/common2.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    
<title>registe</title>

<script type="text/javaScript">
idChecked = false; //중복 확인을 거쳤는지 확인
pwChecked1 = false;
pwChecked2 = false;

$(document).ready(function(){
	

	
	$("#idCheck").on('click',function(e){
		if($("#userId").val() == ""){
			$("#checkId").html("아이디를 입력해주세요")
            $("#checkId").attr('color','red');
		}else{
			checkId();
		}
	});
	
	//비밀번호 조건 확인
	$("#userPassword").on('focusout', function(e){
		checkPw1($(this).val());
		checkPw2($("#checkPassword").val()); // 비밀번호 확인란도 확인
		setAble();	
	});
	
	//비밀번화 재확인
	$("#checkPassword").on('focusout',function(e) {
	    checkPw2($(this).val());
	})
	 
	//userId 변경 시
    $("#userId").on('change', function(){
    	changeId();
    	console.log(idChecked);
    });
	
	$("#signBtn").on('click',function(e){
		console.log("버튼 클릭")
		setAble();
		if(idChecked === false){
			e.preventDefault();
			console.log("아이디 변경 막기 성공")
		}
		
	});

});//ready

function checkId(){
	var checkUrl = 'checkId';
	var userId = $('#userId').val();
	
	$.ajax({
		url : checkUrl,
		type : 'post',
		data : {userId:userId},
		dataType : 'json',
		success : function(cnt){
			console.log("통신 성공");
			if(cnt == 0  ){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                $("#checkId").html("사용 가능한 아이디입니다.")
                $("#checkId").attr('color','green');
                idChecked = true;
            } else if(cnt == 1) { // cnt가 1일 경우 -> 이미 존재하는 아이디
            	$("#checkId").html("이미 사용중인 아이디입니다.")
                $("#checkId").attr('color','red');
                idChecked = false;                
			} else if(userId == ""){
				alert(x);
				idChecked = false;
			}
		},
		error:function(){
			console.log("통신 실패");
		}
	});
}

//중복검사 후 아이디 변경 되었을 때
function changeId(){
	if($("#userId").val() == ""){
		return
	}else{
	idChecked = false;
	$("#checkId").html("");
	}
}

//비밀번호 조건 확인
function checkPw1(pw){
	var id = $("#userId").val();
	var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/;
	var streamReg = /(.)\1{3,}/i; // {3,} 3번이상 반복 / i 대소문자 구분x / 
	/* var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; */
	
	if(id =="" || idChecked === false){
		$("#checkId").html("아이디 입력 및 중복 확인 해주세요");
		$("#checkId").attr('color','red');
		$("#userId").focus();
		$("#userPassword").val("");
	}
	else if(false === reg.test(pw) || /(\w)\1\1\1/.test(pw)){
		$("#checkPw1").html("9~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
		$("#checkPw1").attr('color','red');
		pwChecked1 = false;
	}
	else if(pw.search(id) > -1){
		$("#checkPw1").html("아이디와 동일한 비밀번호를 사용할 수 없습니다.")
		$("#checkPw1").attr('color','red');
		pwChecked1 = false;
	}
	else if(streamReg.test(pw) === true){
		$("#checkPw1").html("연속된 배열은 사용할 수 없습니다.")
		$("#checkPw1").attr('color','red');
		pwChecked1 = false;
	}
	else{
		$("#checkPw1").html("");
		pwChecked1 = true;
	}
	
}

function checkPw2(pw){
	
		if(pw==""){
			$("#checkPw2").text("");
			return;
		}
		
		if( $("#userPassword").val() != $("#checkPassword").val() ){//비밀번호가 일치하지 않는다면
			$("#checkPw2").html('비밀번호가 일치하지 않습니다'); //문구 출력
			$("#checkPw2").attr('color','red');
			$("#checkpassword").focus();
			pwChecked2 = false;
		}
		else {
			$("#checkPw2").html('비밀번호가 일치합니다.');
			$("#checkPw2").attr('color','green');
			pwChecked2 = true;
		}
	setAble();
	}
	
	function setAble(){
		console.log("idChecked : " + idChecked + " pwChecked1 : " + pwChecked1+ " pwChecked2 : " + pwChecked2);
		if(idChecked && pwChecked1 && pwChecked2){
			//아이디와 비밀번호 둘다 유효성 검사를 마쳤다면
			$("button[type=submit]").removeAttr("disabled");
		}
		else{
			checkPw2($(this).val());
			$("button[type=submit]").attr("disabled","")
		}
	}

</script>

</head>

<body>

<div id="container">
    <div class="inner">
        <div class="form_content signup_wrap">
            <h1>User registration</h1>
            <form action="insertMember" method="post" id="insertForm">
                <label>아이디</label>
                <input type="text" name="userId" placeholder="ID" id="userId">
                <button type="button" id="idCheck">중복 확인</button>
                <span><font id="checkId"></font></span>
                <label>비밀번호</label>
                <input type="password" name="userPassword" placeholder="9~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요" id="userPassword" class="pwCheck1">
                <span><font id="checkPw1"></font></span>
                <label>비밀번호 확인</label>
                <input type="password"  placeholder="비밀번호 재입력" id="checkPassword" class="pwCheck2">
                <span><font id="checkPw2"></font></span>
                <button id="signBtn" class="button2 button-md button-primary button-winona wow fadeInRight" type="button" formmethod="post" >가입하기</button>
            </form>
        </div>
    </div>
</div>

</body>

</html>
