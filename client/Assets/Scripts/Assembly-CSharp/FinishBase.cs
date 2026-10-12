using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class FinishBase : CaocaoUIModalPopup
{
	private List<EventCmdBase.RewardItemInfo> rewardsGainedInThisBattle;

	protected virtual bool isWin
	{
		get
		{
			return false;
		}
	}

	protected virtual bool pedlarStoreOpened { get; set; }

	protected virtual Transform m_trParent { get; set; }

	public override void OnInit()
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

	protected override void OnDestroy()
	{
	}

	protected virtual void CheckReplay()
	{
	}

	public virtual void ShowFinish()
	{
	}

	protected virtual void OnRecoverUnitEnergy(JsonData response)
	{
	}

	protected virtual void ProcessReplay()
	{
	}

	protected virtual void OnFailedProcessReplay()
	{
	}

	protected virtual string PrepareBattleResult(bool win, bool setInjury, bool cancelProtect)
	{
		return null;
	}

	protected virtual void OnPayCostForReplay(JsonData response)
	{
	}

	protected virtual void RestartBattle()
	{
	}

	protected virtual void GotoThousandBattle()
	{
	}

	protected virtual void GoToAnnihilationWar()
	{
	}

	protected virtual void GoToCompetitionWar()
	{
	}

	protected virtual void GoToInstance()
	{
	}

	protected virtual void GoToRecon()
	{
	}

	protected virtual void GoToGuildSiegeWar()
	{
	}

	[DebuggerHidden]
	protected virtual IEnumerator ShowNeedLogin(string title, string message)
	{
		return null;
	}

	[DebuggerHidden]
	protected virtual IEnumerator ShowResultFail(string title, string message)
	{
		return null;
	}

	protected virtual bool DidLevelUp(int addUserExp)
	{
		return false;
	}

	protected virtual void GoToWorldMap()
	{
	}

	protected virtual void ChangeScene(string sceneName, bool autoDestroy = true)
	{
	}

	protected virtual void OnAppearBottomMenu(int addUserExp)
	{
	}

	[DebuggerHidden]
	protected virtual IEnumerator ShowPedlarInstanceEffect()
	{
		return null;
	}

	protected virtual int GetTemporaryMayorUnitID(int townId)
	{
		return 0;
	}

	protected virtual void UpdateRewardUI(int stampCnt, List<RewardInfo> items, Transform m_itemGridUI, bool ExceptNotItem = true)
	{
	}

	protected virtual void SetRewardsGainedInThisBattle(JsonData gainedItems)
	{
	}

	private void UpdateUserProfile(int addUserExp)
	{
	}
}
