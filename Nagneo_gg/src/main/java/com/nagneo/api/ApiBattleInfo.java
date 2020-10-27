package com.nagneo.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagneo.vo.MatchVO;

public class ApiBattleInfo {
	
	public List<MatchVO> getMatchData(ArrayList<Long> list) {
		String url = "https://kr.api.riotgames.com/lol/match/v4/matches/";
		String apiKey = "?api_key="+ApiKey.key;
		List<MatchVO> mList = new ArrayList<MatchVO>();
		try {
			ObjectMapper om = new ObjectMapper();
			HttpClient hc = HttpClientBuilder.create().build();
			for (long i : list) {
				MatchVO mVO = new MatchVO(); 
				HttpGet hg = new HttpGet(url + i + apiKey);
				HttpResponse hr = hc.execute(hg);
				if (hr.getStatusLine().getStatusCode() == 200) {
					ResponseHandler<String> h = new BasicResponseHandler();
					String body = h.handleResponse(hr);
					om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
					mVO = om.readValue(body, MatchVO.class);
					mList.add(mVO);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mList;
	}

}
