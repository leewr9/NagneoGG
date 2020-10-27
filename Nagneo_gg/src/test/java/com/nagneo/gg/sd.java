package com.nagneo.gg;

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

public class sd {

	public static void main(String[] args) {
		String url = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/";
		String apiKey = "?api_key=RGAPI-274ef476-d03e-4ace-8ca8-f72e648377e8";
		List<ChampionMasteryVO> listcmVO = null;
		try {
			ObjectMapper om = new ObjectMapper();
			HttpClient hc = HttpClientBuilder.create().build();
			HttpGet hg = new HttpGet(url + "QMw0BD8OG9RRg2S9bBtqUG_k9Sb3vsbNMo_zb7UOEr9FxHI" + apiKey);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> h = new BasicResponseHandler();
				String body = h.handleResponse(hr);

				LeagueEntryVO temp = null;
				String tempQueue = null;

				om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				listcmVO = om.readValue(body, List.class);
				listcmVO = om.convertValue(listcmVO, new TypeReference<List<ChampionMasteryVO>>() {
				});
				
				for(int i = 0; i < 5; i++) {
					System.out.println(listcmVO.get(i).getChampionId());
				}
				System.out.println(listcmVO.size());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
