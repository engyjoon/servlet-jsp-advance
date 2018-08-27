<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/signin.css">
<title>로그인</title>
</head>
<body class="text-center">

	<form class="form-signin" method="post" action="login.do">
		<h1 class="h3 mb-3 font-weight-normal">로그인이 필요합니다.</h1>
		
		<label for="id" class="sr-only">아이디</label>
		<input type="text" name="member_id" id="id" class="form-control" placeholder="아이디" required autofocus>
		
		<label for="passwd" class="sr-only">비밀번호</label>
		<input type="password" name="member_pw" id="passwd" class="form-control" placeholder="비밀번호" required>
		
		<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
	</form>

	<script src="resources/jquery/jquery-3.3.1.min.js"></script>
	<script	src="resources/proper/popper.min.js"></script>
	<script	src="resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>