using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class PanelHistory : CaocaoUIModalPopup
{
	private enum HistoryTab
	{
		All = 0,
		Battle = 1,
		Event = 2
	}

	private enum EventTab
	{
		Event = 0,
		Scenario = 1
	}

	private HistoryTab m_historyTab;

	public CaocaoTabButtons tabButtons;

	public GameObject m_allTab;

	public GameObject m_battleTab;

	public GameObject m_eventTab;

	public GameObject m_battleTabNew;

	public UILabelLocalized m_townResourceNotice;

	public IconLabelUI m_townFoodCnt;

	public IconLabelUI m_townSilverCnt;

	public UILabelLocalized m_unitNotice;

	public IconLabelUI m_promotionCnt;

	public IconLabelUI m_reinforceCnt;

	public IconLabelUI m_healCnt;

	public UILabelLocalized m_battleCnt;

	public IconLabelUI m_silverCnt;

	public IconLabelUI m_foodCnt;

	public IconLabelUI m_honorCnt;

	public IconLabelUI m_expoitCnt;

	public UILabelLocalized m_eventNotice;

	public UITable m_battleListTable;

	public PanelBattleDetail m_panelbattleDetail;

	private BattleListItem m_selectedBattleList;

	[SerializeField]
	private UITable m_scenarioListTable;

	[SerializeField]
	private GameObject m_scenarioListObj;

	private EnumData.SortType m_curSortType;

	private EnumData.SortOrder m_curSortOrder;

	private List<HistoryEventStorybookInfoListItem> m_scenarioListItem;

	private UserUnit m_unitToManage;

	public BattleListItem selectedBattleList
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public EnumData.SortType curSortType
	{
		get
		{
			return EnumData.SortType.START;
		}
		set
		{
		}
	}

	public EnumData.SortOrder curSortOrder
	{
		get
		{
			return EnumData.SortOrder.Ascending;
		}
		set
		{
		}
	}

	public static void Show()
	{
	}

	public static void Close()
	{
	}

	protected override void Awake()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void ShowNotice()
	{
	}

	private void ShowNxArenaNotice()
	{
	}

	private void OnNPANoticeClosed()
	{
	}

	public void GoToUnitManagement()
	{
	}

	private void GoToUnitManagementAsync()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowUnitManageAsync()
	{
		return null;
	}

	public void OpenBattleHistory()
	{
	}

	public void OpenEventHistory()
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUI()
	{
	}

	private void ClearUIDatas()
	{
	}

	private void ClearBattleHistory()
	{
	}

	private void RequestSetReadBattleHistory(BattleList battleList)
	{
	}

	private void OnSetReadBattleHistory(JsonData response)
	{
	}

	private void UpdateAllTab()
	{
	}

	private void UpdateHarvestAmount()
	{
	}

	private void ShowBattleDetail(BattleList battleList)
	{
	}

	private void UpdateBattleTab()
	{
	}

	private void UpdateEventTab()
	{
	}

	private void UpdateScenarioList()
	{
	}

	private void OnClickScenarioItem()
	{
	}

	private void UpdateTabActive(CaocaoRadioButton tabBtn, bool focus)
	{
	}

	private void SelectInstance(UserInstance userInst)
	{
	}

	public void Refresh()
	{
	}

	public void UpdateNewCount()
	{
	}

	public void UpdateBattleCount()
	{
	}

	public static bool HasNewHistory()
	{
		return false;
	}

	public void ShowTownList()
	{
	}

	private int SortEvent(UserInstance x, UserInstance y)
	{
		return 0;
	}

	private int SortCompareTo(UserInstance x, UserInstance y)
	{
		return 0;
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
}
