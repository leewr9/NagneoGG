package com.nagneo.api;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagneo.vo.ChampionMasteryVO;

public class ApiChampionInfo {
	public ArrayList<ChampionMasteryVO> getChampionData(ArrayList<ChampionMasteryVO> id) {
		String url = "http://ddragon.leagueoflegends.com/cdn/10.21.1/data/en_US/champion.json";
		try {
			ObjectMapper om = new ObjectMapper();
			HttpClient hc = HttpClientBuilder.create().build();
			HttpGet hg = new HttpGet(url);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> h = new BasicResponseHandler();
				String body = h.handleResponse(hr);

				for (ChampionMasteryVO i : id) {
					i.setChampionName(body.substring(
							body.indexOf("\"id\":\"", body.indexOf("\"key\":\"" + i.getChampionId() + "\"") - 20) + 6,
							body.indexOf("\"key\":\"" + i.getChampionId() + "\"") - 2));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

}
