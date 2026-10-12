using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class RaidAwakenReserveSetupPopup : CaocaoUIModalPopup
{
	private RaidLobby2 m_parent;

	[SerializeField]
	private CaocaoTabButtons m_tabRaidType;

	[SerializeField]
	private CaocaoTabButtons m_tabDifficulty;

	[SerializeField]
	private UISlider m_sliderMultiplier;

	[SerializeField]
	private UILabelLocalized m_labelMultiplierThumb;

	[SerializeField]
	private UILabelLocalized m_labelMultiplierInfo;

	[SerializeField]
	private UILabelLocalized m_labelWeek;

	[SerializeField]
	private Transform m_trfWeekList;

	[SerializeField]
	private UILabelLocalized m_labelTime;

	[SerializeField]
	private Transform m_trfTimeList;

	[SerializeField]
	private GameObject m_objRageCalibrateCheck;

	[SerializeField]
	private UIScrollView m_scrollViewReserveInfo;

	[SerializeField]
	private UIGrid m_gridReserveInfo;

	private EnumData.RaidType m_curRaidType;

	private EnumData.RaidDifficulty m_curDifficulty;

	private int m_rewardMultiplier;

	private DayOfWeek m_curDay;

	private int m_curTime;

	private bool m_rageCalibrate;

	private List<RaidAwakenReserveInfoListItem> m_reservatioinInfos;

	public static void Show(RaidLobby2 parent)
	{
	}

	private void Initialize()
	{
	}

	public void OnRaidTypeChanged(CaocaoButton button)
	{
	}

	public void OnDifficultyChanged(CaocaoButton button)
	{
	}

	public void MultiplierLeftClicked()
	{
	}

	public void MultiplierRightClicked()
	{
	}

	public void WeekListClicked()
	{
	}

	public void TimeListClicked()
	{
	}

	public void ScheduleAddClicked()
	{
	}

	private void ReserveDeleteClicked(ulong srl)
	{
	}

	private void OnChangeReserveInfo(JsonData response)
	{
	}

	public void RageCalibrateClicked()
	{
	}

	private void OnChangeCalibrate(JsonData response)
	{
	}

	public void WeekListItemSelected(int index)
	{
	}

	public void TimeListItemSelected(int index)
	{
	}

	private void UpdateWeek(DayOfWeek day)
	{
	}

	private void UpdateTime(int time)
	{
	}

	private void CheckRaidTypeButtons()
	{
	}

	private void CheckRaidDifficultyButtons()
	{
	}

	public void OnSliderMultiplierUpdated()
	{
	}

	private void UpdateRewardSliderLabel()
	{
	}

	private void UpdateRewardSliderValue()
	{
	}

	private void UpdateMultiplierInfo()
	{
	}

	private void UpdateReserveInfoUI()
	{
	}

	private void UpdateCalibrateUI()
	{
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
}
