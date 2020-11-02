package com.nagneo.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagneo.service.ChampionService;
import com.nagneo.vo.ChampionMasteryVO;
import com.nagneo.vo.LeagueEntryVO;
import com.nagneo.vo.MatchNumVO;
import com.nagneo.vo.MatchVO;
import com.nagneo.vo.MatchesVO;
import com.nagneo.vo.ParticipantVO;
import com.nagneo.vo.SearchUserVO;
import com.nagneo.vo.SummonerVO;
import com.nagneo.vo.TeamStatsVO;

public class ApiLeagueInfo {
	private ArrayList<Long> arrayKey;
	private List<MatchVO> mList;
	private ArrayList<SearchUserVO> arrayTitle;

	private ObjectMapper om = new ObjectMapper();
	private HttpClient hc = HttpClientBuilder.create().build();
	@Autowired
	private ChampionService c;

	private String key = "RGAPI-82210c7e-5285-46cd-b009-0a9836eb3408";
	
	public MatchVO getDetailMatch(int no, List<MatchVO> mList) {
		int cnt = 0;
		long max = 0;
		mList.get(no).getTeams().get(0).setReset(0);
		mList.get(no).getTeams().get(1).setReset(0);
		for (ParticipantVO p : mList.get(no).getParticipants()) {
			cnt++;
			p.getStats().setReset();
			p.getStats().setItems(p.getStats().getItem0());
			p.getStats().setItems(p.getStats().getItem1());
			p.getStats().setItems(p.getStats().getItem2());
			p.getStats().setItems(p.getStats().getItem3());
			p.getStats().setItems(p.getStats().getItem4());
			p.getStats().setItems(p.getStats().getItem5());
			p.getStats().setItems(p.getStats().getItem6());

			p.getStats().setMinions(p.getStats().getTotalMinionsKilled() + p.getStats().getNeutralMinionsKilled());
			p.getStats().setMinuteMinionsKilled(mList.get(no).getGameDuration());
			p.getStats().setKda();
			if (max < p.getStats().getTotalDamageDealtToChampions()) {
				max = p.getStats().getTotalDamageDealtToChampions();
			}
			if (cnt <= 5) {
				mList.get(no).getTeams().get(0)
						.setScore(mList.get(no).getTeams().get(0).getScore() + p.getStats().getKills());
				mList.get(no).getTeams().get(0)
						.setGold(mList.get(no).getTeams().get(0).getGold() + p.getStats().getGoldEarned());
			} else {
				mList.get(no).getTeams().get(1)
						.setScore(mList.get(no).getTeams().get(1).getScore() + p.getStats().getKills());
				mList.get(no).getTeams().get(1)
						.setGold(mList.get(no).getTeams().get(1).getGold() + p.getStats().getGoldEarned());
			}

			mList.get(no).getParticipantIdentities().get(cnt - 1).getPlayer().setlVO(
					(getLeagueData(mList.get(no).getParticipantIdentities().get(cnt - 1).getPlayer().getSummonerId())));
		}

		for (ParticipantVO p : mList.get(no).getParticipants()) {
			p.getStats().setGraphDamage(max);
		}
		return mList.get(no);
	}

	public SearchUserVO getDetailTitle(int no) {
		return arrayTitle.get(no);
	}

	public ArrayList<LeagueEntryVO> getLeagueData(String id) {
		ArrayList<LeagueEntryVO> arraylVO = new ArrayList<LeagueEntryVO>(2);
		if (id != "") {
			String url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/";
			String apiKey = "?api_key=" + key;
			Set<LeagueEntryVO> setlVO = null;
			try {
				HttpGet hg = new HttpGet(url + id + apiKey);
				HttpResponse hr = hc.execute(hg);
				if (hr.getStatusLine().getStatusCode() == 200) {
					ResponseHandler<String> h = new BasicResponseHandler();
					String body = h.handleResponse(hr);

					LeagueEntryVO temp = null;
					String tempQueue = null;

					om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					setlVO = om.readValue(body, Set.class);
					setlVO = om.convertValue(setlVO, new TypeReference<Set<LeagueEntryVO>>() {
					});
					Iterator<LeagueEntryVO> iteratorlVO = setlVO.iterator();
					while (iteratorlVO.hasNext()) {
						arraylVO.add(iteratorlVO.next());
					}

					for (LeagueEntryVO i : arraylVO) {
						i.setPercentages();
						i.QueueType();
						tempQueue = i.getQueueType();
					}
					if (arraylVO.size() == 0) {
						String type = "¼Ö·Î ·©Å©";
						for (int i = 0; i < 2; i++) {
							temp = new LeagueEntryVO();
							temp.setPercentages();
							temp.setQueueType(type);
							arraylVO.add(temp);
							type = "ÀÚÀ¯ ·©Å©";
						}
					} else if (arraylVO.size() == 1) {
						temp = new LeagueEntryVO();
						temp.setPercentages();
						if (tempQueue.indexOf("¼Ö·Î") > -1) {
							temp.setQueueType("ÀÚÀ¯ ·©Å©");
							arraylVO.add(temp);
						} else {
							temp.setQueueType("¼Ö·Î ·©Å©");
							LeagueEntryVO temps = arraylVO.get(0);
							arraylVO.clear();
							arraylVO.add(temp);
							arraylVO.add(temps);
						}
					} else if (arraylVO.size() == 2 && arraylVO.get(0).getQueueType().indexOf("ÀÚÀ¯") > -1) {
						LeagueEntryVO temps = arraylVO.get(0);
						temp = arraylVO.get(1);
						arraylVO.clear();
						arraylVO.add(temp);
						arraylVO.add(temps);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return arraylVO;
		} else {
			LeagueEntryVO temp = null;
			String type = "¼Ö·Î ·©Å©";
			for (int i = 0; i < 2; i++) {
				temp = new LeagueEntryVO();
				temp.setQueueType(type);
				temp.setPercentages();
				arraylVO.add(temp);
				type = "ÀÚÀ¯ ·©Å©";
			}
			return arraylVO;
		}
	}

	public List<MatchVO> getMatchData(ArrayList<Long> list, int start) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matches/";
		String apiKey = "?api_key=" + key;
		try {
			for (int i = start; i < list.size(); i++) {
				HttpGet hg = new HttpGet(url + list.get(i) + apiKey);
				HttpResponse hr = hc.execute(hg);
				MatchVO mVO = new MatchVO();
				if (hr.getStatusLine().getStatusCode() == 200) {
					ResponseHandler<String> h = new BasicResponseHandler();
					String body = h.handleResponse(hr);
					om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					mVO = om.readValue(body, MatchVO.class);
					if (mVO.getGameMode().equals("URF")) {
						mVO.setGameMode("¿ì¸£ÇÁ");
					} else if (mVO.getGameMode().equals("ARAM")) {
						mVO.setGameMode("Ä®¹Ù¶÷");
					} else if (mVO.getGameMode().equals("CLASSIC")) {
						mVO.setGameMode("Çù¡¡°î");
					} else {
						mVO.setGameMode("´Ü¡¡ÀÏ");
					}
					for (ParticipantVO j : mVO.getParticipants()) {
						j.setChampion(c.champion(j.getChampionId()));
						j.setSpell1(c.spell(j.getSpell1Id()));
						j.setSpell2(c.spell(j.getSpell2Id()));
						j.setRune1(c.rune(j.getStats().getPerk0()));
						j.setRune2(c.rune(j.getStats().getPerkSubStyle()));
					}
					for (int j = 0; j < 2; j++) {
						if (mVO.getTeams().get(j).getWin().equals("Win")) {
							mVO.getTeams().get(j).setWin("½Â¸®");
						} else {
							mVO.getTeams().get(j).setWin("ÆÐ¹è");
						}
					}

					mList.add(mVO);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mList;
	}

	public ArrayList<SearchUserVO> getTitleList(List<MatchVO> mList, String name, int start) {
		String win = "Win";
		for (int j = start; j < mList.size(); j++) {
			for (int i = 0; i < mList.get(j).getParticipantIdentities().size(); i++) {
				if (mList.get(j).getParticipantIdentities().get(i).getPlayer().getSummonerName().equals(name)) {
					SearchUserVO suVO = new SearchUserVO();
					suVO.setParticipantId(mList.get(j).getParticipantIdentities().get(i).getParticipantId());
					suVO.setTeamId(mList.get(j).getParticipants().get(i).getTeamId());
					if (mList.get(j).getTeams().get(0).getTeamId() == suVO.getTeamId()) {
						suVO.setWin(mList.get(j).getTeams().get(0).getWin());
					} else {
						suVO.setWin(mList.get(j).getTeams().get(1).getWin());
					}
					suVO.setChampion(c.champion(mList.get(j).getParticipants().get(i).getChampionId()));
					suVO.setChampLevel(mList.get(j).getParticipants().get(i).getStats().getChampLevel());
					suVO.setKills(mList.get(j).getParticipants().get(i).getStats().getKills());
					suVO.setDeaths(mList.get(j).getParticipants().get(i).getStats().getDeaths());
					suVO.setAssists(mList.get(j).getParticipants().get(i).getStats().getAssists());
					suVO.setKda();
					suVO.setTotalMinionsKilled(mList.get(j).getParticipants().get(i).getStats().getTotalMinionsKilled()
							+ mList.get(j).getParticipants().get(i).getStats().getNeutralMinionsKilled());
					suVO.setTotalDamageDealtToChampions(
							mList.get(j).getParticipants().get(i).getStats().getTotalDamageDealtToChampions());
					suVO.setSpell1Id(c.spell(mList.get(j).getParticipants().get(i).getSpell1Id()));
					suVO.setSpell2Id(c.spell(mList.get(j).getParticipants().get(i).getSpell2Id()));
					suVO.setGameDuration(String.valueOf(mList.get(j).getGameDuration() / 60) + "ºÐ "
							+ String.valueOf(mList.get(j).getGameDuration() % 60) + "ÃÊ");
					suVO.setMinuteMinionsKilled(mList.get(j).getGameDuration());
					arrayTitle.add(suVO);
					break;
				}
			}
		}
		return arrayTitle;
	}

	public ArrayList<Long> getMatchesData(String id, String end, String begin) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/";
		String endIndex = "?endIndex=";
		String beginIndex = "&beginIndex=";
		String apiKey = "&api_key=" + key;
		MatchesVO matches = null;
		try {
			HttpGet hg = new HttpGet(url + id + endIndex + end + beginIndex + begin + apiKey);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> h = new BasicResponseHandler();
				String body = h.handleResponse(hr);
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				matches = om.readValue(body, MatchesVO.class);
				for (MatchNumVO num : matches.getMatches()) {
					arrayKey.add(num.getGameId());
				}
			}
		} catch (Exception e) {

		}
		return arrayKey;
	}

	public ArrayList<ChampionMasteryVO> getMostData(String id) {
		ArrayList<ChampionMasteryVO> arraycmVO = new ArrayList<ChampionMasteryVO>(5);
		if (id != "") {
			String url = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/";
			String apiKey = "?api_key=" + key;
			List<ChampionMasteryVO> listcmVO = null;
			try {
				HttpGet hg = new HttpGet(url + id + apiKey);
				HttpResponse hr = hc.execute(hg);
				if (hr.getStatusLine().getStatusCode() == 200) {
					ResponseHandler<String> h = new BasicResponseHandler();
					String body = h.handleResponse(hr);

					om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					listcmVO = om.readValue(body, List.class);
					listcmVO = om.convertValue(listcmVO, new TypeReference<List<ChampionMasteryVO>>() {
					});

					if (listcmVO.size() > 5) {
						for (int i = 0; i < 5; i++) {
							arraycmVO.add(listcmVO.get(i));
						}
					} else {
						for (int i = 0; i < listcmVO.size(); i++) {
							arraycmVO.add(listcmVO.get(i));
						}
						ChampionMasteryVO temp = null;
						for (int i = 1; i < 6 - listcmVO.size(); i++) {
							temp = new ChampionMasteryVO();
							temp.setChampionId(i);
							temp.setChampionLevel(1);
							temp.setChampionPoints(0);
							arraycmVO.add(temp);
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			ChampionMasteryVO temp = null;
			for (int i = 1; i < 6; i++) {
				temp = new ChampionMasteryVO();
				temp.setChampionId(i);
				temp.setChampionLevel(1);
				temp.setChampionPoints(0);
				arraycmVO.add(temp);
			}
		}
		for (ChampionMasteryVO i : arraycmVO) {
			i.setChampion(c.champion((int) i.getChampionId()));
		}
		return arraycmVO;
	}

	public SummonerVO getUserData(String name) {
		name = name.replaceAll(" ", "%20");
		String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
		String apiKey = "?api_key=" + key;
		SummonerVO sVO = null;
		try {
			HttpGet hg = new HttpGet(url + name + apiKey);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> h = new BasicResponseHandler();
				String body = h.handleResponse(hr);
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				sVO = om.readValue(body, SummonerVO.class);
			} else {
				if (sVO == null) {
					SummonerVO temp = new SummonerVO();
					temp.setId("");
					temp.setName("¡¡Á¸ÀçÇÏÁö¾Ê´Â¼ÒÈ¯»ç");
					temp.setProfileIconId(1);
					temp.setSummonerLevel(1);
					sVO = temp;
				}
			}
		} catch (Exception e) {
		}
		return sVO;
	}

	public void reset() {
		this.arrayKey = new ArrayList<Long>();
		this.mList = new ArrayList<MatchVO>();
		this.arrayTitle = new ArrayList<SearchUserVO>();
	}
	
	public void more(ArrayList<Long> arrayKey, List<MatchVO> mList, ArrayList<SearchUserVO> arrayTitle) {
		this.arrayKey = arrayKey;
		this.mList = mList;
		this.arrayTitle = arrayTitle;
	}
}
