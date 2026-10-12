using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using Multiplay;
using UnityEngine;

public class PopupCompetitionBan : CaocaoUIModalPopup
{
	[SerializeField]
	private UIGrid m_ownHoldUnitGrid;

	[SerializeField]
	private UIGrid m_ownNormalUnitGrid;

	[SerializeField]
	private UITexture m_opponentImageTexture;

	[SerializeField]
	private UILabelLocalized m_opponentNameLabel;

	[SerializeField]
	private UIGrid m_opponentHoldUnitGrid;

	[SerializeField]
	private UIGrid m_opponentNormalUnitGrid;

	[SerializeField]
	private GameObject m_opponentImageBg;

	[SerializeField]
	private UILabelLocalized m_messageLabel;

	[SerializeField]
	private UILabelLocalized m_selectionInfoLabel;

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

	private GameObject[] m_ownUnitGos;

	private UILabelLocalized[] m_ownUnitNameLabels;

	private UILabelLocalized[] m_ownUnitAgiLabels;

	private GameObject[] m_ownBanIconGos;

	private UnitDataDevolved[] m_ownUnitDatas;

	private tk2dSpriteAnimator[] m_ownSpriteAnimators;

	private GameObject[] m_opponentUnitGos;

	private UILabelLocalized[] m_opponentUnitNameLabels;

	private UILabelLocalized[] m_opponentUnitAgiLabels;

	private GameObject[] m_opponentBanIconGos;

	private UnitDataDevolved[] m_opponentUnitDatas;

	private tk2dSpriteAnimator[] m_opponentSpriteAnimators;

	private ClickSelectOwnTroop[] m_opponentClickSelectOwnTroops;

	private List<int> m_ownBanndedUnits;

	private List<int> m_opponentBanndedUnits;

	private List<int> m_ownHoldUnits;

	private List<int> m_opponentHoldUnits;

	private Coroutine m_banTimerCoroutine;

	private PanelPopup2Buttons m_closePopup;

	public static float remainingTime;

	private bool retryRequestStep;

	public static void Show(IDepthManagerblePopup parentPopup = null)
	{
	}

	private void InitUI()
	{
	}

	private void SetUnits(bool isOwn)
	{
	}

	private void SetBanned(bool isOwn, int unitId, bool banned)
	{
	}

	private void OnBanSelectedCallback()
	{
	}

	[DebuggerHidden]
	private IEnumerator GoToBattleDeck()
	{
		return null;
	}

	public void ConfirmBan()
	{
	}

	public void StopCompetition()
	{
	}

	private void SendBan()
	{
	}

	private void AutoSelectBan()
	{
	}

	public bool BanOpponentUnitData(UnitDataDevolved unitData, object arg)
	{
		return false;
	}

	public bool BanOpponentUnit(int unitId)
	{
		return false;
	}

	private void OnMatchmakingCanceledCallback()
	{
	}

	private void OnUserRegisteredCallback()
	{
	}

	private void OnWatchFinishedCallback()
	{
	}

	private void OnStepResponsedCallback(Step step, JsonData data)
	{
	}

	private void StartBanTimer()
	{
	}

	[DebuggerHidden]
	private IEnumerator StartBanTimerCoroutine()
	{
		return null;
	}

	private void StopBanTimerCoroutine()
	{
	}

	protected override void OnDestroy()
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
