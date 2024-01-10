<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form class="d-flex me-3" action="${pageContext.request.contextPath}/mvc/group_buy/fronted/search" method="get">
   				 <div class="search-box">
   				 <div class="row">
   				 <input class="form-control me-2" type="search" id="searchSymbol" name="searchSymbol" placeholder="搜尋代碼" aria-label="Search" autocomplete="off">
    			 <button class="btn btn-outline-success text-nowrap" type="submit"><i class="bi bi-search"></i></button>
    			 </div>
    			 
    			 <div class="result-box">
    			 
    			 </div>
    			 </div>
				</form>



// 遍历stocks数组，将数据添加到表格中
            $.each(stocks, function(index, stock) {
                // 创建超链接
                var link = $('<a>').attr('href', '${pageContext.request.contextPath}/mvc/group_buy/fronted/stcok').text(stock.stockName);

                // 添加表格行
                $('#stock tbody').append(
                    $('<tr>').append(
                        $('<th scope="row">').append(link),
                        $('<td>').text(stock.price),
                        $('<td>').text(stock.openingPrice),
                        $('<td>').text(stock.highPrice),
                        $('<td>').text(stock.lowPrice),
                        $('<td>').text(stock.averagePrice),
                        $('<td>').text(stock.transactionAmountBillion),
                        $('<td>').text(stock.previousClosingPrice),
                        $('<td>').text(stock.changePercentage),
                        $('<td>').text(stock.changeP),
                        $('<td>').text(stock.totalVolume),
                        $('<td>').text(stock.previousVolume),
                        $('<td>').text(stock.amplitude)
                    )
                );
            });

</body>
</html>