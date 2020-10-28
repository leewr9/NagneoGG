package com.nagneo.interceptor;

import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SearchInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if(request.getCookies() != null) {
			HttpSession session = request.getSession();
			HashMap<String, String> log = new HashMap<String, String>(5);
			String string = "";
			for (int i = request.getCookies().length - 1; i >= 0; i--) {
				String[] temp = URLDecoder.decode(request.getCookies()[i].getValue(), "UTF-8").split(",");
				if(temp.length > 1 && !temp[0].equals(string)) {
					string = temp[0];
					log.put(temp[0], temp[1]);
				}
				if(log.size() == 5) break;
			}
			session.setAttribute("log", log);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
