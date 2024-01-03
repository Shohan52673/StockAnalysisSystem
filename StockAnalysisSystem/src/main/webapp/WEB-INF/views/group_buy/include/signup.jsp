<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<%
String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>註冊頁面</title>
<link rel="shortcut icon" type="image/x-icon" href="./images/icon.png">
<%@ include file="./url.jspf"%>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

form {
	background-color: #fff;
	padding: 20px;
	padding-top: 40px;
	padding-bottom: 10px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 500px;
	height: auto;
}

.btn-container {
	display: flex;
	justify-content: center;
	gap: 10px;
	margin-top: 15px; /* 調整按鈕和表單的距離 */
}
.needs-validation{
	border-top: 10px solid #df5334;
}

</style>
</head>
<body>

	<div class="d-flex justify-content-center align-items-center vh-100">
		<sp:form modelAttribute="signupuser" class="needs-validation"  method="post"
			action="${pageContext.request.contextPath}/mvc/group_buy/signup">
			<h4 class="text-center">會員註冊</h4>
			
			<div>
				<label for="username" class="form-label">姓名</label> 
				<sp:input type="text" class="form-control" path="fullname"  value="" />
				<sp:errors path="fullname" style="color: red; font-size:8px"/>
			</div>
			<div>
				<label for="email" class="form-label">電子信箱</label> 
				<sp:input type="email" class="form-control" path="email" value="" />
				<sp:errors path="email" style="color: red; font-size:8px"/>
				<div style="color: red; font-size:8px">${signupemail}</div>
			</div>
			<div>
				<label for="username" class="form-label">會員帳號</label> 
				<sp:input type="text" class="form-control" path="username"  value="" />
				<sp:errors path="username" style="color: red; font-size:8px"/>
            <div style="color: red; font-size:8px">${signupname}</div>
			</div>
			<div>
				<label for="password" class="form-label">會員密碼</label> 
				<sp:input type="password" class="form-control" path="password" value="" />
				<sp:errors path="password" style="color: red; font-size:8px"/>
			</div>
			<div>
				<label for="confirmPassword" class="form-label">密碼確認</label>
				<input type="password" class="form-control" id="comfirm_password"
					name="comfirm_password" value="" >
				<div style="color: red; font-size:8px">${comfirm_password}</div>
			</div>
			<div class="btn-container">
				<a href="./login" class="btn btn-danger cancel-btn">取消</a>
				<button class="btn btn-primary submit" type="submit">註冊</button>
			</div>
		</sp:form>
	</div>
</body>
</html>