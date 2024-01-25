<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Stock Analysis System</title>
<%@ include file="./url.jspf"%>
<style>
        .navbar-container {
            padding-left: 10%;
            padding-right: 10%;
        }
        .search-box{
        	position: relative;
        	background: #fff;
        }
        .input-group{
        	display: flex;
        	align-items: center;
        	padding: 10px 20xp; 
        }
        
        input{
        	flex: 1;
        	height: 37px;
        	/* background: transparent; */
        	outline: 0;
        }
        

        .result-box {
        	position: absolute;
            top: 100%; /* 與輸入框的底部對齊 */
            left: 0;
            z-index: 1000; /* 確保 .result-box 在其他內容上方 */
            border: 1px solid #ccc;
            border-top: none; /* 移除上方邊框 */
            background-color: #fff;
            width: 80%; /* 寬度與輸入框相同 */
            max-height: 300px;
            overflow: scroll;
        }

        .result-box ul {
            border-top: 1px solid #999;
            padding: 15px 10px;
            margin: 0;
        }

        .result-box ul li {
            list-style: none;
            border-radius: 3px;
            padding: 15px 10px;
            cursor: pointer;
        }
        .result-box ul li:hover{
        	background: #e9f3ff;
        }

        

       
    </style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid navbar-container">
			<a class="navbar-brand" href="#">股票分析系統</a>
			<button class="navbar-toggler d-{xl, xxl}-none" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="main.jsp">首頁</a></li>
					<li class="nav-item"><a class="nav-link" href="./portfolio">我的庫存</a></li>
					<li class="nav-item"><a class="nav-link" href="#">台股</a></li>
					<li class="nav-item"><a class="nav-link" href="#">國際</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							個股概況 </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#">Action</a></li>
							<li><a class="dropdown-item" href="#">Another action</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Something else
									here</a></li>
						</ul></li>
				</ul>
				<form class="d-flex me-3" action="${pageContext.request.contextPath}/mvc/group_buy/fronted/search" method="get">
   				 <div class="search-box">
   				 <div class="input-group">
   				 <input class="form-control me-2" type="search" id="searchSymbol" name="searchSymbol" placeholder="搜尋代碼" aria-label="Search" autocomplete="off">
    			 <button class="btn btn-outline-success text-nowrap" type="submit"><i class="bi bi-search"></i></button>
    			 </div>
    			 
    			 <div class="result-box">
    			 
    			 </div>
    			 </div>
				</form>
				<div>
				<%@ include file="./logout.jspf" %>
				</div>
	</nav>
	
	<script>
		let availableKeywords = [
			'2330 台積電',
			'0050 元大台灣50',
			'0056 富邦台50',
			'2303 聯電',
			'1216 統一',
			'2886 兆豐金'
		];
		
		const resultsBox = document.querySelector(".result-box");
		const inputBox = document.getElementById("searchSymbol");
		
		inputBox.onkeyup = function() {
			let result = [];
			let input = inputBox.value;
			if(input.length){
				result = availableKeywords.filter((keyword)=>{
					return keyword.toLowerCase().includes(input.toLowerCase());
				});
				console.log(result);
			}
			display(result);
			if(!result.length){
				resultsBox.innerHTML = '';
			}
		}
		
		function display(result){
			const content = result.map((list)=>{
				return "<li onclick=seleckInput(this)>" + list + "</li>";
			});
			
			resultsBox.innerHTML = "<ul>" + content.join('') + "</ul>";
		}
		
		function seleckInput(list){
			inputBox.value = list.innerHTML;
			resultsBox.innerHTML = '';
		}
	
	</script>
</body>
</html>