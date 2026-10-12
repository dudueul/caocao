using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelAttendance : CaocaoUIModalPopup
{
	public enum Mode
	{
		DailyCheck = 0,
		AbsenceCheck = 1,
		ShowByUser = 2
	}

	private enum TitleType
	{
		Normal = 0,
		NewbieOrReturn = 1
	}

	private enum TabType
	{
		Normal = 0,
		Consecutive = 1,
		Special = 2,
		None = 3
	}

	[SerializeField]
	private UILabelLocalized[] m_titles;

	[SerializeField]
	private TabConsecutiveAttendance m_consecutiveTab;

	private List<GameObject> m_attendRewardObjs;

	private List<GameObject> m_specialAttendObjs;

	private bool m_initialized;

	private GameObject m_normalAttendGridObj;

	private GameObject m_continuousObj;

	private GameObject m_specialAttendGridObj;

	private UILabelLocalized m_infoText;

	private CaocaoTabButtons m_tabButton;

	private TabType m_curTabType;

	protected override void Awake()
	{
	}

	private void Initialize()
	{
	}

	private void InitializeTabButtons()
	{
	}

	private void InitializeNormal()
	{
	}

	private void InitializeContinuous()
	{
	}

	private void InitializeInfoText()
	{
	}

	private void InitializeSpecial()
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	public static bool CheckDaily()
	{
		return false;
	}

	public static bool CheckReturnUser()
	{
		return false;
	}

	public static bool CheckAbsenceDays()
	{
		return false;
	}

	public void SetAttendancePanel(Mode mode)
	{
	}

	public static void ShowAttendancePanel(Mode mode)
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

	private void UpdateNormalUI()
	{
	}

	private void UpdateSpecialUI()
	{
	}

	private void UpdateConsecutiveUI()
	{
	}

	private void UpdateUI()
	{
	}

	public void MarkTodayAttendance()
	{
	}

	private void SetAttendInfo(GameObject obj, DailyGift dailyGift)
	{
	}

	public void GainReward()
	{
	}

	private void OnCompleteReward(JsonData response)
	{
	}

	public void ChangeAttendanceType()
	{
	}

	private void OnCompleteAttendanceType(JsonData response)
	{
	}

	private int GetAttendLastDay(EnumData.UserStatus type)
	{
		return 0;
	}

	public static void ShowAbsencePopup(Action callback)
	{
	}

	private static void ShowNeedGoldPopup()
	{
	}

	private static void ShowContinuePopup(CaocaoUIModalPopup parentPopup, int breakDays, Action callback)
	{
	}

	private static void ShowRestartPopup(CaocaoUIModalPopup parentPopup, Action callback)
	{
	}

	public void OnCloseRewardPopup()
	{
	}

	private bool CheckConditionTab(int tabIndex)
	{
		return false;
	}

	private void ShowConditionalPopup(int tabIndex)
	{
	}
}
