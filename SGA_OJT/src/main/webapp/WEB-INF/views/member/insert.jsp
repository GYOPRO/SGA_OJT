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
var idChecked = false; //중복 확인을 거쳤는지 확인
var pwChecked1 = false;
var pwChecked2 = false;

$(document).ready(function(){
	

	
	$("#idCheck").on('click',function(e){
		var regex = /^[\w-]{5,20}$/;
		var userId = $("#userId").val();
		if(userId == ""){
			$("#checkId").html("아이디를 입력해주세요")
            $("#checkId").attr('color','red');
		}else{
			if(regex.test(userId)){
				checkId();
			}else {
				$("#checkId").html("5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.")
	            $("#checkId").attr('color','red');
			}
			
		}
	});
	
	//비밀번호 조건 확인
	$("#userPassword").on('focusout', function(e){
		checkPw1($(this).val());
		checkPw2($("#checkPassword").val()); // 비밀번호 확인란도 확인
		
	});
	
	//비밀번화 재확인
	$("#checkPassword").on('focusout',function(e) {
	    checkPw2($(this).val());
	})
	 
	//userId 변경 시
    $("#userId").on('change', function(){
        idChecked = false;
        $("#checkId").html("")
    });
	
	//가입하기 누를 때 한번더 확인
	$("#signBtn").on('click',function(e){
		
		if(idChecked === false){
			$("#checkInsert").html("아이디를 확인해주세요")
			e.preventDefault();
		}
		else if(pwChecked1 === false || pwChecked2 === false ){
			$("#checkInsert").html("비밀번호를 다시 한번 확인해 주세요")
			e.preventDefault();
		}else {
			$("#insertForm").submit();
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
                $("#checkInsert").html("")
                idChecked = true;
            } else if(cnt == 1) { // cnt가 1일 경우 -> 이미 존재하는 아이디
            	$("#checkId").html("이미 사용중인 아이디입니다.")
                $("#checkId").attr('color','red');
            	$("#checkInsert").html("")
                idChecked = false;                
			} else if(userId == ""){
				idChecked = false;
			}
		},
		error:function(){
			console.log("통신 실패");
		}
	});
}

//비밀번호 조건 확인
function checkPw1(pw){
	var id = $("#userId").val();
	var reg1 = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,16}$/; //8~16 대,소문자,숫자,특수문자
	var reg2 = /(.)\1{3,}/i; // 같은 문자 4개 이상
	var reg3 = ["1234567890", "qwertyuiop", "asdfghjkl", "zxcvbnm"];
	var reg3Chk = false;
	for(let i = 0 ; i < pw.length-2 ; i++){
	  const sliceValue = pw.substring(i,i+3);
	  
	  if(reg3.some((code) => code.includes(sliceValue))){
	    reg3Chk = true;
	  }
	}

	/* var hangulcheck = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/; */
	if(id =="" || idChecked === false){
		$("#checkId").html("아이디 입력 및 중복 확인 해주세요");
		$("#checkId").attr('color','red');
		$("#userId").focus();
	}
	else if(false === reg1.test(pw) ){
		$("#checkPw1").html("9~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
		$("#checkPw1").attr('color','red');
		pwChecked1 = false;
	}
	else if(reg2.test(pw)){
		$("#checkPw1").html("동일한 문자는 4번 이상 연속적으로 사용할 수 없습니다.")
		$("#checkPw1").attr('color','red');
		pwChecked1 = false;
	}
	else if(pw.search(id) > -1){
		$("#checkPw1").html("아이디가 포함된 비밀번호는 사용할 수 없습니다.")
		$("#checkPw1").attr('color','red');
		pwChecked1 = false;
	}
	else if(reg3Chk){
		$("#checkPw1").html("키보드의 연속된 배열은 나열할 수 없습니다.")
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
			$("#checkInsert").html("")
			pwChecked2 = true;
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
                <span><font id="checkInsert"></font></span>
            </form>
        </div>
    </div>
</div>

</body>

</html>
