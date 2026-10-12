using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelRanking : CaocaoUIModalPopup
{
	public Action<bool> OnPanelRankingToggled;

	public GameObject m_RankListObj;

	public GameObject m_NonRewardUIObj;

	public CaocaoTabButtons tabButtons;

	public GameObject m_LordInfoButton;

	public GameObject m_exMarkObj;

	public UILeagueSelector m_LeagueSelector;

	public RankListItem.IRankingReusaCellData myRankData;

	public RankListItem.IRankingReusaCellData prevMyRankData;

	private Dictionary<int, GameObject> m_gridDataDic;

	private List<RankTitleMenu> m_rankTitles;

	private Dictionary<int, RankSubMenu> m_rankId2Type;

	private List<RankListItem.IRankingReusaCellData> m_rankLists;

	private DateTime m_prevLimitDate;

	private List<RankListItem.IRankingReusaCellData> m_prevRankLists;

	private EnumData.rankGroup defaultRankGroup;

	private EnumData.rankTab rankTab;

	private RankListItem.CellData lordInfoData;

	private readonly int m_limitVisibleMaxRank;

	[SerializeField]
	private PopupGuildRankerList guildRankerList;

	public int selectedRankID { get; set; }

	protected override void Awake()
	{
	}

	private void Init()
	{
	}

	public static void Show(Action<bool> callback, EnumData.rankGroup defaultRankGroup = EnumData.rankGroup.User, int initRankId = 0)
	{
	}

	public void ShowRankingPanel(Action<bool> callback)
	{
	}

	private void UpdateUI()
	{
	}

	private RankSubMenu GetTopRowRankTypeData()
	{
		return null;
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	public void SelectRankId(int rankID)
	{
	}

	public void SelectRankType(RankSubMenu rankType)
	{
	}

	public void RequestRankData(int rankId)
	{
	}

	private void RemoveGrid(Transform scrollView)
	{
	}

	private void UpdateRankMenu()
	{
	}

	private void UpdateRankSubMenu(Transform parent, List<RankSubMenu> rankDataList)
	{
	}

	private void UpdateRankDetail()
	{
	}

	public List<RankingReward> GetRankRewardList()
	{
		return null;
	}

	public int GetRankRewardID(int rankingID, int curRank, float rankPct)
	{
		return 0;
	}

	private void UpdateTabButtonsByUpdateType(int rankID)
	{
	}

	private void CreateRankTypeList()
	{
	}

	private void OnGetRankingList(JsonData response)
	{
	}

	private void OnGetGuildRanker(JsonData response)
	{
	}

	private void SortGuildRankerList(List<RankListItem.IRankingReusaCellData> rankList, RankListItem.IRankingReusaCellData myData)
	{
	}

	private void AddGuildRankerListFunc(RankListItem.GuildRankerCellData cellData)
	{
	}

	public void OnTopRankerList(RankListItem.IRankingReusaCellData data)
	{
	}

	private void OnClickListItem(RankListItem.IRankingReusaCellData data)
	{
	}

	public void OnBtnLordInfo()
	{
	}

	private int GetLastWeekRank(RankListItem.IRankingReusaCellData current)
	{
		return 0;
	}

	public RankListItem.IRankingReusaCellData GetHighRank(int rank, int rankingRewardID)
	{
		return null;
	}

	public void OnTabChanged(CaocaoButton button)
	{
	}
}
