<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"      %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"       %>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"    %>


<!DOCTYPE html>
<html lang="ko">
<head>
	<title>VisualAegis</title>
	<meta charset="UTF-8" http-equiv="Content-Type">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=3.0">
	<meta name="mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no" />
	
	<!-- TODO: search engine info -->
	<meta name="robots" content="VisualAegis" />
	<meta name="keywords" content="VisualAegis" />
	<meta name="title" content="VisualAegis" />
	<meta name="description" content="VisualAegis" />
	
	<!-- TODO: social url link image -->
	<meta property="og:url" content="/">
	<meta property="og:title" content="VisualAegis">
	<meta property="og:type" content="website">
	<meta property="og:image" content="/">
	<meta property="og:description" content="VisualAegis">
	
	<!-- TODO: favicon -->
	<link rel="icon" href="../images/favicon.ico" type="image/x-icon" />
	
	<!-- TODO: import -->
	<link rel="stylesheet" href="../css/style.css">
	
	<script src="../js/jquery.min.js"></script>
	<script type="text/javaScript">
	$(document).ready(function() {
		if(opener == null) $("#btnLayout").show();
	});
	</script>
</head>

<body>
	<section class="container error">
		<article class="error_cont">
			<div class="icon">
				<img src="../images/icon_error01.png" alt="">
			</div>
			<p class="dec">
				<em>일시적 접속장애가 발생하였습니다.</em>
				잠시 후 다시 접속해 주시기 바랍니다. <br>
				이용에 불편을 드려 죄송합니다. <br>
				더 좋은 서비스 제공을 위해 최선을 다하겠습니다. <br>
				감사합니다.
			</p>
			<div id="btnLayout" class="btn_wrap" style="display: none;">
				<a href="javascript:history.back();" class="btn line">이전페이지</a>
				<a href="<c:url value='/index.do'/>" class="btn blue">홈으로</a>
			</div>
		</article>
	</section>
</body>