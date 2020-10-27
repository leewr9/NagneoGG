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

import com.nagneo.api.ApiBattleInfo;
import com.nagneo.api.ApiChampionInfo;
import com.nagneo.api.ApiLeagueInfo;
import com.nagneo.api.ApiMatchInfo;
import com.nagneo.api.ApiMostInfo;
import com.nagneo.api.ApiUserInfo;
import com.nagneo.service.BoardService;
import com.nagneo.service.UserService;
import com.nagneo.vo.ChampionMasteryVO;
import com.nagneo.vo.LeagueEntryVO;
import com.nagneo.vo.MatchVO;
import com.nagneo.vo.ParticipantVO;
import com.nagneo.vo.SearchUserVO;
import com.nagneo.vo.SummonerVO;
import com.nagneo.vo.UserVO;

@Controller
public class NagneoController {
	@Autowired
	private ApiUserInfo user;

	@Autowired
	private ApiMatchInfo match;

	@Autowired
	private ApiBattleInfo battle;

	@Autowired
	private ApiLeagueInfo league;

	@Autowired
	private ApiChampionInfo champion;

	@Autowired
	private ApiMostInfo most;

	@Autowired
	private UserService u;

	@Autowired
	private BoardService b;

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
	public String apiSearch(@RequestParam("name") String name, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		System.out.println(name);
		SummonerVO sVO = user.getUserData(name);
		System.out.println(sVO.getAccountId());
		ArrayList<LeagueEntryVO> arraylVO = league.getLeagueData(sVO.getId());
		ArrayList<ChampionMasteryVO> arraycmVO = most.getMostData(sVO.getId());
		for (ChampionMasteryVO i : arraycmVO) {
			i.setChampionName(champion.getChampionData(String.valueOf(i.getChampionId())));
		}

		ArrayList<Long> arrayKey = match.getMatchesData(sVO.getAccountId());
		List<MatchVO> mList = battle.getMatchData(arrayKey);
		ArrayList<SearchUserVO> arrayTitle = new ArrayList<SearchUserVO>();

		for (MatchVO i : mList) {
			for (ParticipantVO j : i.getParticipants()) {
				j.setChampionName(champion.getChampionData(String.valueOf(j.getChampionId())));
			}
		}

		for (MatchVO a : mList) {
			for (int i = 0; i < a.getParticipantIdentities().size(); i++) {
				if (a.getParticipantIdentities().get(i).getPlayer().getSummonerName().equals(name)) {
					SearchUserVO suVO = new SearchUserVO();
					suVO.setParticipantId(a.getParticipantIdentities().get(i).getParticipantId());
					suVO.setTeamId(a.getParticipants().get(i).getTeamId());
					if (a.getTeams().get(0).getTeamId() == suVO.getTeamId()) {
						suVO.setWin(a.getTeams().get(0).getWin());
					} else {
						suVO.setWin(a.getTeams().get(1).getWin());
					}
					suVO.setChampionId(a.getParticipants().get(i).getChampionId());
					suVO.setChampLevel(a.getParticipants().get(i).getStats().getChampLevel());
					suVO.setKills(a.getParticipants().get(i).getStats().getKills());
					suVO.setDeaths(a.getParticipants().get(i).getStats().getDeaths());
					suVO.setAssists(a.getParticipants().get(i).getStats().getAssists());
					suVO.setTotalMinionsKilled(a.getParticipants().get(i).getStats().getTotalMinionsKilled()
							+ a.getParticipants().get(i).getStats().getNeutralMinionsKilled());
					suVO.setTotalDamageDealtToChampions(
							a.getParticipants().get(i).getStats().getTotalDamageDealtToChampions());
					suVO.setSpell1Id(a.getParticipants().get(i).getSpell1Id());
					suVO.setSpell2Id(a.getParticipants().get(i).getSpell2Id());
					arrayTitle.add(suVO);
					break;
				}
			}
		}
		try {
			Cookie cookie = new Cookie(URLEncoder.encode(String.valueOf(request.getCookies().length + 1), "UTF-8"),
					URLEncoder.encode(sVO.getName() + "," + String.valueOf(sVO.getProfileIconId()), "UTF-8"));
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (LeagueEntryVO i : arraylVO) {
			if (i.getQueueType().indexOf("솔로") > -1) {
				model.addAttribute("sololVO", i);
			} else {
				model.addAttribute("freelVO", i);
			}
		}

		model.addAttribute("arrayTitle", arrayTitle);
		model.addAttribute("mList", mList);
		model.addAttribute("arraycmVO", arraycmVO);
		model.addAttribute("sVO", sVO);
		return "list";
	}

}
