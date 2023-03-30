<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>

<script>
	$(document).ready(function() {

		document.addEventListener("keydown", function(event) {
			if (event.keyCode === 13) {
				$("#loginBtn").click();
			}
		});

		$("#loginBtn").on('click', function() {
			if ($("#userId").val() == "") {
				$("#loginChk").html("아이디를 입력해 주세요.");
			} else if ($("#userPassword").val() == "") {
				$("#loginChk").html("비밀번호를 입력해 주세요.");
			} else {
				loginChk();
			}
		})
	});

	function loginChk() {
		$.ajax({
			url : "/sol/loginChk",
			type : "POST",
			data : {
				userId : $("#userId").val(),
				userPassword : $("#userPassword").val()
			},
			success : function(data) {
				if (data.Code == 0) {
					$("#loginChk").html(
							"아이디 또는 비밀번호를 잘못입력했습니다.<br>입력하신 내용을 다시 확인해주세요");
					$("#loginChk").attr('color', 'red');
				} 
				else if (data.Code == 1) {
					location.href = "list";
				} 
				else if (data.Code == 3) {
					$("#loginChk").html("로그인 시도 초과");
				}
				console.log("통신 성공")

			},
			error : function() {
				console.log("통신 실패")
				alert("err");
			}
		})
	}
</script>
<title>Insert title here</title>
<link
	href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet" href="resources/css/common3.css?ver=1" />
<link href="resources/css/keyboard/style.css?ver=1" rel="stylesheet">
<title>Sign in</title>

</head>
<body>
	<div class="main">
		<p class="sign" align="center">Sign in</p>
		<div class="form1">
			<input class="un " id="userId" name="userId" type="text"
				align="center" placeholder="Username"> <input class="pass"
				id="userPassword" name="userPassword" type="password" align="center"
				placeholder="Password" autocomplete="off"> <input
				class="submit" id="loginBtn" type="button" value="Log in"
				style="background-color: #2186db;">
			<div class="loginChk" id="loginChk"></div>
		</div>
	</div>
<div class="keyboard" id="keyboard" >
            <div class="row">
                <div class="key" data-code="Backquote" data-val="`">
                    <span class="two-value">~</span>
                    <span class="two-value">`</span>
                </div>
                <div class="key" data-code="Digit1" data-val="1">
                    <span class="two-value">!</span>
                    <span class="two-value">1</span>
                </div>
                <div class="key" data-code="Digit2" data-val="2">
                    <span class="two-value">@</span>
                    <span class="two-value">2</span>
                </div>
                <div class="key" data-code="Digit3" data-val="3">
                    <span class="two-value">#</span>
                    <span class="two-value">3</span>
                </div>
                <div class="key" data-code="Digit4" data-val="4">
                    <span class="two-value">$</span>
                    <span class="two-value">4</span>
                </div>
                <div class="key" data-code="Digit5" data-val="5">
                    <span class="two-value">%</span>
                    <span class="two-value">5</span>
                </div>
                <div class="key" data-code="Digit6" data-val="6">
                    <span class="two-value">^</span>
                    <span class="two-value">6</span>
                </div>
                <div class="key" data-code="Digit7" data-val="7">
                    <span class="two-value">&</span>
                    <span class="two-value">7</span>
                </div>
                <div class="key" data-code="Digit8" data-val="8">
                    <span class="two-value">*</span>
                    <span class="two-value">8</span>
                </div>
                <div class="key" data-code="Digit9" data-val="9">
                    <span class="two-value">(</span>
                    <span class="two-value">9</span>
                </div>
                <div class="key" data-code="Digit0" data-val="0">
                    <span class="two-value">)</span>
                    <span class="two-value">0</span>
                </div>
                <div class="key" data-code="Minus" data-val="-">
                    <span class="two-value">_</span>
                    <span class="two-value">-</span>
                </div>
                <div class="key" data-code="Equal" data-val="=">
                    <span class="two-value">+</span>
                    <span class="two-value">=</span>
                </div>
                <div class="key back-space-key" data-code="Backspace" data-val="Backspace">
                    Backspace
                </div>
            </div>
            <div class="row">
                <div class="key tab-key" data-val="clear">Clear</div>
                <div class="key" data-code="KeyQ" data-val="q">Q</div>
                <div class="key" data-code="KeyW" data-val="w">W</div>
                <div class="key" data-code="KeyE" data-val="e">E</div>
                <div class="key" data-code="KeyR" data-val="r">R</div>
                <div class="key" data-code="KeyT" data-val="t">T</div>
                <div class="key" data-code="KeyY" data-val="y">Y</div>
                <div class="key" data-code="KeyU" data-val="u">U</div>
                <div class="key" data-code="KeyI" data-val="i">I</div>
                <div class="key" data-code="KeyO" data-val="o">O</div>
                <div class="key" data-code="KeyP" data-val="p">P</div>
                <div class="key" data-code="BracketLeft" data-val="[">
                    <span class="two-value">{</span>
                    <span class="two-value">[</span>
                </div>
                <div class="key" data-code="BracketRight" data-val="]">
                    <span class="two-value">}</span>
                    <span class="two-value">]</span>
                </div>
                <div class="key back-slash-key" data-code="Backslash" data-val="\">
                    <span class="two-value">|</span>
                    <span class="two-value">\</span>
                </div>
            </div>
            <div class="row">
                <div class="key caps-lock-key">CapsLock</div>
                <div class="key" data-code="KeyA" data-val="a">A</div>
                <div class="key" data-code="KeyS" data-val="s">S</div>
                <div class="key" data-code="KeyD" data-val="d">D</div>
                <div class="key" data-code="KeyF" data-val="f">F</div>
                <div class="key" data-code="KeyG" data-val="g">G</div>
                <div class="key" data-code="KeyH" data-val="h">H</div>
                <div class="key" data-code="KeyJ" data-val="j">J</div>
                <div class="key" data-code="KeyK" data-val="k">K</div>
                <div class="key" data-code="KeyL" data-val="l">L</div>
                <div class="key" data-code="Semicolon" data-val=";">
                    <span class="two-value">:</span>
                    <span class="two-value">;</span>
                </div>
                <div class="key" data-code="Quote" data-val="'">
                    <span class="two-value">"</span>
                    <span class="two-value">'</span>
                </div>
                <div class="key enter-key" data-code="Enter" data-val="Enter">Enter</div>
            </div>
            <div class="row">
                <div class="key left-shift-key" data-code="ShiftLeft" id="shift">Shift</div>
                <div class="key" data-code="KeyZ" data-val="z">Z</div>
                <div class="key" data-code="KeyX" data-val="x">X</div>
                <div class="key" data-code="KeyC" data-val="c">C</div>
                <div class="key" data-code="KeyV" data-val="v">V</div>
                <div class="key" data-code="KeyB" data-val="b">B</div>
                <div class="key" data-code="KeyN" data-val="n">N</div>
                <div class="key" data-code="KeyM" data-val="m">M</div>
                <div class="key" data-code="Comma" data-val=",">
                    <span class="two-value">
                        &lt;
                    </span>
                    <span class="two-value">,</span>
                </div>
                <div class="key" data-code="Period" data-val=".">
                    <span class="two-value">
                        &gt;
                    </span>
                    <span class="two-value">.</span>
                </div>
                <div class="key" data-code="Slash" data-val="/">
                    <span class="two-value">?</span>
                    <span class="two-value">/</span>
                </div>
                <div class="key right-shift-key" data-code="ShiftRight" id="shift">Shift</div>
            </div>

        </div>
</body>
<script type="text/javascript"
	src="resources/js/keyboard/keyboard.js?ver=1"></script>
</html>
