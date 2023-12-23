<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
        /* 添加樣式 */
        body {
            display: flex;
            flex-direction: column;
            height: 100vh;
            margin: 0;
        }

        #header-container {
            padding-left: 10%;
			padding-right: 10%;
        }

         #stock-container {
    padding: 40px;
    padding-left:10%;
    padding-right: 10%; 
	} 
    </style>
</head>

<body>
<div class="bg-light">
	<div id="header-container">
        <%@ include file="/WEB-INF/views/group_buy/include/header.jsp" %>
    </div>
</div>
    <!-- 將股票價格包裝在一個 div 中 -->
    <div id="stock-container">
        <%@ include file="/WEB-INF/views/group_buy/frontend/main.jspf" %>
    </div>



</body>
</html>