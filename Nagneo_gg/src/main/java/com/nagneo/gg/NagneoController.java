package com.nagneo.gg;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagneo.api.ApiChampionInfo;
import com.nagneo.api.ApiLeagueInfo;
import com.nagneo.api.ApiMostInfo;
import com.nagneo.api.ApiUserInfo;
import com.nagneo.service.BoardService;
import com.nagneo.service.UserService;
import com.nagneo.vo.ChampionMasteryVO;
import com.nagneo.vo.LeagueEntryVO;
import com.nagneo.vo.SummonerVO;
import com.nagneo.vo.UserVO;

@Controller
public class NagneoController {
	@Autowired
	private ApiUserInfo user;

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
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "reg", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("msg", "아이디");
		return "register";
	}

	@RequestMapping(value = "chk", method = RequestMethod.GET)
	public String userIdChk(UserVO uVO, Model model) {
		System.out.println("chk 메서드" + uVO.getId());
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
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}

	@RequestMapping(value = "in", method = RequestMethod.POST)
	public String userLogin(UserVO uVO, Model model, HttpSession session) {
		if (session.getAttribute("login") != null)
			session.invalidate();
		if (u.selectOne(uVO)) {
			session.setAttribute("login", uVO);
			return "index";
		}
		return "login";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String apiSearch(@RequestParam("name") String name, Model model) {
		SummonerVO sVO = user.getUserData(name);
		ArrayList<LeagueEntryVO> arraylVO = league.getLeagueData(sVO.getId());
		ArrayList<ChampionMasteryVO> arraycmVO = champion.getChampionData(most.getMostData(sVO.getId()));

		for (LeagueEntryVO i : arraylVO) {
			if (i.getQueueType().indexOf("솔로") > -1) {
				model.addAttribute("sololVO", i);
			} else {
				model.addAttribute("freelVO", i);
			}
		}
		System.out.println(arraycmVO.size());
		model.addAttribute("arraycmVO", arraycmVO);
		model.addAttribute("sVO", sVO);
		return "list";
	}

}
