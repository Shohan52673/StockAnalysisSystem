<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>註冊成功</title>
    <%@ include file="./url.jspf"%>
    <style type="text/css">
    
   body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
}

.success-container {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center;
    transform: scale(2); /* 將背景放大 1.5 倍 */
    width: 25%;
}

.success-icon {
    font-size: 60px; /* 調整 icon 大小 */
    color: #4CAF50; /* 使用淡綠色 */
    background-color: white;
}

a {
    font-size: 8px;
}
    
    </style>
    
</head>
<body>
    <div class="success-container">
        <div class="success-icon"><i class="bi bi-check-circle-fill"></i></div>
        <h5>註冊成功</h5>
        <p><a href="../index.jsp">返回登入頁面</a></p>
    </div>
</body>
</html>