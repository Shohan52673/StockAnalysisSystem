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


</style>
</head>
<body>

	<div class="d-flex justify-content-center align-items-center vh-100">
		<sp:form modelAttribute="user" class="needs-validation" novalidate method="post"
			action="./success.jsp">
			<h4 class="text-center">會員註冊</h4>
			<%
			if (error != null) {
				out.print("<p class='text-center text-danger fw-bold'>" + error + "</p>");
			}
			%>
			<div>
				<label for="username" class="form-label">會員帳號</label> 
				<input type="text" class="form-control" id="username" name="username" value="" required>
				<div class="invalid-feedback">請輸入有效會員名稱</div>
			</div>
			<div>
				<label for="email" class="form-label">電子信箱</label> <input
					type="email" class="form-control" id="email" name="email" value=""
					required>
				<div class="invalid-feedback">請輸入有效的電子信箱</div>
			</div>
			<div>
				<label for="password" class="form-label">會員密碼</label> <input
					type="password" class="form-control" id="password" name="password"
					value="" required>
				<div class="invalid-feedback">請輸入密碼</div>
			</div>
			<div>
				<label for="confirmPassword" class="form-label">密碼確認</label> <input
					type="password" class="form-control" id="confirmPassword"
					name="confirmPassword" value="" required>
				<div class="invalid-feedback">請確認密碼</div>
			</div>
			<div class="btn-container">
				<a href="./login" class="btn btn-danger cancel-btn">取消</a>
				<!-- <a href="./login" class="btn btn-primary" type="">註冊</a> -->
				<button class="btn btn-primary" type="submit">註冊</button>
			</div>
		</sp:form>
	</div>
 
	<script type="text/javascript">
		//Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>
</body>
</html>