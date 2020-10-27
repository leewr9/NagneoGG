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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagneo.vo.LeagueEntryVO;

public class ApiLeagueInfo {
	public ArrayList<LeagueEntryVO> getLeagueData(String id) {
		String url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/";
		String apiKey = "?api_key="+ApiKey.key;
		ArrayList<LeagueEntryVO> arraylVO = new ArrayList<LeagueEntryVO>();
		Set<LeagueEntryVO> setlVO = null;
		try {
			ObjectMapper om = new ObjectMapper();
			HttpClient hc = HttpClientBuilder.create().build();
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

				if (arraylVO.size() < 2) {
					temp = new LeagueEntryVO();
					temp.setLeaguePoints(0);
					temp.setLosses(0);
					temp.setRank("X");
					temp.setTier("IRON");
					temp.setWins(0);
					if (tempQueue.indexOf("¼Ö·Î") > -1) {
						temp.setQueueType("ÀÚÀ¯ ·©Å©");
					} else {
						temp.setQueueType("¼Ö·Î ·©Å©");
					}
					temp.setPercentages();
					arraylVO.add(temp);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arraylVO;
	}
}
