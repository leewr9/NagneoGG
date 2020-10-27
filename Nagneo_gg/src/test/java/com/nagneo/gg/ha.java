package com.nagneo.gg;

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
import com.nagneo.vo.ChampionVO;

public class ha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://ddragon.leagueoflegends.com/cdn/10.21.1/data/en_US/champion.json";
		try {
			ObjectMapper om = new ObjectMapper();
			HttpClient hc = HttpClientBuilder.create().build();
			HttpGet hg = new HttpGet(url);
			HttpResponse hr = hc.execute(hg);
			if (hr.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> h = new BasicResponseHandler();
				String body = h.handleResponse(hr);
				System.out.println(
						body.substring(body.indexOf("\"id\":\"", body.indexOf("\"key\":\"2\"") - 20) + 6, body.indexOf("\"key\":\"2\"") - 2));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
