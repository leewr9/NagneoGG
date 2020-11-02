<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NAGNEO.GG - 리그오브레전드 전적 검색</title>
</head>
<style>
* {
	margin: 0 auto;
	font-family: 맑은고딕;
	letter-spacing: -2.5px;
}

html, body {
	width: 100%;
	height: 100%;
}

header {
	width: 100%;
	height: 50px;
	background-color: #131313;
}

#login {
	border: none;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	background-color: #ffffff;
	border-radius: 10px 0 10px 0;
	float: right;
	margin-right: 7px;
	margin-top: 6px;
	width: 80px;
	height: 35px;
	font-size: 15px;
}

header img {
	margin-top: -15px;
	margin-left: -15px;
	width: 150px;
	height: 75px;
}

nav {
	width: 100%;
	height: 38px;
	background-color: #303030;
	padding-top: 12px;
}

nav a {
	text-decoration: none;
	color: white;
	margin-left: 15px;
}

#navSearch {
	float: right;
}

section {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	height: 85%;
}

section img {
	width: 400px;
	height: 200px;
}

footer {
	width: 100%;
	height: 50px;
	background-color: #131313;
	color: #cccccc;
	letter-spacing: -1px;
	font-size: 12px;
}

table tr td {
	width: 250px;
	height: 50px;
}
</style>
<script>
	function regex() {
		var regex_pw = /^[a-z0-9]{8,12}$/;
		var regex_phone = /^010-([0-9]{3,4})-([0-9]{4})$/;
		var chk = 0;
		if (!regex_pw.test(register_form.pw.value)) {
			alert("비밀번호는 영어와 숫자로 이루어진 8~12글자만 가능합니다");
		} else {
			chk++;
		}
		if (!regex_phone.test(register_form.phone.value)) {
			alert("핸드폰번호는 010-0000-0000 형식만 가능합니다");
		} else {
			chk++;
		}
		if (chk == 2) {
			register_form.submit();
		}
	}

	function chk(id) {
		var regex_id = /^[a-z0-9]{6,10}$/;
		if (!regex_id.test(id)) {
			alert("아이디는 영어와 숫자로 이루어진 6~10글자만 가능합니다");
		} else {
			location.href = "chk?id=" + id;
		}
	}
</script>
</style>
<body>
	<header>
		<a href="./"><img src="resources/image/logo.png"></a> <input
			type="button" id="login" value="${logChk }"
			onclick="location.href='./login'">
	</header>
	<nav>
		<a href="./">소환사검색</a><a href="board">챔피언검색</a>
		<form action="search" method="get" id="navSearch">
			<input type="text" name="name" style="width: 210px; height: 22px;"
				placeholder="리그오브레전드 닉네임"> <input type="hidden"
				name="action" value="search"><input type="submit" value="검색"
				style="width: 70px; height: 28px; background-color: #303030; color: white; cursor: pointer; border: none;">
		</form>
	</nav>
	<section>
		<form name="register_form" action="input" method="post">
			<table>
				<tr>
					<td><a href="./"><img src="resources/image/main.png"></a></td>
				</tr>
				<tr>
					<td><input type="text" name="id"
						style="width: 400px; height: 30px; font-size: 15px;"
						placeholder="아이디" onchange="chk(this.value);" value="${id }"></td>
				</tr>
				<c:if test="${msg ne '아이디'}">
					<tr>
						<td>${msg }</td>
					</tr>
				</c:if>
				<tr>
					<td><input type="password" name="pw"
						style="width: 400px; height: 30px; font-size: 15px;"
						placeholder="비밀번호"></td>
				</tr>
				<tr>
					<td><input type="text" name="phone"
						style="width: 400px; height: 30px; font-size: 15px;"
						placeholder="핸드폰번호"></td>
				</tr>
				<tr>
					<td><input type="text" name="nickname"
						style="width: 400px; height: 30px; font-size: 15px;"
						placeholder="리그오브레전드 닉네임"></td>
				</tr>
				<tr>
					<td><input type="button" value="회원가입" onclick="regex();"
						style="background-color: #303030; border: none; color: white; width: 407px; height: 35px; font-size: 15px; cursor: pointer;"></td>
				</tr>
			</table>
		</form>
	</section>
	<footer>
		© 2020-2020 NAGNEO.GG. NAGNEO.GG isn’t endorsed by Riot Games and
		doesn’t reflect the views or opinions of Riot Games or anyone
		officially involved in producing or managing League of Legends. <br>League
		of Legends and Riot Games are trademarks or registered trademarks of
		Riot Games, Inc. League of Legends © Riot Games, Inc.
	</footer>
</body>
</html>