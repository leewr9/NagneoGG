package com.nagneo.gg;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagneo.api.ApiLeagueInfo;
import com.nagneo.service.BoardService;
import com.nagneo.service.ChampionService;
import com.nagneo.service.UserService;
import com.nagneo.vo.ChampionMasteryVO;
import com.nagneo.vo.ChampionVO;
import com.nagneo.vo.LeagueEntryVO;
import com.nagneo.vo.MatchVO;
import com.nagneo.vo.SearchUserVO;
import com.nagneo.vo.SummonerVO;
import com.nagneo.vo.UserVO;

@Controller
public class NagneoController {
	@Autowired
	private ApiLeagueInfo league;

	@Autowired
	private UserService u;

	@Autowired
	private BoardService b;

	@Autowired
	private ChampionService c;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("log") != null) {
			model.addAttribute("log", request.getSession().getAttribute("log"));
		}
		if (request.getSession().getAttribute("login") == null) {
			request.getSession().setAttribute("logChk", "로그인");
		}
		return "index";
	}

	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("msg", "아이디");
		return "register";
	}

	@RequestMapping(value = "find", method = RequestMethod.GET)
	public String find(Model model) {
		return "find";
	}
	
	@RequestMapping(value = "board", method = RequestMethod.GET)
	public String board(Model model) {
		model.addAttribute("cList", c.allCohampion());
		return "board";
	}
	
	@RequestMapping(value = "champion", method = RequestMethod.GET)
	public String info(@RequestParam("key") String key, Model model) {
		model.addAttribute("cVO", c.champion(Integer.valueOf(key)));
		return "info";
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(@RequestParam("no") String no,Model model) {
		MatchVO mVO = league.getDetailMatch(Integer.valueOf(no));
		SearchUserVO suVO = league.getDetailTitle(Integer.valueOf(no));
		model.addAttribute("mVO", mVO);
		model.addAttribute("suVO", suVO);
		return "detail";
	}


	@RequestMapping(value = "more", method = RequestMethod.GET)
	public String more(@RequestParam("index") String index, HttpServletRequest request, HttpSession session) {
		SummonerVO sVO = (SummonerVO) request.getSession().getAttribute("sVO");
		ArrayList<Long> arrayKey = league.getMatchesData(sVO.getAccountId(), String.valueOf(index),
				String.valueOf(Integer.valueOf(index) - 5));
		List<MatchVO> mList = league.getMatchData(arrayKey, Integer.valueOf(index) - 5);
		ArrayList<SearchUserVO> arrayTitle = league.getTitleList(mList, sVO.getName(), Integer.valueOf(index) - 5);

		session.setAttribute("mList", mList);
		session.setAttribute("arrayTitle", arrayTitle);
		request.setAttribute("index", arrayKey.size());
		return "list";
	}

	@RequestMapping(value = "info", method = RequestMethod.GET)
	public String userInfo(@RequestParam("idpw") String idpw, UserVO uVO, Model model) {
		if (idpw.equals("id")) {
			model.addAttribute("id", u.find(idpw, uVO));
			model.addAttribute("pw", "비밀번호");
		} else {
			model.addAttribute("id", "아이디");
			model.addAttribute("pw", u.find(idpw, uVO));
		}
		return "login";
	}

	@RequestMapping(value = "chk", method = RequestMethod.GET)
	public String userIdChk(UserVO uVO, Model model) {
		String msg = "";
		boolean chk = u.idChk(uVO);
		if (chk) {
			msg = "사용 가능한 ID입니다";
			model.addAttribute("action", "readonly");
			model.addAttribute("id", uVO.getId());
		} else {
			msg = "중복된 ID입니다";
		}
		model.addAttribute("msg", msg);
		return "register";
	}

	@RequestMapping(value = "input", method = RequestMethod.POST)
	public String userInput(UserVO uVO) {
		u.insert(uVO);
		return "redirect:/login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm(HttpSession session, Model model) {
		if (session.getAttribute("login") != null) {
			session.invalidate();
			return "redirect:/";
		}
		model.addAttribute("id", "아이디");
		model.addAttribute("pw", "비밀번호");
		return "login";
	}

	@RequestMapping(value = "in", method = RequestMethod.POST)
	public String userLogin(UserVO uVO, Model model, HttpSession session) {
		if (u.selectOne(uVO)) {
			session.setAttribute("logChk", "로그아웃");
			session.setAttribute("login", uVO);
			return "index";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String apiSearch(@RequestParam("name") String name, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		SummonerVO sVO = league.getUserData(name);
		ArrayList<LeagueEntryVO> arraylVO = league.getLeagueData(sVO.getId());
		ArrayList<ChampionMasteryVO> arraycmVO = league.getMostData(sVO.getId());

		league.reset();
		ArrayList<Long> arrayKey = league.getMatchesData(sVO.getAccountId(), String.valueOf(5), String.valueOf(0));
		List<MatchVO> mList = league.getMatchData(arrayKey, 0);
		ArrayList<SearchUserVO> arrayTitle = league.getTitleList(mList, sVO.getName(), 0);
		try {
			if (!sVO.getName().equals("　존재하지않는소환사")) {
				Cookie cookie = new Cookie(URLEncoder.encode(String.valueOf(request.getCookies().length + 1), "UTF-8"),
						URLEncoder.encode(sVO.getName() + "," + String.valueOf(sVO.getProfileIconId()), "UTF-8"));
				response.addCookie(cookie);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.setAttribute("sVO", sVO);
		session.setAttribute("arraycmVO", arraycmVO);

		session.setAttribute("mList", mList);
		session.setAttribute("arrayTitle", arrayTitle);
		session.setAttribute("arrayKey", arrayKey);

		request.setAttribute("index", mList.size());
		session.setAttribute("sololVO", arraylVO.get(0));
		session.setAttribute("freelVO", arraylVO.get(1));
		return "list";
	}

}
