using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;
using UnityEngine;

public class InstanceInformation : MonoBehaviour
{
	public delegate void Callback();

	protected enum ErrorType
	{
		None = 0,
		InstanceNotActivated = 1,
		InstanceAlreadyFinish = 2,
		AutoClearNotActivated = 3,
		AutoClearNotAvailable = 4,
		AutoClearNotEnoughStamp = 5,
		AutoClearNotEnoughTicket = 6,
		StateIsNotConquered = 7,
		LordLevelIsNotEnough = 8,
		DayNotMatched = 9,
		InstanceExpedition = 10
	}

	public class StateAutoClearContext
	{
		public List<UserInstance> clearablaInstances;

		public List<RewardInfo> totalRewards;

		public List<ObscuredInt> clearedInstanceIds;

		public int befUserRank;

		public int befTicketCount;

		public int befDailyCount;

		public int currentIndex;
	}

	private static InstanceInformation self;

	public Action<int> onAutoCleared;

	public Action<List<ObscuredInt>> onStateAutoCleared;

	public Action<int> onEnterInstanceBattle;

	private Instance m_inst;

	private SpecialInstance m_specialInst;

	private Transform m_autoClearContainer;

	private Transform m_autoClearStateContainer;

	private Transform m_infoBg;

	private UILabelLocalized m_title;

	private UILabelLocalized m_level;

	private UILabelLocalized m_try;

	private Transform[] m_stamps;

	private Transform m_autoCleardisable;

	private Transform m_autoClearFalse;

	private Transform m_autoClearTrue;

	private Transform m_autoClearTicket;

	private Transform m_timeBg;

	private UILabelLocalized m_timeValue;

	private Transform m_expire;

	private UILabelLocalized m_expireValue;

	private UIScrollView m_instScrollView;

	private UITable m_instTable;

	private UILabelLocalized m_instDesc;

	private Transform m_item;

	private UIScrollView m_itemScrollView;

	private Transform m_itemTable;

	private Transform m_conditionBg;

	private Transform m_dayContainer;

	private UILabelLocalized[] m_instDay;

	private Transform m_rankBg;

	private UILabelLocalized m_rank;

	private Transform m_unitList;

	private UIButton m_autoClearBtn;

	private UILabelLocalized m_autoClearLabel;

	private UIButton m_autoClearStateBtn;

	private UILabelLocalized m_autoClearStateLabel;

	private UIButton m_enterBtn;

	private UIButton m_goBattleBtn;

	private UILabelLocalized m_goBattleLabel;

	private UIButton m_consecutiveBtn;

	private UILabelLocalized m_consecutiveLabel;

	private Transform m_titleBonus;

	private Callback m_buttonCallback;

	private bool m_initialized;

	private void Awake()
	{
	}

	public void Initialize()
	{
	}

	private void OnDestroy()
	{
	}

	private void ShowErrorMessage(ErrorType errorType, int value = -1)
	{
	}

	private void EnterButtonClicked()
	{
	}

	private void DoCallback()
	{
	}

	private bool CheckInstanceErrorOccured()
	{
		return false;
	}

	private void GoBattleButtonClicked()
	{
	}

	private void AutoClearButtonClicked()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowPostEffect(int befUserRank, Instance instPD, JsonData response)
	{
		return null;
	}

	private void StateAutoClearButtonClicked()
	{
	}

	public void SendRequestForStateAutoClearInstance(PanelPopup2Buttons currentPopup, StateAutoClearContext ctx)
	{
	}

	public void ShowStateAutoClearInstanceResult(PanelPopup2Buttons currentPopup, StateAutoClearContext ctx, bool isBonus)
	{
	}

	public void UpdateSpecialUI(SpecialInstance specialInst, string desc, Callback buttonCallback)
	{
	}

	public void UpdateUI(Instance inst, Callback buttonCallback = null)
	{
	}

	private void SetButtonEnable(UIButton button, UILabelLocalized btnLabel, bool enable)
	{
	}

	public static void ContinueConsecutive()
	{
	}

	public void GoConsecutiveButtonClicked()
	{
	}

	private void Continue()
	{
	}

	private void ShowBattleDeck(int instId)
	{
	}

	private void CheckEnergy()
	{
	}

	private void StartWaitingForRecoveringEnergy()
	{
	}

	private void ClearAutoProcessTimer()
	{
	}

	public static void HoldRecoveringEnergyTimer(bool hold)
	{
	}

	public static void CallRecoveringEnergyTimer()
	{
	}
}
