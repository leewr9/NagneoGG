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

section {
	display: flex;
	align-items: center;
	justify-content: center;
	width: 100%;
}

section img {
	width: 500px;
	height: 288px;
}

#refresh {
	border: none;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	background-color: #303030;
	border-radius: 5px 5px 5px 5px;
	float: right;
	margin-right: 7px;
	margin-top: 6px;
	width: 80px;
	height: 35px;
	font-size: 15px;
	color: white;
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
		String profileicon = "http://ddragon.leagueoflegends.com/cdn/10.21.1/img/profileicon/";
		String emblems = "resources/image/ranked-emblems/Emblem_";
		String positions = "resources/image/ranked-positions/Position_";
	%>
	<header>
		<a href="./"><img src="resources/image/logo.png"></a> <input
			type="button" id="login" value="로그인"
			onclick="location.href='./login'">
	</header>
	<nav>
		<a href="#" onclick="alert('미구현');">소환사검색</a><a href="#"
			onclick="alert('미구현');">챔피언검색</a><a href="2">커뮤니티</a>
	</nav>
	<section>
		<table>
			<tr>
				<td rowspan="3" width="200">
					<table border=1 cellspacing="0" cellpadding="0" bordercolor="black"
						style="border-collapse: collapse">
						<tr rowspan="4">
							<td width="200"><img
								src="<%=profileicon %>${sVO.profileIconId }.png"
								style="width: 200px; height: 200px; margin-bottom: -5px"></td>
						</tr>
						<tr>
							<td height="50" bgcolor="#303030" align="center"
								style="font-size: 20px; color: white;">${sVO.name }[Lv.${sVO.summonerLevel }]</td>
						</tr>
					</table>
				</td>
				<td height="50"></td>
				<td rowspan="3" width="200">
					<table border=1 cellspacing="0" cellpadding="0" bordercolor="gray"
						style="border-collapse: collapse">
						<c:forEach var="i" items="${arraycmVO }">
							<tr>
								<td width="50"><img
								src="<%=champion %>${i.championName }.png"
								style="width: 50px; height: 50px; margin-bottom: -5px"></td>
								<td width="150" align="center" bgcolor="#303030" style="color: white;">레벨${i.championLevel }[숙련도 ${i.championPoints }]</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td><table border=1 cellspacing="0" cellpadding="0"
						bordercolor="gray" style="border-collapse: collapse">
						<tr>
							<td width="100" height="100" align="center"><img
								src="<%=emblems%>${sololVO.tier }.png"
								style="width: 80px; height: 91px"></td>
							<td width="300">&nbsp;${sololVO.queueType }<br>&nbsp;${sololVO.tier }
								${sololVO.rank } ${sololVO.leaguePoints }포인트<br>&nbsp;${sololVO.wins }승
								${sololVO.losses }패 [승률 ${sololVO.percentages }%]<input
								type="button" id="refresh" value="전적갱신"
								onclick="location.href='search?name=${sVO.name }'"><br></td>
						</tr>
						<tr>
							<td height="100" align="center"><img
								src="<%=emblems%>${freelVO.tier }.png"
								style="width: 80px; height: 91px"></td>
							<td>&nbsp;${freelVO.queueType }<br>&nbsp;${freelVO.tier }
								${freelVO.rank } ${freelVO.leaguePoints }포인트<br>&nbsp;${freelVO.wins }승
								${freelVO.losses }패 [승률 ${freelVO.percentages }%]
							</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="50" colspan="2"></td>
			</tr>
			<tr>
				<td colspan="3"><table border=1 cellspacing="0" cellpadding="0"
						bordercolor="gray" style="border-collapse: collapse">
						<tr>
							<td width="813" height="100">전적</td>
						</tr>
						<tr>
							<td height="100">전적</td>
						</tr>
						<tr>
							<td height="100">전적</td>
						</tr>
						<tr>
							<td height="100">전적</td>
						</tr>
						<tr>
							<td height="100">전적</td>
						</tr>
					</table></td>
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