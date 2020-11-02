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

table a {
	text-decoration: none;
	color: black;
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
	border-radius: 3px 3px 3px 3px;
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

#back {
	border: none;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	background-color: #303030;
	border-radius: 2px 2px 2px 2px;
	width: 100px;
	height: 35px;
	font-size: 15px;
	color: white;
}

hr {
	background-color: black;
	color: black;
	height: 1px;
	border: none;
}
</style>
<%
	String champion = "https://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/";
	String spell = "http://ddragon.leagueoflegends.com/cdn/10.22.1/img/spell/";
	String profileicon = "http://ddragon.leagueoflegends.com/cdn/10.22.1/img/profileicon/";
	String item = "http://ddragon.leagueoflegends.com/cdn/10.22.1/img/item/";
	String emblems = "resources/image/ranked-emblems/Emblem_";
	String trim = "resources/image/ranked-trim/Trim_";
	String mastery = "resources/image/most-mastery/Mastery_level";
	String rune = "https://ddragon.leagueoflegends.com/cdn/img/perk-images/Styles/";
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
			placeholder="리그오브레전드 닉네임"> <input type="hidden" name="action"
			value="search"><input type="submit" value="검색"
			style="width: 70px; height: 28px; background-color: #303030; color: white; cursor: pointer; border: none;">
	</form>
</nav>
<section>
	<table>
		<tr>
			<td rowspan="3" width="200">
				<table border=1 cellspacing="0" cellpadding="0" bordercolor="black"
					style="border-collapse: collapse">
					<tr rowspan="4">
						<td width="200"><div style="position: relative;">
								<img src="<%=profileicon %>${tlVO.sVO.profileIconId }.png"
									style="width: 200px; height: 200px; margin-bottom: -5px">
								<img src="<%=trim %>${tlVO.sololVO.tier }.png"
									style="position: absolute; left: -1px; top: 120px; width: 202px; height: 101px;">
							</div></td>
					</tr>
					<tr>
						<td height="50" bgcolor="#303030" align="center"
							style="font-size: 18px; color: white;"><c:if
								test="${tlVO.sVO.name eq '　존재하지않는소환사'}">
							존재하지 않는 소환사
							</c:if> <c:if test="${tlVO.sVO.name ne '　존재하지않는소환사'}">
								<a href="search?name=${tlVO.sVO.name }&action=search"
									style="font-size: 18px; color: white;">${tlVO.sVO.name } [
									Lv. ${tlVO.sVO.summonerLevel } ]</a>
							</c:if></td>
					</tr>
				</table>
			</td>
			<td height="50"></td>
			<td rowspan="3" width="200">
				<table border=1 cellspacing="0" cellpadding="0" bordercolor="gray"
					style="border-collapse: collapse">
					<c:forEach var="i" items="${tlVO.arraycmVO }">
						<tr>
							<td width="50"><img
								src="<%=champion %>${i.champion.engid }.png"
								style="width: 50px; height: 50px; margin-bottom: -5px; cursor: pointer;"
								onclick="location.href='./champion?key=${i.champion.key }'"
								title="${i.champion.korid }"></td>
							<td align="center" bgcolor="#303030"><img
								src="<%=mastery %>${i.championLevel }.png"
								style="width: 45px; height: 40px; margin-bottom: -5px"
								title="레벨 ${i.championLevel }"></td>
							<td width="200" bgcolor="#303030"
								style="color: white; padding-left: 5px;">숙련도
								${i.championPoints }</td>
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
							src="<%=emblems%>${tlVO.sololVO.tier }.png"
							style="width: 80px; height: 91px"></td>
						<td width="300">&nbsp;${tlVO.sololVO.queueType }<br>&nbsp;${tlVO.sololVO.tier }
							${tlVO.sololVO.rank } ${tlVO.sololVO.leaguePoints }포인트<br>&nbsp;${tlVO.sololVO.wins }승
							${tlVO.sololVO.losses }패 [ 승률 ${tlVO.sololVO.percentages }% ]<font
							style="float: right; margin-right: 7px; margin-top: 5px; font-size: 12px;">${tlVO.lastSearch }</font><br>
							<input type="button" id="refresh" value="전적갱신"
							onclick="location.href='search?name=${tlVO.sVO.name }&action=reset'"
							style="margin-bottom: 7px;"><br></td>
					</tr>
					<tr>
						<td height="100" align="center"><img
							src="<%=emblems%>${tlVO.freelVO.tier }.png"
							style="width: 80px; height: 91px"></td>
						<td>&nbsp;${tlVO.freelVO.queueType }<br>&nbsp;${tlVO.freelVO.tier }
							${tlVO.freelVO.rank } ${tlVO.freelVO.leaguePoints }포인트<br>&nbsp;${tlVO.freelVO.wins }승
							${tlVO.freelVO.losses }패 [ 승률 ${tlVO.freelVO.percentages }% ]
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
						<td height="50" colspan="2">
							<table style="width: 100%">
								<tr>
									<td style="width: 50%; font-size: 20px;"><font
										style="color: #007fcf; font-weight: bold;">블루</font><font
										style="font-size: 15px;">( ${mVO.teams[0].win} )</font><font
										style="font-size: 15px; margin-left: 10%">골드
											${mVO.teams[0].gold}</font> <font
										style="font-size: 15px; margin-left: 10%"> 바론
											${mVO.teams[0].baronKills} &nbsp;드래곤
											${mVO.teams[0].dragonKills} &nbsp;타워
											${mVO.teams[0].towerKills} </font><font
										style="color: #007fcf; font-weight: bold; float: right; margin-right: 5px;">${mVO.teams[0].score}</font></td>


									<td style="width: 50%; font-size: 20px;"><font
										style="color: #cf0000; font-weight: bold; float: left; margin-left: 5px;">${mVO.teams[1].score}</font><font
										style="font-size: 15px; margin-left: 10%"> 바론
											${mVO.teams[1].baronKills} &nbsp;드래곤
											${mVO.teams[1].dragonKills} &nbsp;타워
											${mVO.teams[1].towerKills} </font> <font
										style="font-size: 15px; margin-left: 9%">골드
											${mVO.teams[1].gold}</font><font
										style="font-size: 15px; margin-left: 9%">(
											${mVO.teams[1].win} )</font><font
										style="color: #cf0000; font-weight: bold; float: right; margin-right: 5px;">레드</font></td>
								</tr>
							</table>
						</td>
					</tr>
					<c:forEach var="i" begin="0" end="4">
						<tr>
							<td width="407" height="100" bgcolor="#a3cfec">
								<table>
									<tr>
										<td colspan="3" style="font-size: 18px;" align="center"><a
											href="search?name=${mVO.participantIdentities[i].player.summonerName}&action=search"
											style="font-weight: bold; color: #303030; letter-spacing: -1px;">${mVO.participantIdentities[i].player.summonerName}
												[ ${mVO.participantIdentities[i].player.lVO[0].tier}
												${mVO.participantIdentities[i].player.lVO[0].rank} ]</a>
											<hr style="margin-top: 5px;"></td>
									</tr>
									<tr>
										<td rowspan="2"><img
											src="<%=champion %>${mVO.participants[i].champion.engid }.png"
											style="width: 80px; height: 80px; margin-left: 2px; margin-top: -1px; margin-bottom: -2px; border-radius: 8px; border-color: black; cursor: pointer;"
											onclick="location.href='./champion?key=${mVO.participants[i].champion.key }'"
											border="1" title="${mVO.participants[i].champion.korid }">
										</td>
										<td colspan="2"><c:forEach var="item"
												items="${mVO.participants[i].stats.items }">
												<c:choose>
													<c:when test="${item != 0 }">
														<img src="<%=item %>${item }.png"
															style="width: 40px; height: 40px; margin-top: -5px; margin-bottom: -15px; border-radius: 4px; border-color: black;"
															border="1">
													</c:when>
													<c:otherwise>
														<img src="resources/image/${item }.png"
															style="width: 40px; height: 40px; margin-top: -5px; margin-bottom: -15px; border-radius: 4px; border-color: black;"
															border="1">
													</c:otherwise>
												</c:choose>


											</c:forEach>
											<hr style="margin-top: 15px; margin-bottom: -15px;"></td>
									</tr>
									<tr>
										<td style="font-size: 16px;" align="left"><img
											src="<%=spell %>${mVO.participants[i].spell1.engid }.png"
											style="width: 20px; height: 20px; margin-top: 6px; margin-bottom: -6px; margin-left: 2px; border-radius: 2px; border-color: black;"
											border="1"><img
											src="<%=spell %>${mVO.participants[i].spell2.engid }.png"
											style="width: 20px; height: 20px; margin-top: 6px; margin-bottom: -6px; margin-left: 2px; border-radius: 2px; border-color: black;"
											border="1"><font style="color: gray;"> │ </font><img
											src="<%=rune %>${mVO.participants[i].rune1.engid }.png"
											style="width: 25px; height: 25px; margin-bottom: -7px; background-color: #303030; margin-left: 2px; border-radius: 25px; border-color: black;">
											<div
												style="margin-top: -22px; margin-left: 95px; width: 25px; height: 25px; border-radius: 25px; background-color: #303030;">
												<img src="<%=rune %>${mVO.participants[i].rune2.engid }.png"
													style="width: 15px; height: 15px; margin-top: 5px; margin-left: 5px;">
											</div></td>
										<td><a href="#"
											style="float: right; margin-top: 2px; letter-spacing: -0.5px; cursor: default;">${mVO.participants[i].champion.korid }
												Lv.${mVO.participants[i].stats.champLevel}</a></td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td width="140"><div
												style="float: left; border: 1px solid; width: 100px; height: 15px; margin-top: 2px; margin-bottom: -2px;">
												<div
													style="float: left; background-color: #cf0000; width: ${mVO.participants[i].stats.graphDamage}px; height: 15px;"></div>
											</div>
											<div style="margin-top: -1px; margin-bottom: 1px;">
												${mVO.participants[i].stats.totalDamageDealtToChampions}</div></td>
										<td width="160" style="letter-spacing: -0.9px;" align="center">${mVO.participants[i].stats.kills}
											/ ${mVO.participants[i].stats.deaths} /
											${mVO.participants[i].stats.assists}
											[${mVO.participants[i].stats.kda}]</td>
										<td width="120" style="letter-spacing: -0.9px;" align="right">CS
											${mVO.participants[i].stats.minions}[${mVO.participants[i].stats.minuteMinionsKilled}]</td>
									</tr>
								</table>
							</td>
							<td width="408" bgcolor="#e2b6b3">
								<table>
									<tr>
										<td colspan="3" style="font-size: 18px;" align="center"><a
											href="search?name=${mVO.participantIdentities[i + 5].player.summonerName}&action=search"
											style="font-weight: bold; color: #303030; letter-spacing: -1px;">${mVO.participantIdentities[i + 5].player.summonerName}
												[ ${mVO.participantIdentities[i + 5].player.lVO[0].tier}
												${mVO.participantIdentities[i + 5].player.lVO[0].rank} ]</a>
											<hr style="margin-top: 5px;"></td>
									</tr>
									<tr>
										<td rowspan="2"><img
											src="<%=champion %>${mVO.participants[i + 5].champion.engid }.png"
											style="width: 80px; height: 80px; margin-left: 2px; margin-top: -1px; margin-bottom: -2px; border-radius: 8px; border-color: black; cursor: pointer;"
											onclick="location.href='./champion?key=${mVO.participants[i + 5].champion.key }'"
											border="1" title="${mVO.participants[i + 5].champion.korid }">
										</td>
										<td colspan="2"><c:forEach var="item"
												items="${mVO.participants[i + 5].stats.items }">
												<c:choose>
													<c:when test="${item != 0 }">
														<img src="<%=item %>${item }.png"
															style="width: 40px; height: 40px; margin-top: -5px; margin-bottom: -15px; border-radius: 4px; border-color: black;"
															border="1">
													</c:when>
													<c:otherwise>
														<img src="resources/image/${item }.png"
															style="width: 40px; height: 40px; margin-top: -5px; margin-bottom: -15px; border-radius: 4px; border-color: black;"
															border="1">
													</c:otherwise>
												</c:choose>
											</c:forEach>
											<hr style="margin-top: 15px; margin-bottom: -15px;"></td>
									</tr>
									<tr>
										<td style="font-size: 16px;" align="left"><img
											src="<%=spell %>${mVO.participants[i + 5].spell1.engid }.png"
											style="width: 20px; height: 20px; margin-top: 6px; margin-bottom: -6px; margin-left: 2px; border-radius: 2px; border-color: black;"
											border="1"><img
											src="<%=spell %>${mVO.participants[i + 5].spell2.engid }.png"
											style="width: 20px; height: 20px; margin-top: 6px; margin-bottom: -6px; margin-left: 2px; border-radius: 2px; border-color: black;"
											border="1"><font style="color: gray;"> │ </font><img
											src="<%=rune %>${mVO.participants[i + 5].rune1.engid }.png"
											style="width: 25px; height: 25px; margin-bottom: -7px; background-color: #303030; margin-left: 2px; border-radius: 25px; border-color: black;">
											<div
												style="margin-top: -22px; margin-left: 96px; width: 25px; height: 25px; border-radius: 25px; background-color: #303030;">
												<img
													src="<%=rune %>${mVO.participants[i + 5].rune2.engid }.png"
													style="width: 15px; height: 15px; margin-top: 5px; margin-left: 5px;">
											</div></td>
										<td><a href="#"
											style="float: right; margin-top: 2px; letter-spacing: -0.5px; cursor: default;">${mVO.participants[i + 5].champion.korid }
												Lv.${mVO.participants[i + 5].stats.champLevel}</a></td>
									</tr>
								</table>
								<hr>
								<table>
									<tr>
										<td width="140"><div
												style="float: left; border: 1px solid; width: 100px; height: 15px; margin-top: 2px; margin-bottom: -2px;">
												<div
													style="float: left; background-color: #cf0000; width: ${mVO.participants[i + 5].stats.graphDamage}px; height: 15px;"></div>
											</div>
											<div style="margin-top: -1px; margin-bottom: 1px;">
												${mVO.participants[i + 5].stats.totalDamageDealtToChampions}</div></td>
										<td width="160" style="letter-spacing: -0.9px;" align="center">${mVO.participants[i + 5].stats.kills}
											/ ${mVO.participants[i + 5].stats.deaths} /
											${mVO.participants[i + 5].stats.assists}
											[${mVO.participants[i + 5].stats.kda}]</td>
										<td width="120" style="letter-spacing: -0.9px;" align="right">CS
											${mVO.participants[i + 5].stats.minions}[${mVO.participants[i + 5].stats.minuteMinionsKilled}]</td>
									</tr>
								</table>
							</td>
						</tr>
					</c:forEach>
				</table></td>
		</tr>
		<tr>
			<td height="50" colspan="3" align="center"><input type="button"
				id="back" value="뒤로가기"
				onclick="location.href='./search?name=${tlVO.sVO.name }&action=search'">
			</td>
		</tr>
		<tr>
			<td height="30" colspan="2"></td>
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