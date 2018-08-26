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
			
			loadCharts();
			
			/*
			refreshCharts = setInterval(function() {
				refreshCharts();
			}, 5000);
			*/

		});
		
		var loadCharts = function() {
			$.ajax({
				type : 'post',
				url : '${contextPath}/zabbix/listAlarm.do',
				data : { host: "Zabbix server"},
				dataType : 'json'
			}).done(function(data, status) {
				cfgBar.data.datasets[0].data = data;
				var chartAlarm = new Chart(ctxAlarm, cfgBar);
			}).fail(function(jqXHR, status) {
				console.log("Fail");
			});
			
			$.ajax({
				type : 'post',
				url : '${contextPath}/zabbix/listHistory.do',
				data : {"host":"Zabbix server", "key":"system.cpu.util[,idle]", "table":"history"},
				dataType : 'json'
			}).done(function(data, status) {
				cfgLinePerc.data.datasets[0].data = data;
				var chartCpu = new Chart(ctxCpu, cfgLinePerc);
			}).fail(function(jqXHR, status) {
				console.log("Fail");
				console.log(status);
			});
			
			$.ajax({
				type : 'post',
				url : '${contextPath}/zabbix/listHistory.do',
				data : {"host":"Zabbix server", "key":"vm.memory.size[available]", "table":"history_uint"},
				dataType : 'json'
			}).done(function(data, status) {
				cfgLine.data.datasets[0].data = data;
				cfgLine.options.scales.yAxes[0].ticks.callback = function(value, index, values) {
		            	return Math.round(value/1024/1024) + "MB";
					}
				var chartMemory = new Chart(ctxMemory, cfgLine);
			}).fail(function(jqXHR, status) {
				console.log("Fail");
				console.log(status);
			});
			
			$.ajax({
				type : 'post',
				url : '${contextPath}/zabbix/listHistory.do',
				data : {"host":"Zabbix server", "key":"vfs.fs.size[/,pfree]", "table":"history"},
				dataType : 'json'
			}).done(function(data, status) {
				cfgLinePerc.data.datasets[0].data = data;
				var chartDisk = new Chart(ctxDisk, cfgLinePerc);
			}).fail(function(jqXHR, status) {
				console.log("Fail");
				console.log(status);
			});
			
		}
		
		var ctxAlarm = document.getElementById("chartAlarm").getContext('2d');
		var ctxCpu = document.getElementById("chartCpu").getContext('2d');
		var ctxMemory = document.getElementById("chartMemory").getContext('2d');
		var ctxDisk = document.getElementById("chartDisk").getContext('2d');
		
		cfgBar = {
	        type: 'bar',
	        data: {
	            labels: ["치명", "긴급", "위험", "주의", "무해", "미분류"],
	            datasets: [{
	            	data: [],
	            	backgroundColor: 'rgba(255, 32, 13, 0.5)',
	            	borderColor: 'rgba(255, 32, 13, 0.7)',
	            	borderWidth: 1
	            }]
	        },
        	options: {
	            scales: {
	            	xAxes: [{
	            		ticks: {
	            			fontSize: 13
	            		},
	            		barPercentage: 0.5,
	            		gridLines: {
		            		display: false
		            	}
	            	}],
	            	yAxes: [{
	                	ticks: {
		                	type: 'linear',
		                	beginAtZero: true,
		                	stepSize: 10,
		                	max: 50
	                	}
	            	}]
	            },
	            legend: {
	            	display: false
	            }
        	}
		}
		
		cfgLinePerc = {
	        type: 'line',
	        data: {
	            datasets: [{
	            	data: [],
	            	backgroundColor: 'rgba(15, 71, 254, 0.5)',
	            	borderColor: 'rgba(15, 71, 254, 0.7)',
	            	borderWidth: 1,
	            	pointRadius: 2
	            }]
	        },
        	options: {
	            scales: {
	            	xAxes: [{
	            		type: 'time',
	            		display: true,
	            		ticks: {
	            			fontSize: 13
	            		},
	            		gridLines: {
		            		display: true,
		            		drawOnChartArea: false
		            	},
		            	time: {
		                    displayFormats: {
		                    	minute: 'HH:mm' 
		                    }
		                }
	            	}],
	            	yAxes: [{
	                	ticks: {
		                	type: 'linear',
		                	beginAtZero: true,
		                	max: 100,
		                	callback: function(value, index, values) {
		                        return value + '%';
		                    }
	                	}
	            	}]
	            },
	            legend: {
	            	display: false
	            }
        	}
		}
		
		cfgLine = {
		        type: 'line',
		        data: {
		            datasets: [{
		            	data: [],
		            	backgroundColor: 'rgba(15, 71, 254, 0.5)',
		            	borderColor: 'rgba(15, 71, 254, 0.7)',
		            	borderWidth: 1,
		            	pointRadius: 2
		            }]
		        },
	        	options: {
		            scales: {
		            	xAxes: [{
		            		type: 'time',
		            		display: true,
		            		ticks: {
		            			fontSize: 13
		            		},
		            		gridLines: {
			            		display: true,
			            		drawOnChartArea: false
			            	},
			            	time: {
			                    displayFormats: {
			                    	minute: 'HH:mm' 
			                    }
			                }
		            	}],
		            	yAxes: [{
		                	ticks: {
			                	type: 'linear',
			                	beginAtZero: true
		                	}
		            	}]
		            },
		            legend: {
		            	display: false
		            }
	        	}
			}
    </script>
	
</body>
</html>