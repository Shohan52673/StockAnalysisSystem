<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>忘記密碼</title>
<%@ include file="./url.jspf"%>

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

.form-container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
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
	text-align: center;
	margin-top: 20px;
}

.needs-validation {
	border-top: 10px solid #df5334;
}


</style>
</head>
<body>


	<div class="form-container">
		<sp:form class="needs-validation" method="post"
				 modelAttribute="resetPassword"
				 onsubmit="return submitForm()" 
				 action="${pageContext.request.contextPath}/mvc/group_buy/resetPassword">
			<h4 class="text-center">忘記密碼</h4>

			<div>
				<label for="email" class="form-label">會員帳號</label> 
				<sp:input type="text" class="form-control" path="username" value=""  />
				<sp:errors path="username" style="color: red; font-size:8px"/>
			</div>
			<div>
				<label for="email" class="form-label">電子信箱</label> 
				<sp:input type="email" class="form-control" path="email" value="" />
				<sp:errors path="email" style="color: red; font-size:8px"/> 
			</div>

			<div class="btn-container">
				<a href="./login" class="btn btn-danger cancel-btn">取消</a>
				<button type="submit" class="btn btn-success submit-btn"
					onclick="submitForm()">送出</button>
			</div>
		</sp:form>
	</div>

	<script>
		function submitForm() {
			var form = document.querySelector('.needs-validation');
			if (form.checkValidity()) {
				var formData = new FormData(form);

				// Using AJAX to submit the form
				var xhr = new XMLHttpRequest();
				xhr.open("POST", form.action, true);
				xhr.onload = function() {
					if (xhr.status === 200) {
						alert("email已發送至信箱！");
					}
				};
				xhr.send(formData);
			} else {
				form.classList.add('was-validated');
			}
		}
	</script>

</body>
</html>
