<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html :class="{ 'theme-dark': dark }" x-data="data()" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Tables - Windmill Dashboard</title>
    <link
      href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="<c:url value='resources/css/tailwind.output.css'/>" >
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
    <script src="http://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="resources/js/init-alpine.js"></script>
    <script>
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
    			console.log("통신성공");

    			var memberList = res.memberList;
    			
    			var list = '';
    			$.each(memberList,function(i,item){

    				list += '<tr class="text-gray-700 dark:text-gray-400" )>'
    				list += '<td class="px-4 py-3 text-sm">' + item.userId + '</td>';
    				list += '<td class="px-4 py-3 text-sm">' + item.userPassword + '</td>';
    				list += '<td class="px-4 py-3 text-sm">' + item.allow_ip + '</td>';
    				list += '<td class="px-4 py-3 text-sm">' + item.access_ip + '</td>';
    				list += '<td class="px-4 py-3 text-sm">' + item.lock_dtm + '</td>';
    				list += '<td class="px-4 py-3 text-sm">' + item.last_login_dtm + '</td>';
    				list += '<td class="px-4 py-3 text-sm">' + item.fail_count + '</td>';
    				list += '</tr>';
    				
    			})//each

    			$('#memberList').append(list);
    		},//success
    		error:function(){
    			alert("통신에러");
    		}
    	});//ajax

    };
    </script>
  </head>
  <body>
<!-- 전체 div -->
<div class="flex h-screen bg-gray-50 dark:bg-gray-900"
	:class="{ 'overflow-hidden': isSideMenuOpen}">
	<!-- 사이드 div -->
	<aside
		class="z-20 flex-shrink-0 hidden w-64 overflow-y-auto bg-white dark:bg-gray-800 md:block">
		<div class="py-4 text-gray-500 dark:text-gray-400">
			<a class="ml-6 text-lg font-bold text-gray-800 dark:text-gray-200"
				href="#">OJT</a>
			<!-- 사이드바 -->
			<ul class="mt-6">
				<li class="relative px-6 py-3"><a
					class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
					href="list"> <svg class="w-5 h-5" aria-hidden="true"
							fill="none" stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
	                  <path
								d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
	                </svg> <span class="ml-4">사용자 목록</span>
				</a></li>
				<li class="relative px-6 py-3"><a
					class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
					href="insertMember"> <svg class="w-5 h-5" aria-hidden="true"
							fill="none" stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
	                  <path
								d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6"></path>
	                </svg> <span class="ml-4">사용자 등록</span>
				</a></li>
				<li class="relative px-6 py-3"><a
					class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
					href="updateMember"> <svg class="w-5 h-5" aria-hidden="true"
							fill="none" stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2" viewBox="0 0 24 24" stroke="currentColor">
                  <path
								d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path>
                </svg> <span class="ml-4">사용자 수정/삭제</span>
				</a></li>
				<li class="relative px-6 py-3"><a
					class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
					href="cards.html"> <svg class="w-5 h-5"
							aria-hidden="true" fill="none" stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
							stroke="currentColor">
                  <path
								d="M19 11H5m14 0a2 2 0 012 2v6a2 2 0 01-2 2H5a2 2 0 01-2-2v-6a2 2 0 012-2m14 0V9a2 2 0 00-2-2M5 11V9a2 2 0 012-2m0 0V5a2 2 0 012-2h6a2 2 0 012 2v2M7 7h10"></path>
                </svg> <span class="ml-4">내 정보 수정/삭제<span></a></li>
				<li class="relative px-6 py-3"><a
					class="inline-flex items-center w-full text-sm font-semibold transition-colors duration-150 hover:text-gray-800 dark:hover:text-gray-200"
					href="/sol"> <svg class="w-5 h-5"
							aria-hidden="true" fill="none" stroke-linecap="round"
							stroke-linejoin="round" stroke-width="2" viewBox="0 0 24 24"
							stroke="currentColor">
                  <path
								d="M11 3.055A9.001 9.001 0 1020.945 13H11V3.055z"></path>
                  <path d="M20.488 9H15V3.512A9.025 9.025 0 0120.488 9z"></path>
                </svg> <span class="ml-4">로그아웃</span>
				</a></li>
			</ul>
			
			<div class="px-6 my-6">
          </div>
		</div>
	</aside>
	<!-- 사이드 끝 -->
	<!-- 메인 div -->
	<div class="flex flex-col flex-1 w-full">
		<header class="z-10 py-4 bg-white shadow-md dark:bg-gray-800">
			<div class="container flex items-center justify-between h-full px-6 mx-auto text-purple-600 dark:text-purple-300">
				<button
              class="p-1 mr-5 -ml-1 rounded-md md:hidden focus:outline-none focus:shadow-outline-purple"
              @click="toggleSideMenu"
              aria-label="Menu"
            >
              <svg
                class="w-6 h-6"
                aria-hidden="true"
                fill="currentColor"
                viewBox="0 0 20 20"
              >
                <path
                  fill-rule="evenodd"
                  d="M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
                  clip-rule="evenodd"
                ></path>
              </svg>
            </button>
            <!-- 검색창 -->
	            <div class="flex justify-center flex-1 lg:mr-32">
	              <div
	                class="relative w-full max-w-xl mr-6 focus-within:text-purple-500"
	              >
	                <div class="absolute inset-y-0 flex items-center pl-2">
	                  <svg
	                    class="w-4 h-4"
	                    aria-hidden="true"
	                    fill="currentColor"
	                    viewBox="0 0 20 20"
	                  >
	                    <path
	                      fill-rule="evenodd"
	                      d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z"
	                      clip-rule="evenodd"
	                    ></path>
	                  </svg>
	                </div>
	                <input
	                  class="w-full pl-8 pr-2 text-sm text-gray-700 placeholder-gray-600 bg-gray-100 border-0 rounded-md dark:placeholder-gray-500 dark:focus:shadow-outline-gray dark:focus:placeholder-gray-600 dark:bg-gray-700 dark:text-gray-200 focus:placeholder-gray-500 focus:bg-white focus:border-purple-300 focus:outline-none focus:shadow-outline-purple form-input"
	                  type="text"
	                  placeholder="Search for projects"
	                  aria-label="Search"
	                />
	              </div>
	            </div>
            <!-- 검색창 끝 -->
			</div>
		</header>
		<!-- 상단 끝  -->
		<!-- 메인 시작 -->
		<main class="h-full pb-16 overflow-y-auto">
        	<div class="container grid px-6 mx-auto">
          		<h2 class="my-6 text-2xl font-semibold text-gray-700 dark:text-gray-200">User List</h2>
          	<div class="w-full mb-8 overflow-hidden rounded-lg shadow-xs">
            	<div class="w-full overflow-x-auto">
					<table class="w-full whitespace-no-wrap">
                  		<thead>
                    		<tr
                      		class="text-xs font-semibold tracking-wide text-left text-gray-500 uppercase border-b dark:border-gray-700 bg-gray-50 dark:text-gray-400 dark:bg-gray-800"
                    		>
		                      <th class="px-4 py-3" width="100px">User</th>
		                      <th class="px-4 py-3">Password</th>
		                      <th class="px-4 py-3">allow_ip</th>
		                      <th class="px-4 py-3">access_ip</th>
		                      <th class="px-4 py-3">lock_dtm</th>
		                      <th class="px-4 py-3">last_login_dtm</th>
		                      <th class="px-4 py-3">fail_count</th>    
		                    </tr>
        	          </thead>
            	      <tbody class="bg-white divide-y dark:divide-gray-700 dark:bg-gray-800" id="memberList">
                   
                	   <!-- ajax -->

                  	</tbody>
                </table>
                
                <!-- 하단 페이징 바 -->
              </div>
              <div
                class="grid px-4 py-3 text-xs font-semibold tracking-wide text-gray-500 uppercase border-t dark:border-gray-700 bg-gray-50 sm:grid-cols-9 dark:text-gray-400 dark:bg-gray-800"
              >
              <span class="flex items-center col-span-3">
                  Showing 21-30 of 100
              </span>
              <span class="col-span-2"></span>
               <!-- Pagination -->
              <span class="flex col-span-4 mt-2 sm:mt-auto sm:justify-end">
              	<nav aria-label="Table navigation">
                	<ul class="inline-flex items-center">
                    	<li>
                        	<button
                          	class="px-3 py-1 rounded-md rounded-l-lg focus:outline-none focus:shadow-outline-purple"
                          	aria-label="Previous"
                       		>
                          		<svg
                            	aria-hidden="true"
                           	 	class="w-4 h-4 fill-current"
                           		viewBox="0 0 20 20"
                          		>
                            		<path
		                              d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
		                              clip-rule="evenodd"
		                              fill-rule="evenodd"
                            		></path>
                          		</svg>
                        	</button>
                     	 </li>
	                      <li>
	                        <button
	                          class="px-3 py-1 rounded-md focus:outline-none focus:shadow-outline-purple">
	                          1
	                        </button>
	                      </li>

	                      <li>
	                        <button
	                          class="px-3 py-1 rounded-md rounded-r-lg focus:outline-none focus:shadow-outline-purple"
	                          aria-label="Next"
	                        >
	                          <svg
	                            class="w-4 h-4 fill-current"
	                            aria-hidden="true"
	                            viewBox="0 0 20 20"
	                          >
	                            <path
	                              d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
	                              clip-rule="evenodd"
	                              fill-rule="evenodd"
	                            ></path>
	                          </svg>
	                        </button>
	                      </li>
                   	</ul>
                  </nav>
                </span>
              </div>
            </div>
          		</div>
          	</main>
			
	</div>
</div>
</body>
</html>
