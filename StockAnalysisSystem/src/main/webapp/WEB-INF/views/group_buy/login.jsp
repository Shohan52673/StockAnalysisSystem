<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>會員登入</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<style>
        *{
  font-family:微軟正黑體;  
}


#username, #password, h3, #fullname, #email, #comfirm_password {
  width: 250px;
  height: 28px;
  margin: 10px;
}

#code{
  width: 70px;
  height: 28px;
  margin: 10px;
}

a{
  margin: 20px;
  color: #a3a2a3;
}

a:hover{
  color: black;
}

  
#container1{
  /*margin: 50px;*/
  padding: 10px;
  width: 330px;
  height: 400px;
  background-color: white;
  border-radius: 5px;
  border-top: 10px solid #df5334;
  box-shadow: 0 0px 70px rgba(0, 0, 0, 0.1);
  
  /*定位對齊*/
  position:relative;   
  margin: auto;
  top: 100px;
  /*text-align:center; */ 
}

.system_name{
  /*定位對齊*/
  position:relative;   
  margin: auto;
  top: 100px;
  text-align:center; 
}

.submit{
  color: white;  
  background: #df5334;
  width: 265px;
  height: 35px;
  margin: 10px;
  padding: 5px;
  border-radius: 5px;
  border: 0px;
}

.submit:hover{
  background: #db6937;
}


input{
  padding: 5px;
  border: none; 
  border:solid 1px #ccc;
  border-radius: 5px;
}
.align-horizontal {
    display: flex;
    justify-content: space-between;
}
.captcha-row {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    margin-right: 10px;
}
.captcha-row > *{
	margin-right: 20px;
}

    </style>
</head>
<body>
    <div class="system_name">
      <!-- <h2>○○系統</h2> -->
    </div>
    
    <div class="login_page">
      <div id="container1">

        <div class="login">  
          
          <h3>會員登入</h3>

          <sp:form  modelAttribute="loginuser" method="post" action="${pageContext.request.contextPath}/mvc/group_buy/login">
            <sp:input type="text" path="username" placeholder="帳號" ></sp:input>
            <div style="color: red">${wrongUsername}</div> <sp:errors path="username" style="color: red; font-size:8px"/>
            <sp:input type="password" path="password" placeholder="密碼" ></sp:input>
            <div style="color: red">${wrongPassword}</div> <sp:errors path="password" style="color: red; font-size:8px"/>
            
            
            <div class="captcha-row">
    			<sp:input type="text" path="code" placeholder="驗證碼"></sp:input> 
  		    	<img id="captcha-image" src="./getcode" alt="驗證碼" valign="middle" class="">
  		        <button type="button" class="pure-button pure-button-secondary" id="refresh-btn" onclick="refreshCaptcha()">
       	   	 	<i class="bi bi-arrow-repeat"></i>
    			</button>
			</div>
             <div style="color: red">${wrongCAPTCHA}</div>
    		 <sp:errors path="code" style="color: red; font-size:8px"/>
            
            
            <div class="tab"></div>
            <button type="submit" class="submit" ">登入</button>
          </sp:form>  
          
          
		  <div class="align-horizontal">
          <h5><a href="${pageContext.request.contextPath}/mvc/group_buy/signup">註冊帳號</a></h5>
          <h5><a href="${pageContext.request.contextPath}/mvc/group_buy/resetPassword">忘記密碼</a></h5>
          </div>
          
        </div><!-- login end-->
      </div><!-- container1 end-->
    </div><!-- login_page end-->
 
  <script>
  
  function refreshCaptcha() {
      // 通過 AJAX 請求向後端獲取新的驗證碼圖片
      var xhr = new XMLHttpRequest();
      xhr.open('GET', './refreshCode', true);
      xhr.onreadystatechange = function () {
          if (xhr.readyState == 4 && xhr.status == 200) {
              // 更新圖片源以顯示新的驗證碼圖片
              // 附加一個時間戳到 URL 上確保瀏覽器重新加載新圖片而不是使用緩存的舊圖片
              document.getElementById('captcha-image').src = './getcode?' + new Date().getTime();
          }
      };
      xhr.send();
  }
   
   
 </script>
</html>


