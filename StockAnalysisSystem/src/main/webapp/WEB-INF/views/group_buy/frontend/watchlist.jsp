<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../include/url.jspf"%>
<style>
#header-container {
	padding-left: 10%;
	padding-right: 10%;
}

#watchList-container {
	padding-left: 10%;
	padding-right: 10%;
}

.mb-4 {
	padding-top: 10%
}

.itemName {
	word-wrap: break-word; /* 換行 */
	word-break: break-all; /* 當單字超出容器時斷開 */
	max-width: 5.5ch; /* 設定最大寬度為 4 中文字 */
	text-align: center;
}

.itemNa {
	text-align: right;
}

.item {
	text-align: right;
}

/* 調整按鈕樣式 */
custom-btn {
	width: 150px; /* 設定按鈕寬度，根據需要調整 */
	background-color: #ffffff;
	border: 2px solid #df5334; /* 添加邊框，根據需要調整顏色和寬度 */
	color: #000000; /* 設定文字顏色，根據需要調整 */
}

/* 鼠標懸停時的效果 */
custom-btn:hover {
	background-color: #df5334 !important; /* 鼠標懸停時的背景顏色，根據需要調整 */
	color: #ffffff; /* 鼠標懸停時的文字顏色，根據需要調整 */
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
						<td>${stock.stockName}</td>
						<td class="item">${stock.price}</td>
						<td class="item">${stock.openingPrice}</td>
						<td class="item">${stock.highPrice}</td>
						<td class="item">${stock.lowPrice}</td>
						<td class="item">${stock.averagePrice}</td>
						<td class="item">${stock.transactionAmountBillion}</td>
						<td class="item">${stock.previousClosingPrice}</td>
						<td class="item">${stock.changePercentage}</td>
						<td class="item">${stock.changeP}</td>
						<td class="item">${stock.totalVolume}</td>
						<td class="item">${stock.previousVolume}</td>
						<td class="item">${stock.amplitude}</td>
						<td class="item"><a
							href="javascript:void(0);" onclick="removeOneWatchlist('${stock.stockName}')">
								<button class="custom-btn btn-sm">Ｘ</button>
						</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript">
		function removeOneWatchlist(stockName) {
			window.location.href='${pageContext.request.contextPath}/mvc/group_buy/fronted/removeOneWatchlist?stockName=' + stockName;
		}
	
	
	</script>

</body>
</html>
