<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="${contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" >
<link href="${contextPath}/resources/css/dashboard.css" rel="stylesheet">

<title>Dashboard</title>
</head>
<body>

	<jsp:include page="/include/menuTop.jsp"></jsp:include>
	<jsp:include page="/include/menuLeft.jsp"></jsp:include>

	<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
	<div
		class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
		<h1 class="h2">Dashboard</h1>
		<div class="btn-toolbar mb-2 mb-md-0"></div>
	</div>
	<div class="container-fluid">
		<div class="row mb-3">
			<div class="col-sm">
				<div class="card">
					<h5 class="card-header">
						Alarm Status
					</h5>
					<div class="card-body text-center">
						<canvas id="chartAlarm" height="45"></canvas>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm">
				<div class="card">
					<h5 class="card-header">
						CPU Usage
					</h5>
					<div class="card-body text-center">
						<canvas id="chartCpu" height="120"></canvas>
					</div>
				</div>
			</div>
			<div class="col-sm">
				<div class="card">
					<h5 class="card-header">
						Memory Usage
					</h5>
					<div class="card-body text-center">
						<canvas id="chartMemory" height="120"></canvas>
					</div>
				</div>
			</div>
			<div class="col-sm">
				<div class="card">
					<h5 class="card-header">
						Disk Usage
					</h5>
					<div class="card-body text-center">
						<canvas id="chartDisk" height="120"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>

	<script src="${contextPath}/resources/jquery/jquery-3.3.1.min.js"></script>
	<script> window.jQuery || document.write('<script src="resources/jquery/jquery-slim.min.js"><\/script>') </script>
	<script	src="${contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script	src="${contextPath}/resources/proper/popper.min.js"></script>
	<script src="${contextPath}/resources/feather/feather.min.js"></script>
	<script src="${contextPath}/resources/moment/moment.min.js"></script>
	<script src="${contextPath}/resources/chart/Chart.min.js"></script>
	
    <script>
		$(document).ready(function() {
			feather.replace();
			$(".sidebar-sticky > .nav > li:eq(0)").children().addClass("active");

		});
		

    </script>
	
</body>
</html>