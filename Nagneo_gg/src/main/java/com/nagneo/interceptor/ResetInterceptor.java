package com.nagneo.interceptor;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nagneo.vo.MatchVO;
import com.nagneo.vo.SearchUserVO;
import com.nagneo.vo.SummonerVO;
import com.nagneo.vo.TotalListVO;

public class ResetInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap<String, TotalListVO> saveList;
		if (request.getSession().getAttribute("saveList") != null) {
			saveList = (HashMap<String, TotalListVO>) request.getSession().getAttribute("saveList");
		} else {
			return true;
		}
		
		if (request.getParameter("action").equals("reset")) {
			saveList.get(request.getParameter("name")).reset();
			saveList.get(request.getParameter("name")).setLastSearch();
			return true;
		} else if (saveList.get(request.getParameter("name")) != null) {
			request.getSession().setAttribute("tlVO", saveList.get(request.getParameter("name")));
			request.getSession().setAttribute("index", saveList.get(request.getParameter("name")).getIndex());
			saveList.get(request.getParameter("name")).setLastSearch();
			response.sendRedirect(request.getContextPath() + "/list");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
}
