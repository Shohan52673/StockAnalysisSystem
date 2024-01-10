<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../include/url.jspf"%>
<style>
.card {
	z-index: -1;
}

#header-container {
	padding-left: 10%;
	padding-right: 10%;
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
	<div class="container mt-5">
		<div class="row pl-10 pr-10">
			<!-- 照片1 -->
			<div class="col-md-5 mb-4">
				<div class="">
					<!--  <div class="card"> -->
					<!-- <img src="https://via.placeholder.com/300" class="card-img-top" alt="照片1"> -->
					<div class="card-body">
						<!--  <h5 class="card-title">照片1</h5>
                    <p class="card-text">這是照片1的描述。</p> -->
					</div>
				</div>
			</div>

			<!-- 表格 -->
			<div class="col-md-3">
				<table class="table table-striped">
					<thead>
						<!-- 表格標題 -->
						<tr>
							<th scope="col">項目</th>
							<th scope="col">數值</th>
						</tr>
					</thead>
					<tbody>
						<!-- 表格內容 -->
						<tr>
							<th scope="row">成交</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">開盤</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">最高</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">最低</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">均價</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">成交金額(億)</th>
							<td>22</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-3">

				<table class="table table-striped">
					<thead>
						<!-- 表格標題 -->
						<tr>
							<th scope="col">項目</th>
							<th scope="col">數值</th>
						</tr>
					</thead>
					<tbody>
						<!-- 表格內容 -->
						<tr>
							<th scope="row">昨收</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">漲跌幅</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">漲跌</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">總量</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">昨量</th>
							<td>22</td>
						</tr>
						<tr>
							<th scope="row">振幅</th>
							<td>22</td>
						</tr>
					</tbody>
				</table>

			</div>
		</div>
	</div>
	<script>
		
    $(function() {
        // 使用jQuery的ajax函数从服务器获取数据
        $.ajax({
            url: 'http://localhost:8080/StockAnalysisSystem/mvc/stock',
            success: function (data) {
                console.log(data);
            }
        });
    });

    // 创建WebSocket连接
    var socket = new WebSocket('ws://localhost:8080/StockAnalysisSystem/mvc/websocket');

    // 当WebSocket连接打开时触发的事件
    socket.onopen = function() {
        console.log('WebSocket连接已建立');
    };

    // 当WebSocket接收到消息时触发的事件
    socket.onmessage = function(event) {
        // 解析收到的JSON数据
        let message = JSON.parse(event.data);
        if (message.type == 'stocks') {
            let stocks = message.content;
            console.log(stocks);
             
            // 移除表格中已有的行
            $('#stock tbody tr').remove();
            // 隐藏加载状态
            $('#spinner').addClass('d-none');
            
            // 遍历stocks数组，将数据添加到表格中
            $.each(stocks, function(index, stock) {
                $('#stock tbody').append(
                    `<tr>
                        <th scope="row">\${stock.stockName}</th>
                        <td>\${stock.price}</td>
                        <td>\${stock.openingPrice}</td>
                        <td>\${stock.highPrice}</td>
                        <td>\${stock.lowPrice}</td>
                        <td>\${stock.averagePrice}</td>
                        <td>\${stock.transactionAmountBillion}</td>
                        <td>\${stock.previousClosingPrice}</td>
                        <td>\${stock.changePercentage}</td>
                        <td>\${stock.changeP}</td>
                        <td>\${stock.totalVolume}</td>
                        <td>\${stock.previousVolume}</td>
                        <td>\${stock.amplitude}</td>
                    </tr>`
                );
            });
        }
    };

    // 当WebSocket连接关闭时触发的事件
    socket.onclose = function() {
        console.log('WebSocket连接已关闭');
    };


	</script>
</body>
</html>
