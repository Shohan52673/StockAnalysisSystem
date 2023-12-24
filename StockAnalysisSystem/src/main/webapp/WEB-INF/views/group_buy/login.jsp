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


#username, #password, h3, #fullname, #email, #comfirm_password,#username2, #password2{
  width: 250px;
  height: 28px;
  margin: 10px;
}

#code{
  width: 70px;
  height: 28px;
  margin: 10px;
}

h5{
  margin: 20px;
  color: #a3a2a3;
}

h5:hover{
  color: black;
}

#container2{
  /*margin: 50px;*/
  padding: 10px;
  width: 330px;
  height: 450px;
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
  
#container1{
  /*margin: 50px;*/
  padding: 10px;
  width: 330px;
  height: 350px;
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

#container2{
  visibility: hidden;   /*剛開始消失*/
}


/* #copyright{
  text-align: center;
  color: #a3a2a3;
  margin: -200px 0px 0px 0px;
  font-size: 14px;
} */

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
      <h2>○○系統</h2>
    </div>
    
    <div class="login_page">
      <div id="container1">

        <div class="login">  
          
          <h3>會員登入</h3>

          <form method="post" action="./login">
            <input type="text" id="username" name="username" placeholder="帳號" value="user123" required>
            <div style="color: red">${wrongUsername}</div>
            <div class="tab"></div>
            <input type="password" id="password" name="password" placeholder="密碼" value="pass123" required>
            <div style="color: red">${wrongPassword}</div>
            <div class="tab"></div>
            
            
            <div class="captcha-row">
    			<input type="text" id="code" name="code" placeholder="驗證碼">
  		    	<img id="captcha-image" src="./getcode" alt="驗證碼" valign="middle" class="">
  		        <button type="button" class="pure-button pure-button-secondary" id="refresh-btn" onclick="refreshCaptcha()">
       	   	 	<i class="bi bi-arrow-repeat"></i>
    			</button>
			</div>
             <div style="color: red">${wrongCAPTCHA}</div>
            
            
            <div class="tab"></div>
            <input type="submit" value="登入" class="submit" ">
          
		  <div class="align-horizontal">
          <h5 onclick="show_hide()">註冊帳號</h5>
          <h5 onclick="show_hide2()">忘記密碼</h5>
          </div>
          
          </form>  
        </div><!-- login end-->
      </div><!-- container1 end-->
    </div><!-- login_page end-->
    
    <div class="signup_page">
      <div id="container2">

        <div class="signup">  
          
          <h3>會員註冊</h3>

          <sp:form  method="post" action="./signup" modelAttribute="user">
            <input type="text" id="fullname" name="fullname" placeholder="使用者名稱" required/>
            <div class="tab"></div>
            <input type="text" id="email" name="email" placeholder="電子信箱" required/>
            <div class="tab"></div>
            <input type="text" id="username2" name="username" placeholder="帳號" required/>
            <div class="tab"></div>
            <input type="password" id="password2" name="password" placeholder="密碼" required/>
            <div class="tab"></div>
            <input type="password" id="comfirm_password" name="comfirm_password" placeholder="確認密碼" required/>
            <div class="tab"></div>            
            <button type="submit" class="submit">註冊</button>
          </sp:form>  
          
		  <div class="align-horizontal">
          <h5 onclick="show_hide()">登入帳號</h5>
          <h5 onclick="show_hide2()">忘記密碼</h5>
          </div>
          
        </div><!-- signup end-->
      </div><!-- container2 end-->
    </div><!-- signup_page end--> 
    
    <div class="resetPasswoed_page">
      <div id="container3">

        <div class="resetPasswoed">  
          
          <h3>忘記密碼</h3>

          <form action="">
            <input type="text" id="username" name="username" placeholder="帳號" required>
            <div class="tab"></div>
            <input type="text" id="email" name="email" placeholder="電子信箱" required>
            <div class="tab"></div>            
            <input type="submit" value="送出" class="submit">
          </form>  
          
		  <div class="align-horizontal">
          <h5 onclick="show_hide2()">登入帳號</h5>
          <h5 onclick="show_hide2()">註冊帳號</h5>
          </div>
          
        </div><!-- resetPasswoed end-->
      </div><!-- container3 end-->
    </div><!-- resetPasswoed_page end--> 
    
    	
<!-- 
    <div id="copyright">
      <h4></h4>因為js，會跑版 
    </div>  -->   
  </body>
  <script type="text/javascript">
  function show_hide() {
    var login = document.getElementById("container1");
    var signup = document.getElementById("container2");
   /*   var copyright = document.getElementById("copyright"); */
  
    if (login.style.display === "none") {
        login.style.display = "block";  //lonin出現
        document.getElementById("username").value="";
        document.getElementById("password").value="";
        document.getElementById("code").value="";
        signup.style.display = "none";  //signup消失
        /* copyright.style.margin = "200px 0px 0px 0px"; */
    } else {
        login.style.display = "none";   //login消失
        signup.style.display = "block"; //signup出現
        signup.style.visibility="visible";
        /* copyright.style.margin = "200px 0px 0px 0px"; */
     
        document.getElementById("fullname").value="";
        document.getElementById("email").value="";
        document.getElementById("username2").value="";
        document.getElementById("password2").value="";
        document.getElementById("comfirm_password").value="";
    }
}
  
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


