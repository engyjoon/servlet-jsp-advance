<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet">
<title>List</title>
</head>
<body>
	<jsp:include page="/include/menuTop.jsp"></jsp:include>
	<jsp:include page="/include/menuLeft.jsp"></jsp:include>
	
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">Free Board</h1>
		<div class="btn-toolbar mb-2 mb-md-0"></div>
	</div>
	
	<div class="container-fluid">
		<div class="row mb-3">
			<div class="col">
				<button id="btnWrite" class="btn btn-primary float-right" type="button">글쓰기</button>
			</div>
		</div>
		<div class="row">
			<div class="col">
			<table class="table">
				<thead>
					<tr>
						<th scope="col" class="text-center">번호</th>
						<th scope="col" class="text-center">제목</th>
						<th scope="col" class="text-center">작성자</th>
						<th scope="col" class="text-center">작성일시</th>
						<th scope="col" class="text-center">조회수</th>
					</tr>
				</thead>
				<tbody>

				</tbody>
			</table>
			</div>
		</div>
		<div class="row">
			<div class="col">

			</div>
		</div>
	</div>
	
	</main>
	
	<script src="${contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
	<script src="${contextPath}/resources/feather/feather.min.js"></script>
	<script>
		$(document).ready(function() {
			feather.replace();
			$(".sidebar-sticky > .nav > li:eq(1)").children().addClass("active");
			$("#btnWrite").click(function() {
				
			});
			$("#pageItemActive").addClass("active");
		});
	</script>
</body>
</html>