<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/group_buy/include/url.jspf" %>
<style>
body{
    		background-color: black !important;
    	}


thead {
    background-color: #333333;
    position: sticky;
    top: 0;
    z-index: 1;
}

		#header-container {
            padding-left: 10%;
			padding-right: 10%;
        }
        
        #stock-container{
        	padding-left: 20%;
			padding-right: 20%;
        }
        
        
		.mb-4{
			padding-top:10%
		}
   
   		th.text-right {
    text-align: right;
}

td.text-right {
    text-align: right;
}
	

</style>

</head>
<body>
<div class="bg-light">
	<div id="header-container">
        <%@ include file="/WEB-INF/views/group_buy/include/header.jsp" %>
    </div>
</div>
<div id="stock-container">
    <!-- 股票清单示例 -->
    <h2 class="mb-4" style="color: white;">我的庫存</h2>
	<%-- <div>${buyingLists}</div> --%>
    <!-- Stock Table -->
    <table class="table table-striped">
        <thead>
            <tr>
                <th scope="col" style="color: white;">股票名稱</th>
                <th scope="col" style="color: white;" class="text-right">股數</th>
                <th scope="col" style="color: white;" class="text-right">價值</th>
            </tr>
        </thead>
        <tbody>
            <!-- Sample Data -->
            <c:forEach items="${buyingLists}" var="buyingList">
            	<tr>
	                <td style="color: white;">${buyingList.stockName}</td>
	                <td style="color: white;" class="text-right"><fmt:formatNumber value="${buyingList.quantity}" pattern="#,##0" /></td>
	                <td style="color: white;" class="text-right"><fmt:formatNumber value="${buyingList.price}" pattern="#,##0" /></td>
            	</tr>
            </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>