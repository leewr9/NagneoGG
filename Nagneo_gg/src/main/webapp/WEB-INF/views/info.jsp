<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>NAGNEO.GG</title>
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
}

section img {
	width: 400px;
	height: 200px;
}

span {
	cursor: pointer;
}

.skill {
	display: none;
	position: absolute;
	width: 282px;
	left: 27%;
	padding: 8px;
	background: #303030;
	color: #ffffff;
	font-size: 15px;
	padding: 8px;
}

span:hover+p.skill {
	display: block;
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
	<%
		String champion = "https://ddragon.leagueoflegends.com/cdn/10.21.1/img/champion/";
		String skill = "http://ddragon.leagueoflegends.com/cdn/10.22.1/img/spell/";
		String passive = "http://ddragon.leagueoflegends.com/cdn/10.22.1/img/passive/";
	%>
	<header>
		<a href="./"><img src="resources/image/logo.png"></a> <input
			type="button" id="login" value="${logChk }"
			onclick="location.href='./login'">
	</header>
	<nav>
		<a href="./">소환사검색</a><a href="board">챔피언검색</a>
		<form action="search" method="get" id="navSearch">
			<input type="text" name="name" style="width: 210px; height: 22px;"
				placeholder="리그오브레전드 닉네임"> <input type="submit" value="검색"
				style="width: 70px; height: 28px; background-color: #303030; color: white; cursor: pointer; border: none;">
		</form>
	</nav>
	<section>
		<table>
			<tr>
				<td height="50"></td>
			</tr>
			<tr>
				<td>
					<table border="1" cellspacing="0" cellpadding="0"
						bordercolor="gray"
						style="border-collapse: collapse; width: 800px;">
						<tr>
							<td rowspan="2" width="100"><img
								src="<%=champion %>${cVO.engid }.png" border="1"
								style="width: 100px; height: 100px; margin-bottom: -5px; border-color: black;"
								title="${cVO.korid }"></td>
							<td height="30" bgcolor="#303030"
								style="font-size: 25px; padding-left: 5px; padding-bottom: 5px; letter-spacing: -5px; color: white;">${cVO.korid }<font
								style="font-style: italic; font-size: 15px; letter-spacing: -1.5px;">
									${cVO.engid }</font>
							</td>
						</tr>
						<tr>
							<td><span><img
									src="<%=passive%>${cVO.engid }_Passive.png" border="1"
									style="width: 50px; height: 50px; margin-top: 5px; margin-left: 5px; border-color: black;"
									title="${cVO.korid } 패시브"></span>
								<p class="skill">${cVO.korid }패시브</p> <span><img
									src="<%=skill %>${cVO.engid }Q.png" border="1"
									style="width: 50px; height: 50px; margin-top: 5px; margin-left: 5px; border-color: black;"
									title="${cVO.korid } Q스킬"> </span>
								<p class="skill">${cVO.korid }Q스킬</p> <span><img
									src="<%=skill %>${cVO.engid }W.png" border="1"
									style="width: 50px; height: 50px; margin-top: 5px; margin-left: 5px; border-color: black;"
									title="${cVO.korid } W스킬"> </span>
								<p class="skill">${cVO.korid }W스킬</p> <span><img
									src="<%=skill %>${cVO.engid }E.png" border="1"
									style="width: 50px; height: 50px; margin-top: 5px; margin-left: 5px; border-color: black;"
									title="${cVO.korid } E스킬"> </span>
								<p class="skill">${cVO.korid }E스킬</p> <span><img
									src="<%=skill %>${cVO.engid }R.png" border="1"
									style="width: 50px; height: 50px; margin-top: 5px; margin-left: 5px; border-color: black;"
									title="${cVO.korid } R스킬"> </span>
								<p class="skill">${cVO.korid }R스킬</p></td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="30"></td>
			</tr>
			<tr>
				<td>
					<table border="1" cellspacing="0" cellpadding="0"
						bordercolor="gray"
						style="border-collapse: collapse; width: 800px;">
						<tr><td></td></tr>
						
						
						
					</table>
				</td>
			</tr>
		</table>
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