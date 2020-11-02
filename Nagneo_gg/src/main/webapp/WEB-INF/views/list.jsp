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

#more {
	border: none;
	text-align: center;
	text-decoration: underline;
	cursor: pointer;
	background-color: #ffffff;
	border-radius: 1px 1px 1px 1px;
	float: center;
	margin-right: 3px;
	margin-top: 6px;
	width: 815px;
	height: 40px;
	font-size: 20px;
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
<script>
	function more() {
		window.scrollTo(document.body.scrollTop, document.body.scrollHeight
				- screen.height - 28);
	};
</script>
<c:choose>
	<c:when test="${action eq 'more' }">
		<body onload="more();">
	</c:when>
	<c:otherwise>
		<body>
	</c:otherwise>
</c:choose>
<%
	String champion = "https://ddragon.leagueoflegends.com/cdn/10.22.1/img/champion/";
	String spell = "http://ddragon.leagueoflegends.com/cdn/10.22.1/img/spell/";
	String profileicon = "http://ddragon.leagueoflegends.com/cdn/10.22.1/img/profileicon/";
	String emblems = "resources/image/ranked-emblems/Emblem_";
	String trim = "resources/image/ranked-trim/Trim_";
	String mastery = "resources/image/most-mastery/Mastery_level";
	int index = (Integer) session.getAttribute("index");
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
					<!--
						소환사 번호 -> ${suVO.participantId}<br>
						소환사 팀 -> ${suVO.teamId}<br>
						소환사 승패 -> ${suVO.win}<br>
						소환사 챔피언 -> ${suVO.championId }<br>
						소환사 챔프렙 -> ${suVO.champLevel}<br>
						킬 -> ${suVO.kills}<br>
						데스 -> ${suVO.deaths}<br>
						어시 -> ${suVO.assists}<br>
						미니언 -> ${suVO.totalMinionsKilled}<br>
						챔피언 딜량 -> ${suVO.totalDamageDealtToChampions}<br>
						1번 스펠 -> ${suVO.spell1Id}<br>
						2번 스펠 -> ${suVO.spell2Id}<br>
						 -->
					<%
						for (int i = 0; i < index; i++) {
					%>
					<tr>
						<c:forEach var="t" items="${tlVO.arrayTitle}" begin="<%=i%>"
							end="<%=i%>">
							<td width="333" align="left">
								<table>
									<tr>
										<td colspan="2" align="center" style="font-size: 25px;">${t.win}<font
											size="3">[ ${t.gameDuration} ]</font></td>
									</tr>
									<tr>
										<td rowspan="2" style="padding-left: 6px;"><img
											src="<%=champion %>${t.champion.engid}.png"
											style="width: 125px; height: 125px; border-color: black; cursor: pointer;"
											onclick="location.href='./champion?key=${t.champion.key }'"
											border="1" title="${t.champion.korid }"></td>
										<td width="195"
											style="font-size: 15px; padding-left: 3px; letter-spacing: -1px; padding-bottom: -8px;"
											align="left">${t.champion.korid}[Lv.${t.champLevel}]<br>
											${t.kills}/${t.deaths}/${t.assists} KDA ${t.kda }<br>CS
											${t.totalMinionsKilled} [ ${t.minuteMinionsKilled } ] <br>
											피해량 ${t.totalDamageDealtToChampions}
											<hr>
										</td>
									</tr>
									<tr>

										<td align="left" style="padding-left: 3px;"><img
											src="<%=spell %>${t.spell1Id.engid}.png"
											style="width: 30px; height: 30px; border-color: black;"
											border="1"> <img
											src="<%=spell %>${t.spell2Id.engid}.png"
											style="width: 30px; height: 30px; border-color: black;"
											border="1"></td>
									</tr>
								</table>
							</td>
						</c:forEach>
						<c:forEach var="m" items="${tlVO.mList}" varStatus="status"
							begin="<%=i%>" end="<%=i+1%>">
							<c:if test="${status.count <= 1}">
								<!-- 챔피언 아이디 / 소환사 이름 시작 -->
								<c:forEach var="a" items="${m.participants}" varStatus="vs">
									<c:if test="${(vs.count - 1) % 5 == 0 && vs.count != 10}">
										<td style="padding-top: 3px;">
											<table width="200">
												</c:if>
												<tr>
													<td width="30"><img
														src="<%=champion %>${a.champion.engid }.png"
														style="width: 25px; height: 25px; border-radius: 3px; border-color: black; cursor: pointer;"
														onclick="location.href='./champion?key=${a.champion.key }'"
														border="1" title="${a.champion.korid }"></td>
													<td align="left" style="padding-bottom: 10px;"><a
														href="search?name=${m.participantIdentities[vs.index].player.summonerName}&action=search"
														style="letter-spacing: -1px;">${m.participantIdentities[vs.index].player.summonerName}</a>
													</td>
												</tr>
												<c:if test="${vs.count % 5 == 0 && vs.count != 0}">
											</table>
										</td>
									</c:if>
								</c:forEach>
								<!-- 챔피언 아이디 / 소환사 이름 끝 -->
								<td width="79" bgcolor="#303030" align="center"
									style="color: white; font-size: 20px;">${m.gameMode}<br>
									<br> <br> <a href="detail?no=<%=i%>"
									style="font-size: 15px; color: white;">상세보기</a></td>
							</c:if>

						</c:forEach>
					</tr>
					<%
						}
					%>
				</table></td>
		</tr>
		<tr>
			<td height="50" colspan="3" align="center"><c:if
					test="${index < 30 }">
					<input type="button" id="more" value="전적 더보기"
						onclick="location.href='./more?index=<%=index + 5%>'">
				</c:if></td>
		</tr>
		<tr>
			<td height="30" colspan="3"></td>
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