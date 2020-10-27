package com.nagneo.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagneo.vo.ChampionMasteryVO;
import com.nagneo.vo.LeagueEntryVO;

public class ApiMostInfo {
	public ArrayList<ChampionMasteryVO> getMostData(String id) {
		ArrayList<ChampionMasteryVO> arraycmVO = new ArrayList<ChampionMasteryVO>(5);
		if (id != "") {
			String url = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/";
			String apiKey = "?api_key=" + ApiKey.key;
			List<ChampionMasteryVO> listcmVO = null;
			try {
				ObjectMapper om = new ObjectMapper();
				HttpClient hc = HttpClientBuilder.create().build();
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
			return arraycmVO;
		} else {
			ChampionMasteryVO temp = null;
			for (int i = 1; i < 6; i++) {
				temp = new ChampionMasteryVO();
				temp.setChampionId(i);
				temp.setChampionLevel(1);
				temp.setChampionPoints(0);
				arraycmVO.add(temp);
			}

			return arraycmVO;
		}
	}

}
