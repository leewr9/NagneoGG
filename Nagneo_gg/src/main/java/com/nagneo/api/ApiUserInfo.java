package com.nagneo.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagneo.vo.SummonerVO;

@Controller
public class ApiUserInfo {
	
	public SummonerVO getUserData(String name) {
		name = name.replaceAll(" ", "%20");
		String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
		String apiKey = "?api_key="+ApiKey.key;
		SummonerVO sVO = null;
		try {
			ObjectMapper om = new ObjectMapper();
			HttpClient hc = HttpClientBuilder.create().build();
			HttpGet hg = new HttpGet(url + name + apiKey);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> h = new BasicResponseHandler();
				String body = h.handleResponse(hr);
				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				sVO = om.readValue(body, SummonerVO.class);
			}
		} catch (Exception e) {
		}
		return sVO;
	}

}
