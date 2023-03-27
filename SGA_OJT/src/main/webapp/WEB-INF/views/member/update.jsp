<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Update User</title>
<!--meta -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- css -->
<link href="resources/css/update.css" rel="stylesheet">
<!-- js -->
<script src="http://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    	selectAllMember();
});
    
    function selectAllMember() {
    	
    	var listurl = "getMemberList"
		let list = [];
    	
    	$.ajax({
    		url : listurl,
    		type : 'post',
    		dataType : 'json',
    		success : function(res){
    			var memberList = res.memberList;
    			var list="" ;
    			$.each(memberList,function(i,item){
    				
    			    list += '<tr class="styleone">';
    			    
    			    list += '<td><input type="text" value="' + item.userId + '"></td>';
    			    list += '<td>' + item.userPassword + '</td>';
    			    list += '<td>' + item.allow_ip + '</td>';
    			    list += '<td>' + item.access_ip + '</td>';
    			    list += '<td>' + item.lock_dtm + '</td>';
    			    list += '<td>' + item.last_login_dtm + '</td>';
    			    list += '<td>' + item.fail_count + '</td>';
    			    list += '<td class="deletemember"><input type="submit" value="삭제"/></td>';
    			    list += '</form>';
    			    list += '</tr>';
    			});//each

    			$('#memberList').append(list);
    		},//success
    		error:function(){
    			alert("통신에러");
    		}
    	});//ajax
    };//selectAllMember
    </script>
</head>
<body>
	<main class="list_main">
		<section class="ptlist_sec1">
			<h3 class="h3">사용자 수정/삭제</h3>
			<table id="inventory">
            <colgroup>
              <col width="100px"><col width="100px"><col width="100px"><col width="100px"><col width="150px"><col width="150px"><col width="100px">
               <col width="100px"><col width="100px">
            </colgroup>
            <thead >
               <tr>
                  <th>USER</th>
                  <th>PASSWORD</th>
                  <th>ALLOW_IP</th>
                  <th>ACCESS_IP</th>
                  <th>LOCK_DTM</th>
                  <th>LAST_LOGIN_DTM</th>
                  <th>FAIL_COUT</th>
                  <th>수정</th>
                  <th>삭제</th>
               </tr>
            </thead>
            <tbody id="memberList">
               <!-- ajax -->
            </tbody>
         </table>
		</section>
	</main>
</body>
</html>