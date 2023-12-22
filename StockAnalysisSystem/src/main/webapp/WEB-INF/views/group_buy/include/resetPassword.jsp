<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String error = (String) request.getAttribute("error");
%>
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
    </style>
</head>
<body>

    <div class="form-container">
        <form class="needs-validation" novalidate method="post" onsubmit="return submitForm()" action="../index.jsp">
            <h4 class="text-center">忘記密碼</h4>

            <div class="label-container">
                <label for="email" class="form-label">會員帳號</label>
                <input type="email" class="form-control" id="email" name="email" value=""
                    placeholder="" required>
                    <div class="invalid-feedback">請輸入有效會員帳號</div>
                <label for="email" class="form-label">電子信箱</label>
                <input type="email" class="form-control" id="email" name="email" value=""
                    placeholder="" required>
                    <div class="invalid-feedback">請輸入有效電子信箱</div>
            </div>

            <div class="btn-container">
                <a href="../index.jsp" class="btn btn-danger cancel-btn">取消</a>
                <button type="button" class="btn btn-success submit-btn" onclick="submitForm()">送出</button>
            </div>
        </form>
    </div>

    <script>
        function submitForm() {
            var form = document.querySelector('.needs-validation');
            if (form.checkValidity()) {
                var formData = new FormData(form);

                // Using AJAX to submit the form
                var xhr = new XMLHttpRequest();
                xhr.open("POST", form.action, true);
                xhr.onload = function () {
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
