<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="../include/url.jspf"%>
<style>
.card{
	z-index: -1;
}

</style>
<title>Stock Interface</title>
</head>
<body>
<div class="container mt-5">
    <div class="row pl-10 pr-10">
        <!-- 照片1 -->
        <div class="col-md-5 mb-4">
            <div class=""> <!--  <div class="card"> -->
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
</body>
</html>
