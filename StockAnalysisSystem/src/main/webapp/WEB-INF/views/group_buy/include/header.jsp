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
						href="./WEB-INF/views/group_buy/frontend/main.jsp">首頁</a></li>
					<li class="nav-item"><a class="nav-link" href="#">自選股</a></li>
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
				<form class="d-flex me-3">
					<input class="form-control me-2" type="search" placeholder="搜尋代碼"
						aria-label="Search">
					<button class="btn btn-outline-success text-nowrap" type="submit">搜尋</button>
				</form>
				<div>
				<%@ include file="./logout.jspf" %>
				</div>
	</nav>
</body>
</html>