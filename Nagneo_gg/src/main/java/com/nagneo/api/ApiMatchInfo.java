package com.nagneo.api;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagneo.vo.MatchNumVO;
import com.nagneo.vo.MatchesVO;

public class ApiMatchInfo {

	public ArrayList<Long> getMatchesData(String id) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/";
		String apiKey = "?endIndex=10&beginIndex=0&api_key=" + ApiKey.key;
		MatchesVO matches = null;
		ArrayList<Long> kList = new ArrayList<Long>();
		try {
			ObjectMapper om = new ObjectMapper();
			HttpClient hc = HttpClientBuilder.create().build();
			HttpGet hg = new HttpGet(url + id + apiKey);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> h = new BasicResponseHandler();
				String body = h.handleResponse(hr);
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				matches = om.readValue(body, MatchesVO.class);
				for (MatchNumVO num : matches.getMatches()) {
					kList.add(num.getGameId());
				}
			}
		} catch (Exception e) {

		}
		System.out.println(kList.size());
		return kList;
	}
}
