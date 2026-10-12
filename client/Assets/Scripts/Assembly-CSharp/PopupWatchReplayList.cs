using System.Collections.Generic;
using LitJson;
using Multiplay;
using UnityEngine;

public class PopupWatchReplayList : CaocaoUIModalPopup
{
	public enum TabMenu
	{
		Watch = 0,
		Replay = 1
	}

	public enum Filter
	{
		Recommended = 0,
		Normal = 1,
		Guild = 2,
		Follower = 3,
		ByName = 4,
		Mine = 5
	}

	private static PopupWatchReplayList self;

	private static Filter ms_filterForRestoringReplayList;

	private static string ms_nationNameForRestoringReplayList;

	private static int ms_tierForRestoringReplayList;

	[SerializeField]
	private GameObject watchPanel;

	[SerializeField]
	private GameObject replayPanel;

	[SerializeField]
	private UIReuseScrollView watchScrollView;

	[SerializeField]
	private UIReuseGrid watchUIGrid;

	[SerializeField]
	private UIReuseScrollView replayScrollView;

	[SerializeField]
	private UIReuseGrid replayUIGrid;

	[SerializeField]
	private CaocaoTabButtons tabButtons;

	[SerializeField]
	private GameObject m_filterObj;

	[SerializeField]
	private UILabelLocalized m_filterLabel;

	[SerializeField]
	private UISprite[] m_filterSprites;

	[SerializeField]
	private UILabelLocalized m_recommendedFilter;

	[SerializeField]
	private UILabelLocalized m_mineFilter;

	[SerializeField]
	private UILabelLocalized m_tierLabel;

	[SerializeField]
	private UILabelLocalized m_headDesc;

	private TabMenu m_currentMenu;

	private Filter m_filter;

	private int m_tier;

	private const string filterSpriteSelected = "dropdown_list_o";

	private const string filterSpriteUnselected = "dropdown_list_n";

	private List<Tier> m_tierData;

	private List<Watch> m_watches;

	private List<Replay> m_replays;

	private Dictionary<ulong, MultiplayUserData> m_userData;

	private bool m_clickedTabButton;

	private string filterNationName;

	private string requestNationName;

	private ulong pageFirstItemSrl;

	public static bool willRestoreReplayList { get; private set; }

	private TabMenu currentMenu
	{
		get
		{
			return TabMenu.Watch;
		}
		set
		{
		}
	}

	private Filter filter
	{
		get
		{
			return Filter.Recommended;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	public static void Show()
	{
	}

	public static Filter GetFilter()
	{
		return Filter.Recommended;
	}

	public static void SetRestoreInfos()
	{
	}

	public static void Restore()
	{
	}

	private void Init()
	{
	}

	private void InitOnRestore()
	{
	}

	private string GetTabName()
	{
		return null;
	}

	private void InitMultiplayUserData(JsonData multiplayUserData)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnTabClicked(CaocaoButton button)
	{
	}

	public void OnRefreshClicked()
	{
	}

	private void SetRefreshButtonEnabled(bool enabled)
	{
	}

	private void ResetWatchGrid()
	{
	}

	private void ResetWatchData()
	{
	}

	private void InitWatches(JsonData watchDatas, JsonData matchingDatas)
	{
	}

	private void UpdateWatchUI()
	{
	}

	private void DeserializeMatchingDatas(JsonData matchings)
	{
	}

	private void AddWatchCellData(JsonData responseData)
	{
	}

	public void OnWatchTabClicked()
	{
	}

	private void OnNextWatchLoading()
	{
	}

	private void ResetReplayGrid()
	{
	}

	private void ResetReplayData()
	{
	}

	private void InitReplays(JsonData replayDatas)
	{
	}

	private void UpdateReplayUI()
	{
	}

	private void AddReplayCellData(JsonData responseData)
	{
	}

	public void OnReplayTabClicked()
	{
	}

	private void OnNextReplayLoading()
	{
	}

	private void UpdateFilterLabel()
	{
	}

	public void ShowFilter()
	{
	}

	private void UpdateFilter()
	{
	}

	private void UpdateFilterHighlight()
	{
	}

	public void OnClickFilterRecommended()
	{
	}

	public void OnClickFilterNormal()
	{
	}

	public void OnClickFilterGuild()
	{
	}

	public void OnClickFilterFollower()
	{
	}

	public void OnClickFilterByName()
	{
	}

	public void OnClickFilterMine()
	{
	}

	private string GetFilterName()
	{
		return null;
	}

	private void UpdateHeadDesc()
	{
	}

	public void OnPrevTier()
	{
	}

	public void OnNextTier()
	{
	}

	private void ShowPopupInputNationName()
	{
	}

	private void RequestWatches()
	{
	}

	private void RequestRecommendedWatches()
	{
	}

	private void RequestRecentWatches()
	{
	}

	private void RequestWatchByNationName()
	{
	}

	private void RequestWatchesByGuildId()
	{
	}

	private void RequestWatchesByFollowUsers()
	{
	}

	private void RequestReplays()
	{
	}

	private void RequestRecommendedReplays()
	{
	}

	private void RequestRecentReplays()
	{
	}

	private void RequestReplaysByNationName()
	{
	}

	private void RequestMyReplays()
	{
	}

	private void RequestReplaysByGuildId()
	{
	}

	private void RequestReplaysByFollowUsers()
	{
	}

	private void OnWatchMultiplayCallback()
	{
	}

	private void OnWatchRequestFailedCallback()
	{
	}

	private void OnTimer()
	{
	}

	protected override void OnDestroy()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
