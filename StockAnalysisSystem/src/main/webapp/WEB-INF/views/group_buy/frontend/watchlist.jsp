<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="../include/url.jspf"%>
    <style>
        body {
            background-color: #000000 !important; /* 將整個背景設置為黑色 */
            color: white !important; /* 設置文字顏色為白色 */
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

        #watchList-container {
            padding-left: 10%;
            padding-right: 10%;
        }

        .mb-4 {
            padding-top: 10%;
        }

        .itemName {
            word-wrap: break-word; /* 換行 */
            word-break: break-all; /* 當單字超出容器時斷開 */
            max-width: 5.5ch; /* 設定最大寬度為 4 中文字 */
            text-align: center;
            color: white;
        }

        .itemNa {
            text-align: right;
            color: white;
        }

        .item {
            text-align: right;
        }

    </style>
    <title>Stock Interface</title>
</head>
<body>
    <div class="bg-light">
        <div id="header-container">
            <%@ include file="/WEB-INF/views/group_buy/include/header.jsp"%>
        </div>
    </div>

    <div id="watchList-container">
        <h2 class="mb-4">自選清單</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col" class="itemName">商品</th>
                    <th scope="col" class="itemNa">成交</th>
                    <th scope="col" class="itemNa">開盤</th>
                    <th scope="col" class="itemNa">最高價</th>
                    <th scope="col" class="itemNa">最低價</th>
                    <th scope="col" class="itemNa">均價</th>
                    <th scope="col" class="itemNa">成交金額(億)</th>
                    <th scope="col" class="itemNa">昨收</th>
                    <th scope="col" class="itemNa">漲跌幅</th>
                    <th scope="col" class="itemNa">漲跌</th>
                    <th scope="col" class="itemNa">總量</th>
                    <th scope="col" class="itemNa">昨日交易量</th>
                    <th scope="col" class="itemNa">振幅</th>
                    <th scope="col" class="itemNa"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="stock" items="${watchlist}">
                    <tr>
                        <td style="color : white;">${stock.stockName}</td>
                        <td class="item" style="color : green;">${stock.price}</td>
                        <td class="item" style="color : green;">${stock.openingPrice}</td>
                        <td class="item" style="color : green;">${stock.highPrice}</td>
                        <td class="item" style="color : green;">${stock.lowPrice}</td>
                        <td class="item" style="color : green;">${stock.averagePrice}</td>
                        <td class="item" style="color : green;">${stock.transactionAmountBillion}</td>
                        <td class="item" style="color : green;">${stock.previousClosingPrice}</td>
                        <td class="item" style="color : green;">${stock.changePercentage}</td>
                        <td class="item" style="color : green;">${stock.changeP}</td>
                        <td class="item" style="color : green;">${stock.totalVolume}</td>
                        <td class="item" style="color : green;">${stock.previousVolume}</td>
                        <td class="item" style="color : green;">${stock.amplitude}</td>
                        <td class="item">
                            <a href="javascript:void(0);" onclick="removeOneWatchlist('${stock.stockName}')">
                                <button class="custom-btn btn-sm">Ｘ</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    
    <script type="text/javascript">

        function getFontColor(price, previousClosingPrice) {
            if (parseFloat(price) > parseFloat(previousClosingPrice)) {
                return 'red';
            } else if (parseFloat(price) < parseFloat(previousClosingPrice)) {
                return 'green';
            } else {
                return 'yellow';
            }
        }
	
		 function removeOneWatchlist(stockName) {
			window.location.href='${pageContext.request.contextPath}/mvc/group_buy/fronted/removeOneWatchlist?stockName=' + stockName;
		} 
	</script>

</body>
</html>
