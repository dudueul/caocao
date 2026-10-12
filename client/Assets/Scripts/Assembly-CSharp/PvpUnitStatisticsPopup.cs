using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using UnityEngine;

public class PvpUnitStatisticsPopup : CaocaoUIModalPopup
{
	private enum ViewType
	{
		Annihilation = 0,
		Brawl = 1
	}

	public GameObject[] objBoards;

	public GameObject[] objFilterCheckIcons;

	public PvpTopUnitStatisticListItem[] topListItems;

	public UIReuseGrid[] gridRecommendUnitLists;

	public Transform[] transNoUnitExistDimTexts;

	public UIGrid pvpMapIconGrid;

	public UILabelLocalized recommendJobType;

	private ViewType viewType;

	private int currentWarId;

	private EnumData.BattleMode battleMode;

	private List<int> warIdList;

	private PvpUnitStatistics pvpUnitStatistics;

	private StringBuilder sb;

	private bool m_isFilter;

	private EnumData.PvpUnitStatisticsType unitStatisticType;

	public void Show(int warId, PvpUnitStatistics unitStatistics)
	{
	}

	public static void Show(EnumData.BattleMode battleMode, int currentWarId, List<int> warIdList, PvpUnitStatistics unitStatistics)
	{
	}

	public void OnClickFilter()
	{
	}

	private void ChangeFilter()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateBoardObj()
	{
	}

	private void UpdateWarMapIconList()
	{
	}

	[DebuggerHidden]
	private IEnumerator GetPvpUnitStatistics(int warId)
	{
		return null;
	}

	private void UpdateRecommendJobtypeDescription()
	{
	}

	private void UpdateUnitStatisticsList(EnumData.PvpUnitStatisticsType type)
	{
	}

	private void OnEnable()
	{
	}

	private EnumData.PvpUnitStatisticsType GetViewList()
	{
		return EnumData.PvpUnitStatisticsType.All;
	}

	private void InitViewType()
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
