using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using BattlePlay;
using LitJson;
using Multiplay;
using UnityEngine;

public class PopupCompetitionOrderSelect : CaocaoUIModalPopup
{
	private static PopupCompetitionOrderSelect self;

	[SerializeField]
	private UIGrid m_ownUnitGrid;

	[SerializeField]
	private UIGrid m_opponentUnitGrid;

	[SerializeField]
	private UILabelLocalized m_messageLabel;

	[SerializeField]
	private UILabelLocalized m_remainingTimeSecLabel;

	[SerializeField]
	private UILabelLocalized m_remainingTimePointLabel;

	[SerializeField]
	private UILabelLocalized m_remainingTimeFracLabel;

	[SerializeField]
	private UIButton m_confirmButton;

	[SerializeField]
	private UIButton m_closeButton;

	[SerializeField]
	private GameObject m_direction;

	[SerializeField]
	private List<GameObject> m_directionParentObjs;

	[SerializeField]
	private List<Transform> m_directionUnits;

	[SerializeField]
	private UIGrid m_directionGrid;

	private GameObject[] m_ownUnitGos;

	private GameObject[] m_ownUnitLeaderGos;

	private UILabelLocalized[] m_ownUnitNameLabels;

	private UILabelLocalized[] m_ownUnitAgiLabels;

	private UILabelLocalized[] m_ownUnitNumberLabels;

	private UnitDataDevolved[] m_ownUnitDatas;

	private tk2dSpriteAnimator[] m_ownSpriteAnimators;

	private ClickSelectOwnTroop[] m_ownClickSelectOwnTroops;

	private GameObject[] m_opponentUnitGos;

	private GameObject[] m_opponentUnitLeaderGos;

	private UILabelLocalized[] m_opponentUnitNameLabels;

	private UILabelLocalized[] m_opponentUnitAgiLabels;

	private UILabelLocalized[] m_opponentUnitNumberLabels;

	private UnitDataDevolved[] m_opponentUnitDatas;

	private tk2dSpriteAnimator[] m_opponentSpriteAnimators;

	private int[] m_orders;

	private Coroutine m_orderSelectTimerCoroutine;

	private Coroutine[] m_handsupAnimationCoroutine;

	private bool m_setOrder;

	private PanelPopup2Buttons m_closePopup;

	public static float remainingTime;

	private bool retryRequestStep;

	public float prevRemainingTime { get; set; }

	public static bool IsShowing
	{
		get
		{
			return false;
		}
	}

	public static void Show(float remainingTime, IDepthManagerblePopup parentPopup = null)
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUnitNumber(bool isOwn)
	{
	}

	private bool CheckCompetitionUnitOrder()
	{
		return false;
	}

	private void SetUnit(bool isOwn)
	{
	}

	private bool IgnoreUnit(BattleUser battleUser, int unitId, int pickIndex)
	{
		return false;
	}

	public bool OrderOwnUnitData(UnitDataDevolved unitData, object arg)
	{
		return false;
	}

	public void OrderOwnUnit(int unitId)
	{
	}

	[DebuggerHidden]
	private IEnumerator PlayHandsupAnimation(int idx)
	{
		return null;
	}

	public void StopCompetition()
	{
	}

	private void AutoSelectOrder()
	{
	}

	public void ConfirmOrder()
	{
	}

	private void OnFormationSetCallback()
	{
	}

	private void OnUserRegisteredCallback()
	{
	}

	private void OnOrderSetCallback()
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowOrder()
	{
		return null;
	}

	private void OnMatchmakingCanceledCallback()
	{
	}

	private void OnWatchFinishedCallback()
	{
	}

	private void OnStepResponsedCallback(Step step, JsonData data)
	{
	}

	public int GetEmptyOrder(int[] orders)
	{
		return 0;
	}

	public int GetOrder(int[] orders, int unitId)
	{
		return 0;
	}

	public void AddToUnitOrder(int[] orders, int unitId)
	{
	}

	public void RemoveFromUnitOrder(int[] orders, int unitId)
	{
	}

	protected override void OnDestroy()
	{
	}

	private void StartOrderSelectTimer()
	{
	}

	[DebuggerHidden]
	private IEnumerator StartOrderSelectTimerCoroutine()
	{
		return null;
	}

	private void StopOrderSelectTimerCoroutine()
	{
	}

	public void OnClose()
	{
	}

	public void OnConfirm()
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
