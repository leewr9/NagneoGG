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

table a {
	text-decoration: none;
	color: black;
}

section {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
	height: 85%;
}

section img {
	width: 500px;
	height: 288px;
}

#search {
	border: none;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	background-color: #ffffff;
	border-radius: 3px 3px 3px 3px;
	float: right;
	margin-right: 7px;
	margin-top: 6px;
	width: 80px;
	height: 35px;
	font-size: 15px;
}

footer {
	width: 100%;
	height: 50px;
	background-color: #131313;
	color: #cccccc;
	letter-spacing: -1px;
	font-size: 12px;
}
</style>
<body>
	<header>
		<a href="./"><img src="resources/image/logo.png"></a> <input
			type="button" id="login" value="${logChk }"
			onclick="location.href='./login'">
	</header>
	<nav>
		<a href="./">소환사검색</a><a href="board">챔피언검색</a>
	</nav>
	<section>
		<form action="search" method="get">
			<table>
				<tr>
					<td><table>
							<tr>
								<td><a href="./"><img src="resources/image/search.png"></a></td>
							</tr>
							<tr>
								<td><input type="hidden" name="action" value="search"><input
									type="text" name="name" style="width: 420px; height: 22px;"
									placeholder="리그오브레전드 닉네임"><input type="submit"
									value="검색"
									style="border: none; width: 70px; height: 28px; background-color: #303030; color: white; cursor: pointer;">
								</td>
							</tr>
							<tr>
								<td align="center">최근 검색</td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td><table>
							<tr>
								<hr>
								<c:forEach var="i" items="${log }">
									<td align="center"><img
										src="http://ddragon.leagueoflegends.com/cdn/10.22.1/img/profileicon/${i.value }.png"
										style="width: 20px; height: 20px; border-radius: 3px; border-color: black;"
										border="1"></td>
									<td style="padding-bottom: 9px; padding-right: 5px;"
										align="left"><a href="search?name=${i.key }&action=search">${i.key }</a></td>
								</c:forEach>
							</tr>
						</table></td>
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