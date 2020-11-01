package com.nagneo.vo;

import java.util.ArrayList;
import java.util.List;

public class TotalListVO {
	private SummonerVO sVO;
	private ArrayList<LeagueEntryVO> arraylVO;
	private ArrayList<ChampionMasteryVO> arraycmVO;
	private ArrayList<Long> arrayKey;
	private List<MatchVO> mList;
	private ArrayList<SearchUserVO> arrayTitle;
	private LeagueEntryVO sololVO;
	private LeagueEntryVO freelVO;
	private int index;

	public TotalListVO(SummonerVO sVO, ArrayList<LeagueEntryVO> arraylVO, ArrayList<ChampionMasteryVO> arraycmVO,
			ArrayList<Long> arrayKey, List<MatchVO> mList, ArrayList<SearchUserVO> arrayTitle) {
		this.sVO = sVO;
		this.arraylVO = arraylVO;
		this.arraycmVO = arraycmVO;
		this.arrayKey = arrayKey;
		this.mList = mList;
		this.arrayTitle = arrayTitle;
		this.sololVO = this.arraylVO.get(0);
		this.freelVO = this.arraylVO.get(1);
		this.index = this.mList.size();
	}
	
	public void moreList(ArrayList<Long> arrayKey, List<MatchVO> mList, ArrayList<SearchUserVO> arrayTitle) {
		this.arrayKey = arrayKey;
		this.mList = mList;
		this.arrayTitle = arrayTitle;
		this.index = this.mList.size();
	}

	public SummonerVO getsVO() {
		return sVO;
	}

	public void setsVO(SummonerVO sVO) {
		this.sVO = sVO;
	}

	public ArrayList<LeagueEntryVO> getArraylVO() {
		return arraylVO;
	}

	public void setArraylVO(ArrayList<LeagueEntryVO> arraylVO) {
		this.arraylVO = arraylVO;
	}

	public ArrayList<ChampionMasteryVO> getArraycmVO() {
		return arraycmVO;
	}

	public void setArraycmVO(ArrayList<ChampionMasteryVO> arraycmVO) {
		this.arraycmVO = arraycmVO;
	}

	public ArrayList<Long> getArrayKey() {
		return arrayKey;
	}

	public void setArrayKey(ArrayList<Long> arrayKey) {
		this.arrayKey = arrayKey;
	}

	public List<MatchVO> getmList() {
		return mList;
	}

	public void setmList(List<MatchVO> mList) {
		this.mList = mList;
	}

	public ArrayList<SearchUserVO> getArrayTitle() {
		return arrayTitle;
	}

	public void setArrayTitle(ArrayList<SearchUserVO> arrayTitle) {
		this.arrayTitle = arrayTitle;
	}

	public LeagueEntryVO getSololVO() {
		return sololVO;
	}

	public void setSololVO(LeagueEntryVO sololVO) {
		this.sololVO = arraylVO.get(0);
	}

	public LeagueEntryVO getFreelVO() {
		return freelVO;
	}

	public void setFreelVO(LeagueEntryVO freelVO) {
		this.freelVO = arraylVO.get(1);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = mList.size();
	}

}
