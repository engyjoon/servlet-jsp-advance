<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet">
<title>Read</title>
</head>
<body>
	<jsp:include page="/include/menuTop.jsp"></jsp:include>
	<jsp:include page="/include/menuLeft.jsp"></jsp:include>
	
	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
		<div
			class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<h1 class="h2">Free Board</h1>
			<div class="btn-toolbar mb-2 mb-md-0"></div>
		</div>
		
		<form id="frmMain" method="post">
			<input type="hidden" name="bno" value="">
			<input type="hidden" name="page" value="">
		</form>
		
		<div class="container-fluid">
			
			<div class="form-group row">
				<label for="boardSubject" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="boardSubject" name="boardSubject" value="" readonly>
				</div>
			</div>
			<div class="form-group row">
				<label for="boardContent" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<textarea class="form-control" id="boardContent" rows="10" name="boardContent" readonly></textarea>
				</div>
			</div>
			<button class="btn btn-primary" type="submit" id="btnModify">수정</button>
			<button class="btn btn-danger" type="button" id="btnDelete">삭제</button>
			<button class="btn btn-secondary" type="button" id="btnCancle">취소</button>

		</div>
	</main>
	
	<script src="${contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
	<script src="${contextPath}/resources/feather/feather.min.js"></script>
	<script>
		$(document).ready(function() {
			feather.replace();
			$(".sidebar-sticky > .nav > li:eq(1)").children().addClass("active");
			
			var formObj = $("#frmMain");
			
			$("#btnModify").click(function() {
				
			});
			
			$("#btnDelete").click(function() {
				
			});
			
			$("#btnCancle").click(function() {
				
			});
		});
	</script>
</body>
</html>