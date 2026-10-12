using System;
using System.Collections.Generic;

public class RankSubMenu : IRankMenuItem
{
	private int m_defaultRankId;

	private Action<int> m_onClickMenu;

	private List<Ranking> m_rankList;

	private UILeagueSelector m_leagueSelector;

	public int DefaultRankId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public EnumData.rankGroup rankGroup { get; private set; }

	public EnumData.rankType rankType { get; private set; }

	public string rankName { get; private set; }

	public int order { get; private set; }

	public List<Ranking> ranks
	{
		get
		{
			return null;
		}
	}

	public League defaultLeague { get; private set; }

	public RankSubMenu(EnumData.rankGroup rankGroup, EnumData.rankType rankType, int order, UILeagueSelector selector)
	{
	}

	public void AddRankingPd(Ranking rankingData)
	{
	}

	public void OnClick()
	{
	}

	public void SetOnClickHandler(Action<int> onClick)
	{
	}
}
