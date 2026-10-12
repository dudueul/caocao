using System;
using System.Collections;
using System.Diagnostics;
using LitJson;

public class GFConfirmContinueMultiplay : GameFlowNodeBase
{
	private bool isOnNotiPopup;

	private int m_ealryRetreatPenalty;

	protected override void DefineFollowers()
	{
	}

	protected override void _DoLogic()
	{
	}

	[DebuggerHidden]
	private IEnumerator ContinueGame()
	{
		return null;
	}

	public void OnUserRegistered()
	{
	}

	private void ShowEarlyRetreatPenaltyPopup(EnumData.MultiplayEarlyRetreatReason reason)
	{
	}

	protected override int? _GetState()
	{
		return null;
	}

	private PanelPopupBase.PopupState OnYes()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	[DebuggerHidden]
	private IEnumerator ContinueMultiplay()
	{
		return null;
	}

	private void OnClosePopup()
	{
	}

	private PanelPopupBase.PopupState CheckEarlyRetreatPenalty()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	[DebuggerHidden]
	private IEnumerator GetEarlyRetreatPenalty()
	{
		return null;
	}

	private PanelPopupBase.PopupState RetreatMultiplay()
	{
		return PanelPopupBase.PopupState.KeepOpen;
	}

	private void OnOpponentRetreatCallback(ulong retreatUid)
	{
	}

	private void SetWinBattle()
	{
	}

	private void SetLoseBattle()
	{
	}

	private void OnProcessBattleEnd(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator WaitAndProcessEndFinish()
	{
		return null;
	}

	private void OnProcessBattleEndFinished()
	{
	}

	[DebuggerHidden]
	private IEnumerator SetEndBattle(bool isWin, Action failedCallback)
	{
		return null;
	}
}
